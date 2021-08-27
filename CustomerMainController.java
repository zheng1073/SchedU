/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Appointment;
import model.Customer;
import utils.ApptDaoImpl;
import utils.CustomerDao;
import static utils.CustomerDao.customers;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author j1996
 */
/** Main Customer Controller Class. */
public class CustomerMainController implements Initializable {

    static Customer getSelectedCustomer() {
        return userSelectedCustomer;
    }

    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> customerIDCol;
    @FXML
    private TableColumn<Customer, String> nameCol;
    @FXML
    private TableColumn<Customer, String> addressCol;
    @FXML
    private TableColumn<Customer, String> stateCol;
    @FXML
    private TableColumn<Customer, String> countryCol;
    @FXML
    private TableColumn<Customer, String> postalCodeCol;
    @FXML
    private TableColumn<Customer, String> phoneNumberCol;
    
    private static Customer userSelectedCustomer;
    @FXML
    private Label customerDeleteLabel;

    /**
     * Initializes the controller class.
     */
    /** This is the initialize method. */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conn = DBConnection.getConnection();
        try {
            // tableview.getItems().clear();
            customerTable.getItems().clear();
            customers = CustomerDao.getAllCustomer(conn);
            
            // Associate data with columns
            customerIDCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
            stateCol.setCellValueFactory(new PropertyValueFactory<>("Division"));
            countryCol.setCellValueFactory(new PropertyValueFactory<>("Country"));
            postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
            phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));
          
            // Add data inside table
            customerTable.setItems(customers);
            
            } catch (SQLException e) {
            e.printStackTrace();
            }
        
    }

    /** This is the refresh table method. */
    private void refreshTable() {
        Connection conn = DBConnection.getConnection();
        try {
            // tableview.getItems().clear();
            customerTable.getItems().clear();
            customers = CustomerDao.getAllCustomer(conn);
            
            // Associate data with columns
            customerIDCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
            stateCol.setCellValueFactory(new PropertyValueFactory<>("Division"));
            countryCol.setCellValueFactory(new PropertyValueFactory<>("Country"));
            postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
            phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));
          
            // Add data inside table
            customerTable.setItems(customers);
            
            } catch (SQLException e) {
            e.printStackTrace();
            }
        
    }

    /** This is the appointment screen changer method. 
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

    /** This is the screen changer add customer method. 
     @param event button click event.
     */
    @FXML
    private void onClickCA(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/CustomerAdd.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /** This is the screen changer modify customer method. 
     @param event button click event.
     */
    @FXML
    private void onClickCM(ActionEvent event) throws IOException {
        userSelectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        
        if (userSelectedCustomer == null) {
            customerDeleteLabel.setText("ERROR: You must select a customer to modify it.");
            customerDeleteLabel.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e -> customerDeleteLabel.setVisible(false));
            pause.play();
        } else {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/CustomerModify.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
        }
    }

    /** This is the delete method. 
     @param event button click event.
     */
    @FXML
    private void onClickCD(ActionEvent event) throws SQLException {
        Connection conn = DBConnection.getConnection();
        
        userSelectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (userSelectedCustomer == null) {
            // error telling user to select something
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Are you sure you want to delete the selected customer?");
            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.isPresent() && result.get() == ButtonType.OK) {
                int C_ID = userSelectedCustomer.getCustomer_ID();
                // list of appointments where customer is this one
                ObservableList<Appointment> filteredAppt = ApptDaoImpl.getAppointmentByCustomer(C_ID, conn);
                
                
                // if list is not null then delete, else error message:
                if (filteredAppt.size() < 1) {
                    // delete
                    System.out.println("List size:" + filteredAppt.size());
                    customerDeleteLabel.setText("Customer Deleted! (Customer ID: " + userSelectedCustomer.getCustomer_ID());
                    CustomerDao.deleteCustomer(userSelectedCustomer, conn);
                    // refresh screen
                    this.refreshTable();
                    customerDeleteLabel.setVisible(true);
                    PauseTransition pause = new PauseTransition(Duration.seconds(5));
                    pause.setOnFinished(e -> customerDeleteLabel.setVisible(false));
                    pause.play();
                } else {
                    // error message pop-up
                    customerDeleteLabel.setText("ERROR: Customer cannot be deleted since it is associated with one or more appointments.");
                    customerDeleteLabel.setVisible(true);
                    PauseTransition pause = new PauseTransition(Duration.seconds(5));
                    pause.setOnFinished(e -> customerDeleteLabel.setVisible(false));
                    pause.play();
                    
                }    
            }
                // ObservableList<Customer> customer = userSelectedProduct.getAllAssociatedParts();
                
                // check for assoc appointments
        }
        
    }

    /** This is the screen changer report method. 
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
