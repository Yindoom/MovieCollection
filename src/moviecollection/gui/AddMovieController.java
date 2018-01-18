/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import moviecollection.BE.CatMovie;
import moviecollection.BE.Category;
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
    @FXML
    private ComboBox<String> ComboCategory;
    @FXML
    private TextField txtCategory;
    
    private String Categories;
    
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
        movie.setRating(Float.parseFloat(Rating.getText()));
        movie.setFileLink(FilePath.getText());
      //movie.setCategory(comboCategory.getValue());
        movie.setLocalDate(model.getDate());
        movie.setId(goodNameForVariable);
        
        if(goodNameForVariable != 0)
            model.update(movie);
        else if(movie.getFileLink().endsWith(".mp4") || movie.getFileLink().endsWith("mpeg4"))
            model.add(movie);
            saveCatMovies(MovieName.getText());
        System.out.println(movie);
        
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void Close(ActionEvent event) { //closes the window
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
    public void setModel(model model) {
        this.model = model;
        getCategories();
      }

    public void setMovie(Movies selectedMovies) {
        this.selectedMovies = selectedMovies;
        goodNameForVariable = selectedMovies.getId();
        MovieName.setText(selectedMovies.getName());
        Rating.setText(String.valueOf(selectedMovies.getRating()));
       // comboCategory.setValue(selectedMovies.getCategory());
        FilePath.setText(selectedMovies.getFileLink());
    }
    
    public void getCategories() {
        
        for (Category category : model.getCategories() ) {
            
            ComboCategory.getItems().add(category.getName());
        }
    }

    @FXML
    private void changeText(ActionEvent event) {
        
        setCategoryText(ComboCategory.getValue());  
        ComboCategory.setPromptText("Choose Category");
        
    }
    
    private void setCategoryText(String string){   //method used to make the categories txtfield look pretty  //i should change this method to the bll because it doesnt belong here.
        String txtCategoryList = txtCategory.getText();
        txtCategoryList = String.join("," , string , txtCategoryList);
        txtCategory.setText(txtCategoryList);
        Categories = txtCategoryList; //save to variable outside of method to be later used
    }
    
    private void saveCatMovies(String MovieName){
        
        String[] split = Categories.split(",");
        
        for (int i = 0; i < split.length; i++) {
            
            CatMovie catmovie = new CatMovie();
            
            catmovie.setCategoryId(model.SearchCats(split[i]));
            catmovie.setMovieId(model.SearchMovies(MovieName));
            
            model.addCat(catmovie);
            
            
            
        }
        
        
        
    }
    
}
