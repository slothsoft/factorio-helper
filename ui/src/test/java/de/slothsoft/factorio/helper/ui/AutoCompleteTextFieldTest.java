package de.slothsoft.factorio.helper.ui;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javafx.embed.swing.JFXPanel;

public class AutoCompleteTextFieldTest {

	@BeforeClass
	public static void setUpClass() {
		new JFXPanel();
	}

	private final AutoCompleteTextField recipesTable = new AutoCompleteTextField();

	@Test
	public void testShowPopupBAndB2() throws Exception {
		this.recipesTable.setProposals(Arrays.asList("A", "A1", "B", "B2", "C"));

		this.recipesTable.textProperty().set("B");
		Assert.assertEquals(2, this.recipesTable.popup.getItems().size());
	}

	@Test
	public void testShowPopupC() throws Exception {
		this.recipesTable.setProposals(Arrays.asList("A", "A1", "B", "B2", "C"));

		this.recipesTable.textProperty().set("C");
		Assert.assertEquals(1, this.recipesTable.popup.getItems().size());
	}

	@Test
	public void testShowPopupNothing() throws Exception {
		this.recipesTable.setProposals(Arrays.asList("A", "A1", "B", "B2", "C"));

		this.recipesTable.textProperty().set("D");
		Assert.assertEquals(0, this.recipesTable.popup.getItems().size());
	}

}
