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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Countries;
import model.Customer;
import model.first_level_divisions;
import utils.CountryDao;
import utils.CustomerDao;
import utils.DBConnection;
import utils.DivisionDao;

/**
 * FXML Controller class
 *
 * @author j1996
 */
/** Modify Customer Controller Class. */
public class CustomerModifyController implements Initializable {

    @FXML
    private TextField Customer_ID;
    @FXML
    private TextField Name;
    @FXML
    private TextField Address;
    @FXML
    private ComboBox<Countries> Country;
    @FXML
    private ComboBox<first_level_divisions> Division;
    @FXML
    private TextField PostalCode;
    @FXML
    private TextField PhoneNumber;
    private static Customer userSelectedCustomer;
    @FXML
    private Label customerModifyError;

    /**
     * Initializes the controller class.
     */
    /** This is the initialize method. */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connection conn = DBConnection.getConnection();
        // load combo boxes
        try {
            this.Country.setItems(CountryDao.getAllCountries(conn));
        } catch (SQLException ex) {
            Logger.getLogger(CustomerAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // preset values
        userSelectedCustomer = CustomerMainController.getSelectedCustomer();
        
        this.Customer_ID.setText(String.valueOf(userSelectedCustomer.getCustomer_ID()));
        this.Name.setText(userSelectedCustomer.getCustomer_Name());
        this.Address.setText(userSelectedCustomer.getAddress());
        //String selectedCountry = userSelectedCustomer.getCountry();
        //System.out.println(selectedCountry);
        try {
            //Countries temp = CountryDao.getSelectedCountry(userSelectedCustomer.getCountry(), conn);
            //System.out.println(temp);
            this.Country.getSelectionModel().select(CountryDao.getSelectedCountry(userSelectedCustomer.getCountry(), conn));
        } catch (SQLException ex) {
            Logger.getLogger(CustomerModifyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(userSelectedCustomer.getDivision_ID());
        try {
            this.Division.getSelectionModel().select(DivisionDao.getSelectedDivisionI(userSelectedCustomer.getDivision_ID(),conn));
        } catch (SQLException ex) {
            Logger.getLogger(CustomerModifyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // country
        /**
        String selectedCountry = userSelectedCustomer.getCountry();
        try {
            this.Country.setValue(CountryDao.getSelectedCountry(selectedCountry, conn));
        } catch (SQLException ex) {
            Logger.getLogger(CustomerModifyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        // state
        /**
        String selectedDivision = userSelectedCustomer.getDivision();
        try {
            this.Division.setValue(DivisionDao.getSelectedDivision(selectedDivision, conn));
        } catch (SQLException ex) {
            Logger.getLogger(CustomerModifyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        * */
        this.PostalCode.setText(userSelectedCustomer.getPostal_Code());
        this.PhoneNumber.setText(userSelectedCustomer.getPhone());
        
    }    

    /** This is the save method. 
     @param event button click event.
     */
    @FXML
    private void onClickCMSave(ActionEvent event) throws IOException, SQLException {
        Connection conn = DBConnection.getConnection();
        
        String Name = this.Name.getText();
        String Address = this.Address.getText();
        Countries Country = this.Country.getSelectionModel().getSelectedItem();
        first_level_divisions Division = this.Division.getSelectionModel().getSelectedItem();
        String PostalCode = this.PostalCode.getText();
        String PhoneNumber = this.PhoneNumber.getText();
        
        if ((Name == null) || (Address == null) || (Country == null) || (Division == null) || (PostalCode == null) || (PhoneNumber == null)) {
            customerModifyError.setText("ERROR: All fields have to be filled out.");
            customerModifyError.setVisible(true);
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e -> customerModifyError.setVisible(false));
            pause.play();
        } else {
            System.out.println("Division ID" + Division.getDivision_ID());
            Customer customer = new Customer(Name, Address, PostalCode, PhoneNumber,Division.getDivision_ID());
            CustomerDao.deleteCustomer(userSelectedCustomer, conn);
            CustomerDao.addCustomer(customer, conn);
        
            Parent parent = FXMLLoader.load(getClass().getResource("/view/CustomerMain.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
    

    /** This is the cancel method. 
     @param event button click event.
     */
    @FXML
    private void onClickCMCancel(ActionEvent event) throws IOException {
Parent parent = FXMLLoader.load(getClass().getResource("/view/CustomerMain.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /** This is the division preset method. 
     @param event button click event.
     */
    @FXML
    private void onClickCountry(ActionEvent event) throws SQLException {
        Connection conn = DBConnection.getConnection();
        Countries selected = Country.getSelectionModel().getSelectedItem();
        if(selected != null){
            this.Division.getSelectionModel().clearSelection();
        }
        String selectedCountry = selected.getCountry();
        if("U.S".equals(selectedCountry)){
            this.Division.setItems(DivisionDao.getUSDivisions(conn));
        }
        if("Canada".equals(selectedCountry)){
            this.Division.setItems(DivisionDao.getCanadaDivisions(conn));
        }
        if("UK".equals(selectedCountry)){
            this.Division.setItems(DivisionDao.getUKDivisions(conn));
        }
        
    }

    @FXML
    private void onClickDivision(ActionEvent event) {
    }
    
}
