/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.first_level_divisions;
import java.sql.*;

/**
 *
 * @author j1996
 */
/** Division Class DAO. */
public class DivisionDao {
    public static ObservableList<first_level_divisions> division = FXCollections.observableArrayList();
    private static first_level_divisions temp;
    
    /** get all division method.
     @param con database connection.
     * @return returns a list of division.
     */
    public static ObservableList<first_level_divisions> getAllDivisions(Connection con) throws SQLException {
        Statement st = con.createStatement();      
        ResultSet rs = st.executeQuery("SELECT * FROM first_level_divisions");
        division = FXCollections.observableArrayList();
        while (rs.next()) {
            division.add(new first_level_divisions(rs.getInt("Division_ID"), rs.getString("Division"), rs.getInt("Country_ID")));
        }
        return division;
    }
    
    /** get all US division method.
     @param con database connection.
     * @return returns a list of division.
     */
    public static ObservableList<first_level_divisions> getUSDivisions(Connection con) throws SQLException {
        Statement st = con.createStatement();      
        ResultSet rs = st.executeQuery("SELECT * FROM first_level_divisions WHERE Division_ID < 55");
        division = FXCollections.observableArrayList();
        while (rs.next()) {
            division.add(new first_level_divisions(rs.getInt("Division_ID"), rs.getString("Division"), rs.getInt("Country_ID")));
        }
        return division;
    }
    
    /** get all UK division method.
     @param con database connection.
     * @return returns a list of division.
     */
    public static ObservableList<first_level_divisions> getUKDivisions(Connection con) throws SQLException {
        Statement st = con.createStatement();      
        ResultSet rs = st.executeQuery("SELECT * FROM first_level_divisions WHERE Division_ID > 75");
        division = FXCollections.observableArrayList();
        while (rs.next()) {
            division.add(new first_level_divisions(rs.getInt("Division_ID"), rs.getString("Division"), rs.getInt("Country_ID")));
        }
        return division;
    }
    
    /** get all Canada division method.
     @param con database connection.
     * @return returns a list of division.
     */
    public static ObservableList<first_level_divisions> getCanadaDivisions(Connection con) throws SQLException {
        Statement st = con.createStatement();      
        ResultSet rs = st.executeQuery("SELECT * FROM first_level_divisions WHERE Division_ID > 55 AND Division_ID < 75");
        division = FXCollections.observableArrayList();
        while (rs.next()) {
            division.add(new first_level_divisions(rs.getInt("Division_ID"), rs.getString("Division"), rs.getInt("Country_ID")));
        }
        return division;
    }
    
    /** get selected division method.
     @param con database connection.
     * @param selectedDivision division.
     * @return returns a division.
     */
    public static first_level_divisions getSelectedDivision(String selectedDivision, Connection con) throws SQLException {
        Statement st = con.createStatement();      
        ResultSet rs = st.executeQuery("SELECT * FROM first_level_divisions WHERE Division='"+selectedDivision+"'");
        int d_ID = rs.getInt("Division_ID");
        String d_name = rs.getString("Division");
        int c_ID = rs.getInt("Country_ID");
        first_level_divisions division = new first_level_divisions(d_ID, d_name, c_ID);
        return division;
    }
    
    /** get selected division method.
     @param con database connection.
     * @param selectedDivision division.
     * @return returns a division.
     */
    public static first_level_divisions getSelectedDivisionI(int selectedDivision, Connection con) throws SQLException {
        Statement st = con.createStatement();      
        ResultSet rs = st.executeQuery("SELECT * FROM first_level_divisions WHERE Division_ID='"+selectedDivision+"'");
        while (rs.next()) {
            temp = new first_level_divisions(rs.getInt("Division_ID"), rs.getString("Division"), rs.getInt("Country_ID"));
        }
        return temp;
    }
    
  
}
