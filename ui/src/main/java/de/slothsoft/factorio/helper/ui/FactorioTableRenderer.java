package de.slothsoft.factorio.helper.ui;

import java.awt.Component;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import de.slothsoft.factorio.helper.Ingredient;
import de.slothsoft.factorio.helper.Recipe;
import de.slothsoft.factorio.helper.Result;
import de.slothsoft.factorio.helper.io.FactorioReader;
import de.slothsoft.factorio.helper.io.ServiceBuddy;

public class FactorioTableRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -6917174496910977755L;

	private static final NumberFormat INTEGER_FORMAT = NumberFormat.getIntegerInstance();
	private static ResourceBundle recipeI18n;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		final JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
				column);
		if (value instanceof Ingredient) {
			setText(stringify((Ingredient) value));
		} else if (value instanceof Result) {
			setText(stringify((Result) value));
		} else if (value instanceof Recipe) {
			setText(stringify((Recipe) value));
		}
		return label;
	}

	public static String stringify(Ingredient ingredient) {
		final StringBuilder sb = new StringBuilder();
		sb.append(INTEGER_FORMAT.format(ingredient.getAmount()));
		sb.append("x ");
		sb.append(convertIdToString(ingredient.getId()));
		return sb.toString();
	}

	public static String stringify(Result result) {
		final StringBuilder sb = new StringBuilder();
		sb.append(convertIdToString(result.getId()));
		if (result.getAmount() != 1) {
			// we don't need the amount if it's one
			sb.append(" (").append(INTEGER_FORMAT.format(result.getAmount())).append(')');
		}
		return sb.toString();
	}

	public static String stringify(Recipe recipe) {
		return convertIdToString(recipe.getId());
	}

	private static String convertIdToString(String id) {
		try {
			return getRecipeI18n().getString(id);
		} catch (final MissingResourceException e) {
			return id;
		}
	}

	static void setRecipeI18n(ResourceBundle recipeI18n) {
		FactorioTableRenderer.recipeI18n = recipeI18n;
	}

	public static ResourceBundle getRecipeI18n() {
		if (FactorioTableRenderer.recipeI18n == null) {
			try {
				FactorioTableRenderer.recipeI18n = ServiceBuddy.getService(FactorioReader.class)
						.createRecipeLocalization();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		return FactorioTableRenderer.recipeI18n;
	}
}
