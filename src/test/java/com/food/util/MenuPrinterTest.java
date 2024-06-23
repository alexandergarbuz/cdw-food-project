package com.food.util;

import com.food.service.Menu;
import com.spring.BaseSpringTest;

public class MenuPrinterTest extends BaseSpringTest {
	
	public static final String EXPECTED_OUTPUT_FILE_PATH = "/MenuPrinterOutput.txt";

	private Menu menu;
	
	protected void setUp() throws Exception {
		super.setUp();
		this.menu = (Menu)getBean("menu", "Cannot load menu bean");
	}
	
	protected void tearDown() throws Exception {
		this.menu = null;
		super.tearDown();
	}
	
	public void testPrintInventory() throws Exception {
		menu.reloadMenu();
		String expectedOutput = readFileContent(EXPECTED_OUTPUT_FILE_PATH);
		String printedOutput = MenuPrinter.printMenu(menu).toString();
		assertEquals(expectedOutput, printedOutput);
	}
}
