/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Yindo
 */
public class MainWindowController implements Initializable {
    
    model model = new model();
    
    @FXML
    private Button button;
    @FXML
    private TableView<?> movieList;
    @FXML
    private TableView<?> categoryList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addMovie(javafx.event.ActionEvent event) throws IOException { //opens a window to add a new song
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AddMovie.fxml"));
        
        Parent root = fxLoader.load();
        AddMovieController stc = fxLoader.getController();
        stc.setModel(model);
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }
    
    @FXML
    private void editMovie(ActionEvent event) {
    }

    @FXML
    private void deleteMovie(ActionEvent event) {
    }

    @FXML
    private void showAllMovies(ActionEvent event) {
    }
}
