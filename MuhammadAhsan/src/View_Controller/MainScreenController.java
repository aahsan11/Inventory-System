/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View_Controller;

import Model.Inventory;
import static Model.Inventory.deletePart;
import static Model.Inventory.getPart;
import static Model.Inventory.getPartInventory;
import static Model.Inventory.getProductInventory;
import static Model.Inventory.getProducts;
import static Model.Inventory.removeProduct;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import static java.util.Optional.empty;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author mhamza0
 */
public class MainScreenController implements Initializable {
    
    @FXML
    private TableView<Part> screenParts;
    @FXML
    private TableColumn<Part, Integer> screenPartsIDColumn;
    @FXML
    private TableColumn <Part, String>screenPartsNameColumn;
    @FXML
    private TableColumn<Part, Integer> screenPartsInvColumn;
    @FXML
    private TableColumn<Part, Double> screenPartsPriceColumn;
    private static Part modifyPart;
      @FXML
    public ObservableList<Part> data=FXCollections.observableArrayList();
     
    @FXML
    public ObservableList<Product> data2=FXCollections.observableArrayList();
       
    
    @FXML
    private TableView <Product>screenProducts;
    @FXML
    private TableColumn<Product, Integer> screenProductsIDColumn;
    @FXML
    private TableColumn<Product, String> screenProductsNameColumn;
    @FXML
    private TableColumn<Product, Integer> screenProductsInvColumn;
    @FXML
    private TableColumn<Product, Double> screenProductsPriceColumn;
    private Part selectedPerson;
    
    
    
    @FXML
    private TextField textSearchParts;
    @FXML
    private TextField textSearchProducts;
    
    

    
    

   
    @FXML
    void exit(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Please confirm you want to exit ");
        alert.setContentText("Any unsaved changes will be lost ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            
        }
        
    }

   
       
    @FXML
    void partsDelete(ActionEvent event) {
        Part part=screenParts.getSelectionModel().getSelectedItem();
        
         if (part !=null){
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setContentText("Are you sure you want to delete "  + part.getName());
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
             deletePart(part);
            }
            else{
            if(result.get()==ButtonType.CANCEL){
                alert.close();
            }
        }
        }
        
 else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Product found");
            alert.setContentText("please select part");
            alert.showAndWait();
        }
       
    }
    
    @FXML
    void partsModify(ActionEvent event) throws IOException {
        
    
       if (screenParts.getSelectionModel().isEmpty()){
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No part found");
            alert.setContentText("please select part");
            alert.showAndWait();
       }
       else {
     FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyParts.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
            
    
             
            ModifyPartsController controller=loader.getController();
              controller.initData(screenParts.getSelectionModel().getSelectedItem());
        
        
       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
       window.show();
             
            
            
             
             
       }
              

      
    }
        
    
    @FXML
    void partsAdd(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene addPartScene = new Scene(addPartParent);
        Stage addPartStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }
   
    

    @FXML
             void partsSearch(ActionEvent event) {
     String ItemSearch = textSearchParts.getText();
        if (ItemSearch.equals("")){
                screenParts.setItems(getPart());
        } else {
            boolean found=false;
            try{
                int Item=Integer.parseInt(ItemSearch);
                Part part = Inventory.lookupPart(Item);
                if(part != null){
                    found=true;
                    data.clear();
                    data.add(part);
                    screenParts.setItems(data);
                }
                if (found==false){
                    screenParts.setItems(getPart());
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
                        screenParts.setItems(data);
                    }
            
                }   
                if (found==false){
                    screenParts.setItems(getPart());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Product not found");
                    alert.setContentText("Please search by ProductID or exact Product Name");
                    alert.showAndWait();
                }
            }
        }   
    }
    
    @FXML
    void productsAdd(ActionEvent event) throws IOException {
               Parent root=FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene productsAddScene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(productsAddScene);
        window.show();


    }

    @FXML
    void productsDelete(ActionEvent event) {
       
        Product product=screenProducts.getSelectionModel().getSelectedItem();
        
      if (product !=null){
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setContentText("Are you sure you want to delete "  + product.getName());
            alert.setHeaderText("Product has associated Parts");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
             removeProduct(product);
            }
            else{
            if(result.get()==ButtonType.CANCEL){
                alert.close();
            }
        }
        }
        
 else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Product found");
            alert.setContentText("please select product");
            alert.showAndWait();
        }
       
    }

    @FXML
    void productsModify(ActionEvent event) throws IOException {
         if (screenProducts.getSelectionModel().isEmpty()){
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No part found");
            alert.setContentText("please select product");
            alert.showAndWait();
       }
       else {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyProduct.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
            
       
             
            ModifyProductController controller=loader.getController();
              controller.initData(screenProducts.getSelectionModel().getSelectedItem());
        
        
       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
       window.show();
             
            
         }  
             
             
    
    }

    @FXML
    void productsSearch(ActionEvent event) {
                 String search= textSearchProducts.getText();
        if (search.equals("")){
                screenProducts.setItems(getProducts());
        } else {
            boolean found=false;
            try{
                int item=Integer.parseInt(search);
                Product product = Inventory.lookupProduct(item);
                if(product != null){
                    found=true;
                    data2.clear();
                    data2.add(product);
                    screenProducts.setItems(data2);
                }
                if (found==false){
                    screenProducts.setItems(getProducts());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Product not found");
                    alert.setContentText("Please search by exact Product Name or ID #");
                    alert.showAndWait();
                }
                }
            catch(NumberFormatException e){
                for(Product product: Inventory.getProducts()){
                    if(product.getName().equals(search)){
                        found=true;
                        data2.clear();
                        data2.add(product);
                        screenProducts.setItems(data2);
                    }
            
                }   
                if (found==false){
                    screenProducts.setItems(getProducts());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Product not found");
                    alert.setContentText("Please search by ProductID or Product Name");
                    alert.showAndWait();
                }
            }
        }   
    }
    
        /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        screenPartsIDColumn.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
        screenPartsNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        screenPartsInvColumn.setCellValueFactory(cellData -> cellData.getValue().getPartStockProperty().asObject());
        screenPartsPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getPartPriceProperty().asObject());
      screenParts.setItems(getPartInventory());
      
      screenProductsIDColumn.setCellValueFactory(cellData -> cellData.getValue().getProductIDProperty().asObject());
        screenProductsNameColumn.setCellValueFactory(cellData -> cellData.getValue().getProductNameProperty());
        screenProductsInvColumn.setCellValueFactory(cellData -> cellData.getValue().getProductStockProperty().asObject());
        screenProductsPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getProductPriceProperty().asObject());
      screenProducts.setItems(getProductInventory());
    
        
    }
  
}
     
    

