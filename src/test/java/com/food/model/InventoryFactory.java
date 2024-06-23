package com.food.model;

/**
 * Utility for creating Inventory objects.
 *
 */
public final class InventoryFactory {

	public static final InventoryEntry createNewEntry(final String productName, double cost) {
		InventoryEntry entry = new InventoryEntry();
		entry.setCost(cost);
		entry.setName(productName);
		return entry;
	}
}
