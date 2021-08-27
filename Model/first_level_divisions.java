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
/** Division Class Model. */
public class first_level_divisions {
    private int Division_ID;
    private String Division;
    private Date Create_Date;

    public first_level_divisions(int Division_ID, String Division, int COUNTRY_ID) {
        this.Division_ID = Division_ID;
        this.Division = Division;
        this.COUNTRY_ID = COUNTRY_ID;
    }
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private int COUNTRY_ID;


    public first_level_divisions(int Division_ID, String Division, Date Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By, int COUNTRY_ID) {
        this.Division_ID = Division_ID;
        this.Division = Division;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
        this.COUNTRY_ID = COUNTRY_ID;
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

    /** getter method for last updater.
     * @return  */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /** setter method for last updater.
     * @param Last_Updated_By */
    public void setLast_Updated_By(String Last_Updated_By) {
        this.Last_Updated_By = Last_Updated_By;
    }

    /** getter method for country ID.
     * @return  */
    public int getCOUNTRY_ID() {
        return COUNTRY_ID;
    }

    /** setter method for country ID.
     * @param COUNTRY_ID */
    public void setCOUNTRY_ID(int COUNTRY_ID) {
        this.COUNTRY_ID = COUNTRY_ID;
    }
    
    /** to string method. */
    public String toString() {
        return getDivision();
    }
}
