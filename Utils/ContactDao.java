/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import java.sql.*;
import model.Appointment;

/**
 *
 * @author j1996
 */
/** Contact Class DAO. */
public class ContactDao {
    public static ObservableList<Contacts> contact = FXCollections.observableArrayList();
    public static ObservableList<Contacts> contactFromAppt = FXCollections.observableArrayList();
    
    /** get all contact method.
     @param con database connection.
     * @return returns a list of contacts
     */
    public static ObservableList<Contacts> getAllContact(Connection con) throws SQLException {
        Statement st = con.createStatement();      
        ResultSet rs = st.executeQuery("SELECT * FROM contacts");
        // Create empty list and repopulate list with the data queried above
        contact = FXCollections.observableArrayList();
        while (rs.next()) {
            contact.add(new Contacts(rs.getInt("Contact_ID"), rs.getString("Contact_Name"), rs.getString("Email")));
            //Contacts(int Contact_ID, String Contact_Name, String Email)
        }
        return contact;
    }
    
    /** get all contact method.
     @param con database connection.
     * @param ApptID
     * @return returns a list of contacts
     */
    public static ObservableList<Contacts> getContactFromAppt(int ApptID, Connection con) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM contacts WHERE Contact_ID = (SELECT Contact_ID FROM appointments WHERE Appointment_ID =" + ApptID + ")");
        contactFromAppt = FXCollections.observableArrayList();
        while (rs.next()) {
            contactFromAppt.add(new Contacts(rs.getInt("Contact_ID"), rs.getString("Contact_Name"), rs.getString("Email")));
        }
        return contactFromAppt;
    }

}
