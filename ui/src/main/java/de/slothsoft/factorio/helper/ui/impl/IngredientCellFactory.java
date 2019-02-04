package de.slothsoft.factorio.helper.ui.impl;

import java.text.NumberFormat;

import de.slothsoft.factorio.helper.Ingredient;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class IngredientCellFactory<T> implements Callback<TableColumn<T, Ingredient>, TableCell<T, Ingredient>> {

	private static final NumberFormat INTEGER_FORMAT = NumberFormat.getIntegerInstance();

	public static String stringify(Ingredient ingredient) {
		return stringify(ingredient.getId(), ingredient.getAmount());
	}

	public static String stringify(String recipeId, int amount) {
		final StringBuilder sb = new StringBuilder();
		sb.append(INTEGER_FORMAT.format(amount));
		sb.append("x ");
		sb.append(RecipeCellFactory.convertIdToString(recipeId));
		return sb.toString();
	}

	@Override
	public TableCell<T, Ingredient> call(TableColumn<T, Ingredient> param) {
		return new TableCell<T, Ingredient>() {
			@Override
			protected void updateItem(Ingredient item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null || empty) {
					setText(null);
				} else {
					setText(stringify(item));
				}
			}
		};
	}

}
