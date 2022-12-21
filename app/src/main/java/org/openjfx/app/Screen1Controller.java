/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
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
    private ContextMenu contextMenu;
    @FXML
    private MenuItem open;
    @FXML
    private MenuItem createFileC;
    @FXML
    private MenuItem createDir;
    @FXML
    private MenuItem copy;
    @FXML
    private MenuItem move;
    @FXML
    private MenuItem share;
    @FXML
    private MenuItem delete;
    @FXML
    private MenuItem rename;
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
    private MenuItem close;
    @FXML
    private TextArea txtArea;
    String[] fileName = {};
    private static String username;
    private static Stage stageOfTextArea;
    
    private String currentFileSelected;
   
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
        String username = LogInController.getTxtUsername();
        String path = "/home/ntu-user/NetBeansProjects/files/" + username;
        Stage stage = new Stage();
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("filename.fxml"));
        Parent root = fxml.load();
        Scene scene = new Scene(root);
        FileNameController fc = fxml.getController();
        fc.setPath(path);
        stage.setScene(scene);
        stage.setTitle("File name");
        stage.show();
        stageOfTextArea = (Stage)txtArea.getScene().getWindow();
    }
    
    @FXML
    private void onClickCreateDirectory(ActionEvent event){
        try {
            String username = LogInController.getTxtUsername();
            String path = "/home/ntu-user/NetBeansProjects/files/" + username;
            Stage stage = new Stage();
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("foldername.fxml"));
            Parent root = fxml.load();
            Scene scene = new Scene(root);
            FolderNameController fc = fxml.getController();
            fc.setPath(path);
            stage.setScene(scene);
            stage.setTitle("File name");
            stage.show();
            stageOfTextArea = (Stage)txtArea.getScene().getWindow();
        } catch (IOException ex) {
            System.out.println("error");
        }
    }

    @FXML
    private void onClickSave(ActionEvent event) {
        TreeItem<String> item = myTreeView.getSelectionModel().getSelectedItem();
        String path = "/home/ntu-user/NetBeansProjects/files/" + getPath(item);
        String content = txtArea.getText();
        try{
            File file = new File(path);
            if(file.isFile()){
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.flush();
                bw.close();
                fw.close();
            }
        } catch (IOException ex) {
            System.out.println("error");
        }
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
    
    @FXML
    private void onClickContextOpen(ActionEvent event){
        TreeItem<String> item = myTreeView.getSelectionModel().getSelectedItem();
        String path = "/home/ntu-user/NetBeansProjects/files/" + getPath(item);
        try{
            File file = new File(path);
            if(file.isFile()){
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line = null;
                String content = "";
                while((line = br.readLine()) != null){
                    content = content + line + "\n";
                }
                txtArea.setText(content);
                br.close();
                fr.close();
            }
        } catch (IOException ex) {
            System.out.println("error");
        } catch(NullPointerException ex){
            System.out.println("error");
        }
    }
    
    private String getPath(TreeItem<String> file){
        String filename = file.getValue();
        if(filename.equals("Your Folders")){
            return LogInController.getTxtUsername();
        } else {
            TreeItem<String> item = file.getParent();
            String path = getPath(item) + "/" + file.getValue();
            return path;
        }       
    }
    
    @FXML
    private void onClickContextCreateFile(ActionEvent event) throws IOException{
        TreeItem<String> item = myTreeView.getSelectionModel().getSelectedItem();
        String username = LogInController.getTxtUsername();
        String path = "/home/ntu-user/NetBeansProjects/files/" + getPath(item);
        try{
            File dir = new File(path);
            if(dir.isDirectory()){
                Stage stage = new Stage();
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("filename.fxml"));
                Parent root = fxml.load();
                Scene scene = new Scene(root);
                FileNameController fc = fxml.getController();
                fc.setPath(path);
                stage.setScene(scene);
                stage.setTitle("File Name");
                stage.show();
                stageOfTextArea = (Stage)txtArea.getScene().getWindow();
            }
        }catch(IOException ex){
            System.out.println("error");
        }
        
    }
    
    
    @FXML
    private void onClickContextCreateDir(ActionEvent event){
        TreeItem<String> item = myTreeView.getSelectionModel().getSelectedItem();
        String username = LogInController.getTxtUsername();
        String path = "/home/ntu-user/NetBeansProjects/files/" + getPath(item);
        try{
            File dir = new File(path);
            if(dir.isDirectory()){
                Stage stage = new Stage();
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("foldername.fxml"));
                Parent root = fxml.load();
                Scene scene = new Scene(root);
                FolderNameController fc = fxml.getController();
                fc.setPath(path);
                stage.setScene(scene);
                stage.setTitle("File Name");
                stage.show();
                stageOfTextArea = (Stage)txtArea.getScene().getWindow();
            }
        }catch(IOException ex){
            System.out.println("error");
        }
    }
    
    @FXML
    private void onClickContextCopy(ActionEvent event){
      
    }
    
    @FXML
    private void onClickContextMove(ActionEvent event){
        
    }
    
    @FXML
    private void onClickContextShare(ActionEvent event){
        TreeItem<String> item = myTreeView.getSelectionModel().getSelectedItem();
        String path = "/home/ntu-user/NetBeansProjects/files/" + getPath(item);
        String filename = item.getValue();
        File file = new File(path);
        if(file.isFile()){
            Stage stage = new Stage();
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("sharefile.fxml"));
            Parent root;
            try {
                root = fxml.load();
                Scene scene = new Scene(root);
                ShareFileController sc = fxml.getController();
                sc.setFileToBeShared(filename);
                sc.setPath(path);
                stage.setScene(scene);
                stage.setTitle("Username");
                stage.show();
                stageOfTextArea = (Stage)txtArea.getScene().getWindow();
            } catch (IOException ex) {
                System.out.println("error");
            }
        }
        
    }
    
    private void deleteDirectory(String path){
        FileOperation fo = new FileOperation();
        File dir = new File(path);
        if(dir.isDirectory()){
            String[] files = dir.list();
            for(String s : files){
                String tempPath = "";
                tempPath = path + "/" + s;
                deleteDirectory(tempPath);
            }
            fo.deleteFile(path);
        }else{
            
            fo.deleteFile(path);
        }
        return;
    }
    
    @FXML
    private void onClickContextDelete(ActionEvent event){
        TreeItem<String> item = myTreeView.getSelectionModel().getSelectedItem();
        String path = "/home/ntu-user/NetBeansProjects/files/" + getPath(item);
        deleteDirectory(path);
        try {
            stageOfTextArea = (Stage)txtArea.getScene().getWindow();
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("screen1.fxml"));
            Parent root = fxml.load();
            Screen1Controller sc = fxml.getController();
            sc.changeStage();      
        }catch (IOException ex) {
            System.out.println("errror");
        }          
    }
    
    @FXML
    private void onClickContextRename(ActionEvent event){
        
    }
    
    public void changeStage() throws IOException{
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("screen1.fxml"));
        Parent root = fxml.load();
        Scene s = new Scene(root);
        stageOfTextArea.setScene(s);
        stageOfTextArea.show();
    }

}