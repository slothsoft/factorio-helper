package de.slothsoft.factorio.helper.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.jse.JsePlatform;

import de.slothsoft.factorio.helper.pojo.Ingredient;
import de.slothsoft.factorio.helper.pojo.Recipe;
import de.slothsoft.factorio.helper.pojo.Result;

public class LuaRecipeReader implements RecipeReader {

	private static final String KEY_RECIPE = "recipe";
	private static final String KEY_NORMAL_COSTS = "normal";
	private static final String KEY_INGREDIENTS = "ingredients";
	private static final String KEY_NAME = "name";
	private static final String KEY_AMOUNT = "amount";
	private static final String KEY_RESULT = "result";
	private static final String KEY_RESULTS = "results";

	private final Globals globals = JsePlatform.standardGlobals();
	private String prefix;
	private final String suffix = "return data.raw";

	@Override
	public List<Recipe> readRecipe(InputStream input) throws IOException {
		return readRecipeString(readLines(input));
	}

	@Override
	public List<Recipe> readRecipeString(String input) throws IOException {
		if (this.prefix == null) {
			try (InputStream in = ServiceBuddy.getService(FactorioReader.class).createDataLoaderStream()) {
				this.prefix = readLines(in);
			}
		}
		final List<Recipe> result = new ArrayList<>();

		final LuaValue inputChunk = this.globals.load(this.prefix + "\n" + input + this.suffix);
		final LuaTable resultChunk = inputChunk.call().checktable().get(KEY_RECIPE).checktable();
		forEachKeyValue(resultChunk, (key, value) -> result.add(convertToRecipe(key, value)));

		result.sort(Comparator.comparing(Recipe::getId));

		return result;
	}

	private static String readLines(InputStream input) throws IOException {
		Objects.requireNonNull(input, "Input stream cannot be null!");
		try (final BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
			return reader.lines().collect(Collectors.joining("\n"));
		}
	}

	private static void forEachKeyValue(final LuaTable table, BiConsumer<LuaValue, LuaValue> doFunction) {
		LuaValue key = LuaValue.NIL;
		while (true) {
			final Varargs args = table.next(key);
			if ((key = args.arg1()).isnil()) {
				break;
			}
			final LuaValue value = args.arg(2);
			doFunction.accept(key, value);
		}
	}

	private static Recipe convertToRecipe(LuaValue key, LuaValue value) {
		// sometimes there are tables for "normal" and "expensive"... and sometimes not
		final LuaValue costsChunk = value.get(KEY_NORMAL_COSTS);
		final LuaTable costsTable = costsChunk.isnil() ? value.checktable() : costsChunk.checktable();

		final Recipe recipe = new Recipe(key.tojstring());
		recipe.ingredients(convertToIngredients(costsTable));
		recipe.results(convertToResults(costsTable));
		return recipe;
	}

	private static List<Ingredient> convertToIngredients(LuaTable costsTable) {
		final LuaTable ingredientsTable = costsTable.get(KEY_INGREDIENTS).checktable();

		final List<Ingredient> ingredients = new ArrayList<>();
		forEachKeyValue(ingredientsTable, (k, v) -> ingredients.add(convertToIngredient(v)));
		return ingredients;
	}

	private static Ingredient convertToIngredient(LuaValue value) {
		final LuaValue id = value.get(1);
		if (id.isnil()) {
			// the ingredient is a fluid. the format is approximately:
			// {type="fluid", name="lubricant", amount=10}
			final Ingredient ingredient = new Ingredient(value.get(KEY_NAME).tojstring());
			ingredient.setAmount(value.get(KEY_AMOUNT).toint());
			return ingredient;
		}
		// the ingredient is a standard thingy
		final Ingredient ingredient = new Ingredient(id.tojstring());
		ingredient.setAmount(value.get(2).toint());
		return ingredient;
	}

	private static List<Result> convertToResults(LuaTable costsTable) {
		final LuaValue singleResult = costsTable.get(KEY_RESULT);
		if (!singleResult.isnil()) return Arrays.asList(new Result(singleResult.tojstring()));

		final LuaTable resultTable = costsTable.get(KEY_RESULTS).checktable();
		final List<Result> results = new ArrayList<>();
		forEachKeyValue(resultTable, (k, v) -> results.add(convertToResult(v)));
		return results;
	}

	private static Result convertToResult(LuaValue value) {
		// the result is a fluid. the format is approximately:
		// {type="fluid", name="heavy-oil", amount=30}
		final Result result = new Result(value.get(KEY_NAME).tojstring());
		result.setAmount(value.get(KEY_AMOUNT).toint());
		return result;
	}

}
