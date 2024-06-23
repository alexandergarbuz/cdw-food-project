package com.food.service;

import com.food.model.MenuItem;
import com.spring.BaseSpringTest;

public class FoodDispenserTest extends BaseSpringTest {
	
	private Inventory inventory;
	private Menu menu;
	private FoodDispenser d;
	public static final int SANDWICH_ID = 4;
	
	public void setUp() throws Exception {
		super.setUp();
		this.inventory = (Inventory) getBean("inventory", "Cannot load inventory bean");
		this.menu = (Menu)getBean("menu", "Cannot load menu bean");
		d = new FoodDispenser();
		d.setInventory(inventory);
		d.setMenu(menu);
		
	}

	public void tearDown() throws Exception {
		this.inventory = null;
		this.menu = null;
		super.tearDown();
	}
	
	public void testPlaceOrderTest() throws Exception {
		int id = SANDWICH_ID;
		MenuItem menuItem = menu.findItemById(id);
		boolean orderPlaced = false;
		boolean isAvailable = false;
		/*
		 * Verify that sandwich is available before placing orders. 
		 */
		isAvailable = menu.isMenuItemAvailable(menuItem);
		assertTrue("Sandwich expected to be available before placing any orders", isAvailable);
		/*
		 * Place 3 orders to deplete the inventory.
		 */
		for (int i = 1; i <= 3; i++) {
			isAvailable = menu.isMenuItemAvailable(menuItem);
			assertTrue("Sandwich expected to be available", isAvailable);
			orderPlaced = d.placeOrder(id);
			assertTrue("Expect to be able to place order before running out of inventory", orderPlaced);
		}
		/*
		 * Now once the inventory if low expect to get item out of stock
		 */
		isAvailable = menu.isMenuItemAvailable(menuItem);
		assertFalse("Sandwich expected to be out of stock", isAvailable);
		orderPlaced = d.placeOrder(id);
		assertFalse("Expect to get out of stock order since inventory is depleted", orderPlaced);
	}
	
	public void testIsInRange() throws Exception {
		assertTrue(d.isInRange("1"));
		assertTrue(d.isInRange("2"));
		assertTrue(d.isInRange("6"));
		assertTrue(d.isInRange("7"));
		assertFalse(d.isInRange("0"));
		assertFalse(d.isInRange("8"));
		assertFalse(d.isInRange(" "));
		assertFalse(d.isInRange(""));
		assertFalse(d.isInRange("q"));
		assertFalse(d.isInRange("r"));
	}
}
