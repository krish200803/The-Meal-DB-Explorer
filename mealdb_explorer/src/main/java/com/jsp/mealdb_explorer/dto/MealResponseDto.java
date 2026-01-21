package com.jsp.mealdb_explorer.dto;

import java.util.List;

import lombok.Data;

@Data
public class MealResponseDto {
	
    private List<MealDto> meals;

}
