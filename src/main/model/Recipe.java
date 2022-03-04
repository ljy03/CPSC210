package model;

import javafx.scene.effect.Effect;
import org.json.JSONObject;
import persistence.Writable;

public class Recipe implements Writable {

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

    //Effects : set recipe rating
    public void setRecipeRating(int newRating) {
        recipeRating = newRating;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("recipeTitle",recipeTitle);
        json.put("recipeRating",recipeRating);
        json.put("actualRecipe",actualRecipe);
        return json;
    }


}




