/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.model;

/**
 */
public class Incrementtypes {
    
    private int incrementTypeId;
    
    private String incrementTypeDescription;

    public Incrementtypes(String incrementTypeDescription) {
        //this.incrementTypeId = incrementTypeId;
        this.incrementTypeDescription = incrementTypeDescription;
    }
    
    public Incrementtypes() {
        
    }

    public int getIncrementTypeId() {
        return incrementTypeId;
    }

    public void setIncrementTypeId(int incrementTypeId) {
        this.incrementTypeId = incrementTypeId;
    }

    public String getIncrementTypeDescription() {
        return incrementTypeDescription;
    }

    public void setIncrementTypeDescription(String incrementTypeDescription) {
        this.incrementTypeDescription = incrementTypeDescription;
    }
 
    @Override
    public String toString() {
        return incrementTypeId + "";
    }
    
}
