/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DBConnection;
import utils.DBQuery;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

/** Creates an appointment scheduling application. */
public class S2 extends Application{

 
    /** This is the main method. This is the first method called when this program is ran. */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        // Locale.setDefault(new Locale("fr"));
        DBConnection.startConnection();
        launch(args);
        DBConnection.closeConnection();
 
    }
    
    /** This is the start method. This method loads the login screen */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("/view/ApptMain.fxml"));
        // add fxml document to a scene
        Scene scene = new Scene(root);
        // add scene to stage
        stage.setScene(scene);
        stage.setTitle("Appointment Schelduler Application");
        stage.show();
    }
    

   
}
