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
    
    public boolean createDirectory(String path, String dirname){       //create directory in the container
        String fullPath = path + "/" + dirname;
//        try{
//            File dir = new File(fullPath);
//            if(dir.mkdir()){
//                return true;
//            }else{
//                return false;                           // if new directory was not created successfully return false 
//            }
//        }catch(NullPointerException ex){
//            System.out.println("error");
//        }catch(SecurityException ex){
//            System.out.println("error");
//        } 
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
            channel.mkdir(fullPath);
            channel.disconnect();
            session.disconnect();
        }catch(JSchException ex){
            System.out.println(ex);
            return false;
        } catch (SftpException ex) {
            System.out.println(ex);
            return false;
        }
        
        return true;
    }
    
    public boolean createFile(String path, String filename){
        String localfile = "/home/ntu-user/NetBeansProjects/files/";
        String desfile = path + "/" + filename;
        File file = new File(localfile + filename);
        try {
            //create a file in local directory
            if(file.createNewFile()){}
            else{
                return false;
            }
            //        try{
//            File file = new File(fullPath);
//            if(file.createNewFile()){
//                
//            }else{
//                return false;
//            }
//        }catch(NullPointerException ex){
//            System.out.println("error");
//        }catch(SecurityException ex){
//            System.out.println("error");
//        }catch(IOException ex){
//            System.out.println("error");
//        }
        
        } catch (IOException ex) {
            Logger.getLogger(FileOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            channel.put(localfile+filename, desfile);
            channel.disconnect();
            session.disconnect();
        }catch(JSchException ex){
            System.out.println(ex);
            return false;
        } catch (SftpException ex) {
            System.out.println(ex);
            return false;
        }
        if(file.delete()){}
        
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
    
    public void writeContent(String line, String path, String permission){
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
        if(file.exists()){
            if(permission.equals("ro")){
                file.setReadOnly();
            }
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
