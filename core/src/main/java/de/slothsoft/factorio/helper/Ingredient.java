package de.slothsoft.factorio.helper;

import java.util.Objects;

public class Ingredient implements HasAmount {

	private String id;
	private int amount;

	public Ingredient(String id) {
		this.id = Objects.requireNonNull(id);
	}

	@Override
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

	@Override
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
