/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    
    private int goodNameForVariable = 0; // really bad name.... variable used to control wherer to update or create movie 

    model model;
    
    @FXML
    private ComboBox<String> ComboCategory;
    
    @FXML
    private ListView<Category> listCategory;
    
    private Movies selectedMovies;
    
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
        labelPath.setText(StringPath); // we set the file path to a label instead of a textfield to avoid irritating users inputing a random name folowed with ".mp4"
    }

    @FXML
    private void Save(ActionEvent event) throws SQLException {
        Movies movie = new Movies();
        
        movie.setName(MovieName.getText());
        try {
            movie.setRating(Float.parseFloat(Rating.getText()));
        } catch (NumberFormatException numberFormatException) { //catching the nullpointer exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Please enter a real number");
            alert.showAndWait();
            return;
        }
        movie.setFileLink(labelPath.getText());
        movie.setLocalDate(model.getDate());
        movie.setId(goodNameForVariable);
        
        if(goodNameForVariable != 0 && movie.getFileLink().endsWith(".mp4") || movie.getFileLink().endsWith("mpeg4"))   {
            model.removeAllCats(movie); 
            saveCatMovies(movie);
            model.update(movie);            
        }
        else if(movie.getFileLink().endsWith(".mp4") || movie.getFileLink().endsWith("mpeg4")) {
            model.add(movie);
            saveCatMovies(movie);
        }
        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Please select a '.mp4' or '.mpeg4' file");
            alert.showAndWait();
            return;
        }
        
            
        CategoryList.clear();
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void Close(ActionEvent event) { //closes the window
        CategoryList.clear();
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
    public void setModel(model model) {
        this.model = model;
        getCategories();//used here to avoid making a new model and couldnt be put in this class initialize
      }

    public void setMovie(Movies selectedMovies) { // set existing information about the previously selected movie and transfers its information to the this view
        this.selectedMovies = selectedMovies;
        goodNameForVariable = selectedMovies.getId();
        MovieName.setText(selectedMovies.getName());
        Rating.setText(String.valueOf(selectedMovies.getRating()));
        CategoryList = selectedMovies.getCategories();
        listCategory.setItems(CategoryList);
        labelPath.setText(selectedMovies.getFileLink());
        listCategory.setItems(selectedMovies.getCategories());
    }
    
    public void getCategories() { // method used to add all categories to the combobox
        
        for (Category category : model.getCategories() ) {
            
            ComboCategory.getItems().add(category.getName());
        }
    }

    @FXML
    private void changeText(ActionEvent event) {
        if(ComboCategory.getValue() != null ) { // verifies if combox is empty 
        if (!CategoryList.isEmpty()){ 
            boolean match = false; // see if the is a match of categories below 
            for (Category category : CategoryList) {
                if (category.getName().equalsIgnoreCase(ComboCategory.getValue()))
                    match = true;
            }
            if(!match) { // if its not a match it means that there are no categories of that name so then it can add it
                CategoryList.add(model.SearchCategory(ComboCategory.getValue()));
                listCategory.setItems(CategoryList);
            }
        }
        else {// if the category list is not empty it means thats it not a new movie
            CategoryList.add(model.SearchCategory(ComboCategory.getValue())); 
            listCategory.setItems(CategoryList);
        }                  
        ComboCategory.getSelectionModel().clearSelection();
        }
    }
    
    private void saveCatMovies(Movies movie){ //method used to save catmovie, im really pride myself for coming up with this easy way of doing it
                                              //before it had 12 lines of code and was very difficult to make , after doing it 
        for (Category category : CategoryList) { //i realised this way is much better , ihad to refractor a really big portion of the projects code for this to work - Fabio (just in case you didnt know who did it :D)
            movie.add(category);
            CatMovie catmovie = new CatMovie();
            
            catmovie.setCategoryId(category.getId());
            catmovie.setMovieId(movie.getId());
            
            model.addCat(catmovie);
        }
        
    }

    @FXML
    private void ClickedList(MouseEvent event) {    //method used to remove a category just by clicking the category on the listview
        CategoryList.remove(listCategory.getSelectionModel().getSelectedItem());   
    }
    
}
