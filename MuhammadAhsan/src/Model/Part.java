/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author mhamza0
 */
public abstract class Part {

  
    public StringProperty name;
    public DoubleProperty price;
    public IntegerProperty stock;
    public IntegerProperty min;
    public IntegerProperty max;
    public IntegerProperty partID;

    public Part() {
        name = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
       stock = new SimpleIntegerProperty();
       min = new SimpleIntegerProperty();
     max =new SimpleIntegerProperty();
       partID = new SimpleIntegerProperty();
    }
    
    
    public void setName(String name){
        this.name=new SimpleStringProperty(name);
    }
    public IntegerProperty getPartIDProperty(){
        return partID;
    }
    public IntegerProperty getPartStockProperty(){
        return  stock;
    }
    public DoubleProperty getPartPriceProperty(){
        return price;
    }
    
   public StringProperty partNameProperty(){
       return name;
   }
    public String getName(){
        
        return this.name.get();
    }
    public void SetPrice(double price){
        this.price=new SimpleDoubleProperty(price);
        
    }
    public double getPrice(){
        return this.price.get();
    }
        public int getInStock(){
        return this.stock.get();
        
    }
         public int getMin(){
        return this.min.get();
    }
    public void setInStock(int stock){
        this.stock=new SimpleIntegerProperty(stock);
        
    }

    public void setMin(int min){
        this.min=new SimpleIntegerProperty(min);
    }
    
   
    public void setMax(int max){
        this.max=new SimpleIntegerProperty(max);
    }
    public int getMax(){
        return max.get();
    }
    public void setPartID(int partID){
        this.partID=new SimpleIntegerProperty(partID);
    }
    public int getPartID(){
        return partID.get();
    }
  
        
    
    
    
   
    
    
    
    
    
    
}
