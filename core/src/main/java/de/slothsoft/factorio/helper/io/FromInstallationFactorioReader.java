package de.slothsoft.factorio.helper.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class FromInstallationFactorioReader implements FactorioReader {

	private final Path baseFolder = Paths.get("S:\\Spiele\\Steam\\steamapps\\common\\Factorio");

	@Override
	public InputStream createDataLoaderStream() throws IOException {
		return new FileInputStream(this.baseFolder.resolve("data/core/lualib/dataloader.lua").toFile());
	}

	@Override
	public String[] getAllRecipeFileNames() throws IOException {
		return getRecipeFolder().toFile().list();
	}

	private Path getRecipeFolder() {
		return this.baseFolder.resolve("data/base/prototypes/recipe");
	}

	@Override
	public InputStream createRecipeFileStream(String recipeFileName) throws IOException {
		return new FileInputStream(getRecipeFolder().resolve(recipeFileName).toFile());
	}

	@Override
	public ResourceBundle createRecipeLocalization() throws IOException {
		try (FileInputStream input = new FileInputStream(
				this.baseFolder.resolve("data/base/locale/en/base.cfg").toFile())) {
			return new PropertyResourceBundle(input);
		}
	}

}
