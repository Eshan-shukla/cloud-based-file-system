/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ntu-user
 */
public class LogInController {

    @FXML
    private TextField txtUsername;

    
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogIn;
    @FXML
    private Button btnSignUp;
    
    @FXML
    private Label lblIncorrect;
    
    private static String s;

    /**
     * Initializes the controller class.
     */
    
    public void initialize() {
        // TODO
        btnLogIn.setDisable(true);
        
    }    

    @FXML
    private void onClickLogIn(ActionEvent event) throws IOException {
        s = txtUsername.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        Account a = new Account();
        boolean validateUser = a.validateUser(username, password);
        System.out.println(validateUser);
        if(validateUser){
            System.out.println("successfully logged in!!");
            Stage s = new Stage();
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("screen1.fxml"));
            Parent root = fxml.load();
            Scene scene = new Scene(root);
            s.setScene(scene);
            s.setTitle("File Manager");
            s.show();
            Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
            st.close();
            
        } else{
            lblIncorrect.setText("Incorrect Username or Password.");
            txtUsername.clear();
            txtPassword.clear();
        }
       
    }

    @FXML
    private void onClickSignUp(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("signUp.fxml"));
        Parent root = fxml.load();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Sign Up");
        stage.show();
    }
    
    @FXML
    private void onKeyReleased(){
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        boolean isDisabled = (username.isEmpty() || password.isEmpty() || (password.length() <= 9));
        btnLogIn.setDisable(isDisabled);
    }

    public static String getTxtUsername() {
        
        return s;
    }
    
    
    
}
