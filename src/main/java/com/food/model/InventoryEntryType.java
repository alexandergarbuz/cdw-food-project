package com.food.model;

import java.util.HashMap;
import java.util.Map;

import com.food.util.BaseEnum;


public class InventoryEntryType extends BaseEnum {
	//***********************************************************
    /**
     * Collection of references to created objects.
     */
    private static Map<String,InventoryEntryType> values = new HashMap<String, InventoryEntryType>();

	public static final InventoryEntryType SYSTEM_ADMINISTRATOR = new InventoryEntryType("SYSTEM_ADMINISTRATOR");
	public static final InventoryEntryType PREPRESENTATIVE = new InventoryEntryType("PREPRESENTATIVE");
    /**
	 * Private constructor to prevent 
	 * creation of incorrect objects.
	 *
	 */
	private InventoryEntryType(String value) {
		if(values.get(value)!= null) {
			throw new IllegalArgumentException("Attempt to create a constant with duplicate value:" + getClass() + "#" + value);
		}
		this.value = value;
		values.put(value, this);
	}
    /**
     * Finds an enumeration object with the value that matches specified value.
     * @param value object value.
     * @return object with matching value.
     */    
    public static InventoryEntryType valueOf(final String value) {
    	InventoryEntryType obj = values.get(value);
        if(obj == null) {
            final String errorMessage = "Invalid value. " +
                        "Cannot find any object with value=[" + value + "]. " +
                        "Specified value does not match any of " +
                        "the existing values";
            throw new IllegalArgumentException(errorMessage);
        }
        return obj; 
    }
}
