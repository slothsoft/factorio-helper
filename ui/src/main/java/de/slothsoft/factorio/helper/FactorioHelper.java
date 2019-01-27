package de.slothsoft.factorio.helper;

import java.io.IOException;
import java.util.List;

import de.slothsoft.factorio.helper.io.FactorioReader;
import de.slothsoft.factorio.helper.io.RecipeReader;
import de.slothsoft.factorio.helper.io.ServiceBuddy;

public class FactorioHelper {

	public static void main(String[] args) throws IOException {
		final FactorioReader factorioReader = ServiceBuddy.getService(FactorioReader.class);
		final RecipeReader recipeReader = ServiceBuddy.getService(RecipeReader.class);

		final List<Recipe> result = recipeReader.readRecipes(factorioReader.getAllRecipeFileStreams());
		result.forEach(System.out::println);
		System.out.println();
		System.out.println(result.size() + " recipes found. ");
	}
}
