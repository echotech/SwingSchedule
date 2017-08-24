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
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import schedulingapplication.dao.CustomerDAO;
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
    
    public Customer getModel(){
        return this.model;
    }
    

    private CustomerFrame view;

    public CustomerPresenter(Customer model, CustomerFrame view) {
        this.model = model;
        this.view = view;
    }

    private static AtomicInteger nextCustId = new AtomicInteger(0);
    private static AtomicInteger nextIncId = new AtomicInteger(0);
    private static AtomicInteger nextCityId = new AtomicInteger(0);
    private static AtomicInteger nextAppId = new AtomicInteger(0);

    public void initView() {
        //view.getPanel().getJcbAddCountries().setActionCommand("addcountry");
        //view.getPanel().getJbAddCity().setActionCommand("addcity");
        //view.getPanel().getJbAddAddress().setActionCommand("addaddress");
        view.getPanel().getJbAddCustomer().setActionCommand("addcustomer");
        view.getPanel().getJbAddAppointment().setActionCommand("addappointment");
        view.getPanel().getJbRefreshCust().setActionCommand("refreshcust");

        updateCountries();
        //updateCities();
        //updateAddresses();
        updateCustomers();
           
        applyReminders();
    }

    
    public void updateCustomers() {
        try {
            view.getPanel().getJcbCustomers().removeAllItems();
            List<Customer> customers = model.getCustomerList();
            customers.forEach(c -> view.getPanel().getJcbCustomers().addItem(c));
        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }
    }

    
    private void updateCountries() {
        try {
            
            List<Country> countries = model.getCountryList();
            countries.forEach(c -> view.getPanel().getJcbCountries().addItem(c));
        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }
    } 

    public void initPresenter() {
        // view.getPanel().getJbAddCountry().addActionListener(this);
        //view.getPanel().getJbAddCity().addActionListener(this);
        //view.getPanel().getJbAddAddress().addActionListener(this);
        view.getPanel().getJbAddCustomer().addActionListener(this);
        view.getPanel().getJbAddAppointment().addActionListener(this);
        view.getPanel().getJbRefreshCust().addActionListener(this);
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
            case "refreshcust":
                updateCustomers();
                break;
        }
    }

    
    
    public void applyReminders() {
        // Apply reminders.
        try {
            System.out.println("Timers Started");
            List<Appointment> apps = CustomerDAO.getMyAppointmentList();
            ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
            service.scheduleAtFixedRate(() -> {
                for (Appointment ap : apps) {
                    LocalDateTime before15minutes = ap.getStart().minusMinutes(15);
                    outerloop:
                    while (LocalDateTime.now().isAfter(before15minutes)
                            && LocalDateTime.now().isBefore(ap.getStart())) {
                        if (ap.getReminded() == 0) {
                            System.out.println(before15minutes);
                            Object[] options = {"Ok", "Dismiss"};
                            int input = JOptionPane.showOptionDialog(null, "Reminder!\nAppointment: "
                                    + ap.getTitle() + "\nStart date: " + ap.getStart(), "Appointment Reminder!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
                            if (input == JOptionPane.YES_OPTION) {
                                ap.setReminded(1);
                                try {
                                    CustomerDAO.updateAppointment(1, ap.getId());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            if (input == JOptionPane.NO_OPTION) {
                                ap.setReminded(1);
                                try {
                                    CustomerDAO.updateAppointment(1, ap.getId());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }, 0, 10, TimeUnit.SECONDS);
        } catch (Exception exc) {
            exc.printStackTrace();
            JOptionPane.showMessageDialog(null, "Reminders failed");
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

            LocalDateTime createDate;
            String createdBy;

            try {
                createDate = LocalDateTime.now();
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
            
            view.getPanel().clearFields();

        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }
        applyReminders();
    }

    public void addCustomer() {
        
        try {
            addCity();
        } catch (Exception exc) {
            System.out.println("You can't call addCity that way!");
        }
        try {
            addAddress();
        } catch (Exception exc) {
            System.out.println("You can't call addAddress that way!");
        }
        try {
            String address1=view.getPanel().getJtfAddress1().getText().trim();
            int addressId = CustomerDAO.getAddressId(address1);

            LocalDateTime createDate;
            String createdBy;
            
            //Old way manually setting date time.
            //createDate = LocalDateTime.ofInstant(view.getPanel().getDpCreateDate().getDate().toInstant(), ZoneId.systemDefault());
            createDate = LocalDateTime.now();


            createdBy = view.getPanel().getJtfCreatedBy().getText().trim();
            

            if (createdBy.length() == 0) {
                throw new Exception("Enter customer created by!");
            }

            String name = view.getPanel().getJtfCustName().getText().trim();
            if (name.length() == 0) {
                throw new Exception("Enter name!");
            }
          
            Customer customer = new Customer(
                    addressId,
                    createDate,
                    createdBy,
                    name,
                    view.getPanel().getJchbActive().isSelected());
            model.addCustomer(customer);
            view.getPanel().clearFields();
            updateCustomers();
            view.revalidate();
            view.repaint();


        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }
    }

    public void addAddress() {
        try {

            LocalDateTime createDate;
            String createdBy;

            try {
                createDate = LocalDateTime.now();
            } catch (Exception exc) {
                throw new Exception("Enter created date!");

            }

            createdBy = view.getPanel().getJtfCreatedBy().getText().trim();
            if (createdBy.length() == 0) {
                throw new Exception("Enter address created by!");
            }

            String addr1, postCode, phone;
            addr1 = view.getPanel().getJtfAddress1().getText().trim();
            postCode = view.getPanel().getJtfPostalCode().getText().trim();
            phone = view.getPanel().getJtfPhone().getText().trim();
            String city = view.getPanel().getJtfCity().getText().trim();
            int cityId = CustomerDAO.getCityId(city);
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
                    createDate,
                    createdBy,
                    addr1,
                    view.getPanel().getJtfAddress2().getText().trim(),
                    cityId,
                    postCode,
                    phone);
            model.addAddress(address);
            //view.getPanel().clearFields();

        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }
    }

    private void addCountry() {
        try {

            LocalDateTime createDate;
            String createdBy;

            try {
                createDate = LocalDateTime.now();
            } catch (Exception exc) {
                throw new Exception("Select create date!");
            }

            createdBy = view.getPanel().getJtfCreatedBy().getText().trim();
            if (createdBy.length() == 0) {
                throw new Exception("Enter country created by!");
            }

            String countryName = view.getPanel().getJcbCountries().getSelectedItem().toString();
            if (countryName.length() == 0) {
                throw new Exception("Enter country!");
            }
            Country country = new Country(
                    createDate,
                    createdBy,
                    countryName);
            model.addCountry(country);
            
           //view.getPanel().clearFields();

        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }
    }

    private void addCity() {
        try {
            LocalDateTime createDate;
            String createdBy;

            try {
                createDate = LocalDateTime.now();
            } catch (Exception exc) {
                throw new Exception("Select create date!");
            }

            createdBy = view.getPanel().getJtfCreatedBy().getText().trim();
            if (createdBy.length() == 0) {
                throw new Exception("Enter city created by!");
            }

            String cityName = view.getPanel().getJtfCity().getText().trim();
            if (cityName.length() == 0) {
                throw new Exception("Enter city!");
            }
            String country = view.getPanel().getJcbCountries().getSelectedItem().toString();
            
                    
            City city = new City(createDate, createdBy, cityName, CustomerDAO.getCountryId(country));
            model.addCity(city);

            //view.getPanel().clearFields();

        } catch (Exception exc) {
            view.getPanel().displayError(exc);
        }

    }
    


}