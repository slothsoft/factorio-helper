package de.slothsoft.factorio.helper.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.ToIntFunction;

import de.slothsoft.factorio.helper.Ingredient;
import de.slothsoft.factorio.helper.Recipe;
import de.slothsoft.factorio.helper.Result;
import de.slothsoft.factorio.helper.ui.impl.ElementValueFactory;
import de.slothsoft.factorio.helper.ui.impl.IngredientCellFactory;
import de.slothsoft.factorio.helper.ui.impl.RecipeCellFactory;
import de.slothsoft.factorio.helper.ui.impl.ResultCellFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class RecipesTable extends BorderPane {

	final TableView<Recipe> table = new TableView<>();

	public RecipesTable() {
		centerProperty().set(this.table);

		this.table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		this.table.getItems().addListener((ListChangeListener<Recipe>) e -> recreateColumns());
	}

	private void recreateColumns() {
		final List<TableColumn<Recipe, ?>> columns = new ArrayList<>();

		final TableColumn<Recipe, Recipe> idColumn = new TableColumn<>(Messages.getString("ID"));
		idColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue()));
		idColumn.setCellFactory(new RecipeCellFactory<>());
		idColumn.setPrefWidth(200.0);
		columns.add(idColumn);

		final int maxIngredientsCount = calculateMax(r -> r.getIngredients().size());
		for (int i = 0; i < maxIngredientsCount; i++) {
			final TableColumn<Recipe, Ingredient> ingredientColumn = new TableColumn<>(
					Messages.getString("Ingredient") + ' ' + (i + 1));
			ingredientColumn.setCellValueFactory(new ElementValueFactory<>(i, Recipe::getIngredients));
			ingredientColumn.setCellFactory(new IngredientCellFactory<>());
			ingredientColumn.setPrefWidth(80.0);
			columns.add(ingredientColumn);
		}

		final int maxResultsCount = calculateMax(r -> r.getResults().size());
		for (int i = 0; i < maxResultsCount; i++) {
			final TableColumn<Recipe, Result> resultColumn = new TableColumn<>(
					Messages.getString("Result") + ' ' + (i + 1));
			resultColumn.setCellValueFactory(new ElementValueFactory<>(i, Recipe::getResults));
			resultColumn.setCellFactory(new ResultCellFactory<>());
			resultColumn.setPrefWidth(80.0);
			columns.add(resultColumn);
		}

		this.table.getColumns().setAll(columns);
	}

	private int calculateMax(ToIntFunction<Recipe> intGetter) {
		int result = 0;
		for (final Recipe recipe : getRecipies()) {
			result = Math.max(result, intGetter.applyAsInt(recipe));
		}
		return result;
	}

	public List<Recipe> getRecipies() {
		return Collections.unmodifiableList(this.table.getItems());
	}

	public RecipesTable recipies(List<Recipe> newRecipies) {
		setRecipies(newRecipies);
		return this;
	}

	public void setRecipies(List<Recipe> recipies) {
		this.table.getItems().setAll(recipies);
	}

}