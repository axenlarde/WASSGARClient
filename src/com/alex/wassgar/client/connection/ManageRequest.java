package com.alex.wassgar.client.connection;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.alex.wassgar.client.utils.UsefulMethod;
import com.alex.wassgar.client.utils.Variables;
import com.alex.wassgar.server.Request;
import com.alex.wassgar.server.Request.requestType;

/**
 * Class used to manager request from the server
 */
public class ManageRequest
	{
	
	/**
	 * Init the connection to the server 
	 * and return true if success
	 */
	public static boolean initConnection() throws Exception
		{
		Variables.getLogger().debug("Trying to connect");
		
		String serverIP = UsefulMethod.getTargetOption("serverip");
		String serverPort = UsefulMethod.getTargetOption("serverport");
		String extension = UsefulMethod.getTargetOption("extension");
		
		Socket s = new Socket(serverIP, Integer.parseInt(serverPort));
		
		Variables.getLogger().debug("New socket created, init connection to server");
		
		Request r = RequestBuilder.buildConnectionRequest(extension);
		
		ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
		
		out.writeObject(r);
		out.flush();
		
		//We wait for the reply
		ObjectInputStream in = new ObjectInputStream(s.getInputStream());
		Object o = in.readObject();
		
		if(o instanceof Request)
			{
			Request reply = (Request)o;
			
			if(reply.getType().equals(requestType.connectionAccepted))
				{
				Variables.getLogger().debug("Connection accepted by the server : "+reply.getContent());
				Variables.setSocket(s);
				Variables.setIn(in);
				Variables.setOut(out);
				
				Variables.getTrayMenu().changeStatus(true);
				
				return true;
				}
			else if(reply.getType().equals(requestType.connectionRejected))
				{
				Variables.getLogger().debug("Connection rejected by the server : "+reply.getContent());
				Variables.getLogger().debug("Clearing socket.");
				}
			}
		else
			{
			Variables.getLogger().debug("Unknown request received !");
			}
		
		in.close();
		out.close();
		s.close();
		
		return false;
		}
	
	/**
	 * Update user
	 */
	public static void updateUserOption(String content)
		{
		Variables.getLogger().debug("Trying to update the user option with the following content : \r\n"+content);
		
		try
			{
			Request r = RequestBuilder.buildOptionUpdate(content);
			
			Variables.getOut().writeObject(r);
			Variables.getOut().flush();
			
			Variables.getLogger().debug("User option updated !");
			}
		catch (Exception e)
			{
			Variables.getLogger().error("Error : "+e.getMessage(),e);
			}
		}
	
	}

/*2019*//*RATEL Alexandre 8)*/