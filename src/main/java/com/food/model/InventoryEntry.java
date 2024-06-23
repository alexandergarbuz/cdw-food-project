package com.food.model;

public class InventoryEntry extends BaseBusinessObject {
	/**
	 * Unit Cost.
	 */
	private double cost;
	/**
	 * Name of the Ingredient.
	 */
	private String name;
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
