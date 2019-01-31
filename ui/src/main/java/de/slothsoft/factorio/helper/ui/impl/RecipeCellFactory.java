package de.slothsoft.factorio.helper.ui.impl;

import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import de.slothsoft.factorio.helper.Recipe;
import de.slothsoft.factorio.helper.io.FactorioReader;
import de.slothsoft.factorio.helper.io.ServiceBuddy;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class RecipeCellFactory<T> implements Callback<TableColumn<T, Recipe>, TableCell<T, Recipe>> {

	private static ResourceBundle recipeI18n;

	public static String stringify(Recipe recipe) {
		return convertIdToString(recipe.getId());
	}

	static String convertIdToString(String id) {
		try {
			return getRecipeI18n().getString(id);
		} catch (final MissingResourceException e) {
			return id;
		}
	}

	public static void setRecipeI18n(ResourceBundle recipeI18n) {
		RecipeCellFactory.recipeI18n = recipeI18n;
	}

	public static ResourceBundle getRecipeI18n() {
		if (RecipeCellFactory.recipeI18n == null) {
			try {
				RecipeCellFactory.recipeI18n = ServiceBuddy.getService(FactorioReader.class).createRecipeLocalization();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		return RecipeCellFactory.recipeI18n;
	}

	@Override
	public TableCell<T, Recipe> call(TableColumn<T, Recipe> param) {
		return new TableCell<T, Recipe>() {
			@Override
			protected void updateItem(Recipe item, boolean empty) {
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
