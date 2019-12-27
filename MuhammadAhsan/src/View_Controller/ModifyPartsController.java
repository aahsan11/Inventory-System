/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mhamza0
 */
public class ModifyPartsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private OutsourcedPart selectedOutPart;
    private InhousePart selectedInPart;
   private static Part selectedPart;
   private static int partID;
    @FXML
    private RadioButton ModifyPartsInHouseRadioButton;

    @FXML
    private RadioButton ModifyPartsOutsourcedRadioButton;

    @FXML
    private Label ModifyPartsCompanyNamelbl;

    @FXML
    private TextField ModifyPartsIDtxtfield;

    @FXML
    private TextField ModifyPartsNametxtfield;

    @FXML
    private TextField ModifyPartsInvtxtfield;

    @FXML
    private TextField ModifyPartsPricetxtfield;

    @FXML
    private TextField ModifyPartsMaxtxtfield;

    @FXML
    private TextField ModifyPartstxtfield;

    @FXML
    private TextField ModifyPartsMintxtfield;
    
    @FXML
    private Label ModifyLabel;
     private ToggleGroup radiobutton;

    @FXML
    void InHouse(ActionEvent event) {
if (event.getSource()== ModifyPartsInHouseRadioButton){
    ModifyLabel.setText("Machine ID");
    ModifyPartstxtfield.setPromptText("Mach ID");
    
}
    }

    @FXML
    void Outsourced(ActionEvent event) {
        if (event.getSource()== ModifyPartsOutsourcedRadioButton){
   ModifyLabel.setText("Company Name");
    ModifyPartstxtfield.setPromptText("Company Name");
    }
    }

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
    void save(ActionEvent event) throws IOException {
        if (isvalid()){
        String ModifyPartsName=ModifyPartsNametxtfield.getText();
        String ModifyPartsInv=ModifyPartsInvtxtfield.getText();
        String ModifyPartsPrice=ModifyPartsPricetxtfield.getText();
        String ModifyPartsMin=ModifyPartsMintxtfield.getText();
        String ModifyPartsID=ModifyPartsMintxtfield.getText();
        String ModifyPartsMax=ModifyPartsMaxtxtfield.getText();
        String textfield=ModifyPartstxtfield.getText();
        if(ModifyPartsInHouseRadioButton.isSelected()){
     
            InhousePart inhouse=new InhousePart();
            inhouse.setPartID(Integer.parseInt(ModifyPartsID));
            inhouse.setName(ModifyPartsName);
            inhouse.SetPrice(Double.parseDouble(ModifyPartsPrice));
            inhouse.setMin(Integer.parseInt(ModifyPartsMin));
            inhouse.setMax(Integer.parseInt(ModifyPartsMax));
            inhouse.setInStock(Integer.parseInt(ModifyPartsInv));
            inhouse.setMachineID(Integer.parseInt(textfield));
            Inventory.addPart(inhouse);
            Inventory.deletePart(selectedPart);
            
            
        }
        if(ModifyPartsOutsourcedRadioButton.isSelected()){
            OutsourcedPart outsourced=new OutsourcedPart();
            outsourced.setPartID(Integer.parseInt(ModifyPartsID));
            outsourced.setName(ModifyPartsName);
             outsourced.SetPrice(Double.parseDouble(ModifyPartsPrice));
            outsourced.setMin(Integer.parseInt(ModifyPartsMin));
            outsourced.setMax(Integer.parseInt(ModifyPartsMax));
            outsourced.setInStock(Integer.parseInt(ModifyPartsInv));
            outsourced.setComapnyName(textfield);
            Inventory.addPart(outsourced);
            Inventory.deletePart(selectedPart);
           
        }
        Parent root=FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        }

    } 
    public void initData(Part part)
    {
        selectedPart = part;
        
       ModifyPartsIDtxtfield.setText("Auto Gen" + Integer.toString(selectedPart.getPartID()));
       ModifyPartsIDtxtfield.setEditable(false);
        
      
        ModifyPartsNametxtfield.setText(selectedPart.getName());
        ModifyPartsInvtxtfield.setText(Integer.toString(selectedPart.getInStock()));
        ModifyPartsPricetxtfield.setText(Double.toString(selectedPart.getPrice()));
        ModifyPartsMaxtxtfield.setText(Integer.toString(selectedPart.getMax()));
        ModifyPartsMintxtfield.setText(Integer.toString(selectedPart.getMin()));
        if (part instanceof InhousePart) {
            selectedInPart = (InhousePart) part;
            ModifyLabel.setText("Machine ID");
            ModifyPartsInHouseRadioButton.selectedProperty().set(true);
            ModifyPartstxtfield.setText(Integer.toString(selectedInPart.getMachineID()));
        } else {
            selectedOutPart = (OutsourcedPart) part;
           ModifyLabel.setText("Company Name");
            ModifyPartsOutsourcedRadioButton.selectedProperty().set(true);
            ModifyPartstxtfield.setText(selectedOutPart.getCompanyName());
        }
    }
       
       
     boolean isvalid(){
        String inv=ModifyPartsInvtxtfield.getText();
        String name=ModifyPartsNametxtfield.getText();
        String price=ModifyPartsPricetxtfield.getText();
        String min=ModifyPartsMintxtfield.getText();
        String max=ModifyPartsMaxtxtfield.getText();
        String txtpart=  ModifyPartstxtfield.getText();
        
      

        String error="";
        
        
        
        
        
        try {
            if (this.radiobutton.getSelectedToggle().equals(this.ModifyPartsInHouseRadioButton)){
                
                
                    Integer.parseInt(txtpart);
            }
             }
             catch (Exception e) {
                    error += ("Machine ID must be numeric  ");
                }
        
        if (txtpart == null || txtpart.length() == 0) {
            error += "No valid Company name or Machine ID "; 
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
            error=error+"plz enter valid inventory number ";
            
        }
        }
           
           
           
           try{
               int mi=Integer.parseInt(min);
               int ma=Integer.parseInt(max);
               if (ma <= mi|| mi >= ma){
                  error += "Maximum value must be > than minimum "; 
                   
               }
              
           }catch(NumberFormatException e) {
                error += "No valid value for min or max ";
           }
            
            try {
                
               Double pr=Double.parseDouble(price);
               
               System.out.println(pr);
            
            }catch (NumberFormatException e) {
                error += "No valid Price (double) "; 
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
         radiobutton= new ToggleGroup();
        ModifyPartsInHouseRadioButton.setToggleGroup(radiobutton);
        ModifyPartsOutsourcedRadioButton.setToggleGroup(radiobutton);
        
    }    
    
}
