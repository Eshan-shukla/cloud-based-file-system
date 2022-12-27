/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.app;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author ntu-user
 */
public class readOnlyController {
    @FXML
    private Button btnOk;
    
    @FXML
    private void onClickOk(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        try{
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("screen1.fxml"));
            Parent root = fxml.load();
            Screen1Controller sc = fxml.getController();
            sc.changeStage();
        }catch(IOException ex){
            System.out.println("error");

        }
    }
}
