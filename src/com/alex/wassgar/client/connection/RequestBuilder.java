package com.alex.wassgar.client.connection;

import java.util.ArrayList;

import com.alex.wassgar.client.misc.Option;
import com.alex.wassgar.server.Request;
import com.alex.wassgar.server.Request.requestType;

/**
 * Static method to build request
 * @author Alexandre
 *
 */
public class RequestBuilder
	{
	
	/**
	 * To build a popup request
	 * 
	 * Content = url
	 */
	public static Request buildPopup(String exec)
		{
		StringBuffer buf = new StringBuffer();
		buf.append(exec);
		Request r = new Request(requestType.displayPopup, buf.toString());
		
		return r;
		}
	
	/**
	 * To build a connection accepted request
	 */
	public static Request buildAcceptedConnection(String desc)
		{
		StringBuffer buf = new StringBuffer();
		buf.append(desc);
		Request r = new Request(requestType.connectionAccepted, buf.toString());
		
		return r;
		}
	
	/**
	 * To build a connection rejected request
	 */
	public static Request buildRejectedConnection(String desc)
		{
		StringBuffer buf = new StringBuffer();
		buf.append(desc);
		Request r = new Request(requestType.connectionRejected, buf.toString());
		
		return r;
		}
	
	/**
	 * To build a success request
	 */
	public static Request buildSuccess()
		{
		Request r = new Request(requestType.success, "OK");
		return r;
		}
	
	/**
	 * To build a success request
	 */
	public static Request buildFailed(String desc)
		{
		StringBuffer buf = new StringBuffer();
		buf.append(desc);
		Request r = new Request(requestType.failed, buf.toString());
		
		return r;
		}
	
	/**
	 * To build a success request
	 */
	public static Request buildConnectionRequest(String extension)
		{
		StringBuffer buf = new StringBuffer();
		buf.append(extension);
		Request r = new Request(requestType.connectionRequest, buf.toString());
		
		return r;
		}
	
	/**
	 * To build a success request
	 */
	public static Request buildStatus()
		{
		Request r = new Request(requestType.status, "OK ?");
		return r;
		}
	
	/**
	 * To build an update request
	 */
	public static Request buildOptionUpdate(String content)
		{
		Request r = new Request(requestType.updateOption, content);
		return r;
		}
	}

/*2019*//*RATEL Alexandre 8)*/