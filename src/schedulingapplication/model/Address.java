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
public class Address extends ScheduleItem {
    
    private String address;
    
    private String address2;
    
    private int cityId;
    private static AtomicInteger nextId = new AtomicInteger(0);
    
    private String postalCode;
    
    private String phone;
    
    public Address(LocalDateTime createDate, String createdBy, 
            String address,String address2, String postalCode, String phone) {
        super(createDate, createdBy);
        this.address = address;
        this.address2 = address2;
        this.cityId = nextId.incrementAndGet();
        this.postalCode = postalCode;
        this.phone = phone;
        
    }
    
    public Address() {
        
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Override
    public String toString() {
        return getId() + ", " + address;
    }
    
}
