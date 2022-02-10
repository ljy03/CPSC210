package model;

import javafx.scene.effect.Effect;

public class Recipe {

    private String recipeTitle;
    private int recipeRating;
    private String actualRecipe;


    // Requires: 5>= rating >= 0
    // EFFECTS: Creates a new recipe.
    public Recipe(String title, int rating, String information) {
        recipeTitle = title;
        recipeRating = rating;
        actualRecipe = information;

    }

    //Effects : gets recipe rating
    public int getRecipeRating() {
        return recipeRating;
    }

    //Effects : gets recipe information
    public String getActualRecipe() {
        return actualRecipe;
    }

    //Effects : gets recipe title
    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeRating(int newRating) {
        recipeRating = newRating;
    }


}




