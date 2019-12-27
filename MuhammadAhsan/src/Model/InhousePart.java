/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author mhamza0
 */
public class InhousePart extends Part {
    public IntegerProperty machineID;

    public InhousePart() {
        super();
        this.machineID = new SimpleIntegerProperty();
    }

    
    public void setMachineID(int machineID){
        this.machineID=new SimpleIntegerProperty(machineID);
    }
    public int getMachineID(){
        return machineID.get();
    }
}