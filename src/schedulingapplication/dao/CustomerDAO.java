/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;
import schedulingapplication.model.Address;
import schedulingapplication.model.Appointment;
import schedulingapplication.model.City;
import schedulingapplication.model.Country;
import schedulingapplication.model.Customer;
import schedulingapplication.model.Incrementtypes;
import schedulingapplication.model.Reminder;
import schedulingapplication.model.Schedule;

/**
 *
 */
public class CustomerDAO {

    public static List<Country> getCountryList() throws Exception {
        
        List<Country> list = new ArrayList<>();
        Connection con = TestConnection.getConnection();
        String sql = "select `countryId`, `country` from `U03q1A`.`country`";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Country country = new Country();
            country.setId(rs.getInt(1));
            country.setCountry(rs.getString(2));
            list.add(country);
        }
        return list; 
    }
    
    public static int getAddressId(String address) throws Exception {
            int id =0;
            //SQL Stuff
            Connection con = TestConnection.getConnection();
            String sql ="select addressId from `U03q1A`.`address` where address ="+"'"+address+"';";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                id = rs.getInt(1);
            }
            return id;      
    }
    
    public static int getCountryId(String country) throws Exception {
            int id =0;
            //SQL Stuff
            Connection con = TestConnection.getConnection();
            String sql ="select countryId from `U03q1A`.`country` where country ="+"'"+country+"';";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                id = rs.getInt(1);
            }
            return id;      
    }
    
        public static int getCityId(String city) throws Exception {
            int id=0;
            //SQL Stuff
            Connection con = TestConnection.getConnection();
            String sql ="select cityId from `U03q1A`.`city` where city like"+"'%"+city+"%';";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                id = rs.getInt(1);
            }
            return id;
            
        
    }

    public static List<Address> getAddressList() throws Exception {
        List<Address> list = new ArrayList<>();
        Connection con = TestConnection.getConnection();
        String sql = "select `addressId`, `address` from `U03q1A`.`address`";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Address address = new Address();
            address.setId(rs.getInt(1));
            address.setAddress(rs.getString(2));
            list.add(address);
        }
        return list;
    }

    public static List<Customer> getCustomerList() throws Exception {
        List<Customer> list = new ArrayList<>();
        Connection con = TestConnection.getConnection();
        String sql = "select `customerId`, `customerName` from `U03q1A`.`customer`";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt(1));
            customer.setName(rs.getString(2));
            list.add(customer);
        }
        return list;
    }
    
    public static List<Customer> getActiveCustomerCount() throws Exception {
        List<Customer> list = new ArrayList<>();
        Connection con = TestConnection.getConnection();
        String sql = "select `customerId`, `customerName` from `U03q1A`.`customer` where `active`='1'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        Integer count = new Integer(rs.getInt(1));
        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt(1));
            customer.setName(rs.getString(2));
            list.add(customer);
        }
        return list;
    }
    
    public static List<Incrementtypes> getTypeList() throws Exception {
        List<Incrementtypes> list = new ArrayList<>();
        Connection con = TestConnection.getConnection();
        String sql = "select `incrementTypeId` from `U03q1A`.`incrementtypes`";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Incrementtypes type = new Incrementtypes();
            type.setIncrementTypeId(rs.getInt(1));
            list.add(type);
        }
        return list;
    }

    public static List<City> getCityList() throws Exception {
        List<City> list = new ArrayList<>();
        Connection con = TestConnection.getConnection();
        String sql = "select `cityId`, `city` from `U03q1A`.`city`";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            City city = new City();
            city.setId(rs.getInt(1));
            city.setCity(rs.getString(2));
            list.add(city);
        }
        return list;
    }
    
    public static List<Appointment> getAppointmentList() throws Exception {
        List<Appointment> list = new ArrayList<>();
        Connection con = TestConnection.getConnection();
        String sql = "select `appointmentId`, `title`, `start`, `end` from `U03q1A`.`appointment` where `start`>=CURDATE()";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Appointment appointment = new Appointment();
            appointment.setId(rs.getInt(1));
            appointment.setTitle(rs.getString(2));
            appointment.setStart(rs.getTimestamp(3).toLocalDateTime());
            appointment.setEnd(rs.getTimestamp(4).toLocalDateTime());
            list.add(appointment);
        }
        return list;
    }
    
    public static List<Appointment> getMyAppointmentList() throws Exception {
        List<Appointment> list = new ArrayList<>();
        Connection con = TestConnection.getConnection();
        String sql = "select `appointmentId`, `title`, `start`, `end`, `beenReminded` from `U03q1A`.`appointment` where `createdBy`='demo' and `start`>=CURDATE()";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Appointment appointment = new Appointment();
            appointment.setId(rs.getInt(1));
            appointment.setTitle(rs.getString(2));
            appointment.setStart(rs.getTimestamp(3).toLocalDateTime());
            appointment.setEnd(rs.getTimestamp(4).toLocalDateTime());
            appointment.setReminded(rs.getInt(5));
            list.add(appointment);
        }
        return list;
    }
    
   
    
    public static List<Appointment> getMyAppointmentTimes() throws Exception {
        List<Appointment> list = new ArrayList<>();
        Connection con = TestConnection.getConnection();
        String sql = "select  `start` from `U03q1A`.`appointment` where `createdBy`='demo'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Appointment appointment = new Appointment();
            appointment.setId(rs.getInt(1));
            appointment.setTitle(rs.getString(2));
            appointment.setStart(rs.getTimestamp(3).toLocalDateTime());
            appointment.setEnd(rs.getTimestamp(4).toLocalDateTime());
            list.add(appointment);
        }
        return list;
    }
    
        
    public Appointment getAppointment(int id) throws Exception {
        Connection con = TestConnection.getConnection();
        String sql = "select  `start` from `U03q1A`.`appointment` where `appointmentId`=" + id;
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Appointment appointment = new Appointment();
        while (rs.next()) {

            appointment.setId(rs.getInt(1));
            appointment.setTitle(rs.getString(2));
            appointment.setStart(rs.getTimestamp(3).toLocalDateTime());
            appointment.setEnd(rs.getTimestamp(4).toLocalDateTime());

        }
        return appointment;
    }

    public static void addCountry(Country country) throws Exception {
        try {
        Connection con = TestConnection.getConnection();
        String sql = "insert into `U03q1A`.`country` values (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, country.getId());
        ps.setString(2, country.getCountry());
        Date sqlDate = new Date(country.getCreateDate().
                atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        ps.setDate(3, sqlDate);
        ps.setString(4, country.getCreatedBy());
        ps.setTimestamp(5, new Timestamp(java.util.Date.from(country.getCreateDate().atZone(ZoneId.systemDefault()).toInstant()).getTime()));
        ps.setString(6, country.getCreatedBy());
        ps.executeUpdate();
        }
        catch (Exception exc){
            System.out.println("Bad sql in addCountry");
            exc.printStackTrace();
        }
    }

    public static void addCity(City city) throws Exception {
        try {
        Connection con = TestConnection.getConnection();
        String sql = "insert into `U03q1A`.`city` values (?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, city.getId());
        ps.setString(2, city.getCity());
        Date sqlDate = new Date(city.getCreateDate().
                atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        ps.setInt(3, city.getCountryId());
        ps.setDate(4, sqlDate);
        ps.setString(5, city.getCreatedBy());
        ps.setTimestamp(6, new Timestamp(java.util.Date.from(city.getCreateDate().atZone(ZoneId.systemDefault()).toInstant()).getTime()));
        ps.setString(7, city.getCreatedBy());
        ps.executeUpdate();
        } catch (Exception exc){
            System.out.println("Bad sql in addCity DAO");
            exc.printStackTrace();
        }
    }

    public static void addAddress(Address address) throws Exception {
        try {
        Connection con = TestConnection.getConnection();
        String sql = "insert into `U03q1A`.`address` values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, address.getId());
        ps.setString(2, address.getAddress());
        ps.setString(3, address.getAddress2());
        ps.setInt(4, address.getCityId());
        ps.setString(5, address.getPostalCode());
        ps.setString(6, address.getPhone());
        Date sqlDate = new Date(address.getCreateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        ps.setDate(7, sqlDate);
        ps.setString(8, address.getCreatedBy());
        ps.setTimestamp(9, new Timestamp(java.util.Date.from(address.getCreateDate().atZone(ZoneId.systemDefault()).toInstant()).getTime()));
        ps.setString(10, address.getCreatedBy());
        ps.executeUpdate();
        }catch (Exception exc){
            System.out.println("Bad sql in addAddress");
            exc.printStackTrace();
    }
    }

    public static void addCustomer(Customer customer) throws Exception {
        try {
        String sql = "insert into `U03q1A`.`customer` values (?,?,?,?,?,?,?,?)";
        boolean edit = false;
        for (Customer cust: getCustomerList()) {
            if (cust.getId() == customer.getId()) {
                sql = "update `U03q1A`.`customer` set `customerId`=?, `customerName`=?,"
                        + "`addressId`=?, `active`=?, `createDate`=?, `createdBy`=?,"
                        + "`lastUpdate`=?, `lastUpdateBy`=? where `customerId`=?";
                edit = true;
                break;
            }
        }
        
        Connection con = TestConnection.getConnection();
        
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, customer.getId());
        ps.setString(2, customer.getName());
        ps.setInt(3, customer.getAddressId());
        ps.setBoolean(4, customer.isActive());
        Date sqlDate = new Date(customer.getCreateDate().
                atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        ps.setDate(5, sqlDate);
        ps.setString(6, customer.getCreatedBy());
        ps.setTimestamp(7, new Timestamp(java.util.Date.from(customer.getCreateDate().atZone(ZoneId.systemDefault()).toInstant()).getTime()));
        ps.setString(8, customer.getCreatedBy());
        if (edit) {
            ps.setInt(9, customer.getId());
        }
        ps.executeUpdate();
        } catch (Exception exc){
            System.out.println("Bad addCustomer SQL");
            exc.printStackTrace();
        }
    }
    
    public static void addReminder(Reminder reminder) throws Exception {
        Connection con = TestConnection.getConnection();
        String sql = "insert into `U03q1A`.`reminder` values (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, reminder.getReminderId());
        ps.setDate(2, new Date(reminder.getReminderDate().
                atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
        ps.setInt(3, reminder.getSnoozeIncrement());
        ps.setInt(4, reminder.getSnoozeIncrementTypeId());
        ps.setInt(5, reminder.getAppointmentId());
        ps.setString(6, reminder.getCreatedBy());
        ps.setDate(7, new Date(reminder.getCreatedDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
        ps.setString(8, "col");
        ps.executeUpdate();
    }
    
    
    public static void addAppointment(Appointment appointment) throws Exception {
        Connection con = TestConnection.getConnection();
        String sql = "insert into `U03q1A`.`appointment` values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, appointment.getId());
        ps.setInt(2, appointment.getCustomerId());
        ps.setString(3, appointment.getTitle());
        ps.setString(4, appointment.getDescription());
        ps.setString(5, appointment.getLocation());
        ps.setString(6, appointment.getContact());
        ps.setString(7, appointment.getUrl());
        ps.setTimestamp(8, Timestamp.valueOf(appointment.getStart()));
        ps.setTimestamp(9, Timestamp.valueOf(appointment.getEnd()));
        Date sqlDate = new Date(appointment.getCreateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        ps.setDate(10, sqlDate);
        //TODO Fixme
        ps.setString(11, "demo");
        ps.setTimestamp(12, new Timestamp(java.util.Date.from(appointment.getCreateDate().atZone(ZoneId.systemDefault()).toInstant()).getTime()));
        ps.setString(13, "demo");
        ps.setInt(14,0);
        ps.executeUpdate();
    }
    
    public static void updateAppointment(int rem, int id) throws Exception {
        Connection con = TestConnection.getConnection();
        String sql = "update `U03q1A`.`appointment` set `beenReminded`=? where `appointmentId`=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, rem);
        ps.setInt(2, id);
        ps.executeUpdate();
    }
       
    public static void addType(Incrementtypes type) throws Exception {
        Connection con = TestConnection.getConnection();
        String sql = "insert into `U03q1A`.`incrementtypes` values (?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, type.getIncrementTypeId());
        ps.setString(2, type.getIncrementTypeDescription());       
        ps.executeUpdate();
    }
    
    public static List<Schedule> getSchedule() throws Exception {
        Connection con = TestConnection.getConnection();
        List<Schedule> schedule = new ArrayList<>();
       String sql = "select `customer`.`customerName`, `customer`.`active`, `city`, `country`, "
               + "`title`, `description`, `start`, `end` from `U03q1A`.`customer`, "
               + "`U03q1A`.`address`, `U03q1A`.`city`, `U03q1A`.`country`, `U03q1A`.`appointment` where "
               + "`customer`.`addressId`=`address`.`addressId` and "
               + "`address`.`cityId`=`city`.`cityId` and `city`.`countryId`=`country`.`countryId`"
               + "and `appointment`.`start`>=CURDATE()";
       PreparedStatement ps = con.prepareStatement(sql);
       ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Customer customer = new Customer();
            Appointment app = new Appointment();
            City city = new City();
            Country country = new Country();
            
            customer.setName(rs.getString(1));
            customer.setActive(rs.getBoolean(2));
            city.setCity(rs.getString(3));
            country.setCountry(rs.getString(4));
            app.setTitle(rs.getString(5));
            app.setDescription(rs.getString(6));
            app.setStart(rs.getTimestamp(7).toLocalDateTime());
            app.setEnd(rs.getTimestamp(8).toLocalDateTime());
            Schedule sch = new Schedule();
            sch.setAppointment(app);
            sch.setCity(city);
            sch.setCountry(country);
            sch.setCustomer(customer);
            schedule.add(sch);
        }
        
        return schedule;
    }

}
