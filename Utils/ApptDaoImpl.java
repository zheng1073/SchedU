/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import controller.LoginController;
import javafx.collections.ObservableList;
import model.Appointment;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import javafx.collections.FXCollections;
import model.Contacts;

/**
 *
 * @author j1996
 */
/** Appointment Class DAO. */
public class ApptDaoImpl implements ApptDao {
    
    public static ObservableList<Appointment> appointment = FXCollections.observableArrayList();
    private static DateTimeFormatter datetime_DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    

    // used to populate the table --> need to convert from UTC to the local time
    /** get all appointment method.
     @param con database connection.
     * @return returns a list of appointments.
     */
    public static ObservableList<Appointment> getAllAppointments(Connection con) throws SQLException {
        Statement st = con.createStatement();      
        ResultSet rs = st.executeQuery("SELECT * FROM appointments");
        // Create empty list and repopulate list with the data queried above
        appointment = FXCollections.observableArrayList();
        while (rs.next()) {
            // get database start time (currently in UTC)
            String startUTC = rs.getString("Start").substring(0,19);
            String endUTC = rs.getString("End").substring(0,19);
            // Convert UTC to LocalDateTime
            LocalDateTime startUTC_LDT = LocalDateTime.parse(startUTC, datetime_DTF);
            LocalDateTime endUTC_LDT = LocalDateTime.parse(endUTC, datetime_DTF);
            // Convert UTC to Local Time Zone
            ZoneId userZoneID = ZoneId.systemDefault();
            ZoneId utcZoneID = ZoneId.of("UTC");
            ZonedDateTime start_LZ = startUTC_LDT.atZone(utcZoneID).withZoneSameInstant(userZoneID);
            ZonedDateTime end_LZ = endUTC_LDT.atZone(utcZoneID).withZoneSameInstant(userZoneID);
            // Convert to Strings
            String start_LZ_S = start_LZ.format(datetime_DTF);
            String end_LZ_S = end_LZ.format(datetime_DTF);
            appointment.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getInt("Contact_ID"), rs.getString("Type"), start_LZ_S, end_LZ_S, rs.getInt("Customer_ID")));
            // a
            // appointment.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getInt("Contact_ID"), rs.getString("Type"), start_LZ_S, end_LZ_S, rs.getInt("Customer_ID")));
        }
        System.out.println("Appointments = " + appointment.size());
        return appointment;
    }
    
    /** get all appointment method.
     @param con database connection.
     * @return returns a list of appointments.
     */
    public static ObservableList<Appointment> getAllAppointmentsD(Connection con) throws SQLException {
        Statement st = con.createStatement();      
        ResultSet rs = st.executeQuery("SELECT * FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.contact_ID");
        // Create empty list and repopulate list with the data queried above
        appointment = FXCollections.observableArrayList();
        while (rs.next()) {
            // get database start time (currently in UTC)
            String startUTC = rs.getString("Start").substring(0,19);
            String endUTC = rs.getString("End").substring(0,19);
            System.out.println("UTC format:" + startUTC);
            // Convert UTC to LocalDateTime
            LocalDateTime startUTC_LDT = LocalDateTime.parse(startUTC, datetime_DTF);
            LocalDateTime endUTC_LDT = LocalDateTime.parse(endUTC, datetime_DTF);
            System.out.println("UTC convert 1:" + startUTC_LDT);
            // Convert UTC to Local Time Zone
            ZoneId userZoneID = ZoneId.systemDefault();
            ZoneId utcZoneID = ZoneId.of("UTC");
            ZonedDateTime start_LZ = startUTC_LDT.atZone(utcZoneID).withZoneSameInstant(userZoneID);
            ZonedDateTime end_LZ = endUTC_LDT.atZone(utcZoneID).withZoneSameInstant(userZoneID);
            System.out.println("ZDT Local Time:" + start_LZ);
            // Convert to Strings
            String start_LZ_S = start_LZ.format(datetime_DTF);
            String end_LZ_S = end_LZ.format(datetime_DTF);
            System.out.println("ZDT Local Time in String:" + start_LZ_S);
            appointment.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getInt("Contact_ID"), rs.getString("Type"), start_LZ_S, end_LZ_S, rs.getInt("Customer_ID")));
            // appointment.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getString("Type"), rs.getString("Start"), rs.getString("End"), rs.getInt("Customer_ID"), rs.getString("Contact_Name")));
        }
        System.out.println("Appointments = " + appointment.size());
        return appointment;
    }
    
    public static ObservableList<Appointment> getAllAppointmentsC(Connection con) throws SQLException {
        Statement st = con.createStatement();      
        ResultSet rs = st.executeQuery("SELECT * FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.contact_ID");
        // Create empty list and repopulate list with the data queried above
        appointment = FXCollections.observableArrayList();
        while (rs.next()) {
            // get database start time (currently in UTC)
            String startUTC = rs.getString("Start").substring(0,19);
            String endUTC = rs.getString("End").substring(0,19);
            System.out.println("UTC format:" + startUTC);
            // Convert UTC to LocalDateTime
            LocalDateTime startUTC_LDT = LocalDateTime.parse(startUTC, datetime_DTF);
            LocalDateTime endUTC_LDT = LocalDateTime.parse(endUTC, datetime_DTF);
            System.out.println("UTC convert 1:" + startUTC_LDT);
            // Convert UTC to Local Time Zone
            ZoneId userZoneID = ZoneId.systemDefault();
            ZoneId utcZoneID = ZoneId.of("UTC");
            ZonedDateTime start_LZ = startUTC_LDT.atZone(utcZoneID).withZoneSameInstant(userZoneID);
            ZonedDateTime end_LZ = endUTC_LDT.atZone(utcZoneID).withZoneSameInstant(userZoneID);
            System.out.println("ZDT Local Time:" + start_LZ);
            // Convert to Strings
            String start_LZ_S = start_LZ.format(datetime_DTF) + " " + TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT);
            String end_LZ_S = end_LZ.format(datetime_DTF) + " " + TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT);
            System.out.println("ZDT Local Time in String:" + start_LZ_S);
            appointment.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getString("Type"), start_LZ_S, end_LZ_S, rs.getInt("Customer_ID"), rs.getInt("User_ID"), rs.getString("Contact_Name")));
            // appointment.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getString("Type"), rs.getString("Start"), rs.getString("End"), rs.getInt("Customer_ID"), rs.getString("Contact_Name")));
        }
        System.out.println("Appointments = " + appointment.size());
        return appointment;
    }

    /** get all appointment by week method.
     @param con database connection.
     * @return returns a list of appointments.
     */
    public static ObservableList<Appointment> getWeekAppointments(Connection con) throws SQLException {
        // testing
        LocalDate now = LocalDate.now();
        LocalDate oneWeek = LocalDate.now().plusDays(7);
        
        
        Statement st = con.createStatement();  
        // change this query
        // ResultSet rs = st.executeQuery("SELECT * FROM appointments WHERE Start BETWEEN now() AND adddate(now(), 7)");
        // ResultSet rs = st.executeQuery("SELECT * FROM appointments WHERE Start >= '"+now+"' AND Start <= '"+oneWeek+"'");
        ResultSet rs = st.executeQuery("SELECT * FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.contact_ID WHERE YEARWEEK(Start) = YEARWEEK('"+now+"')");
        appointment = FXCollections.observableArrayList();
        while (rs.next()) {
            // get database start time (currently in UTC)
            String startUTC = rs.getString("Start").substring(0,19);
            String endUTC = rs.getString("End").substring(0,19);
            System.out.println("UTC:" + startUTC);
            // Convert UTC to LocalDateTime
            LocalDateTime startUTC_LDT = LocalDateTime.parse(startUTC, datetime_DTF);
            LocalDateTime endUTC_LDT = LocalDateTime.parse(endUTC, datetime_DTF);
            System.out.println("UTC convert 1:" + startUTC_LDT);
            // Convert UTC to Local Time Zone
            ZoneId userZoneID = ZoneId.systemDefault();
            ZoneId utcZoneID = ZoneId.of("UTC");
            ZonedDateTime start_LZ = startUTC_LDT.atZone(utcZoneID).withZoneSameInstant(userZoneID);
            ZonedDateTime end_LZ = endUTC_LDT.atZone(utcZoneID).withZoneSameInstant(userZoneID);
            System.out.println("ZDT Local Time:" + start_LZ);
            // Convert to Strings
            //String start_LZ_S = start_LZ.format(datetime_DTF);
            //String end_LZ_S = end_LZ.format(datetime_DTF);
            //System.out.println("ZDT Local Time in String:" + start_LZ_S);
            //appointment.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getInt("Contact_ID"), rs.getString("Type"), start_LZ_S, end_LZ_S, rs.getInt("Customer_ID")));
            String start_LZ_S = start_LZ.format(datetime_DTF) + " " + TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT);
            String end_LZ_S = end_LZ.format(datetime_DTF) + " " + TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT);
            System.out.println("ZDT Local Time in String:" + start_LZ_S);
            appointment.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getString("Type"), start_LZ_S, end_LZ_S, rs.getInt("Customer_ID"), rs.getInt("User_ID"), rs.getString("Contact_Name")));
            // a
            // appointment.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getInt("Contact_ID"), rs.getString("Type"), rs.getString("Start"), rs.getString("End"), rs.getInt("Customer_ID")));
        }
        System.out.println("Appointments = " + appointment.size());
        return appointment;
    }

    /** get all appointment by month method.
     @param con database connection.
     * @return returns a list of appointments.
     */
    public static ObservableList<Appointment> getMonthAppointments(Connection con) throws SQLException {
        Statement st = con.createStatement(); 
        
        // testing
        LocalDate now = LocalDate.now();
        LocalDate oneMonth = LocalDate.now().plusMonths(1);
        
        // change this query
        // ResultSet rs = st.executeQuery("SELECT * FROM appointments WHERE Start BETWEEN now() AND adddate(now(), 30)");
        ResultSet rs = st.executeQuery("SELECT * FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.contact_ID WHERE YEAR(Start) = YEAR('"+now+"') AND MONTH(Start) = MONTH('"+now+"')");
        // ResultSet rs = st.executeQuery("SELECT * FROM appointments WHERE YEAR(Start) = YEAR('"+now+"') AND MONTH(Start) = MONTH('"+now+"')");
        appointment = FXCollections.observableArrayList();
        while (rs.next()) {
            // get database start time (currently in UTC)
            String startUTC = rs.getString("Start").substring(0,19);
            String endUTC = rs.getString("End").substring(0,19);
            System.out.println("UTC:" + startUTC);
            // Convert UTC to LocalDateTime
            LocalDateTime startUTC_LDT = LocalDateTime.parse(startUTC, datetime_DTF);
            LocalDateTime endUTC_LDT = LocalDateTime.parse(endUTC, datetime_DTF);
            System.out.println("UTC convert 1:" + startUTC_LDT);
            // Convert UTC to Local Time Zone
            ZoneId userZoneID = ZoneId.systemDefault();
            ZoneId utcZoneID = ZoneId.of("UTC");
            ZonedDateTime start_LZ = startUTC_LDT.atZone(utcZoneID).withZoneSameInstant(userZoneID);
            ZonedDateTime end_LZ = endUTC_LDT.atZone(utcZoneID).withZoneSameInstant(userZoneID);
            System.out.println("ZDT Local Time:" + start_LZ);
            // Convert to Strings
            //String start_LZ_S = start_LZ.format(datetime_DTF);
            //String end_LZ_S = end_LZ.format(datetime_DTF);
            //System.out.println("ZDT Local Time in String:" + start_LZ_S);
            //appointment.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getInt("Contact_ID"), rs.getString("Type"), start_LZ_S, end_LZ_S, rs.getInt("Customer_ID")));
            String start_LZ_S = start_LZ.format(datetime_DTF) + " " + TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT);
            String end_LZ_S = end_LZ.format(datetime_DTF) + " " + TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT);
            System.out.println("ZDT Local Time in String:" + start_LZ_S);
            appointment.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getString("Type"), start_LZ_S, end_LZ_S, rs.getInt("Customer_ID"), rs.getInt("User_ID"), rs.getString("Contact_Name")));
            // a
            // appointment.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getInt("Contact_ID"), rs.getString("Type"), rs.getString("Start"), rs.getString("End"), rs.getInt("Customer_ID")));
        }
        System.out.println("Appointments = " + appointment.size());
        return appointment;
    }
    
    /** get all appointment by customer method.
     @param con database connection.
     * @param Customer_ID id for customer.
     * @return returns a list of appointments.
     */
    public static ObservableList<Appointment> getAppointmentByCustomer(int Customer_ID, Connection con) throws SQLException {
        Statement st = con.createStatement();  
        // change this query
        ResultSet rs = st.executeQuery("SELECT * FROM appointments WHERE Customer_ID ='"+Customer_ID+"'");
        appointment = FXCollections.observableArrayList();
        while (rs.next()) {
            appointment.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getInt("Contact_ID"), rs.getString("Type"), rs.getString("Start"), rs.getString("End"), rs.getInt("Customer_ID")));
        }
        System.out.println("Appointments = " + appointment.size());
        return appointment;
    }
    
    /** get all appointment by contact method.
     @param con database connection.
     * @param C_ID id for contact.
     * @return returns a list of appointments.
     */
    public static ObservableList<Appointment> getAppointmentByContact(int C_ID, Connection con) throws SQLException {
        Statement st = con.createStatement();  
        // change this query
        ResultSet rs = st.executeQuery("SELECT * FROM appointments WHERE Contact_ID ='"+C_ID+"'");
        appointment = FXCollections.observableArrayList();
        while (rs.next()) {
            appointment.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getInt("Contact_ID"), rs.getString("Type"), rs.getString("Start"), rs.getString("End"), rs.getInt("Customer_ID")));
        }
        System.out.println("Appointments = " + appointment.size());
        return appointment;
    }
    
    /** get all appointment by type method.
     @param con database connection.
     * @return returns a list of appointments.
     */
    public static ObservableList<Appointment> getAppointmentByType(Connection con) throws SQLException {
        Statement st = con.createStatement();  
        // change this query
        ResultSet rs = st.executeQuery("SELECT DISTINCT Type FROM appointments;");
        appointment = FXCollections.observableArrayList();
        while (rs.next()) {
            appointment.add(new Appointment(rs.getString("Type")));
        }
        return appointment;
    }
    
    /** get all appointment by month and type method.
     @param con database connection.
     * @return returns a list of appointments.
     */
    public static ObservableList<Appointment> getAC(int ms,int me, String t,Connection con) throws SQLException {
        Statement st = con.createStatement();  
        // change this query
        ResultSet rs = st.executeQuery("SELECT * FROM appointments WHERE (MONTH(Start)="+ms+" OR MONTH(End)="+me+") AND Type='"+t+"';");
        appointment = FXCollections.observableArrayList();
        while (rs.next()) {
            appointment.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getInt("Contact_ID"), rs.getString("Type"), rs.getString("Start"), rs.getString("End"), rs.getInt("Customer_ID")));
        }
        return appointment;
    }
    
    /** check for duplicates method.
     @param con database connection.
     * @return returns boolean true or false.
     */
    public static boolean checkDuplicates(int Customer_ID,Timestamp start, Timestamp end, Connection con) throws SQLException {
        Statement st = con.createStatement();  
        // change this query
        String s = start.toString();
        String e = end.toString();
        ResultSet rs = st.executeQuery("SELECT * FROM appointments WHERE (Customer_ID ='"+Customer_ID+"') AND ('"+s+"' BETWEEN Start AND End OR '"+e+"' BETWEEN Start AND End OR '"+s+"' < Start AND '"+e+"' > End)");
        appointment = FXCollections.observableArrayList();
        while (rs.next()) {
            appointment.add(new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"), rs.getString("Location"), rs.getInt("Contact_ID"), rs.getString("Type"), rs.getString("Start"), rs.getString("End"), rs.getInt("Customer_ID")));
        }
        System.out.println("Appointments = " + appointment.size());
        if(appointment.size() == 0){
            return false;
        }
        return true;
    }
    
    /** get business open method.
     * @return returns int hour.
     */
    public static int businessOpen() {
        LocalDate estDate = LocalDate.of(2019, 10, 26);
        LocalTime estTime = LocalTime.of(8, 00); // hour, min
        ZoneId estZoneId = ZoneId.of("US/Eastern");
        ZonedDateTime estZDT = ZonedDateTime.of(estDate, estTime, estZoneId);
        
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        
        ZonedDateTime estToLocalZDT = estZDT.withZoneSameInstant(localZoneId);
        int hr = estToLocalZDT.getHour();
        
        return hr;
    }
    
    /** get business close method.
     * @return returns int hour.
     */
    public static int businessClose() {
        LocalDate estDate = LocalDate.of(2019, 10, 26);
        LocalTime estTime = LocalTime.of(22, 00); // hour, min
        ZoneId estZoneId = ZoneId.of("US/Eastern");
        ZonedDateTime estZDT = ZonedDateTime.of(estDate, estTime, estZoneId);
        
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        
        ZonedDateTime estToLocalZDT = estZDT.withZoneSameInstant(localZoneId);
        int hr = estToLocalZDT.getHour();
        
        return hr;
    }

    /** add appointment method. */
    public static void addAppointment(Appointment appointment, Connection con) throws SQLException {
        Statement st = con.createStatement();  
        // change this query
        st.executeUpdate("INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES('" + appointment.getTitle() + "','" + appointment.getDescription() + "','" + appointment.getLocation() + "','" + appointment.getType() + "','" + appointment.getStart() + "','" + appointment.getEnd() + "',now(),'" + appointment.getCreated_By() + "',now(),'" + appointment.getLast_Updated_By() + "'," + appointment.getCustomer_ID() +"," + appointment.getUser_ID() + "," + appointment.getContact_ID()+")");
    }

    /** delete appointment method. */
    public static void deleteAppointment(Appointment appointment, Connection con) throws SQLException {
        Statement st = con.createStatement();
        st.executeUpdate("DELETE FROM appointments WHERE Appointment_ID = " + appointment.getAppointment_ID());
    }

    @Override
    public ObservableList<Appointment> getAllAppointments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Appointment> getWeekAppointments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Appointment> getMonthAppointments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addAppointment(Appointment appointment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
