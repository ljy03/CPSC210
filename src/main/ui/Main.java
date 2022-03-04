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
                    + " 4 to change rating of an exist recipe, 5 to save recipe book, 6 to load recipe book"
                    + " 7 to end program");
            int instruction = sc.nextInt();
            if (instruction == 1) {
                printTitleOfRecipe(recipeBook.getRecipes());
            } else if (instruction == 2) {
                addRecipe(recipeBook);
            } else if (instruction == 3) {
                printTitleOfRecipe(recipeBook.getRecipes());
                String name = sc.nextLine();
                System.out.println(recipeBook.displaySelectedRecipe(recipeBook.containsRecipe(name)));
            } else if (instruction == 4) {
                changeRecipeRating(recipeBook);
            } else if (instruction == 5) {
                end = true;
            } else if (instruction == 6) {

            } else if (instruction == 7) {
                end = true;
            }
        }
    }


    //MODIFIES: this
    //EFFECTS: adds index to recipe book to make the recipe book look nicer.
    public static void printTitleOfRecipe(ArrayList<Recipe> recipes) {
        int count = 1;
        for (Recipe recipe : recipes) {
            System.out.println(count + " " + recipe.getRecipeTitle());
            count++;
        }
    }


    //MODIFIES: this
    //EFFECTS: makes a new recipe and add it to the recipe book
    public static void addRecipe(RecipeBook recipeBook) {
        System.out.println("please input title of your recipe");
        String title = sc.nextLine();
        System.out.println("please input your recipe's rating");
        int rating = sc.nextInt();
        System.out.println("please input your recipe");
        String info = sc.nextLine();
        recipeBook.makeRecipe(title, rating, info);
    }


    //MODIFIES: this
    //EFFECTS: change ann existing recipe's rating
    public static void changeRecipeRating(RecipeBook recipeBook) {
        printTitleOfRecipe(recipeBook.getRecipes());
        System.out.println("Please input the recipe you want to rate");
        String newRatingRecipeName = sc.nextLine();
        System.out.println("Please input the rating");
        int newRating = sc.nextInt();
        recipeBook.changeRecipeRating(recipeBook.containsRecipe(newRatingRecipeName), newRating);
        System.out.println(recipeBook.displaySelectedRecipe(recipeBook.containsRecipe(newRatingRecipeName)));
    }



}
