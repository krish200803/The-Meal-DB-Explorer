package com.jsp.mealdb_explorer.dto;

import java.util.List;

import lombok.Data;

@Data
public class MealDto {
	
    private String idMeal;
    private String strMeal;
    private String strMealAlternate;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strTags;
    private String strYoutube;
    private String strSource;
    private String strImageSource;
    private String strCreativeCommonsConfirmed;
    private String dateModified;
    
    // Custom mapped (not directly from API)
    private List<IngredientMeasureDto> ingredients;

}
