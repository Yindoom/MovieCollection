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
import javafx.scene.control.ListView;
import moviecollection.BE.Movies;

/**
 * FXML Controller class
 *
 * @author Yindo
 */
public class DeleteWindowController implements Initializable {

    model model;
    @FXML
    private ListView<Movies> deleteList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setModel(model model) {
        this.model = model;
        setDelete();
    }
    
    public void setDelete() {
            deleteList.setItems(model.getDeleteMovies());
    }

    @FXML
    private void deleteAll(ActionEvent event) {
        for (Movies movie : model.getDeleteMovies()) {
            model.remove(movie);
        }
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void close(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
}
