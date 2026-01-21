package com.jsp.mealdb_explorer.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.mealdb_explorer.dto.AreaDto;
import com.jsp.mealdb_explorer.dto.CategoryDto;
import com.jsp.mealdb_explorer.dto.MealDto;
import com.jsp.mealdb_explorer.service.MealService;

@Controller
public class MealController {
	
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }
    
    // ---------------- HOME PAGE ----------------

    @GetMapping("/")
    public String home(Model model) {

        List<CategoryDto> categories = mealService.getAllCategories();
        List<AreaDto> areas = mealService.getAllAreas();

        model.addAttribute("categories", categories);
        model.addAttribute("areas", areas);

        return "home";   // home.html
    }
    
 // ---------------- SEARCH ----------------

    @GetMapping("/search")
    public String searchMeals(@RequestParam("name") String name, Model model) {

        List<MealDto> meals = mealService.searchMealsByName(name);
        model.addAttribute("meals", meals);
        model.addAttribute("searchTerm", name);

        return "search";   // search.html
    }

    // ---------------- MEAL DETAILS ----------------

    @GetMapping("/meal/{id}")
    public String mealDetails(@PathVariable String id, Model model) {

        MealDto meal = mealService.getMealsById(id);
        model.addAttribute("meal", meal);

        return "meal_details";   // meal-details.html
    }

    // ---------------- FILTER BY CATEGORY ----------------

    @GetMapping("/category")
    public String mealsByCategory(@RequestParam("name") String category, Model model) {

        List<MealDto> meals = mealService.getMealsByCategory(category);
        model.addAttribute("meals", meals);
        model.addAttribute("filterName", category);

        return "category";   // category.html
    }

    // ---------------- FILTER BY AREA ----------------

    @GetMapping("/area")
    public String mealsByArea(@RequestParam("name") String area, Model model) {

        List<MealDto> meals = mealService.getMealsByArea(area);
        model.addAttribute("meals", meals);
        model.addAttribute("filterName", area);

        return "area";   // area.html
    }

    // ---------------- RANDOM MEAL ----------------

    @GetMapping("/random")
    public String randomMeal(Model model) {

        MealDto meal = mealService.getRandomMeal();
        model.addAttribute("meal", meal);

        return "meal_details";
    }

}
