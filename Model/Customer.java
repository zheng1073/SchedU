/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
/**
 *
 * @author j1996
 */
/** Customer Class Model. */
public class Customer {
    private int Customer_ID;
    private String Customer_Name;

    public Customer(String Customer_Name, String Address, String Postal_Code, String Phone, int Division_ID) {
        this.Customer_Name = Customer_Name;
        this.Address = Address;
        this.Postal_Code = Postal_Code;
        this.Phone = Phone;
        this.Division_ID = Division_ID;
    }
    private String Address;
    private String Postal_Code;
    private String Phone;
    private Date Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private int Division_ID;

    

    public Customer(int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone, Date Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By, int Division_ID) {
        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Address = Address;
        this.Postal_Code = Postal_Code;
        this.Phone = Phone;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
        this.Division_ID = Division_ID;
    }
    
    private String Division;
    private String Country;

    public Customer(int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone, Date Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By, int Division_ID, String Division, String Country) {
        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Address = Address;
        this.Postal_Code = Postal_Code;
        this.Phone = Phone;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
        this.Division_ID = Division_ID;
        this.Division = Division;
        this.Country = Country;
    }
    
    /** getter method for customer ID.  */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /** setter method for customer ID.  */
    public void setCustomer_ID(int Customner_ID) {
        this.Customer_ID = Customner_ID;
    }

    /** getter method for name.
     * @return  */
    public String getCustomer_Name() {
        return Customer_Name;
    }

    /** setter method for name.
     * @param Customer_Name */
    public void setCustomer_Name(String Customer_Name) {
        this.Customer_Name = Customer_Name;
    }

    /** getter method for addy.
     * @return  */
    public String getAddress() {
        return Address;
    }

    /** setter method for addy.
     * @param Address */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /** getter method for code.
     * @return  */
    public String getPostal_Code() {
        return Postal_Code;
    }

    /** setter method for code.
     * @param Postal_Code */
    public void setPostal_Code(String Postal_Code) {
        this.Postal_Code = Postal_Code;
    }

    /** getter method for phone.
     * @return  */
    public String getPhone() {
        return Phone;
    }

    /** setter method for phone.
     * @param Phone */
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    /** getter method for create date.
     * @return  */
    public Date getCreate_Date() {
        return Create_Date;
    }

    /** setter method for create date.
     * @param Create_Date */
    public void setCreate_Date(Date Create_Date) {
        this.Create_Date = Create_Date;
    }

    /** getter method for create by.
     * @return  */
    public String getCreated_By() {
        return Created_By;
    }

    /** setter method for create by.
     * @param Created_By */
    public void setCreated_By(String Created_By) {
        this.Created_By = Created_By;
    }

    /** getter method for update.
     * @return  */
    public Timestamp getLast_Update() {
        return Last_Update;
    }

    /** setter method for update.
     * @param Last_Update */
    public void setLast_Update(Timestamp Last_Update) {
        this.Last_Update = Last_Update;
    }

    /** getter method for update by.
     * @return  */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /** setter method for update by.
     * @param Last_Updated_By */
    public void setLast_Updated_By(String Last_Updated_By) {
        this.Last_Updated_By = Last_Updated_By;
    }

    /** getter method for division ID.
     * @return  */
    public int getDivision_ID() {
        return Division_ID;
    }

    /** setter method for division ID.
     * @param Division_ID */
    public void setDivision_ID(int Division_ID) {
        this.Division_ID = Division_ID;
    }
    
    /** getter method for division.
     * @return  */
    public String getDivision() {
        return Division;
    }

    /** setter method for division.
     * @param Division */
    public void setDivision(String Division) {
        this.Division = Division;
    }

    /** getter method for country.
     * @return  */
    public String getCountry() {
        return Country;
    }

    /** setter method for country.
     * @param Country */
    public void setCountry(String Country) {
        this.Country = Country;
    }
    
    
    /** to string method. */
    @Override
    public String toString() {
        return getCustomer_Name();
    }
    
}
