package de.slothsoft.factorio.helper.ui.impl;

import javafx.geometry.Pos;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

public class RecipeIdCellFactory<T> implements Callback<TreeTableColumn<T, String>, TreeTableCell<T, String>> {

	@Override
	public TreeTableCell<T, String> call(TreeTableColumn<T, String> param) {
		return new TreeTableCell<T, String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				setAlignment(Pos.BASELINE_RIGHT);

				if (item == null || empty) {
					setText(null);
				} else {
					setText(RecipeCellFactory.convertIdToString(item));
				}
			}
		};
	}

}
