package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            RecipeBook rb = new RecipeBook("My RecipeBook");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            RecipeBook rb = new RecipeBook("My RecipeBook");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(rb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            rb = reader.read();
            assertEquals("My RecipeBook", rb.getBookTitle());
            assertEquals(0, rb.getRecipes().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            RecipeBook rb = new RecipeBook("My RecipeBook");
            rb.addRecipe(new Recipe("ss",5,"sss"));
            rb.addRecipe(new Recipe("ww",4,"www"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(rb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            rb = reader.read();
            assertEquals("My RecipeBook", rb.getBookTitle());
            List<Recipe> recipes = rb.getRecipes();
            assertEquals(2, rb.getRecipes().size());
            checkRecipe("ss",5,"sss", recipes.get(0));
            checkRecipe("ww",4,"www",recipes.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
