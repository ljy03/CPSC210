package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    Recipe rc1;
    Recipe rc2;
    RecipeBook rb1;
    ArrayList<Recipe> recipes;

    @BeforeEach
    public void setup() {
        rc1 = new Recipe("title1", 5, "info1");
        rc2 = new Recipe("title2", 4, "info2");
        rb1 = new RecipeBook("My RecipeBook");
        recipes = rb1.getRecipes();
        recipes.add(rc1);
    }

    @Test
    public void testMakeRecipe() {
        rb1.makeRecipe("title1", 5, "info1");
        assertEquals(2, recipes.size());
    }

    @Test
    public void testContainsRecipe() {
        assertEquals(rc1, rb1.containsRecipe(rc1.getRecipeTitle()));
        assertNotEquals(rc2, rb1.containsRecipe(rc1.getRecipeTitle()));
        assertNull( rb1.containsRecipe("name"));
    }

    @Test
    public void testDisplayRecipe() {
        assertEquals(rc1.getRecipeTitle() + " "
                + rc1.getActualRecipe() + " " + rc1.getRecipeRating(), rb1.displayRecipe(rc1).getText());

    }

    @Test
    public void testDisplaySelectedRecipe() {
        assertEquals(rb1.displayRecipe(rc1).getText(), rb1.displaySelectedRecipe(rc1).getText());
        assertEquals("There are no Recipe with the given title", rb1.displaySelectedRecipe(null).getText());
    }

    @Test
    public void testChangeRating() {
        rb1.changeRecipeRating(rc1, 3);
        assertEquals(3, rc1.getRecipeRating());
    }

}


