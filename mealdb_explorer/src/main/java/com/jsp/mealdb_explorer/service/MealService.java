package com.jsp.mealdb_explorer.service;

import java.util.List;

import com.jsp.mealdb_explorer.dto.AreaDto;
import com.jsp.mealdb_explorer.dto.CategoryDto;
import com.jsp.mealdb_explorer.dto.MealDto;

public interface MealService {
	
	List<MealDto> searchMealsByName(String name);
	
	MealDto getMealsById(String id);
	
	MealDto getRandomMeal();
	
	List<MealDto> getMealsByCategory(String category);
	
	List<MealDto> getMealsByArea(String area);
	
    List<CategoryDto> getAllCategories();

    List<AreaDto> getAllAreas();

}
