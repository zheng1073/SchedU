/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

/**
 *
 * @author j1996
 */
/** Customer Class DAO. */
public class CustomerDao {
    public static ObservableList<Customer> customers = FXCollections.observableArrayList();

    /** get all customer method.
     @param con database connection.
     * @return returns a list of customers.
     */
    public static ObservableList<Customer> getAllCustomer(Connection con) throws SQLException {
        Statement st = con.createStatement();      
        ResultSet rs = st.executeQuery("SELECT * FROM customers JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID");
        // Create empty list and repopulate list with the data queried above
        customers = FXCollections.observableArrayList();
        while (rs.next()) {
            customers.add(new Customer(rs.getInt("Customer_ID"), rs.getString("Customer_Name"), rs.getString("Address"), rs.getString("Postal_Code"), rs.getString("Phone"), rs.getDate("Create_Date"),rs.getString("Created_By"),rs.getTimestamp("Last_Update"), rs.getString("Last_Updated_By"), rs.getInt("Division_ID"), rs.getString("Division"), rs.getString("Country")));
        }
        System.out.println("Appointments = " + customers.size());
        return customers;
    }
    
    /** get all customer method.
     @param con database connection.
     * @return returns a list of customers.
     */
    public static ObservableList<Customer> getAllCustomerOnly(Connection con) throws SQLException {
        Statement st = con.createStatement();      
        ResultSet rs = st.executeQuery("SELECT * FROM customers");
        // Create empty list and repopulate list with the data queried above
        customers = FXCollections.observableArrayList();
        while (rs.next()) {
            customers.add(new Customer(rs.getInt("Customer_ID"), rs.getString("Customer_Name"), rs.getString("Address"), rs.getString("Postal_Code"), rs.getString("Phone"), rs.getDate("Create_Date"),rs.getString("Created_By"),rs.getTimestamp("Last_Update"), rs.getString("Last_Updated_By"), rs.getInt("Division_ID")));
        }
        return customers;
    }
    
    /** add customer method.
     @param con database connection.
     * @param customer customer.
     */
    public static void addCustomer(Customer customer, Connection con) throws SQLException {
        Statement st = con.createStatement();
        // Customer customer = new Customer(Name, Address, PostalCode, PhoneNumber,Division.getDivision_ID());
        st.executeUpdate("INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES('" + customer.getCustomer_Name() + "','" + customer.getAddress() + "','" + customer.getPostal_Code() + "','" + customer.getPhone() + "','" + customer.getDivision_ID()+"')");
    }
    
    /** delete customer method.
     @param con database connection.
     * @param customer customer object.
     */
    public static void deleteCustomer(Customer customer, Connection con) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("DELETE FROM customers WHERE Customer_ID = '" + customer.getCustomer_ID()+"'");
    }
    
    /** get customer count method.
     @param con database connection.
     * @param C country.
     * @return returns size of customer list.
     */
    public static int getCustomerCount(String C, Connection con) throws SQLException {
        Statement st = con.createStatement();      
        ResultSet rs = st.executeQuery("SELECT * FROM customers JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID WHERE Country = '"+C+"'");
        // Create empty list and repopulate list with the data queried above
        customers = FXCollections.observableArrayList();
        while (rs.next()) {
            customers.add(new Customer(rs.getInt("Customer_ID"), rs.getString("Customer_Name"), rs.getString("Address"), rs.getString("Postal_Code"), rs.getString("Phone"), rs.getDate("Create_Date"),rs.getString("Created_By"),rs.getTimestamp("Last_Update"), rs.getString("Last_Updated_By"), rs.getInt("Division_ID"), rs.getString("Division"), rs.getString("Country")));
        }
        System.out.println("Appointments = " + customers.size());
        return customers.size();
    }
}
