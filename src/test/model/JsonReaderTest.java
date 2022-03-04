package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {


    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            RecipeBook rb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRecipeBook.json");
        try {
            RecipeBook rb = reader.read();
            assertEquals("My RecipeBook", rb.getBookTitle());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRecipe.json");
        try {
            RecipeBook rb = reader.read();
            assertEquals("My RecipeBook", rb.getBookTitle());
            List<Recipe> recipes = rb.getRecipes();
            assertEquals(2, recipes.size());
            checkRecipe("cc", 3, "ccc", recipes.get(0));
            checkRecipe("dd", 4, "ddd", recipes.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}


