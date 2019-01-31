package de.slothsoft.factorio.helper.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.slothsoft.factorio.helper.io.FactorioReader;
import de.slothsoft.factorio.helper.io.RecipeReader;
import de.slothsoft.factorio.helper.io.ServiceBuddy;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public final class MainController implements Initializable {

	@FXML
	private RecipesTable recipesTable;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		final FactorioReader factorioReader = ServiceBuddy.getService(FactorioReader.class);
		final RecipeReader recipeReader = ServiceBuddy.getService(RecipeReader.class);
		try {
			this.recipesTable.recipies(recipeReader.readRecipes(factorioReader.getAllRecipeFileStreams()));
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void showHistory() {
		System.out.println("MainController.showHistory()");
	}

}
