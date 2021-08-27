/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

// import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;

/**
 *
 * @author j1996
 */
/** DBConnection Class. */
public class DBConnection {
    // JDBC URL parts
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress ="//wgudb.ucertify.com:3306/WJ08j3D";
    
    // JDBC URL
    private static final String jdbcURL = protocol + vendorName + ipAddress + "?connectionTimeZone=SERVER";
    
    // Driver Interface reference
    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;
    
    private static final String username = "U08j3D";
    private static final String password = "53689307344";
    
    
    /** start connection method. */
    public static Connection startConnection() {
        try {
            // class.forName(MYSQLJDBCDriver);
            Class.forName(MYSQLJDBCDriver);
            conn = (Connection)DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection successful");
        } catch(ClassNotFoundException e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
        } catch(SQLException e) {
            //System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } 
        
        return conn;
    }
    
    // need to get the connection
    /** get connection method */
    public static Connection getConnection(){
        return conn;
    }
    
    /** close connection method. */
    public static void closeConnection()
    {
        try {
            conn.close();
            System.out.println("Connection closed!");
        } catch(SQLException e) 
        {
            //System.out.println("Error: " + e.getMessage());
        }
 
    }
      
}
