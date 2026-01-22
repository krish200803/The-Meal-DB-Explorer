# TheMealDB Explorer 🍽️

TheMealDB Explorer is a Spring Boot MVC web application that integrates with the public **TheMealDB API** to allow users to search and explore meal recipes, browse meals by category and area (cuisine), and view detailed cooking instructions and ingredients.

The application uses **server-side rendering with Thymeleaf**, **in-memory caching with Caffeine**, and runs locally without a database.

---

## Features

- Search meals by name
- Browse meals by category (Chicken, Dessert, Seafood, etc.)
- Browse meals by area/cuisine (Italian, Indian, Canadian, etc.)
- Get a random meal suggestion
- View detailed meal information:
- Ingredients with measurements
- Cooking instructions
- YouTube recipe video link
- Improved performance using in-memory caching
- Responsive and clean UI using HTML & CSS

## Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring MVC**
- **Thymeleaf**
- **Caffeine Cache**
- **HTML & CSS**
- **Maven**

## External API Used

- **TheMealDB API**  
  https://www.themealdb.com/api.php

 > No database is used because all data is fetched from an external API.

## Project Architecture

- **Client Layer** – Handles communication with TheMealDB API  
- **Service Layer** – Business logic, JSON parsing, caching  
- **Controller Layer** – Spring MVC controllers for UI navigation  
- **DTO Layer** – Data transfer objects for meals, categories, areas  
- **View Layer** – Thymeleaf templates with HTML & CSS  

### Prerequisites
- Java 17 or higher
- Maven
- Internet connection (for API access)


