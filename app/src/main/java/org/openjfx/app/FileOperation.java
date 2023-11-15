/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author ntu-user
 */
public class FileOperation {
    private static final String FILEPATH = "/home/ntu-user/NetBeansProjects/files/";
    private String username;
    
    /**
     * @brief sets the username
     * @param username - username should be of type String
     */
    public void setUsername(String username) {
        //System.out.println(username);
        this.username = username;
    }
    
    /**
     * @brief Creates a directory in the container
     * @param path - path of the directory of type String 
     * @param dirname - name of the directory of type String
     * @return true if created successfully otherwise return false 
     */
    public boolean createDirectory(String path, String dirname){      
        //String fullPath = path + "/" + dirname;
        String fullPath = path + "\\" + dirname;
        try{
            // Create a Path object
            Path patht = Paths.get(fullPath);
            // Use the createDirectory method to create the directory
            Files.createDirectory(patht);
        }catch(IOException ex){
            System.out.println(ex);
            FileOperation.showError();
            return false;
        }
        
        return true;
    }
    
    /**
     * @brief Creates a file in the container
     * @param path - path of the file of type String
     * @param filename - name of the file of type String
     * @return true if created successfully otherwise false
     */
    public boolean createFile(String path, String filename){
        File file = new File(path + "\\" + filename);
        try {
            //create a file in local directory
            if(file.createNewFile()){
                return true;
            }
            else{
                return false;
            }
        
        } catch (IOException ex) {
            Logger.getLogger(FileOperation.class.getName()).log(Level.SEVERE, null, ex);
            FileOperation.showError();
        }
        return true;
    }
   
    
    public void writeContent(String line, String path){
        //write into the path
        File file = new File(path);
        try{
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(line+"\n");
            bw.flush();
            bw.close();
            fw.close();
        }catch(IOException ex){
            System.out.println("error");
            FileOperation.showError();
        }
    }
    
    /**
     * @brief deletes a file in the container
     * @param path - path of the file to be deleted, should be of type String
     */
    public void deleteFile(String path){
        try{
            File file = new File(path);
            if(file.delete()){}
            else{
                System.out.println("error");
            }
        }catch(NullPointerException ex){
            System.out.println("error");
            FileOperation.showError();
        }
    }
    
    public static void showError(){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Some error occured. Try again!");
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK){
            return;
        }
    }
    
}
    

