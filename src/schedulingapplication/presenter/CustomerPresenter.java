/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import schedulingapplication.model.Address;
import schedulingapplication.model.Appointment;
import schedulingapplication.model.City;
import schedulingapplication.model.Country;
import schedulingapplication.model.Customer;
import schedulingapplication.model.Incrementtypes;
import schedulingapplication.model.Reminder;
import schedulingapplication.model.User;
import schedulingapplication.view.CustomerFrame;

/**
 *
 */
public class CustomerPresenter implements ActionListener {
    

    private Customer model;

    private CustomerFrame view;

    public CustomerPresenter(Customer model, CustomerFrame view) {
        this.model = model;
        this.view = view;
    }
    
    private User userName;

    public void initView() {
        view.getPanel().getJbAddCountry().setActionCommand("addcountry");
        view.getPanel().getJbAddCity().setActionCommand("addcity");
        view.getPanel().getJbAddAddress().setActionCommand("addaddress");
        view.getPanel().getJbAddCustomer().setActionCommand("addcustomer");
        view.getPanel().getJbAddAppointment().setActionCommand("addappointment");
        view.getPanel().getJbAddReminder().setActionCommand("addreminder");
        view.getPanel().getJbAddIncrement().setActionCommand("addincrement");

        updateCountries();
        updateCities();
        updateAddresses();
        updateCustomers();
        updateAppointments();
        updateTypes();
    }

    private void updateTypes() {
        try {
            view.getPanel().getJcbTypes().removeAllItems();
            List<Incrementtypes> types = model.getTypeList();
            types.forEach(a -> view.getPanel().getJcbTypes().addItem(a));
        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }
    }

    private void updateAppointments() {
        try {
            view.getPanel().getJcbAppointments().removeAllItems();
            List<Appointment> appointments = model.getAppointmentList();
            appointments.forEach(a -> view.getPanel().getJcbAppointments().addItem(a));
        } catch (Exception exc) {
            view.getPanel().displayError(exc);
            exc.printStackTrace();
        }
    }

    private void updateCustomers() {
        try {
            view.getPanel().getJcbCustomers().removeAllItems();
            List<Customer> customers = model.getCustomerList();
            customers.forEach(c -> view.getPanel().getJcbCustomers().addItem(c));
        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }
    }

    private void updateAddresses() {
        try {
            view.getPanel().getJcbAddresses().removeAllItems();
            List<Address> addresses = model.getAddressList();
            addresses.forEach(a -> view.getPanel().getJcbAddresses().addItem(a));
        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }
    }

    private void updateCountries() {
        try {
            view.getPanel().getJcbCountries().removeAllItems();
            List<Country> countries = model.getCountryList();
            countries.forEach(c -> view.getPanel().getJcbCountries().addItem(c));
        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }
    }

    public void initPresenter() {
        view.getPanel().getJbAddCountry().addActionListener(this);
        view.getPanel().getJbAddCity().addActionListener(this);
        view.getPanel().getJbAddAddress().addActionListener(this);
        view.getPanel().getJbAddCustomer().addActionListener(this);
        view.getPanel().getJbAddAppointment().addActionListener(this);
        view.getPanel().getJbAddReminder().addActionListener(this);
        view.getPanel().getJbAddIncrement().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "addcountry":
                addCountry();
                break;
            case "addcity":
                addCity();
                break;
            case "addaddress":
                addAddress();
                break;
            case "addcustomer":
                addCustomer();
                break;
            case "addappointment":
                addAppointment();
                break;
            case "addreminder":
                addReminder();
                break;
            case "addincrement":
                addIncrement();
                break;
        }
    }

    public void addIncrement() {
       
            try {
            int id;
            String desc = view.getPanel().getJtfDescription().getText().trim();
            if (desc.length() == 0) {
                throw new Exception("Enter increment type description!");
            }
             try {
                id = Integer.parseInt(view.getPanel().getJtfTypeId().getText().trim());
            } catch (Exception exc) {
                throw new Exception ("Enter increment type id!");
            }
            Incrementtypes type = new Incrementtypes(
                    id,
                    desc);
            model.addType(type);
            view.getPanel().clearFields();
            updateTypes();

        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }
    }

    public void addReminder() {
        try {
            int id; 
            try {
                id = Integer.parseInt(view.getPanel().getId().getText().trim());
            } catch (Exception exc) {
                throw new Exception ("Enter id!");
            }
            LocalDateTime date;
            try {
                date = LocalDateTime.ofInstant(view.getPanel().getDpEnterRemDate().getDate().toInstant(), ZoneId.systemDefault());
            } catch (Exception exc) {
                throw new Exception ("Enter reminder date!");
            }
           
            int incr;
           try {
                incr = Integer.parseInt(view.getPanel().getJtfSnoozeIncr().getText().trim());
            } catch (Exception exc) {
                throw new Exception ("Enter snooze increment!");
            }
            LocalDateTime createDate;
           
            try {
                createDate = LocalDateTime.ofInstant(view.getPanel().getDpCreateDate().getDate().toInstant(), ZoneId.systemDefault());
            } catch (Exception exc) {
                throw new Exception ("Enter create date!");
            }
            String createdBy =  view.getPanel().getJtfCreatedBy().getText().trim();
            if (createdBy.length() == 0) { 
                throw new Exception ("Enter created by!");
            }
            Reminder reminder = new Reminder(
                    id,
                    date,
                    incr,
                    ((Incrementtypes) view.getPanel().getJcbTypes().getSelectedItem()).getIncrementTypeId(),
                    ((Appointment) view.getPanel().getJcbAppointments().getSelectedItem()).getId(),
                    createdBy,
                   createDate,
                    "col");
            model.addReminder(reminder);
            view.getPanel().clearFields();

        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }
    }

    public void addAppointment() {
        try {
            LocalDateTime ldtStart = null;
            LocalDateTime ldtEnd = null;
            LocalTime startTime = Instant.ofEpochMilli(((java.util.Date) view.getPanel().getStartTimeApp().getValue()).getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
            LocalTime endTime = Instant.ofEpochMilli(((java.util.Date) view.getPanel().getEndTimeApp().getValue()).getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
            LocalDate startDate, endDate;
            try {
                startDate = view.getPanel().getDpStartDate().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            } catch (Exception exc) {
                throw new Exception("Enter start date!");
            }
            try {
                endDate = view.getPanel().getDpEndDate().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            } catch (Exception exc) {
                throw new Exception("Enter end date!");
            }

            ldtStart = LocalDateTime.of(startDate, startTime);
            ldtEnd = LocalDateTime.of(endDate, endTime);

            int id;
            LocalDateTime createDate;
            String createdBy;
            
            try {
                id = Integer.parseInt(view.getPanel().getId().getText().trim());
            } catch (Exception exc) {
                throw new Exception("Enter id!");
            }
            try {
                createDate = LocalDateTime.ofInstant(view.getPanel().getDpCreateDate().getDate().toInstant(), ZoneId.systemDefault());
            } catch (Exception exc) {
                throw new Exception("Select create date!");
            }

            createdBy = view.getPanel().getJtfCreatedBy().getText().trim();
            if (createdBy.length() == 0) {
                throw new Exception("Enter created by!");
            }

           
            String title, description, location, contact, url;
            title = view.getPanel().getJtfTitle().getText().trim();
            description = view.getPanel().getJtaDescription().getText().trim();
            location = view.getPanel().getJtaLocation().getText().trim();
            contact = view.getPanel().getJtaContact().getText().trim();
            url = view.getPanel().getJtfUrl().getText().trim();
            
            if (title.length() == 0) {
                throw new Exception("Enter title!");
            }
            if (description.length() == 0) {
                throw new Exception("Enter description!");
            }
            if (location.length() == 0) {
                throw new Exception("Enter location!");
            }
            if (contact.length() == 0) {
                throw new Exception("Enter contact!");
            }
            if (url.length() == 0) {
                throw new Exception("Enter url!");
            }
            
            Appointment appointment = new Appointment(
                    id,
                    createDate,
                    createdBy,
                    ((Customer) view.getPanel().getJcbCustomers().getSelectedItem()).getId(),
                    title,
                    description,
                    location,
                    contact,
                    url,
                    ldtStart,
                    ldtEnd);
            model.addAppointment(appointment);
            updateAppointments();
            view.getPanel().clearFields();

        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }
    }

    //TODO add logic that makes current user created by and current date updated by.
    public void addCustomer() {
        try {
             int id;
            LocalDateTime createDate;
            String createdBy;
            
            
            try {
                id = Integer.parseInt(view.getPanel().getId().getText().trim());
            } catch (Exception exc) {
                throw new Exception("Enter id!");
            }
            try {
                //Old way manually setting date time.
                //createDate = LocalDateTime.ofInstant(view.getPanel().getDpCreateDate().getDate().toInstant(), ZoneId.systemDefault());
                createDate=LocalDateTime.now();
            } catch (Exception exc) {
                throw new Exception("Select create date!");
            }

           
            createdBy = view.getPanel().getJtfCreatedBy().getText().trim();
            
            if (createdBy.length() == 0) {
                throw new Exception("Enter created by!");
            }

           
            String name = view.getPanel().getJtfCustName().getText().trim();
            if (name.length() == 0) {
                throw new Exception ("Enter name!");
            }
            Customer customer = new Customer(
                    id,
                    createDate,
                    createdBy,
                   
                    name,
                    view.getPanel().getJchbActive().isSelected(),
                    ((Address) view.getPanel().getJcbAddresses().getSelectedItem()).getId());
            model.addCustomer(customer);
            view.getPanel().clearFields();

        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }
    }

    public void addAddress() {
        try {
             int id;
            LocalDateTime createDate;
            String createdBy;
            
            try {
                id = Integer.parseInt(view.getPanel().getId().getText().trim());
            } catch (Exception exc) {
                throw new Exception("Enter id!");
            }
            try {
                createDate = LocalDateTime.ofInstant(view.getPanel().getDpCreateDate().getDate().toInstant(), ZoneId.systemDefault());
            } catch (Exception exc) {
                throw new Exception("Select create date!");
            }

            createdBy = view.getPanel().getJtfCreatedBy().getText().trim();
            if (createdBy.length() == 0) {
                throw new Exception("Enter created by!");
            }

            
            String addr1, postCode, phone;
            addr1 = view.getPanel().getJtfAddress1().getText().trim();
            postCode = view.getPanel().getJtfPostalCode().getText().trim();
            phone = view.getPanel().getJtfPhone().getText().trim();
            if (addr1.length() == 0) {
                throw new Exception("Enter address1!");
            }
            if (postCode.length() == 0) {
                throw new Exception("Enter postal code!");
            }
            if (phone.length() == 0) {
                throw new Exception("Enter phone!");
            }
            
            Address address = new Address(
                    id,
                    createDate,
                    createdBy,
                    addr1,
                    view.getPanel().getJtfAddress2().getText().trim(),
                    ((City) view.getPanel().getJcbCities().getSelectedItem()).getId(),
                    postCode,
                    phone);
            model.addAddress(address);
            view.getPanel().clearFields();

        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }
    }

    private void addCountry() {
        try {
              int id;
            LocalDateTime createDate;
            String createdBy;
            LocalDate lastUpdate;
            String lastUpdateBy;
            try {
                id = Integer.parseInt(view.getPanel().getId().getText().trim());
            } catch (Exception exc) {
                throw new Exception("Enter id!");
            }
            try {
                createDate = LocalDateTime.ofInstant(view.getPanel().getDpCreateDate().getDate().toInstant(), ZoneId.systemDefault());
            } catch (Exception exc) {
                throw new Exception("Select create date!");
            }

            createdBy = view.getPanel().getJtfCreatedBy().getText().trim();
            if (createdBy.length() == 0) {
                throw new Exception("Enter created by!");
            }

            
            String countryName =  view.getPanel().getJtfCountry().getText().trim();
            if (countryName.length() == 0) {
                throw new Exception("Enter country!");
            }
            Country country = new Country(
                    id,
                    createDate,
                    createdBy,
                    countryName);
            model.addCountry(country);
            updateCountries();
            view.getPanel().clearFields();

        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }
    }

    private void updateCities() {
        try {
            view.getPanel().getJcbCities().removeAllItems();
            List<City> cities = model.getCityList();
            cities.forEach(c -> view.getPanel().getJcbCities().addItem(c));
        } catch (Exception exc) {
            view.getPanel().displayError(exc);
            exc.printStackTrace();
        }
    }

    private void addCity() {
        try {
             int id;
            LocalDateTime createDate;
            String createdBy;
            LocalDate lastUpdate;
            String lastUpdateBy;
            try {
                id = Integer.parseInt(view.getPanel().getId().getText().trim());
            } catch (Exception exc) {
                throw new Exception("Enter id!");
            }
            try {
                createDate = LocalDateTime.ofInstant(view.getPanel().getDpCreateDate().getDate().toInstant(), ZoneId.systemDefault());
            } catch (Exception exc) {
                throw new Exception("Select create date!");
            }

            createdBy = view.getPanel().getJtfCreatedBy().getText().trim();
            if (createdBy.length() == 0) {
                throw new Exception("Enter created by!");
            }

            
            String cityName =  view.getPanel().getJtfCity().getText().trim();
            if (cityName.length() == 0) {
                throw new Exception("Enter city!");
            }
            City city = new City(
                    id,
                    createDate,
                   createdBy,
                    cityName,
                    ((Country) view.getPanel().getJcbCountries().getSelectedItem()).getId());
            model.addCity(city);
            updateCities();
            view.getPanel().clearFields();

        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }

    }

}
