package com.alex.wassgar.client.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.alex.wassgar.client.utils.UsefulMethod;
import com.alex.wassgar.client.utils.Variables;
import com.alex.wassgar.client.utils.Variables.optionType;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * The controller of the option window class
 * 
 * @author Alexandre
 */
public class Controller implements EventHandler
	{
	/**
	 * Variables
	 */
	@FXML
	private CheckBox optionPopup, optionReverse, optionEmail;
	@FXML
	private URL location;
	@FXML
	private ResourceBundle resources;
	
	/**
	 * This method is called by the FXMLLoader when initialization is complete
	 */
    public void initialize()
		{
		//Variables.setController(this);
		
		optionPopup.setOnAction(this);
		optionReverse.setOnAction(this);
		optionEmail.setOnAction(this);
		}

	@Override
	public void handle(Event evt)
		{
		if(evt.getSource() == this.optionPopup)
			{
			Variables.getLogger().debug("Click on option popup");
			UsefulMethod.setOption(optionType.incomingcallpopup, this.optionPopup.isSelected());
			}
		else if(evt.getSource() == this.optionEmail)
			{
			Variables.getLogger().debug("Click on option email");
			UsefulMethod.setOption(optionType.emailreminder, this.optionEmail.isSelected());
			}
		else if(evt.getSource() == this.optionReverse)
			{
			Variables.getLogger().debug("Click on option reverse lookup");
			UsefulMethod.setOption(optionType.reverselookup, this.optionReverse.isSelected());
			}
		}

	public CheckBox getOptionPopup()
		{
		return optionPopup;
		}

	public CheckBox getOptionReverse()
		{
		return optionReverse;
		}

	public CheckBox getOptionEmail()
		{
		return optionEmail;
		}
	}

/*2019*//*RATEL Alexandre 8)*/