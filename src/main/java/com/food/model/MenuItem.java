package com.food.model;

import java.util.HashMap;
import java.util.Map;
/**
 * Awesome Comment.
 * 
 * @author ang012
 *
 */
public class MenuItem extends BaseBusinessObject {
	
	private Integer id;
	private double cost;
	private String description;
	private boolean available = false;
	private Map<InventoryEntry, Integer> ingredients = new HashMap <InventoryEntry, Integer>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public Map<InventoryEntry, Integer> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Map<InventoryEntry, Integer> ingredients) {
		this.ingredients = ingredients;
	}
	
}
