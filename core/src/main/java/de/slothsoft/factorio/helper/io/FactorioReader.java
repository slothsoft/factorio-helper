package de.slothsoft.factorio.helper.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public interface FactorioReader {

	/**
	 * Reads the file "dataloader.lua" from the Factorio installation.
	 *
	 * @return a input stream with the above contents
	 */

	InputStream createDataLoaderStream() throws IOException;

	/**
	 * Returns file names or any other kind of ID for each individual recipe file.
	 *
	 * @return an array of file names
	 */

	String[] getAllRecipeFileNames() throws IOException;

	/**
	 * Reads the recipe file with the file name (or ID) specified in
	 * {@link #getAllRecipeFileNames()}. You need to close the input stream after you're
	 * done.
	 *
	 * @param recipeFileName as specified in {@link #getAllRecipeFileNames()}
	 * @return a input stream with the recipe contents
	 */

	InputStream createRecipeFileStream(String recipeFileName) throws IOException;

	/**
	 * Creates a stream for each recipe find in {@link #getAllRecipeFileNames()} and makes
	 * a supplier with the method {@link #createRecipeFileStream(String)}. Remember: if
	 * you call the supplier, you <b>must</b> close the input stream it creates.
	 *
	 * @return a stream with the recipe contents supplier
	 */

	default Stream<InputStreamSupplier> getAllRecipeFileStreams() throws IOException {
		return Stream.of(getAllRecipeFileNames())
				.map(recipeFileName -> (InputStreamSupplier) () -> createRecipeFileStream(recipeFileName));
	}

	default ResourceBundle createRecipeLocalization() throws IOException {
		try (InputStream input = new ByteArrayInputStream(new byte[0])) {
			return new PropertyResourceBundle(input);
		}
	}
}
