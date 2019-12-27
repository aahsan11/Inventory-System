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
import static Model.Inventory.removeProduct;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mhamza0
 */
public class AddProductController implements Initializable {
    private ObservableList<Part> current = FXCollections.observableArrayList();
    private int ProductCount;
      
    @FXML
    private Label AddProductsIDlbl;
    @FXML
    private TextField AddProductsNametxt;
    @FXML
    private TextField AddProductsInvtxt;
    @FXML
    private TextField AddProductsPricetxt;
    @FXML
    private TextField AddProductsMaxtxt;
    @FXML
    private TextField AddProductsMintxt;
    @FXML
    private TableView<Part> AddProductsAddTable;
    @FXML
    private TableColumn<Part, Integer> AddProductsPartIDCol;
    @FXML
    private TableColumn<Part, String> AddProductsPartNameCol;
    @FXML
    private TableColumn<Part, Integer> AddProductsInvCol;
    @FXML
    private TableColumn<Part, Double> AddProductsPriceCol;
    @FXML
    private TableView<Part> AddProductsCurrentTable;
    @FXML
    private TableColumn<Part, Integer> AddProductsCurrentPartIDCol;
    @FXML
    private TableColumn<Part, String> AddProductsCurrentPartNameCol;
    @FXML
    private TableColumn<Part, Integer> AddProductsCurrentInvCol;
    @FXML
    private TableColumn<Part, Double> AddProductsCurrentPriceCol;
    @FXML
    private TextField AddProductsSearchfield;
      @FXML
    public ObservableList<Part> data=FXCollections.observableArrayList();
    
    


    
    @FXML
    void add(ActionEvent event) {
       if (AddProductsAddTable.getSelectionModel().isEmpty()){
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No part found");
            alert.setContentText("please select part");
            alert.showAndWait();
       }
       else{
         Part part=AddProductsAddTable.getSelectionModel().getSelectedItem();
        
             current.add(part);
            AddProductsCurrentTable.setItems(current);
            //AddProductsCurrentTable.setItems((ObservableList<Product>) part);
         
       }
            
    
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Cancel");
            alert.setHeaderText("Are you sure you want to Cancel adding new produc");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
             Parent cancelbutton=FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene  cancelbuttonscene=new Scene(cancelbutton);
        
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(cancelbuttonscene);
        window.show();
            }
            else{
                alert.close();
            }
        

    }

    @FXML
    void delete(ActionEvent event) {
       if (AddProductsCurrentTable.getSelectionModel().isEmpty()){
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No part found");
            alert.setContentText("please select part");
            alert.showAndWait();
       }
        int siz= AddProductsCurrentTable.getItems().size();
        Part part=AddProductsCurrentTable.getSelectionModel().getSelectedItem();
        if(siz>1){
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete " + part.getName());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
             current.remove(part);
            else{
                alert.close();
            }
        }
        else{
                         Alert alert=new Alert(Alert.AlertType.ERROR);
              
        alert.setTitle("Parts Error");
        alert.setHeaderText("Cannot delete " + part.getName());
        alert.setContentText("Product must have at least one Part");
        alert.showAndWait();

        }

    }

    @FXML
    void save(ActionEvent event) throws IOException {
       if (isValid()) {
        String productName = AddProductsNametxt.getText();
        String productInv = AddProductsInvtxt.getText();
        String productPrice = AddProductsPricetxt.getText();
        String productMin = AddProductsMintxt.getText();
        String productMax = AddProductsMaxtxt.getText();
        
        
        Product products=new Product();
        products.setProductID(ProductCount);
         products.setName(productName);
                    products.setPrice(Double.parseDouble(productPrice));
                   products.setInStock(Integer.parseInt(productInv));
                    products.setMin(Integer.parseInt(productMin));
                    products.setMax(Integer.parseInt(productMax));
                    Inventory.addProduct(products);
                    
        
        Parent root=FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        
       }
        
    }
    
   
    

    @FXML
    void search(ActionEvent event) {

    String searchItem = AddProductsSearchfield.getText();
        if (searchItem.equals("")){
                AddProductsAddTable.setItems(getPart());
        } else {
            boolean found=false;
            try{
                int itemNumber=Integer.parseInt(searchItem);
                Part part = Inventory.lookupPart(itemNumber);
                if(part != null){
                    found=true;
                    data.clear();
                    data.add(part);
                    AddProductsAddTable.setItems(data);
                }
                if (found==false){
                    AddProductsAddTable.setItems(getPart());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Part not found");
                    alert.setContentText("Please search by exact Part Name or ID #");
                    alert.showAndWait();
                }
                }
            catch(NumberFormatException e){
                for(Part part: Inventory.getPart()){
                    if(part.getName().equals(searchItem)){
                        found=true;
                        data.clear();
                        data.add(part);
                        AddProductsAddTable.setItems(data);
                    }
            
                }   
                if (found==false){
                    AddProductsAddTable.setItems(getPart());
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
      
        ProductCount= Inventory.getPartIDCount();
        AddProductsIDlbl.setText("AUTO GEN: " + ProductCount);
      
        AddProductsPartIDCol.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
        AddProductsPartNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        AddProductsInvCol.setCellValueFactory(cellData -> cellData.getValue().getPartStockProperty().asObject());
         AddProductsPriceCol.setCellValueFactory(cellData -> cellData.getValue().getPartPriceProperty().asObject());
         
         AddProductsCurrentPartIDCol.setCellValueFactory(cellData -> cellData.getValue().getPartIDProperty().asObject());
         AddProductsCurrentPartNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        AddProductsCurrentInvCol.setCellValueFactory(cellData -> cellData.getValue().getPartStockProperty().asObject());
         AddProductsCurrentPriceCol.setCellValueFactory(cellData -> cellData.getValue().getPartPriceProperty().asObject());
         
      AddProductsAddTable.setItems(getPartInventory());
      
   
      
      
    } 
   public boolean isValid(){
              String productName = AddProductsNametxt.getText();
              
               String productIn = AddProductsInvtxt.getText();
        String productPr = AddProductsPricetxt.getText();
        
        String productMi = AddProductsMintxt.getText();
        String productMa = AddProductsMaxtxt.getText();
              
        String productInv = AddProductsInvtxt.getText();
        String productPrice = AddProductsPricetxt.getText();
        
        String productMin = AddProductsMintxt.getText();
        String productMax = AddProductsMaxtxt.getText();
        
        String error="";
       
        
       
     
         ArrayList<Part> parts = new ArrayList<>();
        parts.addAll(AddProductsCurrentTable.getItems());
        
        
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
            error=error+"plz enter valid inventory number ";
            
        }
        try {
               double pp= Double.parseDouble(productPrice);
            double partsprice=0;
        for (Part partprice: parts ){
            
            partsprice=partsprice+partprice.getPrice();
        }
            if(pp<=partsprice){
                error=error+ "product price must be greater than sum of parts price ";
                
            }
        }
        catch(NumberFormatException e){
            error=error+"plz enter valid price ";
        }
        try{
             int pmin=Integer.parseInt(productMin);
        int pmax=Integer.parseInt(productMax);
             if(pmin>=pmax){
            error=error+"product min must be less than max ";
        }
        }
        catch(NumberFormatException e){
            error=error+"Plz enter valid inventory value (integer) ";
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
}
       
    

