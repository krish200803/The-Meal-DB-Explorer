package com.jsp.mealdb_explorer.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MealDBClient {
	
	private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	public String searchMealByName(String name)
	{
		return restTemplate.getForObject(BASE_URL + "search.php?s={name}", String.class, name);
	}
	
	public String getMealById(String id)
	{
		return restTemplate.getForObject(BASE_URL + "lookup.php?i={id}", String.class, id);
	}
	
	public String getAllCategories() 
	{
	    return restTemplate.getForObject(BASE_URL + "categories.php", String.class);
	}
	
	public String getAllAreas() 
	{
	    return restTemplate.getForObject(BASE_URL + "list.php?a=list", String.class);
	}

	public String getRandomMeal()
	{
		return restTemplate.getForObject(BASE_URL + "random.php", String.class);
	}
	
	public String getMealByIngredient(String ingredient)
	{
		return restTemplate.getForObject(BASE_URL + "filter.php?i={ingredient}", String.class, ingredient);
	}
	
	public String getMealByCategory(String category)
	{
		return restTemplate.getForObject(BASE_URL + "filter.php?c={category}", String.class, category);
	}
	
	public String getMealByArea(String area)
	{
		return restTemplate.getForObject(BASE_URL + "filter.php?a={area}", String.class, area);
	}
}
