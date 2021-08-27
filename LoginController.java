/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import model.User;
import utils.DBConnection;
import utils.UserDao;

/**
 * FXML Controller class
 *
 * @author j1996
 */
/** Login Controller Class. */
public class LoginController implements Initializable {

    @FXML
    private TextField timezone;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label warning;
    
    static ObservableList<User> loggedInUser;
    static Locale localZoneID;
    @FXML
    private Label timezoneLabel;
    public static BufferedWriter bufferedWriter;
    private static String filename = "login_activity";
    @FXML
    private Label infoText;
    @FXML
    private Label usernameText;
    @FXML
    private Label passwordText;
    @FXML
    private Button loginText;
    private static String temp;

    /**
     * Initializes the controller class.
     */
    /** This is the initialize method. */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        timezoneLabel.setText(TimeZone.getDefault().getDisplayName(false, TimeZone.LONG) + " (" + TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT) + ")");
        /**
        try{
            rb = ResourceBundle.getBundle("/s2/National",Locale.getDefault());
            if(Locale.getDefault().toString().equals("fr")){
                // Locale.getDefault().getLanguage().equals("fr")
            // username.setPromptText(rb.getString("Username"));
            timezoneLabel.setText(rb.getString("Timezone"));
            //password.setPromptText
        }
        } catch(Exception e){
            //
        }
        */
        if(Locale.getDefault().toString().equals("fr")){
            // timezoneLabel.setText("Fuseau horaire");
            usernameText.setText("Nom d'utilisateur");
            passwordText.setText("Le mot de passe");
            loginText.setText("Connexion");
            infoText.setText("Application de Prise de Rendez-vous");
            warning.setText("Nom d'utilisateur ou mot de passe invalide");
            
        }
        
    }    

    /** This is the login method. 
     @param event button click event.
     */
    @FXML
    private void onClickLogin(ActionEvent event) throws IOException, SQLException {
        loggedInUser = UserDao.checkUser(username.getText(), password.getText(),DBConnection.getConnection());
        if (loggedInUser.size() > 0) {
            loginTracker("Success", getUser().toString());
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ApptMain.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            warning.setVisible(true);
            loginTracker("Failed", "["+username.getText()+"]");
        }
    }
    
    /** This is the get User method. 
     @return returns the logged in user.
     */
    static ObservableList<User> getUser() {
        System.out.println(loggedInUser);
        return loggedInUser;
    }
    
    /** This is the get Zone ID method. 
     @return returns the local zone ID.
     */
    public static Locale getLocalZoneID() {
        localZoneID = Locale.getDefault();
        return localZoneID;
    }
    
    /** This is the login tracker method. */
   private static void loginTracker(String status, String user) {
       bufferedWriter = null;
       final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
       final String time = formatter.format(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));
       final String tmz = TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT).toString();
       
       try {
           File file = new File(filename);
           // if file doesn't exist, create it
           if(!file.exists()) {
               file.createNewFile();
           }
           FileWriter fw = new FileWriter(file, true);
           bufferedWriter = new BufferedWriter(fw);
           bufferedWriter.write("time: " + time + "\t");
           bufferedWriter.write("Timezone: " + tmz + "\t");
           //if(getUser().size() > 0){
            bufferedWriter.write("username: " + user + "\t");   
           //} else {
               // temp = username.getText();
               //bufferedWriter.write("username: " +  + "\t");
           //}
           bufferedWriter.write("Login: " + status + "\t");
           //TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT).toString()
           bufferedWriter.newLine();
           bufferedWriter.close();
       } catch(IOException e) {
           e.printStackTrace();
       }
       
   }
    
}
