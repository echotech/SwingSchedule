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
public abstract class ScheduleItem {

    private int id;
    private static AtomicInteger nextId = new AtomicInteger(0);
    private LocalDateTime createDate;

    private String createdBy;

    private LocalDate lastUpdate;

    private String lastUpdateBy;

    public ScheduleItem (LocalDateTime createDate, String createdBy) {
        this.id = nextId.incrementAndGet();
        this.createDate = createDate;
        this.createdBy = createdBy;
        
    }
    
    public ScheduleItem() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    @Override
    public String toString() {
        return "ScheduleItem{" + "id=" + id + ", createDate=" + createDate + 
                ", createdBy=" + createdBy + ", lastUpdate=" + lastUpdate + 
                ", lastUpdateBy=" + lastUpdateBy + '}';
    }
    
    

}
