/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

/**
 *
 * @author j1996
 */
/** DB Query Class. */
public class DBQuery {
    
    // Statement reference
    private static Statement statement;
    
    // Create Statement Object
    /** set statement method.
     @param conn database connection.
     */
    public static void setStatement(Connection conn) throws SQLException {
        statement = conn.createStatement();
    }
    
    // Return Statement Object
    /** return statement method.
     * @return returns statement.
     */
    public static Statement getStatement() {
        return statement;
    }
}
