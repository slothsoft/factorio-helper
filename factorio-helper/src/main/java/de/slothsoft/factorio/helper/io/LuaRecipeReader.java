package de.slothsoft.factorio.helper.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

public class LuaRecipeReader implements RecipeReader {

	private static final String KEY_RECIPE = "recipe";
	private static final String KEY_NORMAL_COSTS = "normal";
	private static final String KEY_INGREDIENTS = "ingredients";

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
		final Recipe recipe = new Recipe(key.tojstring());

		final List<Ingredient> ingredients = new ArrayList<>();

		// sometimes there are tables for "normal" and "expensive"... and sometimes not
		final LuaValue costsChunk = value.get(KEY_NORMAL_COSTS);
		final LuaTable ingredientsChunk = costsChunk.isnil()
				? value.get(KEY_INGREDIENTS).checktable()
				: costsChunk.checktable().get(KEY_INGREDIENTS).checktable();
		forEachKeyValue(ingredientsChunk, (k, v) -> ingredients.add(convertToIngredient(v)));

		return recipe.ingredients(ingredients);
	}

	private static Ingredient convertToIngredient(LuaValue value) {
		final Ingredient ingredient = new Ingredient(value.get(1).tojstring());
		ingredient.setAmount(value.get(2).toint());
		return ingredient;
	}

}
