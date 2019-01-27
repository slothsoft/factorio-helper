package de.slothsoft.factorio.helper.pojo;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Recipe {

	private String id;
	private List<Ingredient> ingredients = Collections.emptyList();
	private List<Result> results = Collections.emptyList();

	public Recipe(String id) {
		this.id = Objects.requireNonNull(id);
	}

	public String getId() {
		return this.id;
	}

	public Recipe id(String newId) {
		setId(newId);
		return this;
	}

	public void setId(String id) {
		this.id = Objects.requireNonNull(id);
	}

	public List<Ingredient> getIngredients() {
		return this.ingredients;
	}

	public Recipe ingredients(List<Ingredient> newIngredients) {
		setIngredients(newIngredients);
		return this;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = Objects.requireNonNull(ingredients);
	}

	public List<Result> getResults() {
		return this.results;
	}

	public Recipe results(List<Result> newResults) {
		setResults(newResults);
		return this;
	}

	public void setResults(List<Result> results) {
		this.results = Objects.requireNonNull(results);
	}

	@Override
	public String toString() {
		return "Recipe [id=" + this.id + ", ingredients=" + this.ingredients.size() + ", results=" + this.results.size()
				+ "]";
	}

}
