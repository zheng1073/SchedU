/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Statement;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.DBConnection;
import java.sql.*;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import utils.ApptDaoImpl;
import utils.DBQuery;
import javafx.scene.shape.*;
import javafx.util.Duration;
// import javafx.animation.transition.*;

/**
 * FXML Controller class
 *
 * @author j1996
 */
/** Main Appointment Controller Class. */
public class ApptMainController implements Initializable {

    static Appointment getSelectedAppointment() {
        return userSelectedAppointment;
    }

    @FXML
    private TableView<Appointment> aptTable;


    @FXML
    private TableColumn<Appointment, String> Appointment_ID;

    @FXML
    private TableColumn<Appointment, String> Title;

    @FXML
    private TableColumn<Appointment, String> Description;

    @FXML
    private TableColumn<Appointment, String> Location;

    @FXML
    private TableColumn<Appointment, String> Contact_Name;

    @FXML
    private TableColumn<Appointment, String> Type;

    @FXML
    private TableColumn<Appointment, String> Start;
    @FXML
    private TableColumn<Appointment, String> End;
    @FXML
    private TableColumn<Appointment, String> Customer_ID;
    @FXML
    private RadioButton weekly;
    @FXML
    private RadioButton monthly;
    
    private ObservableList<Appointment> appointment = FXCollections.observableArrayList();
    @FXML
    private RadioButton all;
    @FXML
    private ToggleGroup View;
    
    private static Appointment userSelectedAppointment; 
    @FXML
    private Label upcomingA;
    @FXML
    private Label productDeleteLabel;
    @FXML
    private TableColumn<Appointment, String> User_ID;
    

    /** This is the on click customers method. It changes scenes into the Customer table page. 
     @param event button click event.
     */
    @FXML
    private void onClickC(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/CustomerMain.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /** On Click Add method. Changes scenes to allow user to add appointments. 
     @param event button click event.
     */
    @FXML
    private void onClickAA(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/ApptAdd.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /** On Click Modify Method. Changes scenes to allow user to modify selected appointment. 
     @param event button click event.
     */
    @FXML
    private void onClickAM(ActionEvent event) throws IOException {
        userSelectedAppointment = aptTable.getSelectionModel().getSelectedItem();
        
        if (userSelectedAppointment == null) {
            productDeleteLabel.setText("ERROR: An appointment must be selected to modify it.");
            productDeleteLabel.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e -> productDeleteLabel.setVisible(false));
            pause.play();
        } else {
           Parent parent = FXMLLoader.load(getClass().getResource("/view/ApptUpdate.fxml"));
           Scene scene = new Scene(parent);
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           stage.setScene(scene);
           stage.show(); 
        }
    }

    /** On Click Delete Method. Allows user to delete selected appointment. 
     @param event button click event.
     */
    @FXML
    private void onClickAD(ActionEvent event) throws SQLException {
        userSelectedAppointment = aptTable.getSelectionModel().getSelectedItem();
        Connection conn = DBConnection.getConnection();
        // RUNTIME ERROR
       if (userSelectedAppointment == null) {
           productDeleteLabel.setText("ERROR: An appointment must be selected to delete it.");
           productDeleteLabel.setVisible(true);
           PauseTransition pause = new PauseTransition(Duration.seconds(5));
           pause.setOnFinished(e -> productDeleteLabel.setVisible(false));
           pause.play();
       } else {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Alert");
           alert.setContentText("Are you sure you want to delete this appointment?");
           
           Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                productDeleteLabel.setText("Appointment Deleted! (Appointment ID: " + userSelectedAppointment.getAppointment_ID()+ " , Type: " + userSelectedAppointment.getType()+")");
                ApptDaoImpl.deleteAppointment(userSelectedAppointment, conn);
                // refresh screen
                all.setSelected(true);
                appointment = ApptDaoImpl.getAllAppointmentsC(conn);
                updateApptTable(appointment);
                // change text for label and set to visible
                productDeleteLabel.setVisible(true);
                // pause transition
                PauseTransition pause = new PauseTransition(Duration.seconds(5));
                pause.setOnFinished(e -> productDeleteLabel.setVisible(false));
                pause.play();
            }
       }
       
    }
    /**
     * Initializes the controller class.
     */
    /** This is the initialize method. It initializes the main appointment controller. */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        Connection conn = DBConnection.getConnection();
        try {
            // tableview.getItems().clear();
            // aptTable.getItems().clear();
            appointment = ApptDaoImpl.getAllAppointmentsC(conn);
            updateApptTable(appointment);
            // Associate data with columns
            /**
            Appointment_ID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
            Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
            Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
            Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
            Contact_ID.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));
            Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
            Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
            End.setCellValueFactory(new PropertyValueFactory<>("End"));
            Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        
            // Add data inside table
            aptTable.setItems(appointment);
            * **/
            
            } catch (SQLException e) {
            e.printStackTrace();
            }
        try {
            appointmentAlert();
        } catch (SQLException ex) {
            Logger.getLogger(ApptMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        final String tmz = TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT).toString();
        this.Start.setText("Start ("+tmz+")");
        this.End.setText("End ("+tmz+")");
      
    } 

    @FXML
    /** On Click Weekly Method. Displays the appointments this week. 
     @param event button click event.
     */
    private void onClickWeekly(ActionEvent event) throws SQLException {
        Connection conn = DBConnection.getConnection();
        appointment = ApptDaoImpl.getWeekAppointments(conn);
        updateApptTable(appointment);
    
    }
    
    /** This is the Appointment Alert method. It sends out an alert if there is an appointment within 15 minutes of user login. 
     @throws SQLException
     */
    public void appointmentAlert() throws SQLException{
        Connection conn = DBConnection.getConnection();
        ObservableList<Appointment> appointments = ApptDaoImpl.getAllAppointmentsC(conn);
        LocalDateTime currentTime = LocalDateTime.now();
        for(Appointment a : appointments){
            int Year = Integer.parseInt(a.getStart().substring(0,4));
            int Month = Integer.parseInt(a.getStart().substring(5,7));
            int Day = Integer.parseInt(a.getStart().substring(8,10));
            int Hour = Integer.parseInt(a.getStart().substring(11,13));
            int Minute = Integer.parseInt(a.getStart().substring(14,16));
            LocalDateTime startTime = LocalDateTime.of(Year,Month,Day,Hour,Minute);
            long timeDifference = ChronoUnit.MINUTES.between(startTime,currentTime);
            if(timeDifference >= -15 && timeDifference < 0){
                // You have an event coming up
                upcomingA.setText("UPCOMING APPOINTMENT: APPOINTMENT ID:"+a.getAppointment_ID()+"   Start Time:"+a.getStart());
            }
        }
        
    }

    /** On Click All Method. Displays all appointments. 
     @param event button click event.
     */
    @FXML
    private void onClickAll(ActionEvent event) throws SQLException {
        Connection conn = DBConnection.getConnection();
        appointment = ApptDaoImpl.getAllAppointmentsC(conn);
        updateApptTable(appointment);
        
    }

    /** On Click Monthly. Displays the appointments this month. 
     @param event button click event.
     */
    @FXML
    private void onClickMonthly(ActionEvent event) throws SQLException {
        Connection conn = DBConnection.getConnection();
        appointment = ApptDaoImpl.getMonthAppointments(conn);
        updateApptTable(appointment);  
    }
    /** Update Appointment Method. Method to reload slash update the appointment table. */
    private void updateApptTable(ObservableList<Appointment> appointment) {
        // tableview.getItems().clear();
        aptTable.getItems().clear();
        // appointment = ApptDaoImpl.getAllAppointments(conn);
        // Associate data with columns
        Appointment_ID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        Contact_Name.setCellValueFactory(new PropertyValueFactory<>("Contact_Name"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        End.setCellValueFactory(new PropertyValueFactory<>("End"));
        Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        User_ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
        // Add data inside table
        aptTable.setItems(appointment);
    }

    /** This is the report method. It changes scenes to the report page.
     @param event button click event.
     */
    @FXML
    private void onClickR(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/Reports.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
