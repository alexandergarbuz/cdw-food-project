package com.spring;

import com.food.model.InventoryEntry;
import com.food.model.MenuItem;
import com.food.service.Inventory;
import com.food.service.Menu;
import com.food.util.InventoryPrinter;

public class SpringLoadTest extends BaseSpringTest {
	
	public static final int TOO_HIGH_INVENTORY_COUNT = 1000;
	public static final int INITIAL_INVENTORY_COUNT = 10;
	public static final String FRENCH_BREAD_BEAN_NAME = "FrenchBread";
	public static final String INVENTORY_BEAN_NAME = "inventory";
	public static final String INVALID_ITEM_ENTRY = "Invalid Item";
	public static final String FRENCH_BREAD_ENTRY = "French Bread";
	private InventoryEntry expectedEntry = null;

	protected void setUp() throws Exception {
		super.setUp();
		expectedEntry = new InventoryEntry();
		expectedEntry.setCost(1.75);
		expectedEntry.setName(FRENCH_BREAD_ENTRY);
	}
	protected void tearDown() throws Exception {
		ctx = null;
		super.tearDown();
	}
	
	protected Inventory loadInventory() {
		Inventory inventory = (Inventory)getBean(INVENTORY_BEAN_NAME, "Inventory bean cannot be NULL");
		return inventory;
	}
	
	public void testLoadSpring() throws Exception {
		InventoryEntry frenchBread = (InventoryEntry)getBean(FRENCH_BREAD_BEAN_NAME, "FRENCH_BREAD_BEAN cannot be NULL");
		assertEquals("Expected to find " + FRENCH_BREAD_ENTRY, FRENCH_BREAD_ENTRY, frenchBread.getName());
	}
	
	public void testLoadInventoryItemInStock() throws Exception {
		Inventory inventory = loadInventory();
		boolean inStock = inventory.isInStock(expectedEntry, 10);
		assertTrue("Expected to find item in stock", inStock);
	}

	public void testLoadInventoryItemOutOfStockQuantity() throws Exception {
		Inventory inventory = loadInventory();
		boolean inStock = inventory.isInStock(expectedEntry, TOO_HIGH_INVENTORY_COUNT);
		assertFalse("Do not expect to find this item in stock with this quantity", inStock);
	}
	
	public void testLoadInventoryItemOutOfStockInvalidItem() throws Exception {
		expectedEntry.setName(INVALID_ITEM_ENTRY);
		Inventory inventory = loadInventory();
		boolean inStock = inventory.isInStock(expectedEntry, INITIAL_INVENTORY_COUNT);
		assertFalse("Do not expect to find invalid item in stock", inStock);
	}
	
	public void testLoadCompleteInventory() throws Exception {
		Inventory inventory = loadInventory();
		StringBuffer output = InventoryPrinter.printInventory(inventory);
		System.out.print(output);
	}
	
	public void testLoadInventoryThenIncreaseAndDEcrease() throws Exception {
		Inventory inventory = loadInventory();
		/*
		 * Increase inventory level and it should be in stock.
		 */
		inventory.increaseInventory(expectedEntry, INITIAL_INVENTORY_COUNT);
		boolean isInStock = inventory.isInStock(expectedEntry, INITIAL_INVENTORY_COUNT);
		assertTrue("Product must be in stock after initial inventory increase", isInStock);

		/**
		 * Now we will decrease inventory level and it still must be in stock.
		 */
		inventory.decreaseInventory(expectedEntry, INITIAL_INVENTORY_COUNT);
		isInStock = inventory.isInStock(expectedEntry, INITIAL_INVENTORY_COUNT);
		assertTrue("Product must be in stock after initial inventory decrease", isInStock);
		/**
		 * Now we will decrease inventory level again and not must be out of stock.
		 */
		inventory.decreaseInventory(expectedEntry, INITIAL_INVENTORY_COUNT);
		isInStock = inventory.isInStock(expectedEntry, INITIAL_INVENTORY_COUNT);
		assertFalse("Product must be out of stock after second inventory decrease", isInStock);
		
		int currentQuantity = inventory.findInventoryCount(expectedEntry);
		assertEquals("Expected to have zero inventory", Inventory.ZERO_INVENTORY_COUNT,  currentQuantity);
		
	}
		
	public void testLoadMenu() throws Exception {
		Menu menu = (Menu) getBean("menu", "Expect to find configured Menu bean");
		MenuItem theCubano = (MenuItem) getBean("TheCubano", "Expected to find not NULL The Cubano bean");
		MenuItem irishRoadReuben = (MenuItem) getBean("IrishRoadReuben", "Expected to find not NULL Irish Road Reuben bean");
		MenuItem irishRoadReubenWithExtraMeat = (MenuItem) getBean("IrishRoadReubenWithExtraMeat", "Expected to find not NULL Irish Road Reuben With Extra Meat bean");
		MenuItem carolinaBBQPork = (MenuItem) getBean("CarolinaBBQPork", "Expected to find not NULL Carolina BBQ Pork bean");
		MenuItem pubBurger = (MenuItem) getBean("PubBurger", "Expected to find not NULL Pub Burger bean");
		MenuItem baconBurger = (MenuItem) getBean("BaconBurger", "Expected to find not NULL Bacon Burger bean");
		MenuItem theBlt = (MenuItem) getBean("TheBlt", "Expected to find not NULL The Blt bean");
		
		
		assertTrue(menu.isMenuItemAvailable(theCubano));
		assertTrue(menu.isMenuItemAvailable(irishRoadReuben));
		assertTrue(menu.isMenuItemAvailable(irishRoadReubenWithExtraMeat));
		assertTrue(menu.isMenuItemAvailable(carolinaBBQPork));
		assertTrue(menu.isMenuItemAvailable(pubBurger));
		assertTrue(menu.isMenuItemAvailable(baconBurger));
		assertTrue(menu.isMenuItemAvailable(theBlt));
		
	}
	
	public void testFindMenuItem() throws Exception {
		Menu menu = (Menu) getBean("menu", "Expect to find configured Menu bean");
		MenuItem theCubano = (MenuItem) getBean("TheCubano", "Expected to find not NULL The Cubano bean");
		MenuItem item1 = menu.findItemById(1);
		assertEquals(theCubano, item1);
	}
	public void testFindMenuItemWrongId() throws Exception {
		Menu menu = (Menu) getBean("menu", "Expect to find configured Menu bean");
		MenuItem item1 = menu.findItemById(100);
		assertNull(item1);
	}
}
