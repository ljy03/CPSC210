package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RecipeBook implements Writable {

    private ArrayList<Recipe> recipes;
    private String bookTitle;


    public RecipeBook(String title) {
        recipes = new ArrayList<>();
        this.bookTitle = title;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }


    //REQUIRES: 5 >= rating >= 0
    //MODIFIES: this
    //EFFECTS: makes a new recipe and add it to the recipe book
    public void makeRecipe(String title, int rating, String information) {
        Recipe recipe = new Recipe(title, rating, information);
        recipes.add(recipe);

    }

    //EFFECTS: if the recipe exist, return the recipe.
    public Recipe containsRecipe(String name) {
        for (Recipe recipe : recipes) {
            if (recipe.getRecipeTitle().equals(name)) {
                return recipe;
            }
        }
        return null;
    }

    //EFFECTS: display recipe as jlabel or else output false statement as jlabel
    public JLabel displaySelectedRecipe(Recipe recipe) {
        if (recipe != null) {
            return displayRecipe(recipe);
        }
        JLabel label = new JLabel("There are no Recipe with the given title");
        label.setBounds(300,100,500,500);
        return label;

    }

    //EFFECTS: transform recipe to jlabel
    public JLabel displayRecipe(Recipe recipe) {
        JLabel label = new JLabel(recipe.getRecipeTitle() + " "
                + recipe.getActualRecipe() + " " + recipe.getRecipeRating());
        label.setBounds(300,100,500,100);
        return label;

    }

    //REQUIRES: 5 >= rating >= 0
    //MODIFIES: this
    //EFFECTS: change an existing recipe's rating
    public void changeRecipeRating(Recipe recipe, int newRating) {
        recipe.setRecipeRating(newRating);
    }

    //add recipe to recipeBook
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    //get bookTitle
    public String getBookTitle() {
        return bookTitle;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("bookTitle",bookTitle);
        json.put("recipes",recipeBookToJson());
        return json;
    }

    //return recipes as jsonArray
    private JSONArray recipeBookToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Recipe r : recipes) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;

    }
}


