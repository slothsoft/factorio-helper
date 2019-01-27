package de.slothsoft.factorio.helper.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.factorio.helper.Ingredient;
import de.slothsoft.factorio.helper.Recipe;
import de.slothsoft.factorio.helper.Result;

public class LuaRecipeReaderTest {

	private final LuaRecipeReader recipeReader = new LuaRecipeReader();

	@Test
	public void testReadString() throws IOException {
		try (final InputStream input = getClass().getResourceAsStream(RecipeFiles.FILE_AMMO)) {
			final List<Recipe> recipes = this.recipeReader.readRecipeString(//
					"data:extend(\n" + //
							"{\n" + //
							"  {\n" + //
							"    type = \"recipe\",\n" + //
							"    name = \"steel-plate\",\n" + //
							"    category = \"smelting\",\n" + //
							"    normal =\n" + //
							"    {\n" + //
							"      enabled = false,\n" + //
							"      energy_required = 17.5,\n" + //
							"      ingredients = {{\"iron-plate\", 5}},\n" + //
							"      result = \"steel-plate\"\n" + //
							"    },\n" + //
							"    expensive =\n" + //
							"    {\n" + //
							"      enabled = false,\n" + //
							"      energy_required = 35,\n" + //
							"      ingredients = {{\"iron-plate\", 10}},\n" + //
							"      result = \"steel-plate\"\n" + //
							"    }\n" + //
							"  }\n" + //
							"}\n" + //
							")\n" + //
							"");//

			Assert.assertNotNull(recipes);
			Assert.assertEquals(1, recipes.size());

			final Recipe recipe = recipes.get(0);
			Assert.assertEquals("steel-plate", recipe.getId());
			Assert.assertNotNull(recipe.getIngredients());
			Assert.assertEquals(1, recipe.getIngredients().size());
			Assert.assertEquals(1, recipe.getResults().size());

			final Ingredient ingredient = recipe.getIngredients().get(0);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("iron-plate", ingredient.getId());
			Assert.assertEquals(5, ingredient.getAmount());

			final Result result = recipe.getResults().get(0);
			Assert.assertNotNull(result);
			Assert.assertEquals("steel-plate", result.getId());
			Assert.assertEquals(1, result.getAmount());
		}
	}

	@Test
	public void testReadAmmo() throws IOException {
		try (final InputStream input = getClass().getResourceAsStream(RecipeFiles.FILE_AMMO)) {
			final List<Recipe> recipes = this.recipeReader.readRecipe(input);

			Assert.assertNotNull(recipes);
			Assert.assertEquals(14, recipes.size());

			final Recipe recipe = recipes.get(7);
			Assert.assertEquals("piercing-rounds-magazine", recipe.getId());
			Assert.assertNotNull(recipe.getIngredients());
			Assert.assertEquals(3, recipe.getIngredients().size());
			Assert.assertEquals(1, recipe.getResults().size());

			Ingredient ingredient = recipe.getIngredients().get(0);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("firearm-magazine", ingredient.getId());
			Assert.assertEquals(1, ingredient.getAmount());

			ingredient = recipe.getIngredients().get(1);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("steel-plate", ingredient.getId());
			Assert.assertEquals(1, ingredient.getAmount());

			ingredient = recipe.getIngredients().get(2);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("copper-plate", ingredient.getId());
			Assert.assertEquals(5, ingredient.getAmount());

			final Result result = recipe.getResults().get(0);
			Assert.assertNotNull(result);
			Assert.assertEquals("piercing-rounds-magazine", result.getId());
			Assert.assertEquals(1, result.getAmount());
		}
	}

	@Test
	public void testReadCapsule() throws IOException {
		try (final InputStream input = getClass().getResourceAsStream(RecipeFiles.FILE_CAPSULE)) {
			final List<Recipe> recipes = this.recipeReader.readRecipe(input);

			Assert.assertNotNull(recipes);
			Assert.assertEquals(10, recipes.size());

			final Recipe recipe = recipes.get(8);
			Assert.assertEquals("poison-capsule", recipe.getId());
			Assert.assertNotNull(recipe.getIngredients());
			Assert.assertEquals(3, recipe.getIngredients().size());
			Assert.assertEquals(1, recipe.getResults().size());

			Ingredient ingredient = recipe.getIngredients().get(0);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("steel-plate", ingredient.getId());
			Assert.assertEquals(3, ingredient.getAmount());

			ingredient = recipe.getIngredients().get(1);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("electronic-circuit", ingredient.getId());
			Assert.assertEquals(3, ingredient.getAmount());

			ingredient = recipe.getIngredients().get(2);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("coal", ingredient.getId());
			Assert.assertEquals(10, ingredient.getAmount());

			final Result result = recipe.getResults().get(0);
			Assert.assertNotNull(result);
			Assert.assertEquals("poison-capsule", result.getId());
			Assert.assertEquals(1, result.getAmount());
		}
	}

	@Test
	public void testReadEquipment() throws IOException {
		try (final InputStream input = getClass().getResourceAsStream(RecipeFiles.FILE_EQUIPMENT)) {
			final List<Recipe> recipes = this.recipeReader.readRecipe(input);

			Assert.assertNotNull(recipes);
			Assert.assertEquals(12, recipes.size());

			final Recipe recipe = recipes.get(7);
			Assert.assertEquals("night-vision-equipment", recipe.getId());
			Assert.assertNotNull(recipe.getIngredients());
			Assert.assertEquals(2, recipe.getIngredients().size());
			Assert.assertEquals(1, recipe.getResults().size());

			Ingredient ingredient = recipe.getIngredients().get(0);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("advanced-circuit", ingredient.getId());
			Assert.assertEquals(5, ingredient.getAmount());

			ingredient = recipe.getIngredients().get(1);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("steel-plate", ingredient.getId());
			Assert.assertEquals(10, ingredient.getAmount());

			final Result result = recipe.getResults().get(0);
			Assert.assertNotNull(result);
			Assert.assertEquals("night-vision-equipment", result.getId());
			Assert.assertEquals(1, result.getAmount());
		}
	}

	@Test
	public void testReadFluid() throws IOException {
		try (final InputStream input = getClass().getResourceAsStream(RecipeFiles.FILE_FLUIDS)) {
			final List<Recipe> recipes = this.recipeReader.readRecipe(input);

			Assert.assertNotNull(recipes);
			Assert.assertEquals(13, recipes.size());

			final Recipe recipe = recipes.get(1);
			Assert.assertEquals("basic-oil-processing", recipe.getId());
			Assert.assertNotNull(recipe.getIngredients());
			Assert.assertEquals(1, recipe.getIngredients().size());
			Assert.assertEquals(3, recipe.getResults().size());

			final Ingredient ingredient = recipe.getIngredients().get(0);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("crude-oil", ingredient.getId());
			Assert.assertEquals(100, ingredient.getAmount());

			Result result = recipe.getResults().get(0);
			Assert.assertNotNull(result);
			Assert.assertEquals("heavy-oil", result.getId());
			Assert.assertEquals(30, result.getAmount());

			result = recipe.getResults().get(1);
			Assert.assertNotNull(result);
			Assert.assertEquals("light-oil", result.getId());
			Assert.assertEquals(30, result.getAmount());

			result = recipe.getResults().get(2);
			Assert.assertNotNull(result);
			Assert.assertEquals("petroleum-gas", result.getId());
			Assert.assertEquals(40, result.getAmount());
		}
	}

	@Test
	public void testReadFurnace() throws IOException {
		try (final InputStream input = getClass().getResourceAsStream(RecipeFiles.FILE_FURNACE)) {
			final List<Recipe> recipes = this.recipeReader.readRecipe(input);

			Assert.assertNotNull(recipes);
			Assert.assertEquals(1, recipes.size());

			final Recipe recipe = recipes.get(0);
			Assert.assertEquals("steel-plate", recipe.getId());
			Assert.assertNotNull(recipe.getIngredients());
			Assert.assertEquals(1, recipe.getIngredients().size());
			Assert.assertEquals(1, recipe.getResults().size());

			final Ingredient ingredient = recipe.getIngredients().get(0);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("iron-plate", ingredient.getId());
			Assert.assertEquals(5, ingredient.getAmount());

			final Result result = recipe.getResults().get(0);
			Assert.assertNotNull(result);
			Assert.assertEquals("steel-plate", result.getId());
			Assert.assertEquals(1, result.getAmount());
		}
	}

	@Test
	public void testReadInserter() throws IOException {
		try (final InputStream input = getClass().getResourceAsStream(RecipeFiles.FILE_INSERTER)) {
			final List<Recipe> recipes = this.recipeReader.readRecipe(input);

			Assert.assertNotNull(recipes);
			Assert.assertEquals(5, recipes.size());

			final Recipe recipe = recipes.get(2);
			Assert.assertEquals("long-handed-inserter", recipe.getId());
			Assert.assertNotNull(recipe.getIngredients());
			Assert.assertEquals(3, recipe.getIngredients().size());
			Assert.assertEquals(1, recipe.getResults().size());

			Ingredient ingredient = recipe.getIngredients().get(0);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("iron-gear-wheel", ingredient.getId());
			Assert.assertEquals(1, ingredient.getAmount());

			ingredient = recipe.getIngredients().get(1);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("iron-plate", ingredient.getId());
			Assert.assertEquals(1, ingredient.getAmount());

			ingredient = recipe.getIngredients().get(2);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("inserter", ingredient.getId());
			Assert.assertEquals(1, ingredient.getAmount());

			final Result result = recipe.getResults().get(0);
			Assert.assertNotNull(result);
			Assert.assertEquals("long-handed-inserter", result.getId());
			Assert.assertEquals(1, result.getAmount());
		}
	}

	@Test
	public void testReadModule() throws IOException {
		try (final InputStream input = getClass().getResourceAsStream(RecipeFiles.FILE_MODULE)) {
			final List<Recipe> recipes = this.recipeReader.readRecipe(input);

			Assert.assertNotNull(recipes);
			Assert.assertEquals(9, recipes.size());

			final Recipe recipe = recipes.get(6);
			Assert.assertEquals("speed-module", recipe.getId());
			Assert.assertNotNull(recipe.getIngredients());
			Assert.assertEquals(2, recipe.getIngredients().size());
			Assert.assertEquals(1, recipe.getResults().size());

			Ingredient ingredient = recipe.getIngredients().get(0);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("advanced-circuit", ingredient.getId());
			Assert.assertEquals(5, ingredient.getAmount());

			ingredient = recipe.getIngredients().get(1);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("electronic-circuit", ingredient.getId());
			Assert.assertEquals(5, ingredient.getAmount());

			final Result result = recipe.getResults().get(0);
			Assert.assertNotNull(result);
			Assert.assertEquals("speed-module", result.getId());
			Assert.assertEquals(1, result.getAmount());
		}
	}

	@Test
	public void testReadRecipe() throws IOException {
		try (final InputStream input = getClass().getResourceAsStream(RecipeFiles.FILE_RECIPE)) {
			final List<Recipe> recipes = this.recipeReader.readRecipe(input);

			Assert.assertNotNull(recipes);
			Assert.assertEquals(104, recipes.size());

			final Recipe recipe = recipes.get(62);
			Assert.assertEquals("player-port", recipe.getId());
			Assert.assertNotNull(recipe.getIngredients());
			Assert.assertEquals(3, recipe.getIngredients().size());
			Assert.assertEquals(1, recipe.getResults().size());

			Ingredient ingredient = recipe.getIngredients().get(0);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("electronic-circuit", ingredient.getId());
			Assert.assertEquals(10, ingredient.getAmount());

			ingredient = recipe.getIngredients().get(1);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("iron-gear-wheel", ingredient.getId());
			Assert.assertEquals(5, ingredient.getAmount());

			ingredient = recipe.getIngredients().get(2);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("iron-plate", ingredient.getId());
			Assert.assertEquals(1, ingredient.getAmount());

			final Result result = recipe.getResults().get(0);
			Assert.assertNotNull(result);
			Assert.assertEquals("player-port", result.getId());
			Assert.assertEquals(1, result.getAmount());
		}
	}

	@Test
	public void testReadTurret() throws IOException {
		try (final InputStream input = getClass().getResourceAsStream(RecipeFiles.FILE_TURRET)) {
			final List<Recipe> recipes = this.recipeReader.readRecipe(input);

			Assert.assertNotNull(recipes);
			Assert.assertEquals(3, recipes.size());

			final Recipe recipe = recipes.get(2);
			Assert.assertEquals("laser-turret", recipe.getId());
			Assert.assertNotNull(recipe.getIngredients());
			Assert.assertEquals(3, recipe.getIngredients().size());
			Assert.assertEquals(1, recipe.getResults().size());

			Ingredient ingredient = recipe.getIngredients().get(0);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("steel-plate", ingredient.getId());
			Assert.assertEquals(20, ingredient.getAmount());

			ingredient = recipe.getIngredients().get(1);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("electronic-circuit", ingredient.getId());
			Assert.assertEquals(20, ingredient.getAmount());

			ingredient = recipe.getIngredients().get(2);
			Assert.assertNotNull(ingredient);
			Assert.assertEquals("battery", ingredient.getId());
			Assert.assertEquals(12, ingredient.getAmount());

			final Result result = recipe.getResults().get(0);
			Assert.assertNotNull(result);
			Assert.assertEquals("laser-turret", result.getId());
			Assert.assertEquals(1, result.getAmount());
		}
	}

	@Test
	public void testReadAll() throws IOException {
		Assert.assertEquals("Test folder has more files than there are tests!",
				new File("src/main/resources/recipes/").list().length, RecipeFiles.FILES.length);

		for (final String file : RecipeFiles.FILES) {
			try (final InputStream input = getClass().getResourceAsStream(file)) {
				final List<Recipe> recipes = this.recipeReader.readRecipe(input);

				Assert.assertNotNull(recipes);
				Assert.assertTrue(recipes.size() > 0);
			}
		}
	}
}
