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

/**
 * FXML Controller class
 *
 * @author ZeXVex
 */
public class AddMovieController implements Initializable {
    
    @FXML
    private TextField MovieName;
    @FXML
    private TextField FilePath;
    @FXML
    private TextField Rating;

    model model;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ChoseFilePath(ActionEvent event) {
    }

    @FXML
    private void Save(ActionEvent event) {
    }

    @FXML
    private void Close(ActionEvent event) { //closes the window
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
    public void setModel(model model) { //sets the model to the SongModel from MWC
        this.model = model;
      }
}
