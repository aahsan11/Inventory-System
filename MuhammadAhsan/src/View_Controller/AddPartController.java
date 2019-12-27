
package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AddPartController implements Initializable {

    @FXML
    private Label lblAddPartID;
     private int partID;

    @FXML
    private RadioButton radioAddPartInHouse;

    @FXML
    private RadioButton radioAddPartOutSourced;

    @FXML
    private Label lblAddPart;

    
    private ToggleGroup radiobutton;
   
    
    
    @FXML
    private TextField txtAddPartName;
    
    @FXML
    private TextField txtAddPartInv;

    @FXML
    private TextField txtAddPartPrice;

    @FXML
    private TextField txtAddPartMax;

    @FXML
    private TextField txtAddPart;

    @FXML
    private TextField txtAddPartMin;
   

    @FXML
    void cancel(ActionEvent event) throws IOException {
        
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Cancel");
            alert.setHeaderText("Are you sure you want to Cancel adding new part");
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
    void radioInHouse(ActionEvent event) {
      if (event.getSource()==radioAddPartInHouse){
            lblAddPart.setText("Machine ID");
            txtAddPart.setPromptText("Mach ID");

    }
    }

    

    @FXML
    void radioOutsourced(ActionEvent event) {
                  if (event.getSource()==radioAddPartOutSourced){
            lblAddPart.setText("Company Name");
            txtAddPart.setPromptText("Company Name");
                  }

    }

    @FXML
    void save(ActionEvent event) throws IOException {
           if (isvalid()) {
         String partName = txtAddPartName.getText();
        String partInv = txtAddPartInv.getText();
        String partPrice = txtAddPartPrice.getText();
        String partMin = txtAddPartMin.getText();
        String partMax = txtAddPartMax.getText();
        String partIDs = txtAddPart.getText();
        
      
            
        
        
            
                if (radioAddPartInHouse.isSelected()) {
                    InhousePart InPart = new InhousePart();

                  
                  InPart.setPartID(partID);
                    InPart.setName(partName);
                    InPart.SetPrice(Double.parseDouble(partPrice));
                    InPart.setInStock(Integer.parseInt(partInv));
                    InPart.setMin(Integer.parseInt(partMin));
                    InPart.setMax(Integer.parseInt(partMax));
                    
                    InPart.setMachineID(Integer.parseInt(partIDs));
                    
                    Inventory.addPart(InPart);
                } else if(radioAddPartOutSourced.isSelected()) {
                    OutsourcedPart OutPart = new OutsourcedPart();

                    
                    OutPart.setPartID(partID);
                    OutPart.setName(partName);
                    OutPart.SetPrice(Double.parseDouble(partPrice));
                    OutPart.setInStock(Integer.parseInt(partInv));
                    OutPart.setMin(Integer.parseInt(partMin));
                    OutPart.setMax(Integer.parseInt(partMax));
                    
                    OutPart.setComapnyName(partIDs);
                    Inventory.addPart(OutPart);
                }

                Parent partsSave = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene scene = new Scene(partsSave);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
           }
            
        
    }
    boolean isvalid(){
        String inv=txtAddPartInv.getText();
        String name=txtAddPartName.getText();
        String price=txtAddPartPrice.getText();
        String min=txtAddPartMin.getText();
        String max=txtAddPartMax.getText();
        String txtpart=txtAddPart.getText();
        
      

        String error="";
        
            
        
         
        
             try {
            if (this.radiobutton.getSelectedToggle().equals(this.radioAddPartInHouse)){
                
                
                    Integer.parseInt(txtpart);
            }
             }
             catch (Exception e) {
                    error += ("Machine ID must be numeric  ");
                }
        
      if (txtpart == null || txtpart.length() == 0) {
            error += "No valid Company name or Machine ID  "; 
        }

        if (inv == null || inv.length() == 0) {
            error += "No valid inv value  "; 
        }
       
           
         if (name == null || name.length() == 0) {
            error += "No valid part name "; 
        }
       
     
        else{
        try{
                    int mi=Integer.parseInt(min);
        int ma=Integer.parseInt(max);
        int in=Integer.parseInt(inv);
            if(in >= ma || in <= mi ){
                
                error = error+"inventory must be between min and max ";
                
               }
            
        }
        
         catch (NumberFormatException e) {
            error=error+"plz enter valid inventory (integer) number  ";
            
        }
        }
           
           
           
           try{
               int mi=Integer.parseInt(min);
               int ma=Integer.parseInt(max);
               if (ma < mi|| mi >= ma){
                  error += "Maximum value must be > than minimum  "; 
                   
               }
              
           }catch(NumberFormatException e) {
                error += "No valid value for min or max  ";
           }
            
            try {
                
             Double.parseDouble(price);
               
             
            
            }catch (NumberFormatException e) {
                error += "No valid Price (must be a double) "; 
            }
            
        
            if (error.length() == 0) {
            return true;
        } else {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
           
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields ");
            alert.setContentText(error);

            alert.showAndWait();

            return false;
        }
         
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partID=Inventory.getPartIDCount();
        lblAddPartID.setText("Auto Gen:  "+ partID);
         radiobutton= new ToggleGroup();
         radioAddPartInHouse.setToggleGroup(radiobutton);
         radioAddPartOutSourced.setToggleGroup(radiobutton);
       
       
    }  

}
