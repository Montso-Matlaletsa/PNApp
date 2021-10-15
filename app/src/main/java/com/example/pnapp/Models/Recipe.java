package com.example.pnapp.Models;

import com.fatsecret.platform.model.Category;
import com.fatsecret.platform.model.Direction;
import com.fatsecret.platform.model.Ingredient;
import com.fatsecret.platform.model.Serving;

import java.math.BigDecimal;
import java.util.List;

public class Recipe {


    /** The overall average rating of a recipe from FatSecret members out of five */
    private Integer rating;

    /** A list of the types that the recipe is classified under */
    private List<String> types;

    /** The number of servings the recipe is intended for */
    private BigDecimal numberOfServings;

    /** The time in minutes to prepare the recipe (where available) */
    private Integer preparationTime;

    /** The time in minutes to cook the recipe (where available) */
    private Integer cookingTime;

    /** A list of the categories that the recipe is classified under */
    private List<com.fatsecret.platform.model.Category> categories;

    /** The complete nutritional information */
    private com.fatsecret.platform.model.Serving serving;

    /** A list of the directions/steps involved in creating the recipe */
    private List<com.fatsecret.platform.model.Direction> directions;

    /** A list of the ingredients that make up the recipe */
    private List<com.fatsecret.platform.model.Ingredient> ingredients;

    /**
     * Returns the average rating of the recipe
     *
     * @return		the average rating of the recipe
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * Sets the average rating of the recipe
     *
     * @param		rating the average rating of the recipe
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * Returns the list of the types that the recipe is classified under
     *
     * @return		the list of the types that the recipe is classified under
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * Sets the list of the types that the recipe is classified under
     *
     * @param		types the list of the types that the recipe is classified under
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

    /**
     * Returns the number of servings the recipe is intended for
     *
     * @return		the number of servings the recipe is intended for
     */
    public BigDecimal getNumberOfServings() {
        return numberOfServings;
    }

    /**
     * Sets the number of servings the recipe is intended for
     *
     * @param		numberOfServings the number of servings the recipe is intended for
     */
    public void setNumberOfServings(BigDecimal numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    /**
     * Returns the time in minutes to prepare the recipe
     *
     * @return		the time in minutes to prepare the recipe
     */
    public Integer getPreparationTime() {
        return preparationTime;
    }

    /**
     * Sets the time in minutes to prepare the recipe
     *
     * @param		preparationTime the time in minutes to prepare the recipe
     */
    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    /**
     * Returns the time in minutes to cook the recipe
     *
     * @return		the time in minutes to cook the recipe
     */
    public Integer getCookingTime() {
        return cookingTime;
    }

    /**
     * Sets the time in minutes to cook the recipe
     *
     * @param		cookingTime the time in minutes to cook the recipe
     */
    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    /**
     * Returns the list of the categories that the recipe is classified under
     *
     * @return		the list of the categories that the recipe is classified under
     */
    public List<com.fatsecret.platform.model.Category> getCategories() {
        return categories;
    }

    /**
     * Sets the list of the categories that the recipe is classified under
     *
     * @param		categories the list of the categories that the recipe is classified under
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * Returns the complete nutritional information
     *
     * @return		the complete nutritional information
     */
    public com.fatsecret.platform.model.Serving getServing() {
        return serving;
    }

    /**
     * Sets the complete nutritional information
     *
     * @param		serving the complete nutritional information
     */
    public void setServing(Serving serving) {
        this.serving = serving;
    }

    /**
     * Returns the list of the directions/steps involved in creating the recipe
     *
     * @return		the list of the directions/steps involved in creating the recipe
     */
    public List<com.fatsecret.platform.model.Direction> getDirections() {
        return directions;
    }

    /**
     * Sets the list of the directions/steps involved in creating the recipe
     *
     * @param		directions the list of the directions/steps involved in creating the recipe
     */
    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    /**
     * Returns the list of the ingredients that make up the recipe
     *
     * @return		the list of the ingredients that make up the recipe
     */
    public List<com.fatsecret.platform.model.Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Sets the list of the ingredients that make up the recipe
     *
     * @param		ingredients the list of the ingredients that make up the recipe
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
