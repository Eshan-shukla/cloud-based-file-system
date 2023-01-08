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
    
    public void setFileToBeShared(String filename){
        this.fileToBeShared = filename;
    }
    
    public void setPath(String p){
        this.path = p;
    }
    
    @FXML
    private void onClickShare(ActionEvent event){
        String per = "";
        if(readWrite.isSelected()){
            per = "rw";
        } else if(readOnly.isSelected()){
            per = "ro";
        }
        String filename = this.fileToBeShared;
        String username = txtUsername.getText();
        String p = "";
        FileOperation fo = new FileOperation();
        String remotepath = this.path;
        String localpath = "/home/ntu-user/NetBeansProjects/files/" + filename;
        String despath = "/tmp/files/" + txtUsername.getText() + "/" + filename;
        boolean check = Account.checkUser(txtUsername.getText());
        if(check){
            try{
                JSch jsch = new JSch();
                //jsch.addIdentity("/home/eshan/NetBeansProjects/network/SFTP/src/main/java/org/openjfx/sftp/pvk");
                //jsch.setKnownHosts("/home/eshan/.ssh/known_hosts");
                Session session = jsch.getSession("root","172.20.0.3",22);
                session.setPassword("soft40051_pass");
                session.setConfig("StrictHostKeyChecking", "no");
                session.connect();
                ChannelSftp channel = (ChannelSftp)session.openChannel("sftp");
                channel.connect();
                channel.get(remotepath, localpath);
                if(per.equals("ro")){
                    File file = new File(localpath);
                    file.setReadOnly();
                }
                channel.put(localpath, despath);
                channel.disconnect();
                session.disconnect();
            }catch(JSchException ex){
                System.out.println(ex);

            } catch (SftpException ex) {
                System.out.println(ex);

            }
            File file = new File(localpath);
            if(file.exists()){
                if(file.delete()){}
            }

            Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
            s.close();
        } else{
            lbl.setText("Username does not exist");
        }      
    }
}
