/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Appointment;
import model.Contacts;
import model.Countries;
import utils.ApptDaoImpl;
import utils.ContactDao;
import utils.CountryDao;
import utils.CustomerDao;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author j1996
 */
/** Reports Controller Class. */
public class ReportsController implements Initializable {

    @FXML
    private ComboBox<Integer> reportOneMonth;
    @FXML
    private ComboBox<Appointment> reportOneType;
    @FXML
    private Label reportOneResult;
    @FXML
    private ComboBox<Contacts> reportTwoContact;
    @FXML
    private ComboBox<Countries> reportThreeCountry;
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
    private TableColumn<Appointment, String> Contact_ID;
    @FXML
    private TableColumn<Appointment, String> Type;
    @FXML
    private TableColumn<Appointment, String> Start;
    @FXML
    private TableColumn<Appointment, String> End;
    @FXML
    private TableColumn<Appointment, String> Customer_ID;
    @FXML
    private Label productDeleteLabel;
    @FXML
    private Label reportThreeResult;
    @FXML
    private Button onClear3;

    /**
     * Initializes the controller class.
     */
    /** This is the initialize method. */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connection conn = DBConnection.getConnection();
        // populate Countries
        try {
            this.reportThreeCountry.setItems(CountryDao.getAllCountries(conn));
        } catch (SQLException ex) {
            Logger.getLogger(CustomerAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // populate month
        ObservableList<Integer> month = FXCollections.observableArrayList();
        for(int i=1; i<=12; i++) {
            month.add(i);
        };
        this.reportOneMonth.setItems(month);
        // populate type
        
        try {
            // Customer Combobox
            this.reportTwoContact.setItems(ContactDao.getAllContact(conn));
            this.reportOneType.setItems(ApptDaoImpl.getAppointmentByType(conn));
        } catch (SQLException ex) {
            Logger.getLogger(ApptAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    /** This is the screen changer appointment method. 
     @param event button click event.
     */
    @FXML
    private void onClickA(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/ApptMain.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /** This is the screen changer customer method. 
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
    
    /** This is the update table method. 
     @param event button click event.
     */
     private void updateApptTable(ObservableList<Appointment> appointment) {
        // tableview.getItems().clear();
        aptTable.getItems().clear();
        // appointment = ApptDaoImpl.getAllAppointments(conn);
        // Associate data with columns
        Appointment_ID.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        Contact_ID.setCellValueFactory(new PropertyValueFactory<>("Contact_Name"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        End.setCellValueFactory(new PropertyValueFactory<>("End"));
        Customer_ID.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        // Add data inside table
        aptTable.setItems(appointment);
    }

     /** LAMBDA FUNCTION USED HERE. This is the report 1 method. The lambda function sets the label back to invisible after 5 seconds. 
      @param event button click event.
      */
    @FXML
    private void onClickReportOne(ActionEvent event) throws SQLException {
        aptTable.getItems().clear();
        // get month
        int m = this.reportOneMonth.getSelectionModel().getSelectedItem();
        // get type
        Appointment t = this.reportOneType.getSelectionModel().getSelectedItem();
        
        // sql statement
        Connection conn = DBConnection.getConnection();
        ObservableList<Appointment> result = ApptDaoImpl.getAC(m, m, t.getType(), conn);
        
        if (result == null) {
            productDeleteLabel.setText("ERROR: You must select a country. [REPORT 3]");
            productDeleteLabel.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e -> productDeleteLabel.setVisible(false));
            pause.play();
        } else {
            int countA = result.size();
            reportOneResult.setText("Number of Appointments: " + countA);
            reportOneResult.setVisible(true);
        }
    }
    
    /** This is the report 2 generator method. 
     @param event button click event.
     */
    @FXML
    private void onClickReportTwo(ActionEvent event) throws SQLException {
        Connection conn = DBConnection.getConnection();
        Contacts selected = reportTwoContact.getSelectionModel().getSelectedItem();
        int selectedContact = selected.getContact_ID();
        // change query
        ObservableList<Appointment> appointment = ApptDaoImpl.getAppointmentByContact(selectedContact, conn);
        updateApptTable(appointment);
    }

    /** LAMBDA FUNCTION USED HERE. This is the report 3 method. The lambda function sets the label back to invisible after 5 seconds. 
     @param event button click event.
     */
    @FXML
    private void onClickReportThree(ActionEvent event) throws SQLException {
        Connection conn = DBConnection.getConnection();
        aptTable.getItems().clear();
        Countries selected = reportThreeCountry.getSelectionModel().getSelectedItem();
        String selectedCountry = selected.getCountry();
        int countC = CustomerDao.getCustomerCount(selectedCountry, conn);
        
        if (reportThreeCountry == null) {
            productDeleteLabel.setText("ERROR: You must select a country. [REPORT 3]");
            productDeleteLabel.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e -> productDeleteLabel.setVisible(false));
            pause.play();
        } else {
           reportThreeResult.setText("Number of Customers: " + countC);
           reportThreeResult.setVisible(true);
        }
    }

    /** LAMBDA FUNCTION used here. This is the clear method for report 1. The lambda function prints out a message to the console if the button is clicked. 
     @param event button click event.
     */
    @FXML
    private void onSelectClear1(ActionEvent event) {
        reportOneMonth.getSelectionModel().clearSelection();
        //reportOneMonth.setPromptText("Month");
        reportOneType.getSelectionModel().clearSelection();
        //reportOneType.setPromptText("Type");
        reportOneResult.setVisible(false);
        reportOneResult.setText("Number of Appointments");
    }

    /** LAMBDA FUNCTION used here. This is the clear method for report 3. The lambda function prints out a message to the console if the button is clicked. 
     @param event button click event.
     */
    @FXML
    private void onSelectClear3(ActionEvent event) {
        onClear3.setOnAction((ActionEvent e) -> {
        System.out.println("Button Action");
    });
        reportThreeCountry.getSelectionModel().clearSelection();
        //reportThreeCountry.setPromptText("Country");
        reportThreeResult.setVisible(false);
        reportThreeResult.setText("Number of Customers");
    }
    
    
    
}
