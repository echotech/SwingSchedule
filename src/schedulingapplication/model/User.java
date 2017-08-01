/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import schedulingapplication.dao.UserDAO;

/**
 *
 */
public class User extends ScheduleItem {
    
    private String username;
    
    private String password;
    
    private boolean active;
    
    public User(int id, LocalDateTime createDate, String createdBy, String username, String password, boolean active) {
        super(id, createDate, createdBy);
        this.username = username;
        this.password = password;
        this.active = active;
    }
    
    public User() {
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean checkUser() throws Exception {
        return UserDAO.checkUser(this);
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + 
                password + ", active=" + active + '}';
    }
    
}
