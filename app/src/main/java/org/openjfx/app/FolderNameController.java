/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.app;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author ntu-user
 */
public class FolderNameController {
    @FXML
    private TextField txtFolderName;
    
    @FXML
    private void onClickCreate(ActionEvent event){
        String dirName = txtFolderName.getText();
        String username = LogInController.getTxtUsername();
        String fullPath = "/home/ntu-user/NetBeansProjects/files/" + username + "/" + dirName;
        //String fullPath = this.path + "/" + dirName;
        try{
            File dir = new File(fullPath);
            if(dir.mkdir()){
                System.out.println("created inside account dir");
            }
        }catch(NullPointerException ex){
            System.out.println("error");
        }catch(SecurityException ex){
            System.out.println("error");
        }
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
    
    @FXML
    private void onClickCancel(ActionEvent event){
        
    }
}
