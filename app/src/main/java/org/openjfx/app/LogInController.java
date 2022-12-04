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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ntu-user
 */
public class LogInController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnLogIn;
    @FXML
    private Button btnSignUp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickLogIn(ActionEvent event) {
    }

    @FXML
    private void onClickSignUp(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("signUp.fxml"));
        Parent root = fxml.load();
        Scene scene = new Scene(root, 400,400);
        
        stage.setScene(scene);
        stage.setTitle("Sign Up");
        stage.show();
    }
    
}
