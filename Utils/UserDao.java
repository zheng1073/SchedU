/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import model.Appointment;
import static utils.ApptDaoImpl.appointment;
import java.sql.*;
import javafx.collections.ObservableList;
import model.User;

/**
 *
 * @author j1996
 */
/** User Class DAO. */
public class UserDao {
    public static ObservableList<User> userList = FXCollections.observableArrayList();
    public static ObservableList<User> user = FXCollections.observableArrayList();
    
    /** get all contact method.
     @param con database connection.
     * @param username username.
     * @param password password.
     * @return returns a list of users.
     */
    public static ObservableList<User> checkUser(String username, String password, Connection con) throws SQLException {
        Statement st = con.createStatement();      
        ResultSet rs = st.executeQuery("SELECT * FROM users WHERE User_Name = '" +username+"' AND Password = '" +password+"'");
        try {
            System.out.println("Start loading user");
            userList = FXCollections.observableArrayList();
            while (rs.next()) {
                userList.add(new User(rs.getInt("User_ID"), rs.getString("User_Name"), rs.getString("Password"), rs.getDate("Create_Date"),rs.getString("Created_By"),rs.getTimestamp("Last_Update"),rs.getString("Last_Updated_By")));
            }
            System.out.println("User loaded");
            
        } catch(Exception e) {
            // print out message with error
            System.out.println("User load fail");
        } 
        return userList;
    };
    
    /** get all user method.
     @param con database connection.
     * @return returns a list of users.
     */
    public static ObservableList<User> getAllUser(Connection con) throws SQLException {
        Statement st = con.createStatement();      
        ResultSet rs = st.executeQuery("SELECT * FROM users");
        // Create empty list and repopulate list with the data queried above
        user = FXCollections.observableArrayList();
        while (rs.next()) {
            user.add(new User(rs.getInt("User_ID"), rs.getString("User_Name"), rs.getString("Password"), rs.getDate("Create_Date"),rs.getString("Created_By"),rs.getTimestamp("Last_Update"), rs.getString("Last_Updated_By")));
            //(int User_ID, String User_Name, String Password, Date Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By) 
        }
        return user;
    }
}
