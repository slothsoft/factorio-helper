package de.slothsoft.factorio.helper.ui.impl;

import java.text.NumberFormat;

import de.slothsoft.factorio.helper.Result;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ResultCellFactory<T> implements Callback<TableColumn<T, Result>, TableCell<T, Result>> {

	private static final NumberFormat INTEGER_FORMAT = NumberFormat.getIntegerInstance();

	public static String stringify(Result result) {
		return stringify(result.getId(), result.getAmount());
	}

	public static String stringify(String resultId, int amount) {
		final StringBuilder sb = new StringBuilder();
		sb.append(RecipeCellFactory.convertIdToString(resultId));
		if (amount != 1) {
			// we don't need the amount if it's one
			sb.append(" (").append(INTEGER_FORMAT.format(amount)).append(')');
		}
		return sb.toString();
	}

	@Override
	public TableCell<T, Result> call(TableColumn<T, Result> param) {
		return new TableCell<T, Result>() {
			@Override
			protected void updateItem(Result item, boolean empty) {
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
