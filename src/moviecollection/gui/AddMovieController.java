/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import moviecollection.BE.Movies;

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
    
    private int goodNameForVariable = 0;

    model model;
    private Movies selectedMovies;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ChooseFilePath(ActionEvent event) {
        String StringPath = null;
        
        final FileChooser fileChooser = new FileChooser();
        
        File filePath = fileChooser.showOpenDialog(null);
        if (filePath != null)
        {
            StringPath = filePath.getAbsolutePath();
        }
        FilePath.setText(StringPath);
    }

    @FXML
    private void Save(ActionEvent event) {
        Movies movie = new Movies();
        
        movie.setName(MovieName.getText());
        movie.setRating(Rating.getText());
        movie.setFileLink(FilePath.getText());
      //movie.setCategory(comboCategory.getValue());
        movie.setLastview(model.getDate());
        
        movie.setId(goodNameForVariable);
        if(goodNameForVariable != 0)
            model.update(movie);
        else
            model.add(movie);
        System.out.println(movie);
        
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void Close(ActionEvent event) { //closes the window
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
    public void setModel(model model) {
        this.model = model;
      }

    void setMovie(Movies selectedMovies) {
        this.selectedMovies = selectedMovies;
        goodNameForVariable = selectedMovies.getId();
        MovieName.setText(selectedMovies.getName());
        Rating.setText(selectedMovies.getRating());
       // comboCategory.setValue(selectedMovies.getCategory());
        FilePath.setText(selectedMovies.getFileLink());
    }
}
