/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.app;

import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
/**
 * FXML Controller class
 *
 * @author ntu-user
 */


public class Screen1Controller implements Initializable{

    @FXML
    private MenuItem closeFile;

    @FXML
    private ListView<String> myListview;

    @FXML
    private MenuItem shareFile;

    @FXML
    private MenuItem saveFile;

    @FXML
    private MenuItem myprofile;

    @FXML
    private MenuItem logOut;

    @FXML
    private MenuItem addFile;

    @FXML
    private MenuItem createFile;
    
    String[] files = {"ABC"};
    
    @FXML
    private void addfile(ActionEvent event) {

    }

    @FXML
    private void createfile(ActionEvent event) {

    }

    @FXML
    private void savefile(ActionEvent event) {

    }


    @FXML
    private void closefile(ActionEvent event) {

    }

    @FXML
    private void profile(ActionEvent event) {

    }

    @FXML
    private void logout(ActionEvent event) {

    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       myListview.getItems().addAll(files);
    }

}