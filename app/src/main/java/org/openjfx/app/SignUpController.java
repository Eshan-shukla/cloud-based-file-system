/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.app;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class SignUpController {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtConfirmPassword;
    @FXML
    private Button btnSignUp;
    @FXML
    private Label lblWrongPassword;
    @FXML
    private Label lblIncorrectPassword;

    /**
     * Initializes the controller class.
     */
    
    public void initialize() {
        // TODO
        btnSignUp.setDisable(true);
    }    

    @FXML
    private void onClickSignUp(ActionEvent event) {
        String username = txtUsername.getText();
        String path = "/home/ntu-user/NetBeansProjects/files/";
        String filename = username;
        FileOperation fo = new FileOperation();
        if(fo.createDirectory(path, filename)){
            String password = txtConfirmPassword.getText();
            String cpassword = txtPassword.getText();
            boolean check = matchPasswords(password, cpassword);
            boolean correctPassword = checkPasswordString(cpassword);
            if(!check){
                 lblWrongPassword.setText("Password and Confirm password do not match.");
            } else{
                if(correctPassword){
                    Account.createNewUser(username, password);
                    Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
                    s.close();
                } else{
                    lblIncorrectPassword.setText("Password must contain atleast a number and a special character.");
                }       
            }
        } else{
            lblWrongPassword.setText("Username already exists.");
        }
    }
    
    @FXML
    private void onKeyReleased(){
        String password = txtConfirmPassword.getText();
        String username = txtUsername.getText();
        String cpassword = txtPassword.getText();
        boolean isDisabled = (password.isEmpty() || username.isEmpty() || cpassword.isEmpty() || (password.length() <= 9) || (cpassword.length() <= 9));
        btnSignUp.setDisable(isDisabled);
    }
    
    private boolean matchPasswords(String password, String cpassword){
        boolean result = false;
        if(password.equals(cpassword)){
            result = true;
        }
        return result;
    }
    
    private boolean checkPasswordString(String password){
        boolean result = false;
        boolean numeral = false;                
        boolean specialCharacter = false;
        int length = password.length();
        for(int i = 0; i < length; ++i){
            //check each char for numeral
            int charNumeral = password.charAt(i);
            if((charNumeral >= 48) && (charNumeral <= 57)){
                numeral = true;
            }
            //check for special character in the string
            int charSpecial = password.charAt(i);
            if(((charSpecial >= 33) && (charSpecial <= 47)) || (charSpecial == 64)){
                specialCharacter = true;
            }
        }
        if(numeral && specialCharacter){
            result = true;
            return result;
        } else{
            return result;
        }       
    }
    
}
