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
/** Country Class Model. */
public class Countries {
    private int Country_ID;
    private String Country;
    
    /** country constructor
     * @param Country_ID
     * @param Country */
    public Countries(int Country_ID, String Country) {
        this.Country_ID = Country_ID;
        this.Country = Country;
    }
    private Date Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Update_By;

    
    public Countries(int Country_ID, String Country, Date Create_Date, String Created_By, Timestamp Last_Update, String Last_Update_By) {
        this.Country_ID = Country_ID;
        this.Country = Country;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Update_By = Last_Update_By;
    }

    /** getter method for country ID.
     * @return  */
    public int getCountry_ID() {
        return Country_ID;
    }

    /** setter method for country ID.
     * @param Country_ID */
    public void setCountry_ID(int Country_ID) {
        this.Country_ID = Country_ID;
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

    /** getter method for date.
     * @return  */
    public Date getCreate_Date() {
        return Create_Date;
    }

    /** setter method for date.
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

    /** getter method for last update.
     * @return  */
    public Timestamp getLast_Update() {
        return Last_Update;
    }

    /** setter method for last update.
     * @param Last_Update */
    public void setLast_Update(Timestamp Last_Update) {
        this.Last_Update = Last_Update;
    }

    /** getter method for update by.
     * @return  */
    public String getLast_Update_By() {
        return Last_Update_By;
    }

    /** setter method for update by.
     * @param Last_Update_By */
    public void setLast_Update_By(String Last_Update_By) {
        this.Last_Update_By = Last_Update_By;
    }
    
    /** to string method.  */
    public String toString() {
        return getCountry();
    }
}
