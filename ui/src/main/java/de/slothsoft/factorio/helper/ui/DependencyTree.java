package de.slothsoft.factorio.helper.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import de.slothsoft.factorio.helper.Recipe;
import de.slothsoft.factorio.helper.ui.common.Accordion;

public class DependencyTree extends JPanel {

	private static final long serialVersionUID = 5983220261999043174L;

	final JTree detailsTree = new JTree();
	JComponent detailsComponent;

	final RecipesTable sumTable = new RecipesTable();
	JComponent sumComponent;

	public DependencyTree() {
		super(new BorderLayout());

		this.detailsComponent = new JScrollPane(this.detailsTree);
		add(this.detailsComponent, BorderLayout.CENTER);

		final Accordion accordion = new Accordion();
		accordion.addPage("Test, ", new JScrollPane(this.sumTable));

		add(accordion, BorderLayout.SOUTH);

		updateSum(new ArrayList<>());
	}

	void updateSum(List<Recipe> recipies) {
		this.sumTable.setRecipies(recipies);
//		final int height = (recipies.size() + 1) * this.sumTable.table.getRowHeight() + 10;
//		this.sumTable.getParent().setPreferredSize(new Dimension(1100, height));
//		doLayout();
	}

}