/**
 *
 */
package com.food.util;

import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Helper object that defines the rules of implementing custom <code>toString()</code> methods.
 * 
 * @version 1.0
 * @author Alexander Garbuz
 *
 */
public class MyToStringStyle extends ToStringStyle {
	public MyToStringStyle(){
		super();
		this.setUseIdentityHashCode(false);
		this.setUseShortClassName(true);
	}
}
