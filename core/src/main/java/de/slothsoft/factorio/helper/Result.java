package de.slothsoft.factorio.helper;

import java.util.Objects;

public class Result implements HasAmount {

	private String id;
	private int amount = 1;

	public Result(String id) {
		this.id = Objects.requireNonNull(id);
	}

	@Override
	public int getAmount() {
		return this.amount;
	}

	public Result amount(int newAmount) {
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

	public Result id(String newId) {
		setId(newId);
		return this;
	}

	public void setId(String id) {
		this.id = Objects.requireNonNull(id);
	}

	@Override
	public String toString() {
		return "Result [id=" + this.id + ", count=" + this.amount + "]";
	}
}
