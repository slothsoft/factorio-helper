package de.slothsoft.factorio.helper.ui;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AutoCompleteTextFieldTest {

	@Rule
	public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
	private AutoCompleteTextField autoCompleteField;

	@Before
	public void setUp() {
		this.autoCompleteField = new AutoCompleteTextField();
	}

	@Test
	public void testShowPopupBAndB2() throws Exception {
		this.autoCompleteField.setProposals(Arrays.asList("A", "A1", "B", "B2", "C"));

		this.autoCompleteField.textProperty().set("B");
		Assert.assertEquals(2, this.autoCompleteField.popup.getItems().size());
	}

	@Test
	public void testShowPopupC() throws Exception {
		this.autoCompleteField.setProposals(Arrays.asList("A", "A1", "B", "B2", "C"));

		this.autoCompleteField.textProperty().set("C");
		Assert.assertEquals(1, this.autoCompleteField.popup.getItems().size());
	}

	@Test
	public void testShowPopupNothing() throws Exception {
		this.autoCompleteField.setProposals(Arrays.asList("A", "A1", "B", "B2", "C"));

		this.autoCompleteField.textProperty().set("D");
		Assert.assertEquals(0, this.autoCompleteField.popup.getItems().size());
	}

}
