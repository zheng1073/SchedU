/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import java.sql.*;

/**
 *
 * @author j1996
 */
/** Country Class DAO. */
public class CountryDao {
    public static ObservableList<Countries> country = FXCollections.observableArrayList();
    private static Countries temp;
    
    /** get all country method.
     @param con database connection.
     * @return returns a list of countries.
     */
    public static ObservableList<Countries> getAllCountries(Connection con) throws SQLException {
        Statement st = con.createStatement();      
        ResultSet rs = st.executeQuery("SELECT * FROM countries");
        country = FXCollections.observableArrayList();
        while (rs.next()) {
            country.add(new Countries(rs.getInt("Country_ID"), rs.getString("Country")));
        }
        return country;
    }
    
    /** get selected country method.
     @param con database connection.
     * @param selectedCountry one country.
     * @return returns a country.
     */
    public static Countries getSelectedCountry(String selectedCountry, Connection con) throws SQLException {
        Statement st = con.createStatement();      
        ResultSet rs = st.executeQuery("SELECT * FROM countries WHERE Country='"+selectedCountry+"'");
        // country = FXCollections.observableArrayList();
        while (rs.next()) {
            temp = new Countries(rs.getInt("Country_ID"), rs.getString("Country"));
        }
        return temp;
    }
}
