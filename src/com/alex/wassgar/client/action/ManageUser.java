package com.alex.wassgar.client.action;

import java.io.IOException;

import com.alex.wassgar.client.utils.Variables;

/**********************************
* Used to manage user action
* 
* @author RATEL Alexandre
**********************************/
public class ManageUser
	{
	
	/**
	 * Used to display a notification to the user
	 */
	public static void fireNotification(String url)
		{
		try
			{
			Variables.getLogger().debug("Following url to fire : "+url);
			Runtime.getRuntime().exec(url);
			}
		catch (IOException e)
			{
			Variables.getLogger().error("ERROR : While firing a notification : "+e.getMessage(),e);
			}
		}
	
	
	
	/*2019*//*RATEL Alexandre 8)*/
	}

