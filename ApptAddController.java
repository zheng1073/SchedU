/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.LoginController.localZoneID;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Appointment;
import model.Contacts;
import model.Customer;
import model.User;
import utils.*;

/**
 * FXML Controller class
 *
 * @author j1996
 */
/** Add Appointment Controller Class. */
public class ApptAddController implements Initializable {

    @FXML
    private TextField Title;
    @FXML
    private TextField Description;
    @FXML
    private TextField Location;
    @FXML
    private ComboBox<Contacts> Contact;
    @FXML
    private TextField Type;
    @FXML
    private DatePicker Start;
    @FXML
    private DatePicker End;
    @FXML
    private ComboBox<Customer> Customer_ID;
    @FXML
    private ComboBox<User> User_ID;
    @FXML
    private TextField Appointment_ID;
    @FXML
    private ComboBox<Integer> startHour;
    @FXML
    private ComboBox<Integer> startMin;
    @FXML
    private ComboBox<Integer> endHour;
    @FXML
    private ComboBox<Integer> endMin;
    @FXML
    private Label appointmentAddError;
    
    private static DateTimeFormatter datetime_DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @FXML
    private Label sTimeZone;
    @FXML
    private Label eTimeZone;

    /**
     * Initializes the controller class.
     */
    /** This is the initialize method that initializes the controller class. */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connection conn = DBConnection.getConnection();
        
        ObservableList<Integer> hour = FXCollections.observableArrayList();
        int start = ApptDaoImpl.businessOpen();
        int end = ApptDaoImpl.businessClose();
        for(int i=start; i<=(end - 1); i++) {
            //int temp = i;
            hour.add(i);
        };
        ObservableList<Integer> min = FXCollections.observableArrayList();
        for(int i=0; i<=59; i++) {
            min.add(i);
        };
        
        sTimeZone.setText(TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT).toString());
        eTimeZone.setText(TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT).toString());
        
        this.startHour.setItems(hour);
        this.startMin.setItems(min);
        
        this.endHour.setItems(hour);
        this.endMin.setItems(min);
        
        
        try {
            // Customer Combobox
            this.Customer_ID.setItems(CustomerDao.getAllCustomerOnly(conn));
            this.User_ID.setItems(UserDao.getAllUser(conn));
            this.Contact.setItems(ContactDao.getAllContact(conn));
        } catch (SQLException ex) {
            Logger.getLogger(ApptAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Start.valueProperty().addListener((ov, oldValue, newValue) -> {
            End.setValue(newValue.plusDays(0));
        });
        
    }    

    /** This is the save method. It saves the entered data.
     @param event button click event.
     */
    @FXML
    private void onClickAASave(ActionEvent event) throws IOException, SQLException {
        Connection conn = DBConnection.getConnection();
        
        String Title = this.Title.getText();
        String Description = this.Description.getText();
        String Location = this.Location.getText();
        Contacts Contact = this.Contact.getSelectionModel().getSelectedItem();
        String Type = this.Type.getText();
        Customer Customer_ID = this.Customer_ID.getSelectionModel().getSelectedItem();
        User User_ID = this.User_ID.getSelectionModel().getSelectedItem();
      
        
        if ((Title == null) || (Description == null) || (Location == null) || (Contact == null) || (Type == null) || (this.Start.getValue().toString() == null) || (this.End.getValue().toString() == null) || (this.startHour.getSelectionModel().getSelectedItem().toString() == null) || (this.startMin.getSelectionModel().getSelectedItem().toString() == null) || (this.endHour.getSelectionModel().getSelectedItem().toString() == null) || (this.endMin.getSelectionModel().getSelectedItem().toString() == null) || (Customer_ID == null) || (User_ID == null)) {
            appointmentAddError.setText("ERROR: All fields have to be filled out.");
            appointmentAddError.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e -> appointmentAddError.setVisible(false));
            pause.play();
        } else {
            // datetime
            String startDate = this.Start.getValue().toString();
            String endDate = this.End.getValue().toString();
            String startHour = this.startHour.getSelectionModel().getSelectedItem().toString();
            String startMin = this.startMin.getSelectionModel().getSelectedItem().toString();
            String endHour = this.endHour.getSelectionModel().getSelectedItem().toString();
            String endMin = this.endMin.getSelectionModel().getSelectedItem().toString();
                // YYYY-MM-DD HH:MM:SS
            if (startHour.length() == 1) {
                startHour = "0" + startHour;
            };
            if (startMin.length() == 1) {
                startMin = "0" + startMin;
            };
            if (endHour.length() == 1) {
                endHour = "0" + endHour;
            };
            if (endMin.length() == 1) {
                endMin = "0" + endMin;
            };
            String startDateTime = startDate + " " + startHour + ":" + startMin + ":" + "00";
            String endDateTime = endDate + " " + endHour + ":" + endMin + ":" + "00";
            
            LocalDateTime startDT = LocalDateTime.parse(startDateTime, datetime_DTF);
            LocalDateTime endDT = LocalDateTime.parse(endDateTime, datetime_DTF);

            String loggedInUser = LoginController.getUser().toString();
            
            // convert to UTC
                ZoneId userZoneID = ZoneId.systemDefault();
                ZonedDateTime startUTC = startDT.atZone(userZoneID).withZoneSameInstant(ZoneId.of("UTC"));
                ZonedDateTime endUTC = endDT.atZone(userZoneID).withZoneSameInstant(ZoneId.of("UTC"));
                
                Timestamp tempStart = Timestamp.valueOf(startUTC.toLocalDateTime());
                Timestamp tempEnd = Timestamp.valueOf(endUTC.toLocalDateTime());
            
            if(startDT.isAfter(endDT)){
                appointmentAddError.setText("ERROR: Start datetime cannot be after End datetime");
                appointmentAddError.setVisible(true);
                PauseTransition pause = new PauseTransition(Duration.seconds(5));
                pause.setOnFinished(e -> appointmentAddError.setVisible(false));
                pause.play();
            } else if(ApptDaoImpl.checkDuplicates(Customer_ID.getCustomer_ID(), tempStart, tempEnd, conn) == true){
                //
                appointmentAddError.setText("ERROR: This customer has a conflicting appointment.");
                appointmentAddError.setVisible(true);
                PauseTransition pause = new PauseTransition(Duration.seconds(5));
                pause.setOnFinished(e -> appointmentAddError.setVisible(false));
                pause.play();
            }else{
                
                startDateTime = tempStart.toString();
                endDateTime = tempEnd.toString();
                
                Appointment appointment = new Appointment(Title, Description, Location, Contact.getContact_ID(), Type, startDateTime, endDateTime, Customer_ID.getCustomer_ID(), User_ID.getUser_ID(), loggedInUser, loggedInUser);
                                       // Appointment(String Title, String Description, String Location, int Contact_ID, String Type, Timestamp Start, Timestamp End, int Customer_ID, int User_ID, String Created_By, String Last_Updated_By)
        
                ApptDaoImpl.addAppointment(appointment, conn);
                Parent parent = FXMLLoader.load(getClass().getResource("/view/ApptMain.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    /** This is the cancel method. It redirects the user back to the main screen.
     @param event button click event.
     */
    @FXML
    private void onClickAACancel(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/ApptMain.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onClickContact(ActionEvent event) {
    }

    @FXML
    private void endMin(ActionEvent event) {
    }

    @FXML
    private void onClickCustomer_ID(ActionEvent event) {
    }

    @FXML
    private void onClickUser_ID(ActionEvent event) {
    }
    
}
