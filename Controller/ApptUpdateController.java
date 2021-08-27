/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import static java.lang.Integer.parseInt;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Appointment;
import utils.DBConnection;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import model.Contacts;
import model.Customer;
import model.User;
import utils.ContactDao;
import utils.CustomerDao;
import utils.UserDao;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.util.Duration;
import utils.ApptDaoImpl;

/**
 * FXML Controller class
 *
 * @author j1996
 */
/** Update Appointment Controller Class. */
public class ApptUpdateController implements Initializable {

    @FXML
    private TextField Appointment_ID;
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
    private ComboBox<Customer> Customer_ID;
    @FXML
    private ComboBox<User> User_ID;

    Appointment userSelectedAppointment;
    private ObservableList<Appointment> selectedAppointment = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Integer> startMin;
    @FXML
    private DatePicker start;
    @FXML
    private ComboBox<Integer> startHour;
    @FXML
    private DatePicker end;
    @FXML
    private ComboBox<Integer> endHour;
    @FXML
    private ComboBox<Integer> endMin;
    @FXML
    private Label appointmentModifyError;
    @FXML
    private Label sTimeZone;
    @FXML
    private Label eTimeZone;
    private static final DateTimeFormatter datetime_DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static int indexS;
    public static int indexE;

    /**
     * Initializes the controller class.
     */
    /** This is the initialize method. It initializes the update appointment controller. */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // pre-fill combo boxes
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
            //
        }
        
        // preset values
        userSelectedAppointment = ApptMainController.getSelectedAppointment();
    
        this.Appointment_ID.setText(String.valueOf(userSelectedAppointment.getAppointment_ID()));
        this.Title.setText(userSelectedAppointment.getTitle());
        this.Description.setText(userSelectedAppointment.getDescription());
        this.Location.setText(userSelectedAppointment.getLocation());
        int tempVal = userSelectedAppointment.getAppointment_ID();
        // this.Contact.setValue(userSelectedAppointment.getContact_ID().toString());
        this.Contact.getSelectionModel().select(userSelectedAppointment.getContact_ID());
        this.Type.setText(userSelectedAppointment.getType());
        System.out.println("Stae datetime:"+userSelectedAppointment.getStart());
        // database start time
        String s = userSelectedAppointment.getStart();
        String e = userSelectedAppointment.getEnd();
        // String startUTC = s.substring(0,19);
        // String endUTC = e.substring(0,19);
        
        /**
        // convert UTC to local datetime
        LocalDateTime startUTC_LDT = LocalDateTime.parse(startUTC, datetime_DTF);
        LocalDateTime endUTC_LDT = LocalDateTime.parse(endUTC, datetime_DTF);
        // convert UTC to local time zone
        ZoneId userZoneID = ZoneId.systemDefault();
        ZoneId utcZoneID = ZoneId.of("UTC");
        ZonedDateTime start_LZ = startUTC_LDT.atZone(utcZoneID).withZoneSameInstant(userZoneID);
        ZonedDateTime end_LZ = endUTC_LDT.atZone(utcZoneID).withZoneSameInstant(userZoneID);
        // Convert to Strings
        String ss = start_LZ.format(datetime_DTF);
        String ee = end_LZ.format(datetime_DTF);
        System.out.println("converted substring:"+ss+" --and-- "+ee);
        System.out.println("Mind as well see this too:" +start_LZ + " --anbd== "+startUTC);
        */
        int Year = Integer.parseInt(s.substring(0,4));
        int Month = Integer.parseInt(s.substring(5,7));
        int Day = Integer.parseInt(s.substring(8,10));
        int Hour = Integer.parseInt(s.substring(11,13));
        int Minute = Integer.parseInt(s.substring(14,16));
        System.out.println("Year:"+Year+" --Month:"+Month+" --Day:"+Day+" --Hour:"+Hour+" --Min:"+Minute);
        // start
        LocalDate startD = LocalDate.of(Year, Month, Day);
        this.start.setValue(startD);
        
        indexS = 0;
        for(int i=start; i<=(end - 1); i++) {
            //int temp = i;
            if(Hour == i){
                break;
            }
            indexS = indexS + 1;
        };
        
        this.startHour.getSelectionModel().select(indexS);
        this.startMin.getSelectionModel().select(Minute);
        
        
        int YearE = Integer.parseInt(e.substring(0,4));
        int MonthE = Integer.parseInt(e.substring(5,7));
        int DayE = Integer.parseInt(e.substring(8,10));
        int HourE = Integer.parseInt(e.substring(11,13));
        int MinuteE = Integer.parseInt(e.substring(14,16));
        // end
        LocalDate endD = LocalDate.of(YearE, MonthE, DayE);
        this.end.setValue(endD);
        indexE = 0;
        for(int i=start; i<=(end - 1); i++) {
            //int temp = i;
           
            System.out.println("end index:"+indexE);
            if(HourE == i){
                System.out.println("end index in if:"+indexE);
                break;
            }
            indexE = indexE + 1;
        };
        
        this.endHour.getSelectionModel().select(indexE);
        this.endMin.getSelectionModel().select(MinuteE);
        // Customer ID
        this.Customer_ID.getSelectionModel().select(userSelectedAppointment.getCustomer_ID());
        // User ID
        this.User_ID.getSelectionModel().select(userSelectedAppointment.getUser_ID());
        
        this.start.valueProperty().addListener((ov, oldValue, newValue) -> {
            this.end.setValue(newValue.plusDays(0));
        });
        
    }    

    /** This is the save method. To save appointments. */
    @FXML
    private void onClickAMSave(ActionEvent event) throws IOException, SQLException {
        Connection conn = DBConnection.getConnection();
         
        String Appointment_ID = this.Appointment_ID.getText();
        String Title = this.Title.getText();
        String Description = this.Description.getText();
        String Location = this.Location.getText();
        Contacts Contact = this.Contact.getSelectionModel().getSelectedItem();
        String Type = this.Type.getText();
        Customer Customer_ID = this.Customer_ID.getSelectionModel().getSelectedItem();
        User User_ID = this.User_ID.getSelectionModel().getSelectedItem();
       
        
        if ((Title == null) || (Description == null) || (Location == null) || (Contact == null) || (Type == null) || (this.start.getValue().toString() == null) || (this.end.getValue().toString() == null) || (this.startHour.getSelectionModel().getSelectedItem().toString() == null) || (this.startMin.getSelectionModel().getSelectedItem().toString() == null) || (this.endHour.getSelectionModel().getSelectedItem().toString() == null) || (this.endMin.getSelectionModel().getSelectedItem().toString() == null) || (Customer_ID == null) || (User_ID == null)) {
            appointmentModifyError.setText("ERROR: All fields have to be filled out.");
            appointmentModifyError.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e -> appointmentModifyError.setVisible(false));
            pause.play();
        } else {
            // datetime
            String startDate = this.start.getValue().toString();
            String endDate = this.end.getValue().toString();
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
            
            ZoneId userZoneID = ZoneId.systemDefault();
            ZonedDateTime startUTC = startDT.atZone(userZoneID).withZoneSameInstant(ZoneId.of("UTC"));
            ZonedDateTime endUTC = endDT.atZone(userZoneID).withZoneSameInstant(ZoneId.of("UTC"));
                
            Timestamp tempStart = Timestamp.valueOf(startUTC.toLocalDateTime());
            Timestamp tempEnd = Timestamp.valueOf(endUTC.toLocalDateTime());
            
            if(startDT.isAfter(endDT)){
                //
                appointmentModifyError.setText("ERROR: Start datetime cannot be after End datetime.");
                appointmentModifyError.setVisible(true);
                PauseTransition pause = new PauseTransition(Duration.seconds(5));
                pause.setOnFinished(e -> appointmentModifyError.setVisible(false));
                pause.play();
            } else if(ApptDaoImpl.checkDuplicates(Customer_ID.getCustomer_ID(), tempStart, tempEnd, conn) == true){
                //
                appointmentModifyError.setText("ERROR: This customer has a conflicting appointment.");
                appointmentModifyError.setVisible(true);
                PauseTransition pause = new PauseTransition(Duration.seconds(5));
                pause.setOnFinished(e -> appointmentModifyError.setVisible(false));
                pause.play();
            }else{
                startDateTime = tempStart.toString();
                endDateTime = tempEnd.toString();
            
                Appointment appointment = new Appointment(parseInt(Appointment_ID) , Title, Description, Location, Contact.getContact_ID(), Type, startDateTime, endDateTime, Customer_ID.getCustomer_ID(), User_ID.getUser_ID(), loggedInUser, loggedInUser);
                                           // Appointment(String Title, String Description, String Location, int Contact_ID, String Type, Timestamp Start, Timestamp End, int Customer_ID, int User_ID, String Created_By, String Last_Updated_By)
                ApptDaoImpl.deleteAppointment(userSelectedAppointment, conn); 
                ApptDaoImpl.addAppointment(appointment, conn);

                Parent parent = FXMLLoader.load(getClass().getResource("/view/ApptMain.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    /** This is the cancel method. 
     @param event button click event.
     */
    @FXML
    private void onClickAMCancel(ActionEvent event) throws IOException {
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
    private void onClickCustomer_ID(ActionEvent event) {
    }

    @FXML
    private void onClickUser_ID(ActionEvent event) {
    }

    
}
