package com.alex.wassgar.client.action;

import com.alex.wassgar.client.connection.ConnectionManager;
import com.alex.wassgar.client.connection.Watchman;
import com.alex.wassgar.client.gui.Aspect;
import com.alex.wassgar.client.gui.Controller;
import com.alex.wassgar.client.gui.OptionWindow;
import com.alex.wassgar.client.utils.Variables;

/**********************************
* Class used to start the application
* 
* @author RATEL Alexandre
**********************************/
public class Action
	{
	/**
	 * Variables
	 */
	
	
	public Action()
		{
		/**
		 * We start the system tray menu
		 */
		Variables.setTrayMenu(new Aspect());
		
		/**
		 * We start the connection manager thread
		 */
		Variables.setConnectionManager(new ConnectionManager());
		Variables.setWatchman(new Watchman());
		
		/**
		 * We launch the JavaFX option window
		 */
		//OptionWindow.launch(OptionWindow.class);
		}
	
	
	
	
	/*2019*//*RATEL Alexandre 8)*/
	}

