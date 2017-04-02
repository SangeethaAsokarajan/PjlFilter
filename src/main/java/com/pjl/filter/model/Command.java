/**
 * 
 */
package com.pjl.filter.model;

/**
 * @author Sangita
 *
 */
public class Command {

	public Command(String paramName, String paramValue) {
		name = paramName;
		value = paramValue;
	}

	private String name;
	private String value;

	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
}