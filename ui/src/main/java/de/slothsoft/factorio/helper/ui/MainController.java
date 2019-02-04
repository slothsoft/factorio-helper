package de.slothsoft.factorio.helper.ui;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import de.slothsoft.factorio.helper.Recipe;
import de.slothsoft.factorio.helper.io.FactorioReader;
import de.slothsoft.factorio.helper.io.RecipeReader;
import de.slothsoft.factorio.helper.io.ServiceBuddy;
import de.slothsoft.factorio.helper.ui.impl.RecipeCellFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
public final class MainController implements Initializable {

	@FXML
	private AutoCompleteTextField insertTextField;
	@FXML
	private DependencyTree dependencyTree;
	@FXML
	private RecipesTable dependencySumTable;
	@FXML
	private RecipesTable recipesTable;

	List<Recipe> allRecipes;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		final FactorioReader factorioReader = ServiceBuddy.getService(FactorioReader.class);
		final RecipeReader recipeReader = ServiceBuddy.getService(RecipeReader.class);

		try {
			this.allRecipes = recipeReader.readRecipes(factorioReader.getAllRecipeFileStreams());
		} catch (final IOException e) {
			e.printStackTrace();
			this.allRecipes = Collections.emptyList();
		}

		this.insertTextField.setProposals(
				this.allRecipes.stream().map(RecipeCellFactory::stringify).sorted().collect(Collectors.toList()));
		this.dependencyTree.setAvailableRecipes(this.allRecipes);
		this.recipesTable.recipies(this.allRecipes.stream().sorted(Comparator.comparing(RecipeCellFactory::stringify))
				.collect(Collectors.toList()));
	}

	@FXML
	void insertDependency() {
		final String input = this.insertTextField.getText();
		final Recipe recipe = this.allRecipes.stream()
				.filter(r -> RecipeCellFactory.stringify(r).equalsIgnoreCase(input)).findFirst().orElse(null);
		if (recipe != null) {
			this.insertTextField.setText("");
			this.dependencyTree.addDependency(new Dependency(recipe.getId()));
		}
	}

	@FXML
	void showHistory() {
		System.out.println("MainController.showHistory()");
	}

}
