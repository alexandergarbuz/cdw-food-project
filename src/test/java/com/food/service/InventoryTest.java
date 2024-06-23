package com.food.service;

import com.food.model.InventoryEntry;
import com.food.model.InventoryFactory;

import junit.framework.TestCase;

public class InventoryTest extends TestCase {
	
	private Inventory inventory;
	public void setUp() throws Exception {
		super.setUp();
		this.inventory = new Inventory();
	}
	
	public void tearDown() throws Exception {
		this.inventory = null;
		super.tearDown();
	}
	
	public void testIsInStock() throws Exception {
		int expectedCount = 10;
		InventoryEntry entry1 = InventoryFactory.createNewEntry("product1", 1);
		InventoryEntry entry2 = InventoryFactory.createNewEntry("product2", 2);
		InventoryEntry entry3 = InventoryFactory.createNewEntry("product3", 3);
		
		inventory.increaseInventory(entry3, expectedCount);
		inventory.increaseInventory(entry2, expectedCount);
		inventory.increaseInventory(entry1, expectedCount);
		
		boolean inStock = inventory.isInStock(entry1, expectedCount);
		
		assertTrue("Expected to find the product in the inventory with sufficient qantity",inStock);		
	}
	
	public void testIsInStockOutOfInventory() throws Exception {
		int expectedCount = 10;
		InventoryEntry entry1 = InventoryFactory.createNewEntry("product1", 1);
		InventoryEntry entry2 = InventoryFactory.createNewEntry("product2", 2);
		InventoryEntry entry3 = InventoryFactory.createNewEntry("product3", 3);
		
		inventory.increaseInventory(entry3, expectedCount);
		inventory.increaseInventory(entry2, expectedCount);
		inventory.increaseInventory(entry1, expectedCount);
		
		boolean inStock = inventory.isInStock(entry1, expectedCount + 1);
		
		assertFalse("Expected to find the product out of stock",inStock);		
	}
	public void testDecreaseInventory() throws Exception {
		int initialCount = 10;
		int decreaseByCount = 5;
		Integer expectedCount = new Integer(5);
		
		InventoryEntry entry1 = InventoryFactory.createNewEntry("product1", 10);
		InventoryEntry entry2 = InventoryFactory.createNewEntry("product2", 2);
		InventoryEntry entry3 = InventoryFactory.createNewEntry("product3", 16);
		
		inventory.increaseInventory(entry3, initialCount);
		inventory.increaseInventory(entry2, initialCount);
		inventory.increaseInventory(entry1, initialCount);
		
		
		inventory.decreaseInventory(entry1, decreaseByCount);
		
		Integer currentInventoryCount = inventory.getInventory().get(entry1);
		assertEquals(
				"Invalid product count in the inventory. Exepcted to find decreased inventory count", 
				expectedCount, 
				currentInventoryCount);
	}
	public void testDecreaseInventoryIntoNegative() throws Exception {
		int initialCount = 10;
		int decreaseByCount = 100;
		Integer expectedCount = new Integer(0);
		InventoryEntry entry1 = InventoryFactory.createNewEntry("product1", 10);
		InventoryEntry entry2 = InventoryFactory.createNewEntry("product2", 2);
		InventoryEntry entry3 = InventoryFactory.createNewEntry("product3", 16);
		
		inventory.increaseInventory(entry3, initialCount);
		inventory.increaseInventory(entry2, initialCount);
		inventory.increaseInventory(entry1, initialCount);
		
		
		inventory.decreaseInventory(entry1, decreaseByCount);
		
		Integer currentInventoryCount = inventory.getInventory().get(entry1);
		assertEquals("Invalid product count in the inventory. Exepcted to find 0", expectedCount, currentInventoryCount);
	}
	public void testIncreaseEmptyInventory() throws Exception {
		int newCount = 10;
		InventoryEntry entry1 = InventoryFactory.createNewEntry("product1", 10);
		InventoryEntry entry2 = InventoryFactory.createNewEntry("product2", 2);
		InventoryEntry entry3 = InventoryFactory.createNewEntry("product3", 16);
		
		inventory.increaseInventory(entry3, newCount);
		inventory.increaseInventory(entry2, newCount);
		inventory.increaseInventory(entry1, newCount);
		
		
		Integer currentInventoryCount = inventory.getInventory().get(entry1);
		assertEquals("Invalid product count in the inventory", new Integer(newCount), currentInventoryCount);
	}


}
