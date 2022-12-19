/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.app;

import java.io.File;
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
    
}
    
//    /**
//     * @brief This function creates a file in the directory if it does not exists.
//     * @param filename
//     * @return returns true if new file is added, otherwise false.
//     */
//    public boolean createFile(String filename){
//        String directoryPath = FILEPATH + this.username;
//        //create a directory named this.username inside files directory
//        try{
//            File dir = new File(directoryPath);
//            if(dir.mkdir()){
//                System.out.println("created!!!!!!");
//            }
//        }catch(NullPointerException ex){
//            System.out.println("error");
//        }catch(SecurityException ex){
//            System.out.println("error");
//        }
//        
//        //Now, put the file inside the above directory
//        String fullPath = FILEPATH + this.username + "/" + filename;
//        try{
//            File file = new File(fullPath);
//            if(file.exists()){
//                return false;
//            }
//            if(file.createNewFile()){
//                return true;
//            }
//            
//        }catch(NullPointerException ex){
//            System.out.println("error");
//        }catch(SecurityException ex){
//            System.out.println("error");
//        }catch(IOException ex){
//            System.out.println("error");
//        }
//        
//        return true;
//    }
//    
//}
