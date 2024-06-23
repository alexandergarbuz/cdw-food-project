package com.food.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.food.model.InventoryEntry;
import com.food.model.MenuItem;

public class Menu {

	public static final int ZERO_COST = 0;

	private Inventory inventory;
	private List<MenuItem> menuItems = new ArrayList<MenuItem>();

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public void reloadMenuItem(final MenuItem menuItem) {
		boolean allIngridientsAvailable = true;
		double cost = ZERO_COST;
		Map<InventoryEntry, Integer> ingridients = menuItem.getIngredients();
		Iterator<InventoryEntry> it = ingridients.keySet().iterator();
		while (it.hasNext() && allIngridientsAvailable) {
			InventoryEntry nextIngridient = it.next();
			int requiredQuantity = ingridients.get(nextIngridient).intValue();
			allIngridientsAvailable = inventory.isInStock(nextIngridient,
					requiredQuantity);
			cost = cost + nextIngridient.getCost() * requiredQuantity;
		}
		if (!allIngridientsAvailable) {
			cost = ZERO_COST;
		}
		menuItem.setAvailable(allIngridientsAvailable);
		menuItem.setCost(cost);
	}

	public boolean isMenuItemAvailable(final MenuItem menuItem) {
		reloadMenuItem(menuItem);
		return menuItem.isAvailable();
	}
	public void reloadMenu() {
		Iterator<MenuItem> it = this.menuItems.iterator();
		while(it.hasNext()){
			MenuItem nextMenuItem = it.next();
			reloadMenuItem(nextMenuItem);
		}
	}
	
	public MenuItem findItemById (final int id) {
		Iterator<MenuItem> items = getMenuItems().iterator();
		MenuItem item = null;
		boolean found = false;
		while(items.hasNext() && !found) {
			MenuItem nextItem = items.next();
			if(nextItem.getId().intValue() == id) {
				found = true;
				item = nextItem;
			}
		}
		return item;
	}
	
}
