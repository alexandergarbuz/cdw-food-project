package com.food.util;

import com.food.service.Inventory;
import com.spring.BaseSpringTest;

public class InventoryPrinterTest extends BaseSpringTest  {
	public static final String EXPECTED_OUTPUT_FILE_PATH = "/InventoryPrinterOutput.txt";

	private Inventory inventory;
	protected void setUp() throws Exception {
		super.setUp();
		this.inventory = (Inventory) getBean("inventory", "Cannot load inventory bean");
				
	}
	protected void tearDown() throws Exception {
		this.inventory = null;
		super.tearDown();
	}
	public void testPrintInventory() throws Exception {
		String expectedOutput = readFileContent(EXPECTED_OUTPUT_FILE_PATH);
		String printedOutput = InventoryPrinter.printInventory(inventory).toString();
		assertEquals(expectedOutput, printedOutput);
	}
}
