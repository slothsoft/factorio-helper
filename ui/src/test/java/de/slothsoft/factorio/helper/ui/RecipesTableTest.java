package de.slothsoft.factorio.helper.ui;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import de.slothsoft.factorio.helper.Ingredient;
import de.slothsoft.factorio.helper.Recipe;
import de.slothsoft.factorio.helper.Result;
import de.slothsoft.factorio.helper.io.IOUtils;

public class RecipesTableTest {

	@BeforeClass
	public static void setUpClass() throws IOException {
		FactorioTableRenderer.setRecipeI18n(IOUtils.createEmptyResourceBundle());
	}

	@Rule
	public final TestName testName = new TestName();
	private final RecipesTable recipesTable = new RecipesTable();

	@Test
	public void testSetRecipies() throws Exception {
		final Recipe recipe = new Recipe(this.testName.getMethodName());
		this.recipesTable.setRecipies(Arrays.asList(recipe));

		Assert.assertEquals(1, this.recipesTable.table.getColumnCount());
		Assert.assertEquals(1, this.recipesTable.table.getRowCount());
		Assert.assertEquals(recipe, this.recipesTable.table.getValueAt(0, 0));
	}

	@Test
	public void testSetRecipiesNull() throws Exception {
		this.recipesTable.setRecipies(Arrays.asList());

		Assert.assertEquals(1, this.recipesTable.table.getColumnCount());
		Assert.assertEquals(0, this.recipesTable.table.getRowCount());
	}

	@Test
	public void testSetRecipiesMultiple() throws Exception {
		final Recipe recipe1 = new Recipe(this.testName.getMethodName() + 1);
		final Recipe recipe2 = new Recipe(this.testName.getMethodName() + 2);
		final Recipe recipe3 = new Recipe(this.testName.getMethodName() + 3);
		this.recipesTable.setRecipies(Arrays.asList(recipe1, recipe2, recipe3));

		Assert.assertEquals(1, this.recipesTable.table.getColumnCount());
		Assert.assertEquals(3, this.recipesTable.table.getRowCount());
		Assert.assertEquals(recipe1, this.recipesTable.table.getValueAt(0, 0));
		Assert.assertEquals(recipe2, this.recipesTable.table.getValueAt(1, 0));
		Assert.assertEquals(recipe3, this.recipesTable.table.getValueAt(2, 0));
	}

	@Test
	public void testSetRecipiesMultipleWithResults() throws Exception {
		final String id = this.testName.getMethodName();
		final Recipe recipe1 = new Recipe(id + 1).results(Arrays.asList(new Result(id + "A").amount(1)));
		final Recipe recipe2 = new Recipe(id + 2)
				.results(Arrays.asList(new Result(id + "B").amount(1), new Result(id + "C").amount(2)));
		final Recipe recipe3 = new Recipe(id + 3).results(Arrays.asList(new Result(id + "D").amount(1),
				new Result(id + "E").amount(2), new Result(id + "F").amount(3)));
		this.recipesTable.setRecipies(Arrays.asList(recipe1, recipe2, recipe3));

		Assert.assertEquals(4, this.recipesTable.table.getColumnCount());
		Assert.assertEquals(3, this.recipesTable.table.getRowCount());

		Assert.assertEquals(recipe1, this.recipesTable.table.getValueAt(0, 0));
		Assert.assertEquals(recipe1.getResults().get(0), this.recipesTable.table.getValueAt(0, 1));
		Assert.assertEquals(null, this.recipesTable.table.getValueAt(0, 2));
		Assert.assertEquals(null, this.recipesTable.table.getValueAt(0, 3));

		Assert.assertEquals(recipe2, this.recipesTable.table.getValueAt(1, 0));
		Assert.assertEquals(recipe2.getResults().get(0), this.recipesTable.table.getValueAt(1, 1));
		Assert.assertEquals(recipe2.getResults().get(1), this.recipesTable.table.getValueAt(1, 2));
		Assert.assertEquals(null, this.recipesTable.table.getValueAt(1, 3));

		Assert.assertEquals(recipe3, this.recipesTable.table.getValueAt(2, 0));
		Assert.assertEquals(recipe3.getResults().get(0), this.recipesTable.table.getValueAt(2, 1));
		Assert.assertEquals(recipe3.getResults().get(1), this.recipesTable.table.getValueAt(2, 2));
		Assert.assertEquals(recipe3.getResults().get(2), this.recipesTable.table.getValueAt(2, 3));
	}

	@Test
	public void testSetRecipiesMultipleWithIngredients() throws Exception {
		final String id = this.testName.getMethodName();
		final Recipe recipe1 = new Recipe(id + 1)
				.ingredients(Arrays.asList(new Ingredient(id + "A").amount(1), new Ingredient(id + "B").amount(1)));
		final Recipe recipe2 = new Recipe(id + 2).ingredients(Arrays.asList(new Ingredient(id + "C").amount(2),
				new Ingredient(id + "D").amount(1), new Ingredient(id + "E").amount(2)));
		final Recipe recipe3 = new Recipe(id + 3).ingredients(Arrays.asList(new Ingredient(id + "F").amount(3)));
		this.recipesTable.setRecipies(Arrays.asList(recipe1, recipe2, recipe3));

		Assert.assertEquals(4, this.recipesTable.table.getColumnCount());
		Assert.assertEquals(3, this.recipesTable.table.getRowCount());

		Assert.assertEquals(recipe1, this.recipesTable.table.getValueAt(0, 0));
		Assert.assertEquals(recipe1.getIngredients().get(0), this.recipesTable.table.getValueAt(0, 1));
		Assert.assertEquals(recipe1.getIngredients().get(1), this.recipesTable.table.getValueAt(0, 2));
		Assert.assertEquals(null, this.recipesTable.table.getValueAt(0, 3));

		Assert.assertEquals(recipe2, this.recipesTable.table.getValueAt(1, 0));
		Assert.assertEquals(recipe2.getIngredients().get(0), this.recipesTable.table.getValueAt(1, 1));
		Assert.assertEquals(recipe2.getIngredients().get(1), this.recipesTable.table.getValueAt(1, 2));
		Assert.assertEquals(recipe2.getIngredients().get(2), this.recipesTable.table.getValueAt(1, 3));

		Assert.assertEquals(recipe3, this.recipesTable.table.getValueAt(2, 0));
		Assert.assertEquals(recipe3.getIngredients().get(0), this.recipesTable.table.getValueAt(2, 1));
		Assert.assertEquals(null, this.recipesTable.table.getValueAt(2, 2));
		Assert.assertEquals(null, this.recipesTable.table.getValueAt(2, 3));
	}

	@Test
	public void testSetRecipiesMultipleWithResultsAndIngredients() throws Exception {
		final String id = this.testName.getMethodName();
		final Recipe recipe1 = new Recipe(id + 1).results(Arrays.asList(new Result(id + "A").amount(1)))
				.ingredients(Arrays.asList(new Ingredient(id + "A").amount(1), new Ingredient(id + "B").amount(1)));
		final Recipe recipe2 = new Recipe(id + 2)
				.results(Arrays.asList(new Result(id + "B").amount(1), new Result(id + "C").amount(2)))
				.ingredients(Arrays.asList(new Ingredient(id + "C").amount(2), new Ingredient(id + "D").amount(1),
						new Ingredient(id + "E").amount(2)));
		final Recipe recipe3 = new Recipe(id + 3).results(Arrays.asList(new Result(id + "D").amount(1),
				new Result(id + "E").amount(2), new Result(id + "F").amount(3)))
				.ingredients(Arrays.asList(new Ingredient(id + "F").amount(3)));
		this.recipesTable.setRecipies(Arrays.asList(recipe1, recipe2, recipe3));
		this.recipesTable.setRecipies(Arrays.asList(recipe1, recipe2, recipe3));

		Assert.assertEquals(7, this.recipesTable.table.getColumnCount());
		Assert.assertEquals(3, this.recipesTable.table.getRowCount());

		Assert.assertEquals(recipe1, this.recipesTable.table.getValueAt(0, 0));
		Assert.assertEquals(recipe1.getIngredients().get(0), this.recipesTable.table.getValueAt(0, 1));
		Assert.assertEquals(recipe1.getIngredients().get(1), this.recipesTable.table.getValueAt(0, 2));
		Assert.assertEquals(null, this.recipesTable.table.getValueAt(0, 3));
		Assert.assertEquals(recipe1.getResults().get(0), this.recipesTable.table.getValueAt(0, 4));
		Assert.assertEquals(null, this.recipesTable.table.getValueAt(0, 5));
		Assert.assertEquals(null, this.recipesTable.table.getValueAt(0, 6));

		Assert.assertEquals(recipe2, this.recipesTable.table.getValueAt(1, 0));
		Assert.assertEquals(recipe2.getIngredients().get(0), this.recipesTable.table.getValueAt(1, 1));
		Assert.assertEquals(recipe2.getIngredients().get(1), this.recipesTable.table.getValueAt(1, 2));
		Assert.assertEquals(recipe2.getIngredients().get(2), this.recipesTable.table.getValueAt(1, 3));
		Assert.assertEquals(recipe2.getResults().get(0), this.recipesTable.table.getValueAt(1, 4));
		Assert.assertEquals(recipe2.getResults().get(1), this.recipesTable.table.getValueAt(1, 5));
		Assert.assertEquals(null, this.recipesTable.table.getValueAt(1, 6));

		Assert.assertEquals(recipe3, this.recipesTable.table.getValueAt(2, 0));
		Assert.assertEquals(recipe3.getIngredients().get(0), this.recipesTable.table.getValueAt(2, 1));
		Assert.assertEquals(null, this.recipesTable.table.getValueAt(2, 2));
		Assert.assertEquals(null, this.recipesTable.table.getValueAt(2, 3));
		Assert.assertEquals(recipe3.getResults().get(0), this.recipesTable.table.getValueAt(2, 4));
		Assert.assertEquals(recipe3.getResults().get(1), this.recipesTable.table.getValueAt(2, 5));
		Assert.assertEquals(recipe3.getResults().get(2), this.recipesTable.table.getValueAt(2, 6));
	}

}
