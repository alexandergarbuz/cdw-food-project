/**
 * 
 */
package com.food.util;

/**
 * Base enumeration object.
 * 
 * @version 1.0
 * @author Alexander Garbuz
 *
 */
public abstract class BaseEnum {
	/**
	 * Value of the enum.
	 */
	protected String value;
	/**
	 * Returns the value of the enumeration.
	 * @return the value of the enumeration.
	 */
	public final String getValue() {
		return value;
	}
	/**
	 * Returns the {@link String} value of the enumeration.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getClass().getName() + "=[value=[" + getValue() + "]]";
	}
}
