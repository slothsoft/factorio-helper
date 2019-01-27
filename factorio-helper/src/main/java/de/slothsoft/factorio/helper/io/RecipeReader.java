package de.slothsoft.factorio.helper.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

import de.slothsoft.factorio.helper.pojo.Recipe;

public interface RecipeReader {

	default List<Recipe> readRecipeString(String input) throws IOException {
		Objects.requireNonNull(input, "Input string cannot be null!");
		try (ByteArrayInputStream stream = new ByteArrayInputStream(input.getBytes(Charset.defaultCharset()))) {
			return readRecipe(stream);
		}
	}

	List<Recipe> readRecipe(InputStream input) throws IOException;
}