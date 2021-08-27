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
/** User Class Model. */
public class User {
    private int User_ID;
    private String User_Name;
    private String Password;
    private Date Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;

    public User(int User_ID, String User_Name, String Password, Date Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By) {
        this.User_ID = User_ID;
        this.User_Name = User_Name;
        this.Password = Password;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
    }

    /** getter/setter method.
     * @return  */
    public int getUser_ID() {
        return User_ID;
    }

    /** getter/setter method.
     * @param User_ID */
    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID;
    }

    /** getter/setter method.
     * @return  */
    public String getUser_Name() {
        return User_Name;
    }

    /** getter/setter method.
     * @param User_Name */
    public void setUser_Name(String User_Name) {
        this.User_Name = User_Name;
    }

    /** getter/setter method.
     * @return  */
    public String getPassword() {
        return Password;
    }

    /** getter/setter method.
     * @param Password */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /** getter/setter method.
     * @return  */
    public Date getCreate_Date() {
        return Create_Date;
    }

    /** getter/setter method.
     * @param Create_Date */
    public void setCreate_Date(Date Create_Date) {
        this.Create_Date = Create_Date;
    }

    /** getter/setter method.
     * @return  */
    public String getCreated_By() {
        return Created_By;
    }

    /** getter/setter method.
     * @param Created_By */
    public void setCreated_By(String Created_By) {
        this.Created_By = Created_By;
    }

    /** getter/setter method.
     * @return  */
    public Timestamp getLast_Update() {
        return Last_Update;
    }

    /** getter/setter method.
     * @param Last_Update */
    public void setLast_Update(Timestamp Last_Update) {
        this.Last_Update = Last_Update;
    }

    /** getter/setter method.
     * @return  */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /** getter/setter method.
     * @param Last_Updated_By */
    public void setLast_Updated_By(String Last_Updated_By) {
        this.Last_Updated_By = Last_Updated_By;
    }
    
    //** to string method. */
    @Override
    public String toString() {
        return getUser_Name();
    }
}
