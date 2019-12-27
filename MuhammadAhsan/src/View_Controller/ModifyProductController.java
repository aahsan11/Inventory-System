/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import static Model.Inventory.getPart;
import static Model.Inventory.getPartInventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
        

        
/**
 * FXML Controller class
 *
 * @author mhamza0
 */
public class ModifyProductController implements Initializable {
    int count=0;
     @FXML
    private TextField ModifyProductsIDtxtfield;
     private static Product selectedProduct;

    @FXML
    private TextField ModifyProductsNametxtfield;

    @FXML
    private TextField ModifyProductsInvtxtfield;

    @FXML
    private TextField ModifyProductsPricetxtfield;

    @FXML
    private TextField ModifyProductsMaxtxtfield;

    @FXML
    private TextField ModifyProductsMintxtfield;

    @FXML
    private TextField ModifyProductsSearchfield;
    private ObservableList<Part> currentPart = FXCollections.observableArrayList();
       private ObservableList<Part> data = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Part> ModifyProductsAddTable;
    @FXML
    private TableColumn<Part, Integer> ModifyProductsAddIDCol;
    @FXML
    private TableColumn<Part, String> ModifyProductsAddNameCol;
    @FXML
    private TableColumn<Part, Integer> ModifyProductsAddInvCol;
    @FXML
    private TableColumn<Part, Double> ModifyProductsAddPriceCol;
    @FXML
    private TableView<Part> ModifyProductsDeleteTable;
   @FXML
     private TableColumn<Part, Integer> ModifyProductsDeleteIDCol;
    @FXML
    private TableColumn<Part, String> ModifyProductsDeleteNameCol;
    @FXML
    private TableColumn<Part, Integer> ModifyProductsDeleteInvCol;
    @FXML
    private TableColumn<Part, Double> ModifyProductsDeletePriceCol;

    @FXML
    void add(ActionEvent event) {
 Part part=ModifyProductsAddTable.getSelectionModel().getSelectedItem();
        
             currentPart.add(part);
            ModifyProductsDeleteTable.setItems(currentPart);
    }
    @FXML
    void delete(ActionEvent event) throws IOException {
         int siz= ModifyProductsDeleteTable.getItems().size();
        Part part=ModifyProductsDeleteTable.getSelectionModel().getSelectedItem();
        if(siz>1){
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete " + part.getName() + " from Associated Parts?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
             currentPart.remove(part);
            else{
                alert.close();
            }
        }
        if(siz<1){
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Product found");
            alert.setContentText("please select product");
            alert.showAndWait();
        }
        
        else{
                         Alert alert=new Alert(Alert.AlertType.ERROR);
              
        alert.setTitle("Parts Error");
        alert.setHeaderText("Cannot delete " + part.getName());
        alert.setContentText("Product must have at least one Part!\n");
        alert.showAndWait();
        

    }
        
    }
    
    @FXML
    void cancel(ActionEvent event) throws IOException {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Are you sure you want to Cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
              Parent partsSave = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene scene = new Scene(partsSave);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            
        } else {
            alert.close();
        }

    }

    

    @FXML
    void save(ActionEvent event) throws IOException {
    if (isValid()){
        String ModifyPartsName=ModifyProductsNametxtfield.getText();
        String ModifyPartsInv=ModifyProductsInvtxtfield.getText();
        String ModifyPartsPrice=ModifyProductsPricetxtfield.getText();
        String ModifyPartsMin=ModifyProductsMintxtfield.getText();
        String ModifyPartsMax=ModifyProductsMaxtxtfield.getText();
     Product products=new Product();
        products.setProductID(count);
         products.setName(ModifyPartsName);
                    products.setPrice(Double.parseDouble(ModifyPartsPrice));
                   products.setInStock(Integer.parseInt(ModifyPartsInv));
                    products.setMin(Integer.parseInt(ModifyPartsMin));
                    products.setMax(Integer.parseInt(ModifyPartsMax));
                    Inventory.addProduct(products);
                    
        
        Parent root=FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        Inventory.removeProduct(selectedProduct);
        
       // ArrayList<Part> parts = new ArrayList<>();
         //   parts.addAll(ModifyProductsDeleteTable.getItems());
      
    }
        

    }
    
     public boolean isValid(){
              String productName = ModifyProductsNametxtfield.getText();
              
              
              
        String productInv =ModifyProductsInvtxtfield.getText();
        String productPrice = ModifyProductsPricetxtfield.getText();
        
        String productMin = ModifyProductsMintxtfield.getText();
        String productMax = ModifyProductsMaxtxtfield.getText();
        
        String error="";
       
        
       
     
         ArrayList<Part> parts = new ArrayList<>();
        parts.addAll(ModifyProductsDeleteTable.getItems());
        //double paPrice=Double.parseDouble(partPrice);
        
        if(productName.isEmpty()){
            error=error+"please add productName ";
            
        }
        try{
             int pi=Integer.parseInt(productInv);
              int pmin=Integer.parseInt(productMin);
        int pmax=Integer.parseInt(productMax);
         if(pi <= pmin || pi >= pmax){
            error=error+"product inv must be between productMin and productMax ";
            
        }
        
            
        }
        catch(NumberFormatException e){
            error=error+ "plz enter valid inventory (integer) number ";
            
        }
        try {
               double pp= Double.parseDouble(productPrice);
            double partsprice=0;
        for (Part partprice: parts ){
            
            partsprice=partsprice + partprice.getPrice();
        }
            if(pp < partsprice){
                error=error + "product price must be greater than sum of parts price ";
                
            }
        }
        catch(NumberFormatException e){
            error=error + "plz enter valid price(integer) ";
        }
        try{
             int pmin=Integer.parseInt(productMin);
        int pmax=Integer.parseInt(productMax);
             if(pmin>pmax){
            error=error+"product min must be less than max ";
        }
        }
        catch(NumberFormatException e){
            error=error+"no valid inventory value (integer) ";
        }
         
        if(parts.size()==0){
            error=error+"product must have 1 part ";
        }
                
        
     
            
        
        
        
       
       
             if (error.length() == 0) {
            return true;
        } else {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
           
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(error);

            alert.showAndWait();

            return false;
        }

        }

    @FXML
    void search(ActionEvent event) {
        String ItemSearch=ModifyProductsSearchfield.getText();
        
        if (ItemSearch.equals("")){
            ModifyProductsAddTable.setItems(getPart());
      } else {
            boolean found=false;
            try{
                int Item=Integer.parseInt(ItemSearch);
                Part part = Inventory.lookupPart(Item);
                if(part != null){
                    found=true;
                    data.clear();
                    data.add(part);
                    ModifyProductsAddTable.setItems(data);
                }
                if (found==false){
                    ModifyProductsAddTable.setItems(getPart());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Part not found");
                    alert.setContentText("Please search by exact Part Name or Part ID");
                    alert.showAndWait();
                }
                }
            catch(NumberFormatException e){
                for(Part part: Inventory.getPart()){
                    if(part.getName().equals(ItemSearch)){
                        found=true;
                        data.clear();
                        data.add(part);
                        ModifyProductsAddTable.setItems(data);
                    }
            
                }   
                if (found==false){
                    ModifyProductsAddTable.setItems(getPart());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Product not found");
                    alert.setContentText("Please search by ProductID or exact Product Name");
                    alert.showAndWait();
                }
            }
        }   
    }
    
    
     public void initData(Product product)
    {
        selectedProduct = product;
        
       ModifyProductsIDtxtfield.setText("Auto Gen: " + Integer.toString(selectedProduct.getProductID()));
       ModifyProductsIDtxtfield.setEditable(false);
        
      
        ModifyProductsNametxtfield.setText(selectedProduct.getName());
        ModifyProductsInvtxtfield.setText(Integer.toString(selectedProduct.getInStock()));
        ModifyProductsPricetxtfield.setText(Double.toString(selectedProduct.getPrice()));
        ModifyProductsMaxtxtfield.setText(Integer.toString(selectedProduct.getMax()));
        ModifyProductsMintxtfield.setText(Integer.toString(selectedProduct.getMin()));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ModifyProductsAddIDCol.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
        ModifyProductsAddNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        ModifyProductsAddInvCol.setCellValueFactory(cellData -> cellData.getValue().getPartStockProperty().asObject());
         ModifyProductsAddPriceCol.setCellValueFactory(cellData -> cellData.getValue().getPartPriceProperty().asObject());
         
        ModifyProductsDeleteIDCol.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
        ModifyProductsDeleteNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        ModifyProductsDeleteInvCol.setCellValueFactory(cellData -> cellData.getValue().getPartStockProperty().asObject());
         ModifyProductsDeletePriceCol.setCellValueFactory(cellData -> cellData.getValue().getPartPriceProperty().asObject());
         
      ModifyProductsAddTable.setItems(getPartInventory());
       count=Inventory.getProductIDCount();
       ModifyProductsIDtxtfield.setText("AUTO GEN: " + count);
      
    }    
    
}
