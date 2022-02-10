package model;


import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RecipeBook {

    private String bookTitle;
    private ArrayList<Recipe> recipes;


    public RecipeBook() {
        recipes = new ArrayList<Recipe>();
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void makeRecipe(String title, int rating, String information) {
        Recipe recipe = new Recipe(title, rating, information);
        recipes.add(recipe);

    }

    public Recipe containsRecipe(String name) {
        for (Recipe recipe : recipes) {
            if (recipe.getRecipeTitle().equals(name)) {
                return recipe;
            }
        }
        return null;
    }

    public String displaySelectedRecipe(Recipe recipe) {
        if (recipe != null) {
            return displayRecipe(recipe);
        }
        String falseStatement = "There are no Recipe with the given title";
        return falseStatement;

    }

    public String displayRecipe(Recipe recipe) {
        String realRecipe = recipe.getRecipeTitle() + "\n"
                + recipe.getActualRecipe() + "\n" + recipe.getRecipeRating();
        return realRecipe;
    }

    public void changeRecipeRating(Recipe recipe, int newRating) {
        recipe.setRecipeRating(newRating);
    }
}
