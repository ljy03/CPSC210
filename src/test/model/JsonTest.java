package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkRecipe(String title, int rating, String info, Recipe recipe) {
        assertEquals(title, recipe.getRecipeTitle());
        assertEquals(rating, recipe.getRecipeRating());
        assertEquals(info, recipe.getActualRecipe());
    }
}


