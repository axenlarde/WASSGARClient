package com.alex.wassgar.client.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.apache.log4j.Level;

import com.alex.wassgar.client.connection.ManageRequest;
import com.alex.wassgar.client.connection.RequestBuilder;
import com.alex.wassgar.client.misc.Option;
import com.alex.wassgar.client.utils.Variables.optionType;
import com.alex.wassgar.server.Request;

/**********************************
 * Class used to store the useful static methods
 * 
 * @author RATEL Alexandre
 **********************************/
public class UsefulMethod
	{
	
	/*****
	 * Method used to read the main config file
	 * @throws Exception 
	 */
	public static ArrayList<String[][]> readMainConfigFile(String fileName) throws Exception
		{
		String file;
		ArrayList<String> listParams = new ArrayList<String>();
		
		try
			{
			file = xMLReader.fileRead("./"+fileName);
			
			listParams.add("config");
			return xMLGear.getResultListTab(file, listParams);
			}
		catch(FileNotFoundException fnfexc)
			{
			fnfexc.printStackTrace();
			throw new FileNotFoundException("The "+fileName+" file was not found : "+fnfexc.getMessage());
			}
		catch(Exception exc)
			{
			exc.printStackTrace();
			Variables.getLogger().error(exc.getMessage(),exc);
			throw new Exception("ERROR with the file : "+fileName+" : "+exc.getMessage());
			}
		
		}
	
	
	/***************************************
	 * Method used to get a specific value
	 * in the user preference XML File
	 ***************************************/
	public synchronized static String getTargetOption(String node) throws Exception
		{
		for(String[] s : Variables.getMainConfig().get(0))
			{
			if(s[0].equals(node))return s[1];
			}
		
		/***********
		 * If this point is reached, the option looked for was not found
		 */
		throw new Exception("Option \""+node+"\" not found"); 
		}
	/*************************/
	
	
	
	/************************
	 * Check if java version
	 * is correct
	 ***********************/
	public static void checkJavaVersion()
		{
		try
			{
			String jVer = new String(System.getProperty("java.version"));
			Variables.getLogger().info("Detected JRE version : "+jVer);
			
			/*Need to use the config file value*/
			
			if(jVer.contains("1.6"))
				{
				
				if(Integer.parseInt(jVer.substring(6,8))<16)
					{
					Variables.getLogger().info("JRE version is not compatible. The application will now exit. system.exit(0)");
					System.exit(0);
					}
				}
			}
		catch(Exception exc)
			{
			exc.printStackTrace();
			Variables.getLogger().info("ERROR : It has not been possible to detect JRE version",exc);
			}
		}
	/***********************/
	
	/**
	 * Method used when the application failed to 
	 * initialize
	 */
	public static void failedToInit(Exception exc)
		{
		exc.printStackTrace();
		Variables.getLogger().error(exc.getMessage());
		Variables.getLogger().error("Application failed to init : System.exit(0)");
		System.exit(0);
		}
	
	/**
	 * Initialization of the internal variables from
	 * what we read in the configuration file
	 * @throws Exception 
	 */
	public static void initInternalVariables() throws Exception
		{
		/***********
		 * Logger
		 */
		String level = UsefulMethod.getTargetOption("log4j");
		if(level.compareTo("DEBUG")==0)
			{
			Variables.getLogger().setLevel(Level.DEBUG);
			}
		else if (level.compareTo("INFO")==0)
			{
			Variables.getLogger().setLevel(Level.INFO);
			}
		else if (level.compareTo("ERROR")==0)
			{
			Variables.getLogger().setLevel(Level.ERROR);
			}
		else
			{
			//Default level is INFO
			Variables.getLogger().setLevel(Level.INFO);
			}
		Variables.getLogger().info("Log level found in the configuration file : "+Variables.getLogger().getLevel().toString());
		/*************/
		
		/************
		 * Etc...
		 */
		//If needed, just write it here
		/*************/
		}
	
	/**************
	 * Method aims to get a template item value by giving its name
	 * @throws Exception 
	 *************/
	public static String getItemByName(String name, String[][] itemDetails) throws Exception
		{
		for(int i=0; i<itemDetails.length; i++)
			{
			if(itemDetails[i][0].equals(name))
				{
				return itemDetails[i][1];
				}
			}
		//throw new Exception("Item not found : "+name);
		Variables.getLogger().debug("Item not found : "+name);
		return "";
		}
	
	/**
	 * Method used to find the file name from a file path
	 * For intance :
	 * C:\JAVAELILEX\YUZA\Maquette_CNAF_TA_FichierCollecteDonneesTelephonie_v1.7_mac.xls
	 * gives :
	 * Maquette_CNAF_TA_FichierCollecteDonneesTelephonie_v1.7_mac.xls
	 */
	public static String extractFileName(String fullFilePath)
		{
		String[] tab =  fullFilePath.split("\\\\");
		return tab[tab.length-1];
		}
	
	/************
	 * Method used to read an advanced configuration file
	 * @throws Exception 
	 */
	public static ArrayList<ArrayList<String[][]>> readExtFile(String param, String fileName) throws Exception
		{
		String file;
		ArrayList<String> listParams = new ArrayList<String>();
		
		try
			{
			Variables.getLogger().info("Reading of the file : "+fileName);
			file = xMLReader.fileRead(Variables.getMainConfigFileDirectory()+"/"+fileName);
			
			listParams.add(param);
			return xMLGear.getResultListTabExt(file, listParams);
			}
		catch(FileNotFoundException fnfexc)
			{
			fnfexc.printStackTrace();
			throw new FileNotFoundException("The "+fileName+" file was not found : "+fnfexc.getMessage());
			}
		catch(Exception exc)
			{
			exc.printStackTrace();
			Variables.getLogger().error(exc.getMessage(),exc);
			throw new Exception("ERROR with the file : "+exc.getMessage());
			}
		}
	
	/**
	 * To read an option xml content
	 * 
	 * Example of option xml content :
	 * <xml>
	 * 	<options>
	 * 		<incomingcallpopup>true</incomingcallpopup>
	 *		<reverselookup>true</reverselookup>
	 *		<emailreminder>true</emailreminder>
	 *	</options>
	 * </xml>
	 */
	public static ArrayList<Option> parseOptionList(String xmlContent)
		{
		Variables.getLogger().debug("Received the following options set to apply : \r\n"+xmlContent);
		
		ArrayList<Option> optionList = new ArrayList<Option>();
		
		try
			{
			ArrayList<String> params = new ArrayList<String>();
			params.add("options");
			
			ArrayList<String[][]> content = xMLGear.getResultListTab(xmlContent, params);
			
			for(String[] s : content.get(0))
				{
				for(optionType ot : optionType.values())
					{
					if(s[0].equals(ot.name()))optionList.add(new Option(ot, Boolean.parseBoolean(s[1])));
					}
				}
			}
		catch (Exception e)
			{
			Variables.getLogger().error("Error : "+e.getMessage(),e);
			}
		
		return optionList;
		}
	
	/**
	 * Create the xml option pattern used to send
	 * a new option value to the server
	 */
	public static String encodeOptionList(ArrayList<Option> optionList)
		{
		StringBuffer buf = new StringBuffer();
		buf.append("<xml>\r\n");
		buf.append("	<options>\r\n");
		for(Option o : optionList)
			{
			buf.append("		<"+o.getType().name()+">");
			buf.append(o.isValue());
			buf.append("</"+o.getType().name()+">\r\n");
			}
		buf.append("	</options>\r\n");
		buf.append("</xml>");
		
		return buf.toString();
		}
	
	public static boolean getOption(optionType ot)
		{
		for(Option o : Variables.getOptionList())
			{
			if(o.getType().equals(ot))return o.isValue();
			}
		
		return true;
		}
	
	public static void setOption(optionType ot, boolean value)
		{
		try
			{
			//Updating the option in memory
			for(int i=0; i<Variables.getOptionList().size(); i++)
				{
				if(Variables.getOptionList().get(i).getType().equals(ot))
					{
					Variables.getOptionList().get(i).setValue(value);
					break;
					}
				}
			
			//Sending the update to the server
			ManageRequest.updateUserOption(UsefulMethod.encodeOptionList(Variables.getOptionList()));
			}
		catch (Exception e)
			{
			Variables.getLogger().debug("Error : "+e.getMessage(),e);
			}
		}
	
	public static ArrayList<Option> initOptionList()
		{
		ArrayList<Option> optionList = new ArrayList<Option>();
		
		for(optionType ot : optionType.values())
			{
			optionList.add(new Option(ot, true));
			}
		
		return optionList;
		}
	
	/**
	 * Used to update the option window according to the given
	 * option list
	 */
	public static void updateOptionWindow()
		{
		/*
		 * JavaFX
		Variables.getController().getOptionEmail().setSelected(UsefulMethod.getOption(optionType.emailreminder));
		Variables.getController().getOptionPopup().setSelected(UsefulMethod.getOption(optionType.incomingcallpopup));
		Variables.getController().getOptionReverse().setSelected(UsefulMethod.getOption(optionType.reverselookup));*/
		
		Variables.getTrayMenu().getEmailReminder().setState(UsefulMethod.getOption(optionType.emailreminder));
		Variables.getTrayMenu().getIncomingCallPopup().setState(UsefulMethod.getOption(optionType.incomingcallpopup));
		Variables.getTrayMenu().getReverseLookup().setState(UsefulMethod.getOption(optionType.reverselookup));
		}
	
	/*2019*//*RATEL Alexandre 8)*/
	}

