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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    private TextField Rating;
    
    private int goodNameForVariable = 0;

    model model;
    
    private Movies selectedMovies;
    @FXML
    private ComboBox<String> ComboCategory;
    
    private String Categories;
    @FXML
    private ListView<Category> listCategory;
    
    private ObservableList<Category> CategoryList = FXCollections.observableArrayList(new ArrayList<>());
    @FXML
    private Label labelPath;
    
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
        labelPath.setText(StringPath);
    }

    @FXML
    private void Save(ActionEvent event) {
        Movies movie = new Movies();
        
        movie.setName(MovieName.getText());
        movie.setRating(Float.parseFloat(Rating.getText()));
        movie.setFileLink(labelPath.getText());
      //movie.setCategory(comboCategory.getValue());
        movie.setLocalDate(model.getDate());
        movie.setId(goodNameForVariable);
        
        if(goodNameForVariable != 0)
            model.update(movie);
        else if(movie.getFileLink().endsWith(".mp4") || movie.getFileLink().endsWith("mpeg4"))
            model.add(movie);
            saveCatMovies(movie);
        
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
        labelPath.setText(selectedMovies.getFileLink());
    }
    
    public void getCategories() {
        
        for (Category category : model.getCategories() ) {
            
            ComboCategory.getItems().add(category.getName());
        }
    }

    @FXML
    private void changeText(ActionEvent event) {
        if(ComboCategory.getValue() != null ) {
        if (!CategoryList.isEmpty()){
            boolean match = false;
            for (Category category : CategoryList) {
                if (category.getName().equalsIgnoreCase(ComboCategory.getValue()))
                    match = true;
            }
            if(!match) {
                CategoryList.add(model.SearchCategory(ComboCategory.getValue()));
                listCategory.setItems(CategoryList);
            }
        }
        else {
            CategoryList.add(model.SearchCategory(ComboCategory.getValue()));
            listCategory.setItems(CategoryList);
        }                  
        ComboCategory.getSelectionModel().clearSelection();
        }
    }
    
    private void saveCatMovies(Movies movie){
        
        for (Category category : CategoryList) {
            movie.add(category);
            CatMovie catmovie = new CatMovie();
            
            catmovie.setCategoryId(category.getId());
            catmovie.setMovieId(movie.getId());
            
            model.addCat(catmovie);
        }
        //System.out.println(movie.getCategories());
        
    }

    @FXML
    private void ClickedList(MouseEvent event) {    
        CategoryList.remove(listCategory.getSelectionModel().getSelectedItem());   
    }
    
}
