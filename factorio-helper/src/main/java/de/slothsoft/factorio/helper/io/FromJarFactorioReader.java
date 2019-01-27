package de.slothsoft.factorio.helper.io;

import java.io.InputStream;

public class FromJarFactorioReader implements FactorioReader {

	@Override
	public InputStream createDataLoaderStream() {
		return getClass().getResourceAsStream("dataloader.lua");
	}

	@Override
	public String[] getAllRecipeFileNames() {
		return RecipeFiles.FILES;
	}

	@Override
	public InputStream createRecipeFileStream(String recipeFileName) {
		return getClass().getResourceAsStream(recipeFileName);
	}
}
