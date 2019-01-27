package de.slothsoft.factorio.helper.pojo;

import java.util.Objects;

public class Ingredient {

	private String id;
	private int amount;

	public Ingredient(String id) {
		this.id = Objects.requireNonNull(id);
	}

	public int getAmount() {
		return this.amount;
	}

	public Ingredient amount(int newAmount) {
		setAmount(newAmount);
		return this;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getId() {
		return this.id;
	}

	public Ingredient id(String newId) {
		setId(newId);
		return this;
	}

	public void setId(String id) {
		this.id = Objects.requireNonNull(id);
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + this.id + ", count=" + this.amount + "]";
	}

}
