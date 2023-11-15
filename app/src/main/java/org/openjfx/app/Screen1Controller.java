/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.app;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
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
    private MenuItem AccDelete;
    @FXML
    private MenuItem close;
    @FXML
    private TextArea txtArea;
    String[] fileName = {};
    private static String username;
    private static Stage stageOfTextArea;   
    private String currentFileSelected;
    private String fixedPath = "C:\\Users\\eshan\\OneDrive\\Desktop\\files\\";
   
    public void initialize() {
       this.username = LogInController.getTxtUsername();
        String path = fixedPath + username;
        Vector<String> files = new Vector<String>();
        TreeItem<String> root = new TreeItem<>("Your Folders");
        files = getFileNames(path);
        //Vector<TreeItem<String>> branches = createView(files, path + "/");
        Vector<TreeItem<String>> branches = createView(files, path + "\\");
        for(int i = 0; i < branches.size(); ++i){
            root.getChildren().add(branches.get(i));
        }
        myTreeView.setRoot(root);           
    }
        
    private Vector<String> getFileNames(String path){
        Vector<String> filenames = new Vector<String>();
        int len = 0;
        // File object
        File maindir = new File(path);
        if(maindir.exists() && maindir.isDirectory()){
            String[] files = maindir.list();
            for(String s : files){
                //String name = s.getFilename();
                if(!((s.equals(".")) || s.equals(".."))){
                    filenames.add(s);
                    
                }
                
            }
        }
        return filenames;
    }
    
    private boolean isDir(String path){
        File file = new File(path);
        if(file.isDirectory()){
            return true;
        }else{
            return false;
        }
 
    }
    
    private Vector<TreeItem<String>> createView(Vector<String> fileName, String rootPath){
        Vector<TreeItem<String>> branches = new Vector<TreeItem<String>>();
        String tempPath = rootPath;
        for(String s : fileName){
            if(!((s.equals(".")) || s.equals(".."))){
            tempPath = rootPath + s;
            //File f = new File(tempPath);
            if(isDir(tempPath)){
                TreeItem<String> root = new TreeItem<String>(s);
                fileName = getFileNames(tempPath);
                Vector<TreeItem<String>> tempBranch = createView(fileName, tempPath + "/");
                for(int i = 0; i < tempBranch.size(); ++i){
                    root.getChildren().add(tempBranch.get(i));
                    
                }
                branches.add(root);
            }
            else{
                TreeItem<String> branch = new TreeItem<String>(s);
                branches.add(branch);
            }
                
        }
       
        }
        return branches;
  }
    

    public void setUsername(String user) {
        this.username = user;
    }
    
    /**
     * @brief Uploads localfiles into the system.
     * @param event 
     */
    @FXML
    private void onClickUpload(ActionEvent event) {
        //String path = "/home/ntu-user/NetBeansProjects/files";
        String username = LogInController.getTxtUsername();
        String p = fixedPath + username;
        FileOperation fo = new FileOperation();
        FileChooser fileChooser = new FileChooser();
          fileChooser.setTitle("Select Text File");
          fileChooser.getExtensionFilters().addAll(
             new FileChooser.ExtensionFilter("Text Files", "*.txt")
          );
          File selectedFile = fileChooser.showOpenDialog(contextMenu);
          String f = selectedFile.getName();
          //String fullPath = path + "/" + username + "/" + f;
          String fullPath = fixedPath + "\\" + username + "\\" + f;
          if(fo.createFile(p, f)){
              try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                String line = null;
                String content = "";
                while ((line = reader.readLine()) != null) {
                   content = content + line + "\n";
                }
                File ff = new File(fullPath);
                BufferedWriter writer = new BufferedWriter(new FileWriter(ff));
                writer.write(content);
                writer.flush();
                writer.close();
                reader.close();
             } catch (IOException e) {
                e.printStackTrace();
                FileOperation.showError();
             }
          }
          stageOfTextArea = (Stage)txtArea.getScene().getWindow();
          try{
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("screen1.fxml"));
                Parent root = fxml.load();
                Screen1Controller sc = fxml.getController();
                sc.changeStage();
            }catch(IOException ex){
                System.out.println("error");
                FileOperation.showError();
            }
    }
    
    /**
     * @brief creates a new file
     * @param event
     * @throws IOException 
     */
    @FXML
    private void onClickCreateFile(ActionEvent event) throws IOException {
        String username = LogInController.getTxtUsername();
        String path = "/tmp/files/" + username;
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
    
    /**
     * @brief creates a new directory
     * @param event 
     */
    @FXML
    private void onClickCreateDirectory(ActionEvent event){
        try {
            String username = LogInController.getTxtUsername();
            //String path = "/tmp/files/" + username;                  //path of the container
            String path = fixedPath + username;
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
            FileOperation.showError();
        }
    }
    
    /**
     * @brief saves the content to the file
     * @param event 
     */
    @FXML
    private void onClickSave(ActionEvent event) {
        TreeItem<String> item = myTreeView.getSelectionModel().getSelectedItem();
        String despath = fixedPath + getPath(item);
        String content = txtArea.getText();
        try{
            File file = new File(despath);
//            if(file.createNewFile()){
//                
//            }else{
//                System.out.println("error");
//            }
            if(file.isFile()){
                if(file.canWrite()){
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(content + "\n");
                    bw.flush();
                    bw.close();
                    fw.close();
                }           
            }
        } catch (IOException ex) {
            System.out.println(" hh error");
            FileOperation.showError();
        }
    }

    
    @FXML
    private void onClickClose(ActionEvent event){
        
    }

    @FXML
    private void profile(ActionEvent event) {
         
    }
    
    /**
     * @brief logs out from the system
     * @param event 
     */
    @FXML
    private void logout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to logout?");
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK){
            stageOfTextArea = (Stage)txtArea.getScene().getWindow();
            try {
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("logIn.fxml"));
                Parent root = fxml.load();
                Scene scene = new Scene(root,400,400);
                stageOfTextArea.setScene(scene);
                stageOfTextArea.show();

            } catch (IOException ex) {
                ex.printStackTrace();
                FileOperation.showError();
            }


        }
    }
    
    /**
     * @brief shows the content of the files
     * @param event 
     */
    @FXML
    private void onClickContextOpen(ActionEvent event){
        String username = LogInController.getTxtUsername();
        TreeItem<String> item = myTreeView.getSelectionModel().getSelectedItem();
        String despath = fixedPath + getPath(item);
        try{
            File file = new File(despath);
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
            //if(file.delete()){}
            
        } catch (IOException ex) {
            System.out.println("error");
            FileOperation.showError();
        } catch(NullPointerException ex){
            System.out.println("error");
            FileOperation.showError();
        }
    }
    
    private String getPath(TreeItem<String> file){
        String filename = file.getValue();
        if(filename.equals("Your Folders")){
            return LogInController.getTxtUsername();
        } else {
            TreeItem<String> item = file.getParent();
            //String path = getPath(item) + "/" + file.getValue();
            String path = getPath(item) + "\\" + file.getValue();
            return path;
        }       
    }
    
    /**
     * @brief creates a new file in the directory
     * @param event
     * @throws IOException 
     */
    @FXML
    private void onClickContextCreateFile(ActionEvent event) throws IOException{
        TreeItem<String> item = myTreeView.getSelectionModel().getSelectedItem();
        String username = LogInController.getTxtUsername();
        //String path = "/tmp/files/" + getPath(item);
        String path = fixedPath + getPath(item);
        try{
            if(isDir(path)){
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
            FileOperation.showError();
        }
        
    }
    
    /**
     * @brief creates a new directory inside a directory 
     * @param event 
     */
    @FXML
    private void onClickContextCreateDir(ActionEvent event){
        TreeItem<String> item = myTreeView.getSelectionModel().getSelectedItem();
        String username = LogInController.getTxtUsername();
        String path = fixedPath + getPath(item);
        try{
            //File dir = new File(path);
            if(isDir(path)){
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
            FileOperation.showError();
        }
    }
    
    /**
     * @brief copies a file into another directory
     * @param event 
     */
    @FXML
    private void onClickContextCopy(ActionEvent event){
      FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file to copy");
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage); // stage is a reference to the current stage in your Javafx application

        if (file != null) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select destination folder");
        File destinationFolder = directoryChooser.showDialog(stage);
        if (destinationFolder != null) {
        try {
            File newFile = new File(destinationFolder, file.getName());
            try {
                Files.copy(file.toPath(), newFile.toPath());

                // file was successfully copied
            } catch (IOException e) {
                e.printStackTrace();
                FileOperation.showError();

            }
            stageOfTextArea = (Stage)txtArea.getScene().getWindow();
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("screen1.fxml"));
            Parent root = fxml.load();
            Screen1Controller sc = fxml.getController();
            sc.changeStage();
            // file could not be copied
        } catch (IOException ex) {
                ex.printStackTrace();
                FileOperation.showError();
        }
        }

    }}
    
    /**
     * @brief moves a file from one directory to another
     * @param event 
     */
    @FXML
    private void onClickContextMove(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file to move");
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage); // stage is a reference to the current stage in your Javafx application

        if (file != null) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select destination folder");
        File destinationFolder = directoryChooser.showDialog(stage);
        if (destinationFolder != null) {
            try {
                File newFile = new File(destinationFolder, file.getName());
                if (file.renameTo(newFile)) {
                    // file was successfully moved
                } else {
                    // file could not be moved
                }
                stageOfTextArea = (Stage)txtArea.getScene().getWindow();
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("screen1.fxml"));
                Parent root = fxml.load();
                Screen1Controller sc = fxml.getController();
                sc.changeStage();
            } catch (IOException ex) {
                Logger.getLogger(Screen1Controller.class.getName()).log(Level.SEVERE, null, ex);
                FileOperation.showError();
            }
        }

        }
    }
    
    /**
     * @brief shares a file with another user
     * @param event 
     */
    @FXML
    private void onClickContextShare(ActionEvent event){
        TreeItem<String> item = myTreeView.getSelectionModel().getSelectedItem();
        //String path = "/tmp/files/" + getPath(item);
        String path = fixedPath + getPath(item);
        String filename = item.getValue();
        //File file = new File(path);
        if(!(isDir(path))){
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
                FileOperation.showError();
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
                //tempPath = path + "/" + s;
                tempPath = path + "\\" + s;
                deleteDirectory(tempPath);
            }
            fo.deleteFile(path);
        }else{
            
            fo.deleteFile(path);
        }
        
    }
    
    /** 
     * @brief deletes a file from the directory
     * @param event 
     */
    @FXML
    private void onClickContextDelete(ActionEvent event){
        TreeItem<String> item = myTreeView.getSelectionModel().getSelectedItem();
        //String path = "/tmp/files/" + getPath(item);
        String path = fixedPath + getPath(item);
        deleteDirectory(path);
        //FileOperation fo = new FileOperation();
        //fo.deleteFile(path);
        try {
            stageOfTextArea = (Stage)txtArea.getScene().getWindow();
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("screen1.fxml"));
            Parent root = fxml.load();
            Screen1Controller sc = fxml.getController();
            sc.changeStage();      
        }catch (IOException ex) {
            System.out.println("errror");
            FileOperation.showError();
        }          
    }
    
    /**
     * @brief renames a file 
     * @param event 
     */
    @FXML
    private void onClickContextRename(ActionEvent event){
        TreeItem<String> item = myTreeView.getSelectionModel().getSelectedItem();
        TreeItem<String> parent = myTreeView.getSelectionModel().getSelectedItem().getParent();
        String renamePath = fixedPath + getPath(parent);
        String path = fixedPath + getPath(item);
        String filename = item.getValue();
        File file = new File(path);
        if(file.isFile()){
            Stage stage = new Stage();
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("foldername2.fxml"));
            Parent root;
            try {
                root = fxml.load();
                Scene scene = new Scene(root);
                FolderName2Controller sc = fxml.getController();
                //sc.setFileToBeShared(filename);
                sc.setPath(path);
                sc.setRenamePath(renamePath);
                stage.setScene(scene);
                stage.setTitle("Rename");
                stage.show();
                stageOfTextArea = (Stage)txtArea.getScene().getWindow();
            } catch (IOException ex) {
                System.out.println("error");
                FileOperation.showError();
            }
        }}
    
    
    /**
     * @brief deletes Account 
     * @param event 
     */
     @FXML
    private void deleteAccount(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete?");
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK){
            stageOfTextArea = (Stage)txtArea.getScene().getWindow();
            String Username = LogInController.getTxtUsername();
            try {
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("logIn.fxml"));
                Parent root = fxml.load();
                Scene scene = new Scene(root,400,400);
                stageOfTextArea.setScene(scene);
                stageOfTextArea.show();
                //delete from the database
                Account.deleteExistingUser(Username);
                deleteDirectory(fixedPath + Username);
            } catch (IOException ex) {
                ex.printStackTrace();
                FileOperation.showError();
            }
        }
//            String Username = LogInController.getTxtUsername();
//            Account.deleteExistingUser(Username);
//            FXMLLoader fxml = new FXMLLoader(getClass().getResource("signUp.fxml"));
//            Stage stage = new Stage();
//            Parent root = fxml.load();
//            Scene scene = new Scene(root);
//            stage.show();
//            stageOfTextArea = (Stage)txtArea.getScene().getWindow();

    }
    
    public void changeStage() throws IOException{
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("screen1.fxml"));
        Parent root = fxml.load();
        Scene s = new Scene(root);
        stageOfTextArea.setScene(s);
        stageOfTextArea.show();
    }

}