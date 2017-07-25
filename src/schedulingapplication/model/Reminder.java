/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.model;

import java.time.LocalDateTime;

/**
 *
 */
public class Reminder {
    
    private int reminderId;
    
    private LocalDateTime reminderDate;
    
    private int snoozeIncrement;
    
    private int snoozeIncrementTypeId;
    
    private int appointmentId;
    
    private String createdBy;
    
    private LocalDateTime createdDate;
    
    private String remindercol;

    public Reminder(int reminderId, LocalDateTime reminderDate, 
            int snoozeIncrement, int snoozeIncrementTypeId, int appointmentId, 
            String createdBy, LocalDateTime createdDate, String remindercol) {
        this.reminderId = reminderId;
        this.reminderDate = reminderDate;
        this.snoozeIncrement = snoozeIncrement;
        this.snoozeIncrementTypeId = snoozeIncrementTypeId;
        this.appointmentId = appointmentId;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.remindercol = remindercol;
    }
    
    public Reminder() {
        
    }

    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public LocalDateTime getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(LocalDateTime reminderDate) {
        this.reminderDate = reminderDate;
    }

    public int getSnoozeIncrement() {
        return snoozeIncrement;
    }

    public void setSnoozeIncrement(int snoozeIncrement) {
        this.snoozeIncrement = snoozeIncrement;
    }

    public int getSnoozeIncrementTypeId() {
        return snoozeIncrementTypeId;
    }

    public void setSnoozeIncrementTypeId(int snoozeIncrementTypeId) {
        this.snoozeIncrementTypeId = snoozeIncrementTypeId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getRemindercol() {
        return remindercol;
    }

    public void setRemindercol(String remindercol) {
        this.remindercol = remindercol;
    }
    
    
    
}
