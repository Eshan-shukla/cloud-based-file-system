/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.app;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author N1097098
 */
public class ShareFileController {
    @FXML
    private TextField txtUsername;
    @FXML
    private Label lbl;
    @FXML
    private RadioButton readWrite, readOnly;   
    private String fileToBeShared;
    private String path;
    private String fixedPath = "C:\\Users\\eshan\\OneDrive\\Desktop\\files\\";
    
    public void setFileToBeShared(String filename){
        this.fileToBeShared = filename;
    }
    
    public void setPath(String p){
        this.path = p;
    }
    
    @FXML
    private void onClickShare(ActionEvent event) throws IOException{
        String filename = this.fileToBeShared;
        String username = txtUsername.getText();
        String sourcePath = this.path;
        String desPath = fixedPath + username;
        boolean check = Account.checkUser(txtUsername.getText());
        if(check){
              FileOperation fo = new FileOperation();
              //create file in desPath
              fo.createFile(desPath, filename);
              
              //extract content from sourcePath
              File source = new File(sourcePath);
              String content = "";
              if(source.exists()){
                FileReader fr = new FileReader(source);
                BufferedReader br = new BufferedReader(fr);
                String line = null;
                while((line = br.readLine()) != null){
                    content = content + line + "\n";
                }
                br.close();
                fr.close();
              }
              
              //write contnt into desPath
              fo.writeContent(content, desPath + "\\" + filename);

            Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
            s.close();
        } else{
            lbl.setText("Username does not exist");
        }      
    }
}
