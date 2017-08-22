/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 */
public class Appointment extends ScheduleItem {
    
    private int customerId;
    
    private String title;
    
    private String description;
    
    private String location;
    
    private String contact;
    
    private String url;
    
    private LocalDateTime start;
    private int beenReminded;
    private LocalDateTime end;
    private int snoozeCounter;
    
    public Appointment(LocalDateTime createDate, String createdBy, 
            int customerId, String title, String description, String location, String contact, 
            String url, LocalDateTime start, LocalDateTime end) {
        super(createDate, createdBy);
        this.customerId = customerId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.url = url;
        this.start = start;
        this.end = end;
        this.snoozeCounter=10;
        
    }
    
    public Appointment() {
        
    }
    
    public int getSnoozeCounter(){
        return snoozeCounter;
    }
    
    public void decrementSnoozeCounter(){
        this.snoozeCounter-=5;
    }
    
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setReminded(int i){
        this.beenReminded=i;
    }
    
    public boolean getReminded(){
        if (beenReminded==0){
            return false;
        }
        else return true;
    }
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
    
    public String display() {
        return "Id: " + getId() + "<br>Title: " + title +  "<br>Location: " + location + "<br>Contact: " +
                contact + "<br>Start: " + start +
                "<br>End: " + end + "";
    }
    
    @Override
    public String toString() {
        return getId() + ", " + title;
    }
    
}
