package de.slothsoft.factorio.helper.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

import de.slothsoft.factorio.helper.Recipe;

public interface RecipeReader {

	default List<Recipe> readRecipeString(String input) throws IOException {
		Objects.requireNonNull(input, "Input string cannot be null!");
		try (ByteArrayInputStream stream = new ByteArrayInputStream(input.getBytes(Charset.defaultCharset()))) {
			return readRecipe(stream);
		}
	}

	default List<Recipe> readRecipes(Stream<Supplier<InputStream>> streamSupplier) throws IOException {
		final List<Recipe> result = new ArrayList<>();
		final Iterator<Supplier<InputStream>> it = streamSupplier.iterator();
		while (it.hasNext()) {
			try (InputStream input = it.next().get()) {
				result.addAll(readRecipe(input));
			}
		}
		return result;
	}

	List<Recipe> readRecipe(InputStream input) throws IOException;
}