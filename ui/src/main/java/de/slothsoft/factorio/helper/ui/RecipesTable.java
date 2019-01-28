package de.slothsoft.factorio.helper.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.ToIntFunction;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import de.slothsoft.factorio.helper.Recipe;

public class RecipesTable extends JPanel {

	private static final long serialVersionUID = 5983220261999043174L;

	final JTable table = new JTable();
	final RecipesTableModel tableModel = new RecipesTableModel();

	public RecipesTable() {
		super(new BorderLayout());

		this.table.setPreferredScrollableViewportSize(new Dimension(1000, 600));
		this.table.setFillsViewportHeight(true);
		this.table.setModel(this.tableModel);
		this.table.setDefaultRenderer(Object.class, new FactorioTableRenderer());

		add(new JScrollPane(this.table), BorderLayout.CENTER);
	}

	public List<Recipe> getRecipies() {
		return this.tableModel.getRecipies();
	}

	public RecipesTable recipies(List<Recipe> newRecipies) {
		setRecipies(newRecipies);
		return this;
	}

	public void setRecipies(List<Recipe> recipies) {
		this.tableModel.setRecipies(recipies);
	}

	/*
	 * The table model for this table.
	 */

	static class RecipesTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -2750451202980979584L;

		private static final String[] STATIC_COLUMN_NAMES = {Messages.getString("ID")};
		private static final int COLUMN_ID = 0;

		private List<Recipe> recipies = Collections.emptyList();
		private int ingredientsCount = 0;

		@Override
		public int getColumnCount() {
			final int count = STATIC_COLUMN_NAMES.length;
			this.ingredientsCount = calculateMax(r -> r.getIngredients().size());
			final int resultsCount = calculateMax(r -> r.getResults().size());
			return count + this.ingredientsCount + resultsCount;
		}

		private int calculateMax(ToIntFunction<Recipe> intGetter) {
			int result = 0;
			for (final Recipe recipe : this.recipies) {
				result = Math.max(result, intGetter.applyAsInt(recipe));
			}
			return result;
		}

		@Override
		public String getColumnName(int column) {
			if (column < STATIC_COLUMN_NAMES.length) {
				return STATIC_COLUMN_NAMES[column];
			}
			if (column < STATIC_COLUMN_NAMES.length + this.ingredientsCount) {
				return Messages.getString("Ingredient") + ' ' + (column - STATIC_COLUMN_NAMES.length + 1);
			}
			return Messages.getString("Result") + ' '
					+ (column - STATIC_COLUMN_NAMES.length - this.ingredientsCount + 1);
		}

		@Override
		public int getRowCount() {
			return this.recipies.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			final Recipe row = this.recipies.get(rowIndex);
			switch (columnIndex) {
				case COLUMN_ID :
					return row;
				default :
					if (columnIndex < STATIC_COLUMN_NAMES.length + this.ingredientsCount) {
						final int index = columnIndex - STATIC_COLUMN_NAMES.length;
						if (index < row.getIngredients().size()) {
							return row.getIngredients().get(index);
						}
						return null;
					}
					final int index = columnIndex - STATIC_COLUMN_NAMES.length - this.ingredientsCount;
					if (index < row.getResults().size()) {
						return row.getResults().get(index);
					}
					return null;
			}
		}

		public List<Recipe> getRecipies() {
			return this.recipies;
		}

		public void setRecipies(List<Recipe> recipies) {
			this.recipies = Objects.requireNonNull(recipies);
			fireTableStructureChanged();
		}

	}

}