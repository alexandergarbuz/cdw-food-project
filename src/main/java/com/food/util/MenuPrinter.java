package com.food.util;

import java.util.Iterator;

import com.food.model.MenuItem;
import com.food.service.Menu;

/**
 * Prints the contents of the menu with the availability in stock.
 *
 */
public class MenuPrinter extends BasePrinter {
	
	public static final String HEADER = "Menu:";
	public static final String UNAVAILABLE = "Out Of Stock";
	
	public static final StringBuffer printMenuItem(final MenuItem itemToPrint) {
		StringBuffer output = new StringBuffer()
		.append(itemToPrint.getId())
		.append(DOT)
		.append(itemToPrint.getDescription())
		.append(SPACER).append(DASH_SIGN).append(SPACER);
		if(itemToPrint.isAvailable()) {
			output
			.append(CURRENTCY_SIGN)
			.append(itemToPrint.getCost())
			.append(SPACER);
		} else {
			output
			.append(UNAVAILABLE)
			.append(SPACER);
		}
		output.append(LINE_SEPARATOR);
		return output;
	}
	
	public static final StringBuffer printMenu(final Menu menuToPrint) {
		Iterator<MenuItem> it = menuToPrint.getMenuItems().iterator();
		StringBuffer output = new StringBuffer(HEADER)
		.append(LINE_SEPARATOR);
		while(it.hasNext()) {
			MenuItem nextMenuItem = it.next();
			output.append(printMenuItem(nextMenuItem));
		}
		return output;		
	}
}
