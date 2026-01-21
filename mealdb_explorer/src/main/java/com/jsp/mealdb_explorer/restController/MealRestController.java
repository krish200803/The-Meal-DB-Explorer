package com.jsp.mealdb_explorer.restController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.mealdb_explorer.dto.MealDto;
import com.jsp.mealdb_explorer.service.MealService;

@RestController
@RequestMapping("/api/meals")
public class MealRestController {
	
	private final MealService mealService;

    public MealRestController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/search")
    public List<MealDto> search(@RequestParam String name) {
        return mealService.searchMealsByName(name);
    }

    @GetMapping("/{id}")
    public MealDto getById(@PathVariable String id) {
        return mealService.getMealsById(id);
    }

    @GetMapping("/random")
    public MealDto random() {
        return mealService.getRandomMeal();
    }

    @GetMapping("/category/{name}")
    public List<MealDto> byCategory(@PathVariable String name) {
        return mealService.getMealsByCategory(name);
    }

    @GetMapping("/area/{name}")
    public List<MealDto> byArea(@PathVariable String name) {
        return mealService.getMealsByArea(name);
    }

}
