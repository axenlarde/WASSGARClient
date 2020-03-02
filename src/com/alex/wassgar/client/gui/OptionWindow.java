package com.alex.wassgar.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Option window using JavaFX window
 * 
 * @author Alexandre
 */
public class OptionWindow extends Application
	{
	/**
	 * Constructor
	 */
	
	@Override
	public void start(Stage stage) throws Exception
		{
		Parent root = FXMLLoader.load(getClass().getResource("OptionWindow.fxml"));
		Scene scene = new Scene(root);
		stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setTitle("Wassgar client options");
        stage.show();
		}
	}

/*2019*//*RATEL Alexandre 8)*/