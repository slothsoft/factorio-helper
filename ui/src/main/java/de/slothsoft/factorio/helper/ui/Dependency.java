package de.slothsoft.factorio.helper.ui;

import java.util.Objects;

public class Dependency {

	private double factor = 1.0;
	private String recipeId;

	public Dependency(String recipeId) {
		this.recipeId = Objects.requireNonNull(recipeId);
	}

	public double getFactor() {
		return this.factor;
	}

	public Dependency factor(double newFactor) {
		setFactor(newFactor);
		return this;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}

	public String getRecipeId() {
		return this.recipeId;
	}

	public Dependency recipeId(String newRecipeId) {
		setRecipeId(newRecipeId);
		return this;
	}

	public void setRecipeId(String recipeId) {
		this.recipeId = Objects.requireNonNull(recipeId);
	}

	@Override
	public int hashCode() {
		return 39 * Objects.hash(Double.valueOf(this.factor), this.recipeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Dependency that = (Dependency) obj;
		if (Double.doubleToLongBits(this.factor) != Double.doubleToLongBits(that.factor)) {
			return false;
		}
		if (!Objects.equals(this.recipeId, that.recipeId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Dependency [recipeId=" + this.recipeId + ", factor=" + this.factor + "]";
	}

}