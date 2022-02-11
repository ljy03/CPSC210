package model;


import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RecipeBook {

    private String bookTitle;
    private ArrayList<Recipe> recipes;


    public RecipeBook() {
        recipes = new ArrayList<>();
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

    //EFFECTS: displays the recipe or else output false statement
    public String displaySelectedRecipe(Recipe recipe) {
        if (recipe != null) {
            return displayRecipe(recipe);
        }
        String falseStatement = "There are no Recipe with the given title";
        return falseStatement;

    }

    //EFFECTS: transform recipe to strings to display recipe.
    public String displayRecipe(Recipe recipe) {
        String realRecipe = recipe.getRecipeTitle() + "\n"
                + recipe.getActualRecipe() + "\n" + recipe.getRecipeRating();
        return realRecipe;
    }

    //REQUIRES: 5 >= rating >= 0
    //MODIFIES: this
    //EFFECTS: change an existing recipe's rating
    public void changeRecipeRating(Recipe recipe, int newRating) {
        recipe.setRecipeRating(newRating);
    }
}
