/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mhamza0
 */
public class Inventory {
    int updateProduct;
    int updatepart;
        private static int partIDCount = 0;
    private static int productIDCount = 0;
    
    
    private static ObservableList<Part>allparts=FXCollections.observableArrayList();
    private static ObservableList<Product>products=FXCollections.observableArrayList();
    
    public static void addProduct(Product product){
        products.add(product);
        
    }
    public static void removeProduct(Product removeProduct){
     products.remove(removeProduct);
       
        
    }
    

    public static int getPartIDCount(){
        partIDCount++;
        return partIDCount;
    }
    public static int getProductIDCount(){
        productIDCount++;
        return productIDCount;
    }
    public void updateProduct(int updateproduct){
        this.updateProduct=updateproduct;
    }
    public static void addPart(Part part){
        allparts.add(part);
        
    }
      public void updatePart(int updatepart){
        this.updatepart=updatepart;
    }
   public static  void  deletePart(Part part){
     allparts.remove(part);
       
        
    }
  public static Product lookupProduct(int itemNumber) {
        for(Product product: getProducts()){
            if(product.getProductID()==itemNumber){
                return product;                
            }
       }
       return null;
    }
   public static Part lookupPart(int itemNumber) {
        for(Part part: getPart()){
            if(part.getPartID()==itemNumber){
                return part;                
            }
       }
       return null;
    }
        public static ObservableList<Product> getProducts() {
        return products;
    }
          public static ObservableList<Part> getPart() {
        return allparts;
    }
      public static ObservableList<Product> getProductInventory(){
          return products;
      }
      public static ObservableList<Part> getPartInventory(){
          return allparts;
      }
   

}
