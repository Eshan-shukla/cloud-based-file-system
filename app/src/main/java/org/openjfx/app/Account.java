/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.random;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author N1097098
 */
public class Account {
    
    private static final String FILENAME = "jdbc:sqlite:C:\\Users\\eshan\\OneDrive\\Desktop\\cloud-app\\cloud-based-file-system\\app\\Account.db";
    private static final String TABLENAME = "Users";
    private int iterations = 10000;
    private int keylength = 256;
    private Random random = new SecureRandom();
    private String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private String saltValue;
    private static final String fixedPath = "C:\\Users\\eshan\\OneDrive\\Desktop\\files\\";
    
    Account(){
        try {
            File fp = new File(".salt");
            if (!fp.exists()) {
                saltValue = this.getSaltvalue(30);
                FileWriter myWriter = new FileWriter(fp);
                myWriter.write(saltValue);
                myWriter.close();
            } else {
                Scanner myReader = new Scanner(fp);
                while (myReader.hasNextLine()) {
                    saltValue = myReader.nextLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            FileOperation.showError();
        }
    }
    
    /**
     * @brief checks if the username exists
     * @param username - should be of type String
     * @return true if username exists, otherwise false
     */
    public static boolean checkUser(String username){
        //String path = "/tmp/files/";
        String path = fixedPath + username;
        File dir = new File(path);
        if(dir.isDirectory()){
            return true;
        }
        return false;
    }
    
    /**
     * @brief Add username and password to the database.
     * @param username of type String
     * @param password of type String
     */
    public void createNewUser(String username, String password){
        Connection connection = null;
        Statement stmt = null;
        try {
            password = generateSecurePassword(password);
        } catch (InvalidKeySpecException ex) {
            System.out.println("error");
            FileOperation.showError();
        }
        try{
            connection = DriverManager.getConnection(FILENAME);
            stmt = connection.createStatement();
            stmt.setQueryTimeout(30);
            String sql = "INSERT INTO " + TABLENAME + " (Username, Password)" + " VALUES ('"+ username + "', '" + password + "');";
            stmt.executeUpdate(sql);
            
        } catch(SQLException ex){
            System.out.println("error");
            FileOperation.showError();
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
                FileOperation.showError();
            }
        }  
    }
    
    private String getSaltvalue(int length) {
        StringBuilder finalval = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            finalval.append(characters.charAt(random.nextInt(characters.length())));
        }
        return new String(finalval);
    }
    
    /* Method to generate the hash value */
    private byte[] hash(char[] password, byte[] salt) throws InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keylength);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
            
        } finally {
            spec.clearPassword();
        }
    }
    
    public String generateSecurePassword(String password) throws InvalidKeySpecException {
        String finalval = null;
        byte[] securePassword = hash(password.toCharArray(), saltValue.getBytes());
        finalval = Base64.getEncoder().encodeToString(securePassword);
        return finalval;
    }
    
    /**
     * @brief verify username and password against the database
     * @param username of type String
     * @param password of type String
     * @return true if username and passwords exists, otherwise false
     */
    public boolean validateUser(String username, String password){
        boolean result = false;
        Connection connection = null;
        Statement stmt = null;
        try {
            password = generateSecurePassword(password);
        } catch (InvalidKeySpecException ex) {
            System.out.println("error");
            FileOperation.showError();
        }
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
            FileOperation.showError();
            
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
                FileOperation.showError();
            }
        }
        return result;
    }
    
    /**
     * @brief deletes existing user
     * @param Username - username of type string
     * 
     */
    public static void deleteExistingUser(String Username){
        Connection connection = null;
        Statement stmt = null;
        try{
            connection = DriverManager.getConnection(FILENAME);
            stmt = connection.createStatement();
            stmt.setQueryTimeout(30);
            String sql = "DELETE FROM Users WHERE Username = " + "'"+ Username + "'";
            stmt.executeUpdate(sql);

            //String path = "/home/ntu-user/NetBeansProjects/files/" + Username;
            String path = fixedPath + Username;
            File file =new File(path);
            if(file.delete()){
                
            }else{
                System.out.println("error");
            }

        } catch(SQLException ex){
            System.out.println("error");
            FileOperation.showError();
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
                FileOperation.showError();
            }
        }
    }
    
    public void updatePassword(String newPassword){
        
    }
       
}
