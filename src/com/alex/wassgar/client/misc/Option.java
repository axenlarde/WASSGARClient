package com.alex.wassgar.client.misc;

import com.alex.wassgar.client.utils.Variables.optionType;

/**
 * Represent an option
 * 
 * @author Alexandre
 */
public class Option
	{
	/**
	 * Variables
	 */
	private optionType type;
	private boolean value;
	
	public Option(optionType type, boolean value)
		{
		super();
		this.type = type;
		this.value = value;
		}

	public optionType getType()
		{
		return type;
		}

	public void setType(optionType type)
		{
		this.type = type;
		}

	public boolean isValue()
		{
		return value;
		}

	public void setValue(boolean value)
		{
		this.value = value;
		}
	
	}

/*2019*//*RATEL Alexandre 8)*/