package com.jsp.mealdb_explorer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jsp.mealdb_explorer.client.MealDBClient;
import com.jsp.mealdb_explorer.dto.AreaDto;
import com.jsp.mealdb_explorer.dto.CategoryDto;
import com.jsp.mealdb_explorer.dto.IngredientMeasureDto;
import com.jsp.mealdb_explorer.dto.MealDto;
import com.jsp.mealdb_explorer.dto.MealResponseDto;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class MealServiceImpl implements MealService{
	
    private final MealDBClient mealDbClient;
    private final ObjectMapper objectMapper;

    public MealServiceImpl(MealDBClient mealDbClient, ObjectMapper objectMapper) {     // constructor dependency injection
        this.mealDbClient = mealDbClient;
        this.objectMapper = objectMapper;
    }

	@Override
	@Cacheable("search")
	public List<MealDto> searchMealsByName(String name) {
		
		String json = mealDbClient.searchMealByName(name);

	    if (json == null || json.isBlank()) {
	        return List.of();                     // Empty List
	    }

	    MealResponseDto dto = objectMapper.readValue(json, MealResponseDto.class);

	    List<MealDto> meals = dto.getMeals();
	    if (meals == null) {
	        return List.of();
	    }
	    
	    return meals;
	}

	@Override
	@Cacheable("mealDetails")
	public MealDto getMealsById(String id) {
		
	    try {
	        String json = mealDbClient.getMealById(id);

	        JsonNode root = objectMapper.readTree(json);
	        JsonNode mealNode = root.path("meals").get(0);

	        // Convert JSON → MealDto
	        MealDto meal = objectMapper.treeToValue(mealNode, MealDto.class);

	        // Extract ingredients
	        meal.setIngredients(extractIngredients(mealNode));

	        return meal;

	    } catch (Exception e) {
	        throw new RuntimeException("Failed to fetch meal", e);
	    }
		
	}

	@Override
	@Cacheable("random")
	public MealDto getRandomMeal() {
		
        try 
        {
			String json = mealDbClient.getRandomMeal();
	
	        JsonNode root = objectMapper.readTree(json);
	        JsonNode mealsNode = root.path("meals");
	
	        if (!mealsNode.isArray() || mealsNode.isEmpty()) {
	            return null;
	        }
	
	        JsonNode mealNode = mealsNode.get(0);
	
	        MealDto meal = objectMapper.treeToValue(mealNode, MealDto.class);
	
	        meal.setIngredients(extractIngredients(mealNode));
	
	        return meal;
        } 
        catch (Exception e) 
        {
            throw new RuntimeException("Error fetching random meal", e);
        }
        
	}

	@Override
	@Cacheable("category")
	public List<MealDto> getMealsByCategory(String category) {
		
        try 
        {
			String json = mealDbClient.getMealByCategory(category);
	
		    if (json == null || json.isBlank()) {
		        return List.of();                     // Empty List
		    }
	
		    MealResponseDto dto = objectMapper.readValue(json, MealResponseDto.class);
	
		    List<MealDto> meals = dto.getMeals();
		    if (meals == null) {
		        return List.of();
		    }
		    
		    return meals;
        } 
        catch (Exception e) 
        {
            throw new RuntimeException("Error fetching meals by category", e);
        }
	}

	@Override
	@Cacheable("area")
	public List<MealDto> getMealsByArea(String area) {
		
        try 
        {
			String json = mealDbClient.getMealByArea(area);
	
		    if (json == null || json.isBlank()) {
		        return List.of();                     // Empty List
		    }
	
		    MealResponseDto dto = objectMapper.readValue(json, MealResponseDto.class);
	
		    List<MealDto> meals = dto.getMeals();
		    if (meals == null) {
		        return List.of();
		    }
		    
		    return meals;
        } 
        catch (Exception e) 
        {
            throw new RuntimeException("Error fetching meals by area", e);
        }
	}

	@Override
	@Cacheable("categories")
	public List<CategoryDto> getAllCategories() {
		
        try 
        {
	        String json = mealDbClient.getAllCategories();
	
	        JsonNode rootNode = objectMapper.readTree(json);
	        JsonNode categoriesNode = rootNode.get("categories");
	
	        if (categoriesNode == null || !categoriesNode.isArray()) {
	            return List.of();
	        }
	
	        return objectMapper.readerForListOf(CategoryDto.class).readValue(categoriesNode);
        } 
        catch (Exception e) 
        {
            throw new RuntimeException("Error fetching categories", e);
        }
	}

	@Override
	@Cacheable("areas")
	public List<AreaDto> getAllAreas() {
		
        try 
        {
			String json = mealDbClient.getAllAreas();
	
	        JsonNode rootNode = objectMapper.readTree(json);
	        JsonNode mealsNode = rootNode.get("meals");
	
	        if (mealsNode == null || !mealsNode.isArray()) {
	            return List.of();
	        }
	
	        return objectMapper.readerForListOf(AreaDto.class).readValue(mealsNode);
        } 
        catch (Exception e) 
        {
            throw new RuntimeException("Error fetching areas", e);
        }
		
	}
	
	  // ---------------- INGREDIENT EXTRACTION ----------------
	
	private List<IngredientMeasureDto> extractIngredients(JsonNode mealNode) {

	    List<IngredientMeasureDto> ingredients = new ArrayList<>();

	    for (int i = 1; i <= 20; i++) {

	        JsonNode ingredientNode = mealNode.get("strIngredient" + i);
	        JsonNode measureNode = mealNode.get("strMeasure" + i);

	        if (ingredientNode != null && ingredientNode.isTextual()) 
	        {

	            String ingredient = ingredientNode.asText();
	            String measure = (measureNode != null && measureNode.isTextual()) ? measureNode.asText() : "";

	            if (!ingredient.isBlank()) {
	                IngredientMeasureDto dto = new IngredientMeasureDto();
	                dto.setIngredient(ingredient);
	                dto.setMeasure(measure);
	                ingredients.add(dto);
	            }
	        }
	    }
	    return ingredients;
	}


}
