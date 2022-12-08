/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ntu-user
 */
public class Account {
    
    private static final String FILENAME = "jdbc:sqlite:/home/ntu-user/NetBeansProjects/Course-Work/app/Account.db";
    private static final String TABLENAME = "Users";
    
    /**
     * @brief Add username and password to the database.
     * @param username of type String
     * @param password of type String
     */
    public static void createNewUser(String username, String password){
        Connection connection = null;
        Statement stmt = null;
        
        try{
            connection = DriverManager.getConnection(FILENAME);
            stmt = connection.createStatement();
            stmt.setQueryTimeout(30);
            String sql = "INSERT INTO " + TABLENAME + " (Username, Password)" + " VALUES ('"+ username + "', '" + password + "');";
            stmt.executeUpdate(sql);
            
        } catch(SQLException ex){
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error");
        } finally{
            try{
                if(connection!=null){
                    connection.close();
                }       
                if(stmt != null){
                    stmt.close();
                }
            }catch(SQLException ex){
                System.out.println("error!!!");
            }
        }
        
    }
    
    /**
     * @brief verify username and password against the database
     * @param username of type String
     * @param password of type String
     * @return true if username and passwords exists, otherwise false
     */
    public static boolean validateUser(String username, String password){
        boolean result = false;
        Connection connection = null;
        Statement stmt = null;
        
        try{
            connection = DriverManager.getConnection(FILENAME);
            stmt = connection.createStatement();
            stmt.setQueryTimeout(30);
            String sql = "SELECT Username, Password FROM " + TABLENAME + ";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String user = rs.getString("Username");
                String pass = rs.getString("Password");
                
                if(username.equals(user) && password.equals(pass)){
                    result = true;
                }
            }
            
        } catch(SQLException ex){
            System.out.println("error");
            
        } finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
                if(connection != null){
                    connection.close();
                }
                
            } catch(SQLException ex){
                System.out.println("error");
            }
        }
        return result;
    }
    
    public void deleteExistingUser(){
        
    }
    
    public void updatePassword(String newPassword){
        
    }
    
    
    
    
    
}
