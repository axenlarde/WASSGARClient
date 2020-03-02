package com.alex.wassgar.client.connection;

import java.io.ObjectOutputStream;

import com.alex.wassgar.client.utils.UsefulMethod;
import com.alex.wassgar.client.utils.Variables;
import com.alex.wassgar.server.Request;

/**********************************
* Class used to check if the socket is still active. Indeed, if the connection is up
* and the server crash, the client realize that the server is down only 
* 
* @author RATEL Alexandre
**********************************/
public class Watchman extends Thread
	{
	/**
	 * Variables
	 */
	private boolean run = true;
	
	/***************
	 * Constructor
	 ***************/
	public Watchman()
		{
		start();
		}
	
	
	public void run()
		{
		Variables.getLogger().debug("Watchman started !");
		while(run)
			{
			try
				{
				if(Variables.getSocket() != null)
					{
					Request r = RequestBuilder.buildStatus();
					Variables.getOut().writeObject(r);
					Variables.getOut().flush();
					
					//If the writing succeed , the server connection is up, otherwise an exception would have been raised
					Variables.getLogger().debug("Watchman : Server connection is UP");
					}
				else
					{
					Variables.getLogger().debug("Watchman : socket not setup");
					}
				this.sleep(Integer.parseInt(UsefulMethod.getTargetOption("retryinterval"))*1000);
				}
			catch (Exception e)
				{
				Variables.getLogger().error("Watchman : Socket down, clearing up");
				
				try
					{
					Variables.getSocket().close();
					Variables.setSocket(null);
					Variables.setIn(null);
					Variables.setOut(null);
					}
				catch (Exception exc)
					{
					Variables.getLogger().error("Watchman : Unable to close socket");
					Variables.setSocket(null);
					Variables.setIn(null);
					Variables.setOut(null);
					}
				Variables.getTrayMenu().changeStatus(false);
				}
			System.gc();//To force garbage cleaner
			}
		Variables.getLogger().debug("Watchman stopped !");
		}
	
	/**
	 * To interrupt the thread
	 */
	public void tchao()
		{
		run = false;
		}

	/*2019*//*RATEL Alexandre 8)*/
	}

