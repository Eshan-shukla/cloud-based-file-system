/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.app;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
/**
 * FXML Controller class
 *
 * @author ntu-user
 */


public class Screen1Controller {
    @FXML
    private TreeView<String> myTreeView;
    @FXML
    private MenuItem myprofile;
    @FXML
    private MenuItem logOut;
    @FXML
    private MenuItem upload;
    @FXML
    private MenuItem createFile;
    @FXML
    private MenuItem createDirectory;
    @FXML
    private MenuItem save;
    @FXML
    private MenuItem share;
    @FXML
    private MenuItem close;
    @FXML
    private TextArea txtArea;
    String[] fileName = {};
    private static String username;
    private static Stage stageOfTextArea;
   
    public void initialize() {
       this.username = LogInController.getTxtUsername();
       System.out.println("inside initialize");
       String dirPath = "/home/ntu-user/NetBeansProjects/files/";
       String path = dirPath + this.username;
       try{
           File dir = new File(path);
           this.fileName = dir.list();
           TreeItem<String> root = new TreeItem<>("Your Folders");
           Vector<TreeItem<String>> branches = createView(fileName, path + "/");
           int len = branches.size();
           for(int i = 0; i < len; ++i){
               root.getChildren().add(branches.get(i));
           }
         myTreeView.setRoot(root);
       } catch(SecurityException ex){
           System.out.println("error");
       } catch(NullPointerException ex){
           System.out.println("which error");
       }
       
        
    }
    
    private Vector<TreeItem<String>> createView(String[] fileName, String rootPath){
        Vector<TreeItem<String>> branches = new Vector<TreeItem<String>>();
        String tempPath = rootPath;
        for(String s : fileName){
            tempPath = rootPath + s;
            try{
                File f = new File(tempPath);
                if(f.isDirectory()){
                    TreeItem<String> root = new TreeItem<String>(s);
                    fileName = f.list();
                    Vector<TreeItem<String>> tempBranch = createView(fileName, tempPath + "/");
                    int len = tempBranch.size();
                    for(int i = 0; i < len; ++i){
                        root.getChildren().add(tempBranch.get(i));
                    }
                    branches.add(root);
                }else{
                    TreeItem<String> branch = new TreeItem<String>(s);
                    branches.add(branch);
                }
                
            }catch(SecurityException ex){
                System.out.println("error");
            } catch(NullPointerException ex){
                System.out.println("which error");
            }
            
        }
        return branches;
    }

    public void setUsername(String user) {
        this.username = user;
    }
    
    @FXML
    private void onClickUpload(ActionEvent event) {

    }

    @FXML
    private void onClickCreateFile(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("filename.fxml"));
        Parent root = fxml.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("File name");
        stage.show();
        stageOfTextArea = (Stage)txtArea.getScene().getWindow();
    }
    
    @FXML
    private void onClickCreateDirectory(ActionEvent event){
        
    }

    @FXML
    private void onClickSave(ActionEvent event) {

    }


    @FXML
    private void onClickShare(ActionEvent event) {

    }
    
    @FXML
    private void onClickClose(ActionEvent event){
        
    }

    @FXML
    private void profile(ActionEvent event) {

    }

    @FXML
    private void logout(ActionEvent event) {

    }
    

    
    
    
    public void changeStage() throws IOException{
        System.out.println("inside changeState");
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("screen1.fxml"));
        Parent root = fxml.load();
        Scene s = new Scene(root);
        stageOfTextArea.setScene(s);
        stageOfTextArea.show();
        System.out.println("passesd");
    }

}