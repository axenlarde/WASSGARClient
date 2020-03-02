package com.alex.wassgar.client.gui;

import java.awt.CheckboxMenuItem;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.ImageIcon;

import com.alex.wassgar.client.connection.ManageRequest;
import com.alex.wassgar.client.utils.UsefulMethod;
import com.alex.wassgar.client.utils.Variables;
import com.alex.wassgar.client.utils.Variables.optionType;


/**********************************
* Used to setup the client aspect
* 
* @author RATEL Alexandre
**********************************/
public class Aspect implements ItemListener, ActionListener
	{
	/**
	 * Variables
	 */
	private SystemTray tray;
	private TrayIcon tic;
	private CheckboxMenuItem incomingCallPopup;
	private CheckboxMenuItem reverseLookup;
	private CheckboxMenuItem emailReminder;
	private MenuItem reconnect;
	
	/***************
	 * Constructor
	 ***************
	 */
	public Aspect()
		{
		tray = SystemTray.getSystemTray();
		
		reconnect = new MenuItem("Forcer une reconnexion");
		incomingCallPopup = new CheckboxMenuItem("Popup d'appel entrant");
        reverseLookup = new CheckboxMenuItem("Affichage du nom sur le téléphone");
        emailReminder = new CheckboxMenuItem("Envoi d'email de rappel");
        
        incomingCallPopup.addItemListener(this);
        reverseLookup.addItemListener(this);
        emailReminder.addItemListener(this);
		reconnect.addActionListener(this);
		
		try
			{
			setupSystrayIcon(getClass().getResource("art/Red.png"),Variables.getSoftwareName());
			setupSystrayMenu(false);
			}
		catch (Exception e)
			{
			Variables.getLogger().error("Error while setting up the systray : "+e.getMessage(),e);
			}
		}
	
	/**
	 * Setup the systray icon
	 */
	private void setupSystrayIcon(URL iconPath, String softwareName) throws Exception
		{
		if (SystemTray.isSupported())
        	{
            try
            	{
                ImageIcon im = new ImageIcon(iconPath);
                Image image = im.getImage();
                tic = new TrayIcon(image, softwareName);
                tic.setImageAutoSize(true);
                tray.add(tic);
            	}
            catch (Exception e)
            	{
            	throw new Exception("Unable to setup systray : "+e.getMessage(),e);
            	}
        	}
		else
			{
			throw new Exception("Systray not supported with this operating system");
			}
		}
	
	/**
	 * Setup the system tray menu
	 */
	private void setupSystrayMenu(boolean connected)
		{
		if(connected)
			{
			//When client is connected
			PopupMenu popup = new PopupMenu();
	        popup.add(incomingCallPopup);
	        popup.add(reverseLookup);
	        popup.add(emailReminder);
	        tic.setPopupMenu(popup);
			}
		else
			{
			//When client is not connected
			PopupMenu popup = new PopupMenu();
			popup.add(reconnect);
			tic.setPopupMenu(popup);
			}
		}
	
	/**
	 * To change the system tray icon
	 */
	private void changeTrayIcon(URL iconPath) throws Exception
		{
		ImageIcon im = new ImageIcon(iconPath);
        Image image = im.getImage();
		tic.setImage(image);
		}
	
	
	/**
	 * Used to change the icon color
	 */
	public void changeStatus(boolean b)
		{
		try
			{
			if(b)
				{
				changeTrayIcon((getClass().getResource("art/Green.png")));
				setupSystrayMenu(true);
				}
			else
				{
				changeTrayIcon((getClass().getResource("art/Red.png")));
				setupSystrayMenu(false);
				}
			}
		catch (Exception e)
			{
			Variables.getLogger().error("Error while changing the systray icon : "+e.getMessage(),e);
			}
		}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent evt)
		{
		if(evt.getSource() == this.incomingCallPopup)
			{
			Variables.getLogger().debug("incomingCallPopup tray button pressed");
			UsefulMethod.setOption(optionType.incomingcallpopup, this.incomingCallPopup.getState());
			}
		else if(evt.getSource() == this.reverseLookup)
			{
			Variables.getLogger().debug("reverseLookup tray button pressed");
			UsefulMethod.setOption(optionType.reverselookup, this.reverseLookup.getState());
			}
		else if(evt.getSource() == this.emailReminder)
			{
			Variables.getLogger().debug("emailReminder tray button pressed");
			UsefulMethod.setOption(optionType.emailreminder, this.emailReminder.getState());
			}
		}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evt)
		{
		if(evt.getSource() == this.reconnect)
			{
			Variables.getLogger().debug("reconnect tray button pressed");
			if(Variables.getSocket() == null)
				{
				try
					{
					ManageRequest.initConnection();
					}
				catch (Exception e)
					{
					Variables.getLogger().error("Connexion failed : "+e.getMessage(),e);
					}
				}
			}
		}
	

	public CheckboxMenuItem getIncomingCallPopup()
		{
		return incomingCallPopup;
		}

	public CheckboxMenuItem getReverseLookup()
		{
		return reverseLookup;
		}

	public CheckboxMenuItem getEmailReminder()
		{
		return emailReminder;
		}

	

	
	/*2019*//*RATEL Alexandre 8)*/
	}

