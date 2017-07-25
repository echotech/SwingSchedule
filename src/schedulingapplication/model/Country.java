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
public class Country extends ScheduleItem {
    
    private String country;
    
    public Country(int id, LocalDateTime createDate, String createdBy, 
            LocalDate lastUpdate, String lastUpdateBy, String country) {
        super(id, createDate, createdBy, lastUpdate, lastUpdateBy);
        this.country = country;
    }
    
    public Country() {
        
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }  
    
    @Override
    public String toString() {
        return country;
    }

}
