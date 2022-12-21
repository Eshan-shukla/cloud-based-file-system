/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author ntu-user
 */
public class ShareFileController {
    @FXML
    private TextField txtUsername;
    @FXML
    private Label lbl;
    private String fileToBeShared;
    private String path;
    
    public void setFileToBeShared(String filename){
        this.fileToBeShared = filename;
    }
    
    public void setPath(String p){
        this.path = p;
    }
    
    @FXML
    private void onClickShare(ActionEvent event){
        String filename = this.fileToBeShared;
        String username = txtUsername.getText();
        String p = "";
        FileOperation fo = new FileOperation();
        boolean check = Account.checkUser(txtUsername.getText());
        if(check){
            p = "/home/ntu-user/NetBeansProjects/files/" + username + "/";
            fo.shareFile(filename, username);
            File file = new File(this.path);
        try{
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            String content = "";
            while((line = br.readLine()) != null){
                content = content + line + "\n";
            }
            fo.writeContent(content, p+filename);
            fr.close();
            br.close();
                    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ShareFileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ShareFileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
        s.close();
        } else{
            //label user does not exist
            lbl.setText("Username does not exist");
        }
        
       
        
    }
}
