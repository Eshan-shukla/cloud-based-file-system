/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.app;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 *
 * @author ntu-user
 */
public class ShareFileController {
    @FXML
    private TextField txtUsername;
    private String fileToBeShared;
    
    public void setFileToBeShared(String filename){
        this.fileToBeShared = filename;
    }
    
    @FXML
    private void onClickShare(ActionEvent event){
        String username = txtUsername.getText();
        String path = "/home/ntu-user/NetBeansProjects/files/" + username + "/" + fileToBeShared;
        try{
            File file = new File(path);
            if(file.createNewFile()){
                //send the content of the file as well
                
            }
        }catch(NullPointerException ex){
            System.out.println("error");
        }catch(SecurityException ex){
            System.out.println("error");
        }catch(IOException ex){
            System.out.println("error");
        }
        
    }
}
