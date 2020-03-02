package com.alex.wassgar.client.utils;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.alex.wassgar.client.connection.ConnectionManager;
import com.alex.wassgar.client.connection.Watchman;
import com.alex.wassgar.client.gui.Aspect;
import com.alex.wassgar.client.gui.Controller;
import com.alex.wassgar.client.misc.Option;



/**********************************
 * Used to store static variables
 * 
 * @author RATEL Alexandre
 **********************************/
public class Variables
	{
	/**
	 * Variables
	 */
	
	/**	ENUM	**/
	
	/**
	 * Option type
	 */
	public enum optionType
		{
		incomingcallpopup,
		reverselookup,
		emailreminder
		};
	
	/**
	 * Call type
	 */
	public enum callType
		{
		incoming,
		outgoing
		};
		
	/**
	 * SF Object type
	 */
	public enum sfObjectType
		{
		contact,
		lead,
		account,
		task
		};
		
	/**
	 * Contact search area
	 */
	public enum searchArea
		{
		all,
		user
		};
		
	/**	MISC	**/
	private static String softwareName;
	private static String softwareVersion;
	private static Logger logger;
	private static String mainConfigFileDirectory;
	private static ArrayList<String[][]> mainConfig;
	private static String configFileName;
	private static ArrayList<Option> optionList;
	
	/**	Language management	**/
	public enum language{english,french};
	private static String languageFileName;
	private static ArrayList<ArrayList<String[][]>> languageContentList;
	
	/** Client management**/
	private static Socket socket;
	private static ObjectInputStream in;
	private static ObjectOutputStream out;
	private static ConnectionManager connectionManager;
	private static Watchman watchman;
	
	/** GUI **/
	private static Aspect trayMenu;
	
	/**************
     * Constructor
     **************/
	public Variables()
		{
		configFileName = "configFile.xml";
		languageFileName = "languages.xml";
		mainConfigFileDirectory = ".";
		}

	public static ArrayList<ArrayList<String[][]>> getLanguageContentList() throws Exception
		{
		if(languageContentList == null)
			{
			Variables.getLogger().debug("Initialisation of languageContentList");
			Variables.setLanguageContentList(UsefulMethod.readExtFile("language", Variables.getLanguageFileName()));
			}
		
		return languageContentList;
		}

	public static String getSoftwareName()
		{
		return softwareName;
		}

	public static void setSoftwareName(String softwareName)
		{
		Variables.softwareName = softwareName;
		}

	public static String getSoftwareVersion()
		{
		return softwareVersion;
		}

	public static void setSoftwareVersion(String softwareVersion)
		{
		Variables.softwareVersion = softwareVersion;
		}

	public static Logger getLogger()
		{
		return logger;
		}

	public static void setLogger(Logger logger)
		{
		Variables.logger = logger;
		}

	public static String getMainConfigFileDirectory()
		{
		return mainConfigFileDirectory;
		}

	public static void setMainConfigFileDirectory(String mainConfigFileDirectory)
		{
		Variables.mainConfigFileDirectory = mainConfigFileDirectory;
		}

	public static ArrayList<String[][]> getMainConfig()
		{
		return mainConfig;
		}

	public static void setMainConfig(ArrayList<String[][]> mainConfig)
		{
		Variables.mainConfig = mainConfig;
		}

	public static String getConfigFileName()
		{
		return configFileName;
		}

	public static void setConfigFileName(String configFileName)
		{
		Variables.configFileName = configFileName;
		}

	public static String getLanguageFileName()
		{
		return languageFileName;
		}

	public static void setLanguageFileName(String languageFileName)
		{
		Variables.languageFileName = languageFileName;
		}
	
	public static void setLanguageContentList(
			ArrayList<ArrayList<String[][]>> languageContentList)
		{
		Variables.languageContentList = languageContentList;
		}

	public static Socket getSocket()
		{
		return socket;
		}

	public static void setSocket(Socket socket)
		{
		Variables.socket = socket;
		}

	public static ConnectionManager getConnectionManager()
		{
		return connectionManager;
		}

	public static void setConnectionManager(ConnectionManager connectionManager)
		{
		Variables.connectionManager = connectionManager;
		}

	public static Watchman getWatchman()
		{
		return watchman;
		}

	public static void setWatchman(Watchman watchman)
		{
		Variables.watchman = watchman;
		}

	public static ArrayList<Option> getOptionList()
		{
		if(optionList == null)
			{
			optionList = UsefulMethod.initOptionList();
			}
		
		return optionList;
		}

	public static void setOptionList(ArrayList<Option> optionList)
		{
		Variables.optionList = optionList;
		}

	public static synchronized ObjectInputStream getIn()
		{
		return in;
		}

	public static void setIn(ObjectInputStream in)
		{
		Variables.in = in;
		}

	public static synchronized ObjectOutputStream getOut()
		{
		return out;
		}

	public static void setOut(ObjectOutputStream out)
		{
		Variables.out = out;
		}

	public static Aspect getTrayMenu()
		{
		return trayMenu;
		}

	public static void setTrayMenu(Aspect trayMenu)
		{
		Variables.trayMenu = trayMenu;
		}
	
	/*2019*//*RATEL Alexandre 8)*/
	}
