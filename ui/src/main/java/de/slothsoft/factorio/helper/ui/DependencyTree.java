package de.slothsoft.factorio.helper.ui;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import de.slothsoft.factorio.helper.Ingredient;
import de.slothsoft.factorio.helper.Recipe;
import de.slothsoft.factorio.helper.Result;
import de.slothsoft.factorio.helper.ui.impl.RecipeIdCellFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;

public class DependencyTree extends BorderPane {

	private static final NumberFormat AMOUNT_FORMAT;

	static {
		final DecimalFormat format = (DecimalFormat) NumberFormat.getInstance();
		format.setMaximumFractionDigits(1);
		AMOUNT_FORMAT = format;
	}

	final TreeTableView<Dependency> tree = new TreeTableView<>();
	private List<Recipe> availableRecipes = new ArrayList<>();

	public DependencyTree() {
		centerProperty().set(this.tree);
		createColumns();

		this.tree.setRoot(new TreeItem<>(new Dependency("")));
		this.tree.setShowRoot(false);
	}

	private void createColumns() {
		final List<TreeTableColumn<Dependency, ?>> columns = new ArrayList<>();

		final TreeTableColumn<Dependency, String> factorColumn = new TreeTableColumn<>(Messages.getString("Factor"));
		factorColumn.setCellValueFactory(
				data -> new ReadOnlyObjectWrapper<>(AMOUNT_FORMAT.format(data.getValue().getValue().getFactor())));
		factorColumn.setCellFactory(new RecipeIdCellFactory<>());
		factorColumn.setPrefWidth(100.0);
		columns.add(factorColumn);

		final TreeTableColumn<Dependency, String> idColumn = new TreeTableColumn<>(Messages.getString("ID"));
		idColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getValue().getRecipeId()));
		idColumn.setCellFactory(new RecipeIdCellFactory<>());
		idColumn.setPrefWidth(200.0);
		columns.add(idColumn);

		this.tree.getColumns().setAll(columns);
	}

	public void addDependency(Dependency dependency) {
		final TreeItem<Dependency> treeItem = new TreeItem<>(dependency);
		treeItem.expandedProperty().set(true);
		this.tree.getRoot().getChildren().add(treeItem);
		calculateDependencies(treeItem);
	}

	private void calculateDependencies(TreeItem<Dependency> treeItem) {
		final Recipe availableRecipe = getAvailableRecipe(treeItem.getValue().getRecipeId());
		if (availableRecipe != null) {
			final double factor = treeItem.getValue().getFactor();
			for (final Ingredient ingredient : availableRecipe.getIngredients()) {
				final Result ingredientRecipeResult = getAvailableRecipeResult(ingredient.getId());

				if (ingredientRecipeResult == null) {
					continue;
				}

				final TreeItem<Dependency> ingredientItem = new TreeItem<>(new Dependency(ingredient.getId())
						.factor(factor * ingredient.getAmount() / ingredientRecipeResult.getAmount()));
				ingredientItem.expandedProperty().set(true);
				treeItem.getChildren().add(ingredientItem);
				calculateDependencies(ingredientItem);
			}
		}
	}

	Result getAvailableRecipeResult(String recipeId) {
		for (final Recipe recipe : this.availableRecipes) {
			for (final Result result : recipe.getResults()) {
				if (result.getId().equalsIgnoreCase(recipeId)) {
					return result;
				}
			}
		}
		return null;
	}

	Recipe getAvailableRecipe(String recipeId) {
		for (final Recipe recipe : this.availableRecipes) {
			for (final Result result : recipe.getResults()) {
				if (result.getId().equalsIgnoreCase(recipeId)) {
					return recipe;
				}
			}
		}
		return null;
	}

	public List<Recipe> getAvailableRecipes() {
		return this.availableRecipes;
	}

	public DependencyTree availableRecipes(List<Recipe> newAvailableRecipes) {
		setAvailableRecipes(newAvailableRecipes);
		return this;
	}

	public void setAvailableRecipes(List<Recipe> availableRecipes) {
		this.availableRecipes = availableRecipes;
	}

}