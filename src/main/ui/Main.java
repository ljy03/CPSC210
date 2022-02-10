package ui;

import model.Recipe;
import model.RecipeBook;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static boolean end = false;

    public static void main(String[] args) {
        RecipeBook recipeBook = new RecipeBook();

        while (!end) {
            System.out.println("input 1 to see the list of recipes,"
                    + " 2 to add new recipe to recipe book, 3 to view an exist recipe,"
                    + " 4 to change rating of an exist recipe, 5 to end program");
            int instruction = sc.nextInt();
            if (instruction == 1) {
                printTitleOfRecipe(recipeBook.getRecipes());
            } else if (instruction == 2) {
                addRecipe(recipeBook);
            } else if (instruction == 3) {
                printTitleOfRecipe(recipeBook.getRecipes());
                String name = sc.next();
                System.out.println(recipeBook.displaySelectedRecipe(recipeBook.containsRecipe(name)));
            } else if (instruction == 4) {
                changeRecipeRating(recipeBook);
            } else if (instruction == 5) {
                end = true;
            }

        }

    }

    public static void printTitleOfRecipe(ArrayList<Recipe> recipes) {
        int count = 1;
        for (Recipe recipe : recipes) {
            System.out.println(count + " " + recipe.getRecipeTitle());
            count++;
        }
    }

    public static void addRecipe(RecipeBook recipeBook) {
        System.out.println("please input title of your recipe");
        String title = sc.next();
        System.out.println("please input your recipe's rating");
        int rating = sc.nextInt();
        System.out.println("please input your recipe");
        String info = sc.next();
        recipeBook.makeRecipe(title, rating, info);
    }

    public static void changeRecipeRating(RecipeBook recipeBook) {
        printTitleOfRecipe(recipeBook.getRecipes());
        System.out.println("Please input the recipe you want to rate");
        String newRatingRecipeName = sc.next();
        System.out.println("Please input the rating");
        int newRating = sc.nextInt();
        recipeBook.changeRecipeRating(recipeBook.containsRecipe(newRatingRecipeName), newRating);
        System.out.println(recipeBook.displaySelectedRecipe(recipeBook.containsRecipe(newRatingRecipeName)));
    }

}
