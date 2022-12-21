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

/**
 *
 * @author ntu-user
 */
public class FileOperation {
    private static final String FILEPATH = "/home/ntu-user/NetBeansProjects/files/";
    private String username;

    public void setUsername(String username) {
        //System.out.println(username);
        this.username = username;
    }
    
    public boolean createDirectory(String path, String filename){
        String fullPath = path + "/" + filename;
        try{
            File dir = new File(fullPath);
            if(dir.mkdir()){
                return true;
            }else{
                return false;                           // if new directory was not created successfully return false 
            }
        }catch(NullPointerException ex){
            System.out.println("error");
        }catch(SecurityException ex){
            System.out.println("error");
        }
        return true;
    }
    
    public boolean createFile(String path, String filename){
        String fullPath = path + "/" + filename;
        try{
            File file = new File(fullPath);
            if(file.createNewFile()){
                
            }else{
                return false;
            }
        }catch(NullPointerException ex){
            System.out.println("error");
        }catch(SecurityException ex){
            System.out.println("error");
        }catch(IOException ex){
            System.out.println("error");
        }
        return true;
    }
    
    //just share the file
    public void shareFile(String filename, String username){
        String path = "/home/ntu-user/NetBeansProjects/files/" + username + "/" + filename;
        File file = new File(path);
        try {
            if(file.createNewFile()){}
            //handle file with same name
        } catch (IOException ex) {
            System.out.println("error");
        }
        
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
        }
    }
    
    public void deleteFile(String path){
        try{
            File file = new File(path);
            if(file.delete()){}
            else{
                System.out.println("error");
            }
        }catch(NullPointerException ex){
            System.out.println("error");
        }
    }
    
}
    
// 
