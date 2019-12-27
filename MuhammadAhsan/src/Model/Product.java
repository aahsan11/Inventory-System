/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mhamza0
 */
public class Product {
public StringProperty name;
public DoubleProperty price;
public IntegerProperty stock;
public IntegerProperty min;
public IntegerProperty max;
public IntegerProperty part;
public IntegerProperty remove;
public IntegerProperty lookup;
public IntegerProperty productID;
ObservableList<Part>associatedPart=FXCollections.observableArrayList(); 
    private ArrayList<Part> associatedParts;

    public Product() {
        name=new SimpleStringProperty();
        price=new SimpleDoubleProperty();
        stock=new SimpleIntegerProperty();
         min = new SimpleIntegerProperty();
     max =new SimpleIntegerProperty();
       productID = new SimpleIntegerProperty();
      
        
        
    }
     public IntegerProperty getProductIDProperty(){
       return productID;
   }
      public StringProperty getProductNameProperty(){
       return name;
   }
    public IntegerProperty getProductStockProperty(){
        return  stock;
    }
    public DoubleProperty getProductPriceProperty(){
        return price;
    }
    public int getMax(){
        return max.get();
    }
    public void setName(String name){
        this.name=new SimpleStringProperty(name);
    }
    public String getName(){
        return name.get();
    }
    public void setPrice(double price){
        this.price=new SimpleDoubleProperty(price);
    }
    public double getPrice(){
        return price.get();
    }
    public void setInStock(int stock){
        this.stock=new SimpleIntegerProperty(stock);
    }
    public int getInStock(){
        return stock.get();
    }
    public void setMin(int min){
        this.min=new SimpleIntegerProperty(min);
        
    }
    public int getMin(){
     return min.get();
    }
    public void setMax(int max){
        this.max=new SimpleIntegerProperty(max);
    }
    
  
 
  public void addAssociatedParts(ArrayList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }
    
    public ArrayList<Part> lookupAssociatedParts() {
        return this.associatedParts;
    }
    
   
    public boolean removeAssociatedPart() {
        return true;
    }

  
   public void setProductID(int productID){
       this.productID=new SimpleIntegerProperty(productID);
   }
   public int getProductID(){
       return productID.get();
   }
}
