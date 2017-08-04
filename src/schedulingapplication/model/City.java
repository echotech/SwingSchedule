/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class City extends ScheduleItem {
    
    private String city;
    private static AtomicInteger nextId = new AtomicInteger(0);
    
    private int countryId;
    
    public City( LocalDateTime createDate, String createdBy, 
            String city) {
        super(createDate, createdBy);
        this.city = city;
        this.countryId = nextId.incrementAndGet();
    }
    
    public City() {
        
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    
    @Override
    public String toString() {
        return city;
    }
    
}
