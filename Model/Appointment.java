/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.time.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author j1996
 */
/** Appointment Class Model. */
public final class Appointment {
    private int Appointment_ID;
    
    /** getter method for apt.
     * @param Type */
    public Appointment(String Type) {
        this.Type = Type;
    }
    private String Title;
    private String Description;
    private String Location;

    public Appointment(int Appointment_ID, String Title, String Description, String Location, String Type, String Start, String End, int Customer_ID, int User_ID, String Contact_Name) {
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.Customer_ID = Customer_ID;
        this.User_ID = User_ID;
        this.Contact_Name = Contact_Name;
    }
    private int Contact_ID;
    private String Type;
    private String Start;

    public Appointment(int Appointment_ID, String Title, String Description, String Location, String Type, String Start, int Customer_ID, int User_ID, String Contact_Name) {
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.Start = Start;
        this.Customer_ID = Customer_ID;
        this.User_ID = User_ID;
        this.Contact_Name = Contact_Name;
    }

   
    public Appointment(int Appointment_ID, String Title, String Description, String Location, String Type, String Start, String End, int Customer_ID, String Contact_Name) {
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.Customer_ID = Customer_ID;
        this.Contact_Name = Contact_Name;
    }

    /** getter method for division ID.
     * @return  */
    public String getContact_Name() {
        return Contact_Name;
    }

    /** getter method for division ID.
     * @param Contact_Name */
    public void setContact_Name(String Contact_Name) {
        this.Contact_Name = Contact_Name;
    }
    private String End;
    private int Customer_ID;
    private int User_ID;
    private String Created_By;
    private String Last_Updated_By;
    private String Last_Update;
    private String Create_Date;
    private String Contact_Name;

    
    public Appointment(String Title, String Description, String Location, int Contact_ID, String Type, String Start, String End, int Customer_ID, int User_ID, String Created_By, String Last_Updated_By) {
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Contact_ID = Contact_ID;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.Customer_ID = Customer_ID;
        this.User_ID = User_ID;
        this.Created_By = Created_By;
        this.Last_Updated_By = Last_Updated_By;
    }
    
    public Appointment(int Appointment_ID, String Title, String Description, String Location, int Contact_ID, String Type, String Start, String End, int Customer_ID, int User_ID, String Created_By, String Last_Updated_By) {
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Contact_ID = Contact_ID;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.Customer_ID = Customer_ID;
        this.User_ID = User_ID;
        this.Created_By = Created_By;
        this.Last_Updated_By = Last_Updated_By;
    }

    public Appointment(int Appointment_ID, String Title, String Description, String Location, int Contact_ID, String Type, String Start, String End, int Customer_ID, int User_ID, String Created_By, String Last_Updated_By, String Last_Update, String Create_Date) {
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Contact_ID = Contact_ID;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.Customer_ID = Customer_ID;
        this.User_ID = User_ID;
        this.Created_By = Created_By;
        this.Last_Updated_By = Last_Updated_By;
        this.Last_Update = Last_Update;
        this.Create_Date = Create_Date;
    }

    /** getter method for user ID.
     * @return  */
    public int getUser_ID() {
        return User_ID;
    }

    /** setter method for user ID.
     * @param User_ID */
    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID;
    }

    /** getter method for create date.
     * @return  */
    public String getCreated_By() {
        return Created_By;
    }

    /** setter method for create bu.
     * @param Created_By */
    public void setCreated_By(String Created_By) {
        this.Created_By = Created_By;
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

    /** getter method for last update.
     * @return  */
    public String getLast_Update() {
        return Last_Update;
    }

    /** setter method for last update.
     * @param Last_Update */
    public void setLast_Update(String Last_Update) {
        this.Last_Update = Last_Update;
    }

    /** getter method for date.
     * @return  */
    public String getCreate_Date() {
        return Create_Date;
    }

    /** setter method for date.
     * @param Create_Date */
    public void setCreate_Date(String Create_Date) {
        this.Create_Date = Create_Date;
    }

    public Appointment(int Appointment_ID, String Title, String Description, String Location, int Contact_ID, String Type, String Start, String End, int Customer_ID) {
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Contact_ID = Contact_ID;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.Customer_ID = Customer_ID;
    }

    /** getter method for appt ID.
     * @return  */
    public int getAppointment_ID() {
        return Appointment_ID;
    }

    /** setter method for appt ID.
     * @param Appointment_ID */
    public void setAppointment_ID(int Appointment_ID) {
        this.Appointment_ID = Appointment_ID;
    }

    /** getter method for title.
     * @return  */
    public String getTitle() {
        return Title;
    }

    /** setter method for title.
     * @param Title */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /** getter method for desc.
     * @return  */
    public String getDescription() {
        return Description;
    }

    /** setter method for desc.
     * @param Description */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /** getter method for location.
     * @return  */
    public String getLocation() {
        return Location;
    }

    /** setter method for location.
     * @param Location */
    public void setLocation(String Location) {
        this.Location = Location;
    }

    /** getter method for contact ID.
     * @return  */
    public int getContact_ID() {
        return Contact_ID;
    }

    /** setter method for contact ID.
     * @param Contact_ID */
    public void setContact_ID(int Contact_ID) {
        this.Contact_ID = Contact_ID;
    }

    /** getter method for type.
     * @return  */
    public String getType() {
        return Type;
    }

    /** setter method for type.
     * @param Type */
    public void setType(String Type) {
        this.Type = Type;
    }

    /** getter method for start.
     * @return  */
    public String getStart() {
        return Start;
    }

    /** setter method for start.
     * @param Start */
    public void setStart(String Start) {
        this.Start = Start;
    }

    /** getter method for end.
     * @return  */
    public String getEnd() {
        return End;
    }

    /** setter method for end.
     * @param End */
    public void setEnd(String End) {
        this.End = End;
    }

    /** getter method for customer ID.
     * @return  */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /** setter method for customer ID.
     * @param Customer_ID */
    public void setCustomer_ID(int Customer_ID) {
        this.Customer_ID = Customer_ID;
    }
    
    /** to string method.  */
    @Override
    public String toString() {
        return getType();
    }
}
