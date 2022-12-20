/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.app;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author ntu-user
 */
public class FileNameController {
    @FXML
    private TextField txtFileName;
    
    @FXML
    private Button btnCancel;
    
    @FXML
    private Button btnCreate;
    
    @FXML
    private Label lblError;
    private String path;
 
    
    public void initialize(){
        
    }
    public void setPath(String path){
        this.path = path;
    }
    
    
    @FXML
    private void onClickCreate(ActionEvent event){
        String filename = txtFileName.getText();
        String username = LogInController.getTxtUsername();
        FileOperation fo = new FileOperation();
        //fo.setUsername(this.path, filename);
        boolean check = fo.createFile(this.path, filename);
        if(!check){
            //System.out.println("file already exists");
            lblError.setText("File of this name already exists.");
        } else{
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();
            try{
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("screen1.fxml"));
                Parent root = fxml.load();
                Screen1Controller sc = fxml.getController();
            //sc.initialize(username);
                sc.changeStage();
            }catch(IOException ex){
                System.out.println("error");
            
            }
        }
        
        
        
    }
    
    
    @FXML
    private void onClickCancel(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
