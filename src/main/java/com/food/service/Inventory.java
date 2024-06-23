package com.food.service;

import java.util.HashMap;
import java.util.Map;

import com.food.model.InventoryEntry;

public class Inventory {
	
	public final static int ZERO_INVENTORY_COUNT = 0;
	
	private Map<InventoryEntry, Integer> inventory = new HashMap<InventoryEntry, Integer>();

	public Map<InventoryEntry, Integer> getInventory() {
		return inventory;
	}

	public void setInventory(Map<InventoryEntry, Integer> inventory) {
		this.inventory = inventory;
	}
	
	public boolean isInStock(final InventoryEntry inventoryEntry, int requiredQuantity) {
		boolean isInStock = false;
		if(inventory.containsKey(inventoryEntry)) {
			int availableQantity  = findInventoryCount(inventoryEntry);
			isInStock = (availableQantity >= requiredQuantity);
		}
		return isInStock;
	}
	public  void decreaseInventory(final InventoryEntry inventoryEntry, int decreaseBy) {
		int currentQuanity = findInventoryCount(inventoryEntry);
		int newQuantitty = currentQuanity - decreaseBy;
		if(newQuantitty < ZERO_INVENTORY_COUNT) {
			newQuantitty = ZERO_INVENTORY_COUNT;
		}
		inventory.put(inventoryEntry, new Integer(newQuantitty));
	}
	public int findInventoryCount(final InventoryEntry entryToFind) {
		int quantity = 0;
		if(inventory.containsKey(entryToFind)) {
			Integer currentQuantity = inventory.get(entryToFind);
			if(currentQuantity != null) {
				quantity = currentQuantity.intValue();
				
			}
		}
		return quantity;
	}
	/**
	 * Increases the inventory for specific inventory type by new quantity.
	 * @param inventoryEntry the type of the product for which inventory needs to be increased.
	 * @param quantity the new quantity for the inventory type.
	 */
	public void increaseInventory(final InventoryEntry inventoryEntry, int quantity) {
		int currentQuantity = findInventoryCount(inventoryEntry);
		int newQuantitty = currentQuantity + quantity;	
		inventory.put(inventoryEntry, new Integer(newQuantitty));
	}

}
