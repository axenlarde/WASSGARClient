package com.alex.wassgar.client.connection;

import com.alex.wassgar.client.action.ManageUser;
import com.alex.wassgar.client.utils.UsefulMethod;
import com.alex.wassgar.client.utils.Variables;
import com.alex.wassgar.server.Request;
import com.alex.wassgar.server.Request.requestType;

/**********************************
* Used to manage the connection to the server
* 
* @author RATEL Alexandre
**********************************/
public class ConnectionManager extends Thread
	{
	/**
	 * Variables
	 */
	private boolean run = true;
	
	/**
	 * Constructor
	 */
	public ConnectionManager()
		{
		super();
		start();
		}
	
	
	public void run()
		{
		Variables.getLogger().debug("Connection Manager Thread started !");
		
		try
			{
			while(run)
				{
				try
					{
					if(Variables.getSocket() == null)
						{
						ManageRequest.initConnection();
						}
					else
						{
						//We wait for an instruction
						Variables.getLogger().debug("Waiting for request...");
						
						Object o = Variables.getIn().readObject();
						if(o instanceof Request)
							{
							Request r = (Request)o;
							
							if(r.getType().equals(requestType.displayPopup))
								{
								String[] tab = r.getContent().split(",");
								String url = tab[0];
								
								ManageUser.fireNotification(url);
								Variables.getLogger().debug("URL fired !");
								}
							else if(r.getType().equals(requestType.status))
								{
								Variables.getLogger().debug("Status request received from server");
								}
							else if(r.getType().equals(requestType.updateOption))
								{
								Variables.getLogger().debug("New options received from the server");
								Variables.setOptionList(UsefulMethod.parseOptionList(r.getContent()));
								UsefulMethod.updateOptionWindow();
								Variables.getLogger().debug("Options updated");
								}
							else
								{
								Variables.getLogger().debug("Received unkown request : "+r.getType().name());
								}
							}
						}
					}
				catch (Exception e)
					{
					Variables.getLogger().error("ERROR : Whith the server connection : "+e.getMessage(),e);
					try
						{
						Variables.getSocket().close();
						Variables.setSocket(null);
						Variables.setIn(null);
						Variables.setOut(null);
						}
					catch (Exception exc)
						{
						Variables.getLogger().error("ERROR : Unable to close socket : "+e.getMessage(),e);
						Variables.setSocket(null);
						Variables.setIn(null);
						Variables.setOut(null);
						}
					
					Variables.getTrayMenu().changeStatus(false);
					
					this.sleep(Integer.parseInt(UsefulMethod.getTargetOption("retryinterval"))*1000);
					}
				/**
				 * We force the GC just to avoid memory leak
				 * 
				 * Indeed, When I check the memory consumption over long period of time
				 * I see it always increasing, never going down. Forcing the GC fix this
				 */
				System.gc();
				}
			}
		catch (Exception e)
			{
			Variables.getLogger().error("ERROR : "+e.getMessage(),e);
			}
		
		Variables.getLogger().debug("Connection Manager Thread stopped !");
		}
	
	/**
	 * To stop this thread
	 */
	public void tchao()
		{
		run = false;
		}
	
	
	/*2019*//*RATEL Alexandre 8)*/
	}

