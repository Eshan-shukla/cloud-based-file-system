/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.app;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
/**
 * FXML Controller class
 *
 * @author ntu-user
 */


public class Screen1Controller {

    @FXML
    private MenuItem closeFile;

    @FXML
    private ListView<String> myListView;

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
    
    @FXML
    private TextArea txtArea;
    
    String[] files = {};
    
    private static String username;
    
    private static Stage stageOfTextArea;
   
    public void initialize() {
       this.username = LogInController.getTxtUsername();
       System.out.println("inside initialize");
       String dirPath = "/home/ntu-user/NetBeansProjects/files/";
       String path = dirPath + this.username;
       try{
           File dir = new File(path);
           this.files = dir.list();
           for(String s : files){
               System.out.println(s);
           }
       } catch(SecurityException ex){
           System.out.println("error");
       } catch(NullPointerException ex){
           System.out.println("which error");
       }
       myListView.getItems().addAll(files);
       
        
    }

    public void setUsername(String user) {
        this.username = user;
    }
    
    @FXML
    private void addfile(ActionEvent event) {

    }

    @FXML
    private void createfile(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("filename.fxml"));
        Parent root = fxml.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("File name");
        stage.show();
        stageOfTextArea = (Stage)txtArea.getScene().getWindow();
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
    

    
    
    
    public void changeStage() throws IOException{
        System.out.println("inside changeState");
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("screen1.fxml"));
        Parent root = fxml.load();
        Scene s = new Scene(root);
        stageOfTextArea.setScene(s);
        stageOfTextArea.show();
        System.out.println("passesd");
    }

}