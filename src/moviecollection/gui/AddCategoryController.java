/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import moviecollection.BE.Category;

/**
 * FXML Controller class
 *
 * @author Fábio
 */
public class AddCategoryController implements Initializable {

    @FXML
    private TextField txtCategory;
    private model model;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ClickedSave(ActionEvent event) {
        Category category = new Category();
        
        category.setName(txtCategory.getText());
        
        model.add(category);
        
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void ClickedClose(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void setModel(model model) { 
        this.model = model;
      }
    
}
