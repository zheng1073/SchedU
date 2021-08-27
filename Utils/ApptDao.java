/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.collections.ObservableList;
import model.Appointment;

/**
 *
 * @author j1996
 */
/** Interface class for the Appointment DAO. */
public interface ApptDao {
    ObservableList<Appointment> getAllAppointments();
    ObservableList<Appointment> getWeekAppointments();
    ObservableList<Appointment> getMonthAppointments();
    
    public void updateAppointment(Appointment appointment);
    public void addAppointment(Appointment appointment);
    public void deleteAppointment(Appointment appointment);
}
