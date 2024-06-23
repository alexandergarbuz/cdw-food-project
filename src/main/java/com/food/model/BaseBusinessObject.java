package com.food.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.food.util.MyToStringStyle;

/**
 * Base domain object. Must be sub classed by all business objects.
 *
 * @author Alexander Garbuz
 *
 */
public abstract class BaseBusinessObject  {

	private static transient final ToStringStyle TO_STRING_STYLE = new MyToStringStyle();
	/**
	 * Returns a string representation of the object.
	 * @return an object as a string.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this, TO_STRING_STYLE);
	}

	/**
	 * Returns a hash code value for the object.
	 *
	 * @return the hash code of this object.
	 *
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param objectToCompare the object to compare
	 *
	 * @return true, if objects are equal
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object objectToCompare) {
		return EqualsBuilder.reflectionEquals(this, objectToCompare);
	}
}
