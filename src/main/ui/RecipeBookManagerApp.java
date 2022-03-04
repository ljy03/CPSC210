package ui;

import model.Recipe;
import model.RecipeBook;
import persistence.JsonWriter;
import persistence.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class RecipeBookManagerApp {
    private static final String JSON_STORE = "./data/recipeBook.json";
    private static Scanner input;
    private RecipeBook recipeBook;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //constructs recipeBook and runs application
    public RecipeBookManagerApp() {
        input = new Scanner(System.in);
        recipeBook = new RecipeBook("My RecipeBook");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runRecipeBook();

    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runRecipeBook() {
        boolean end = false;
        int command;
        input = new Scanner(System.in);

        while (!end) {
            displayMenu();
            command = input.nextInt();
            input.nextLine();

            if (command == 0) {
                end = true;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays menu of options to user
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> see the list of recipes");
        System.out.println("\t2 -> add new recipe to recipe book");
        System.out.println("\t3 -> view an exist recipe");
        System.out.println("\t4 -> change rating of an exist recipe");
        System.out.println("\t5 -> save recipe book");
        System.out.println("\t6 -> load recipe book");
        System.out.println("\t0 -> quit program");

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(int command) {
        if (command == 1) {
            printTitleOfRecipe(recipeBook.getRecipes());
        } else if (command == 2) {
            addRecipe(recipeBook);
        } else if (command == 3) {
            printTitleOfRecipe(recipeBook.getRecipes());
            String name = input.nextLine();
            System.out.println(recipeBook.displaySelectedRecipe(recipeBook.containsRecipe(name)));
        } else if (command == 4) {
            changeRecipeRating(recipeBook);
        } else if (command == 5) {
            saveRecipeBook();
        } else if (command == 6) {
            loadRecipeBook();
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
        String title = input.nextLine();
        System.out.println("please input your recipe's rating");
        int rating = input.nextInt();
        input.nextLine();
        System.out.println("please input your recipe");
        String info = input.nextLine();
        recipeBook.makeRecipe(title, rating, info);
    }


    //MODIFIES: this
    //EFFECTS: change ann existing recipe's rating
    public static void changeRecipeRating(RecipeBook recipeBook) {
        printTitleOfRecipe(recipeBook.getRecipes());
        System.out.println("Please input the recipe you want to rate");
        String newRatingRecipeName = input.nextLine();
        System.out.println("Please input the rating");
        int newRating = input.nextInt();
        input.nextLine();
        recipeBook.changeRecipeRating(recipeBook.containsRecipe(newRatingRecipeName), newRating);
        System.out.println(recipeBook.displaySelectedRecipe(recipeBook.containsRecipe(newRatingRecipeName)));
    }

    // EFFECTS: saves the workroom to file
    private void saveRecipeBook() {
        try {
            jsonWriter.open();
            jsonWriter.write(recipeBook);
            jsonWriter.close();
            System.out.println("Saved " + recipeBook.getBookTitle() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadRecipeBook() {
        try {
            recipeBook = jsonReader.read();
            System.out.println("Loaded " + recipeBook.getBookTitle() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}

