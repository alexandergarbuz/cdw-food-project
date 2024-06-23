package com.food.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.food.model.InventoryEntry;
import com.food.model.MenuItem;
import com.food.util.BasePrinter;
import com.food.util.InventoryPrinter;
import com.food.util.MenuPrinter;
import com.food.util.SpringConstants;

public class FoodDispenser {
	
	private static final String PLEASE_SELECT_YOUR_SANDWICH_MESSAGE = "Please select your sandwich....";
	private static final String STARTING_OVER_MESSAGE = "Starting Over!";
	private static final String GOOD_BYE_MESSAGE = "Have a nice day!";
	private static final String INPUT_INSTRUCTIONS = "Please select a number [1-7] or 'q' to finish or 'r' to start over.";
	private static final String QUIT_COMMAND = "q";
	private static final String RELOAD_COMMAND = "r";
	private final static Logger LOG = Logger.getLogger(FoodDispenser.class);
	
	private Inventory inventory;
	private Menu menu;
	
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(SpringConstants.SPRING_CONFIGS);
		FoodDispenser d = (FoodDispenser)ctx.getBean(SpringConstants.FOOD_DISPENSER_BEAN_NAME);
		d.getMenu().reloadMenu();
		d.displayMenu();
		
		String input = null;
		//while(!(input = StringUtils.lowerCase(d.readInput(System.in))).equals(QUIT_COMMAND)) {
		while((input = StringUtils.lowerCase(d.readInput(System.in))) != null) {
			if(QUIT_COMMAND.equalsIgnoreCase(input)) {
				d.displayGoodByeMessage();
				System.exit(0);
			}else if(RELOAD_COMMAND.equalsIgnoreCase(input)) {
				/*
				 * Restart the system
				 */
				d.displayStartingOverMessage(); 
				ctx = new ClassPathXmlApplicationContext(SpringConstants.SPRING_CONFIGS);
				d = (FoodDispenser)ctx.getBean(SpringConstants.FOOD_DISPENSER_BEAN_NAME);
				 
				d.getMenu().reloadMenu();
				d.displayMenu();
				
			} else if(d.isInRange(input) ) {
				d.getMenu().reloadMenu();
				Integer id =  NumberUtils.createInteger(input);
				MenuItem selectedItem = d.getMenu().findItemById(id);
				
				boolean orderProccessed = d.placeOrder(id);
				d.displayConfirmation(selectedItem, orderProccessed);
				d.displayMenu();			
			} else {
				/**
				 * Invalid user input
				 */
				d.displayUserInputInstructions();
			}
		}
	}
	protected boolean isInRange(String input) {
		boolean result = false;
		if(NumberUtils.isNumber(input)) {
			int number = NumberUtils.createInteger(input).intValue();
			result = ( number >= 1 && number <= 7 );
		}
		return result;
	}
	protected void displayStartingOverMessage() {
		System.out.println(STARTING_OVER_MESSAGE);
	}
	protected void displayGoodByeMessage() {
		System.out.println(GOOD_BYE_MESSAGE);
	}
	protected void displayUserInputInstructions() {
		System.out.println(INPUT_INSTRUCTIONS);
	}
	protected void displayConfirmation(final MenuItem selectedItem, boolean orderProccessed) {
		StringBuffer output = new StringBuffer();
		if(orderProccessed) {
			output.append("Dispensing  ").append(selectedItem.getDescription()).append(". Please pay $").append(selectedItem.getCost());
		} else {
			output.append("Selected item  ").append(selectedItem.getDescription()).append(" is Out of Stock. Please select anotehr ");
		}
		System.out.println(output.toString());
	}
	protected void displayMenu() {
		StringBuffer output = new StringBuffer()
		.append(MenuPrinter.printMenu(getMenu()))
		.append(InventoryPrinter.printInventory(getInventory()))
		.append(PLEASE_SELECT_YOUR_SANDWICH_MESSAGE)
		.append(BasePrinter.LINE_SEPARATOR);
		System.out.println(output);
	}
	protected final String readInput(final InputStream in) throws IOException {
		String input = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		input = br.readLine();
		return input;
	}
	
	protected boolean placeOrder(final int id) {
		LOG.debug("Placing order");
		boolean result = false;
		menu.reloadMenu();
		MenuItem item = menu.findItemById(id);
		Map<InventoryEntry, Integer> ingredients = item.getIngredients();
		boolean isAvailable = menu.isMenuItemAvailable(item);
		if(item.isAvailable()) {
			Iterator<InventoryEntry> it = ingredients.keySet().iterator();
			while(it.hasNext()) {
				InventoryEntry nextIngredient = it.next();
				int requiredQantity = ingredients.get(nextIngredient).intValue();
				int availableCount = inventory.findInventoryCount(nextIngredient);
				isAvailable = menu.isMenuItemAvailable(item);
				LOG.debug("Ingredient " + nextIngredient.getName() + " | required " + requiredQantity + " | available " + availableCount + " | " + isAvailable);
				inventory.decreaseInventory(nextIngredient, requiredQantity);
			}
			menu.reloadMenu();
			result = true;
		}
		return result;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
