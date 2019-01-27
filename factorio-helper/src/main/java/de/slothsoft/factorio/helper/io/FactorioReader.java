package de.slothsoft.factorio.helper.io;

import java.io.InputStream;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface FactorioReader {

	/**
	 * Reads the file "dataloader.lua" from the Factorio installation.
	 *
	 * @return a input stream with the above contents
	 */

	InputStream createDataLoaderStream();

	/**
	 * Returns file names or any other kind of ID for each individual recipe file.
	 *
	 * @return an array of file names
	 */

	String[] getAllRecipeFileNames();

	/**
	 * Reads the recipe file with the file name (or ID) specified in
	 * {@link #getAllRecipeFileNames()}. You need to close the input stream after you're
	 * done.
	 *
	 * @param recipeFileName as specified in {@link #getAllRecipeFileNames()}
	 * @return a input stream with the recipe contents
	 */

	InputStream createRecipeFileStream(String recipeFileName);

	/**
	 * Creates a stream for each recipe find in {@link #getAllRecipeFileNames()} and makes
	 * a supplier with the method {@link #createRecipeFileStream(String)}. Remember: if
	 * you call the supplier, you <b>must</b> close the input stream it creates.
	 *
	 * @return a stream with the recipe contents supplier
	 */

	default Stream<Supplier<InputStream>> getAllRecipeFileStreams() {
		return Stream.of(getAllRecipeFileNames()).map(recipeFileName -> new Supplier<InputStream>() {

			@Override
			public InputStream get() {
				return createRecipeFileStream(recipeFileName);
			}
		});
	}

}
