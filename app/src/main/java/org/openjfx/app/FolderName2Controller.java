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
public class FolderName2Controller {
    @FXML
    private TextField txtName;
    private String path;
    private String renamePath;
    
    public void setPath(String p){
        this.path = p;
    }
    
    public void setRenamePath(String p){
        this.renamePath = p;
    }
    
    @FXML
    private void onClickCreate(ActionEvent event) {
        String fileName = this.renamePath + "\\" + txtName.getText();
        //String fullPath = this.path + "/" + fileName;
        try{
            File file = new File(this.path);
            File desFile = new File(fileName);
            if(file.renameTo(desFile)){
            }
        }catch(NullPointerException ex){
            System.out.println("error");
            FileOperation.showError();
        }catch(SecurityException ex){
            System.out.println("error");
            FileOperation.showError();
        }
        Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
        s.close();
        try{
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("screen1.fxml"));
            Parent root = fxml.load();
            Screen1Controller sc = fxml.getController();
            //sc.initialize(username);
            sc.changeStage();
        }catch(IOException ex){
            System.out.println("error");
            FileOperation.showError();
        }
    }
    
}
