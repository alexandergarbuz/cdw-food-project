package com.food.util;

import java.util.Iterator;
import java.util.Map;

import com.food.model.InventoryEntry;
import com.food.service.Inventory;

/**
 * Prints the contents of the current inventory.
 *
 */
public final class InventoryPrinter extends BasePrinter {
	
	public static final String HEADER = "Inventory:";

	protected static final String printEntryWithQuantity(final InventoryEntry entryToPrint, int quantity) {
		StringBuffer output = new StringBuffer("");
		output
		.append(entryToPrint.getName())
		.append(COMA)
		.append(SPACER)
		.append(quantity)
		.append(LINE_SEPARATOR);
		return output.toString();
	}
	
	public static final StringBuffer printInventory(final Inventory inventoryToPrint) {
		StringBuffer output = new StringBuffer(HEADER).append(LINE_SEPARATOR);
		Map<InventoryEntry, Integer> entries =  inventoryToPrint.getInventory();
		Iterator<InventoryEntry> it = entries.keySet().iterator();
		while(it.hasNext()) {
			InventoryEntry nextEntry = it.next();
			int quantity = entries.get(nextEntry).intValue();
			output.append(InventoryPrinter.printEntryWithQuantity(nextEntry, quantity));
		}
		return output;
	}
}
