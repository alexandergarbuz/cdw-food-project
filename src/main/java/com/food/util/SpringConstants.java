package com.food.util;

public interface SpringConstants {
	public static final String[] SPRING_CONFIGS = new String[] {
			"applicationContext.xml", //generic context file
			"applicationContext-Ingredients.xml"//sandwiches and their ingredients are defined in this file 
			};
	
	public static final String INVENTORY_BEAN_NAME = "inventory";
	public static final String MENU_BEAN_NAME = "menu";
	public static final String FOOD_DISPENSER_BEAN_NAME = "foodDispenser";
	
}
