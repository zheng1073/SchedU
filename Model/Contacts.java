/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author j1996
 */
/** Contact Class Model. */
public class Contacts {
    private int Contact_ID;
    private String Contact_Name;
    private String Email;

 
    public Contacts(int Contact_ID, String Contact_Name, String Email) {
        this.Contact_ID = Contact_ID;
        this.Contact_Name = Contact_Name;
        this.Email = Email;
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

    /** getter method for name.
     * @return  */
    public String getContact_Name() {
        return Contact_Name;
    }

    /** setter method for name.
     * @param Contact_Name */
    public void setContact_Name(String Contact_Name) {
        this.Contact_Name = Contact_Name;
    }

    /** getter method for email.
     * @return  */
    public String getEmail() {
        return Email;
    }

    /** setter method for email.
     * @param Email */
    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    /** to string method.  */
    @Override
    public String toString() {
        return getContact_Name();
    }
}
