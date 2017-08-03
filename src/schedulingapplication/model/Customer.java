/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import schedulingapplication.dao.CustomerDAO;

/**
 *
 */
public class Customer extends ScheduleItem {

    private String name;
   
    private boolean active;

    private int addressId;

    public Customer(LocalDateTime createDate, String createdBy,
           String name, boolean active) {
        super(createDate, createdBy);
        this.name = name;
        this.active = active;
        this.addressId = super.getId();
        
    }

    public Customer() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public List<Country> getCountryList() throws Exception {
        return CustomerDAO.getCountryList();
    }

    public List<Customer> getCustomerList() throws Exception {
        return CustomerDAO.getCustomerList();
    }

    public List<Address> getAddressList() throws Exception {
        return CustomerDAO.getAddressList();
    }

    public List<City> getCityList() throws Exception {
        return CustomerDAO.getCityList();
    }

    public List<Appointment> getAppointmentList() throws Exception {
        return CustomerDAO.getAppointmentList();
    }

    public List<Incrementtypes> getTypeList() throws Exception {
        return CustomerDAO.getTypeList();
    }

    public void addCountry(Country country) throws Exception {
        CustomerDAO.addCountry(country);
    }

    public void addCity(City city) throws Exception {
        CustomerDAO.addCity(city);
    }

    public void addAddress(Address address) throws Exception {
        CustomerDAO.addAddress(address);
    }

    public void addCustomer(Customer customer) throws Exception {
        CustomerDAO.addCustomer(customer);
    }

    public void addAppointment(Appointment appointment) throws Exception {
        CustomerDAO.addAppointment(appointment);
    }

    public void addReminder(Reminder reminder) throws Exception {
        CustomerDAO.addReminder(reminder);
    }

    public void addType(Incrementtypes type) throws Exception {
        CustomerDAO.addType(type);
    }

    @Override
    public String toString() {
        return getId() + ", " + name;
    }

}
