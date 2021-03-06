/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import moviecollection.BE.Category;
import moviecollection.BE.Movies;

/**
 *
 * @author Yindo
 */
public class MainWindowController implements Initializable {
    
    model model = new model();
    
    @FXML
    private TableView<Movies> movieList;
    @FXML
    private TableView<Category> categoryList;
    @FXML
    private Label label;
    @FXML
    private TableColumn<Movies, String> columnName;
    @FXML
    private TableColumn<Movies, String> columnCategory;
    @FXML
    private TableColumn<Movies, String> columnRating;
    @FXML
    private TableColumn<Movies, LocalDate> columnLastSeen;
    @FXML
    private TableColumn<Movies, String> columnCategory2;
    @FXML
    private TextField txtSearch;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        columnName.setCellValueFactory(
                new PropertyValueFactory("name"));
        columnCategory.setCellValueFactory(
                new PropertyValueFactory("categories"));
        columnRating.setCellValueFactory(
                new PropertyValueFactory("rating"));
        columnLastSeen.setCellValueFactory(
                new PropertyValueFactory("LocalDate"));
        
        columnCategory2.setCellValueFactory(
                new PropertyValueFactory("name"));
        
        movieList.setItems(model.getmovieList());
        
        categoryList.setItems(model.getCategoryList());
        
        model.setAllCatMovies();
        
        try {
            checkDelete();
            // TODO
        } catch (IOException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        private void editMovie(javafx.event.ActionEvent event) throws IOException {
            
        try {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AddMovie.fxml"));
        
        Parent root = fxLoader.load();
        AddMovieController stc = fxLoader.getController();
        stc.setModel(model);
        
        Movies selectedMovies = movieList.getSelectionModel().getSelectedItem();
        stc.setMovie(selectedMovies);
        
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog your fault");
            alert.setHeaderText("You did something wrong");
            alert.setContentText("Please select movie from the view");
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteMovie(ActionEvent event) { //deletes a song
        Movies selectedMovie
                = movieList.getSelectionModel().getSelectedItem();

        model.remove(selectedMovie);
    }


    @FXML
    private void ClickNewCat(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AddCategory.fxml"));
        
        Parent root = fxLoader.load();
        AddCategoryController stc = fxLoader.getController();
        stc.setModel(model);
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    @FXML
    private void ClickDeleteCat(ActionEvent event) {
        Category selectedCategory
                = categoryList.getSelectionModel().getSelectedItem();

        model.remove(selectedCategory);
    }

    @FXML
    private void clickSearch(ActionEvent event) { //searches the songlist for songs containing the input string
        String search = txtSearch.getText().toLowerCase();
        model.search(search);
    }
    
    
    private void checkDelete() throws IOException {
        model.checkDelete();
        openDelete();
    }
    
    private void openDelete() throws IOException   {
        if(!model.getDeleteMovies().isEmpty())   {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("DeleteWindow.fxml"));
        
        Parent root = fxLoader.load();
        DeleteWindowController stc = fxLoader.getController();
        stc.setModel(model);
        
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        }
            
    }

    @FXML
    private void playMovie(ActionEvent event) throws IOException {
        Movies selectedMovie
                = movieList.getSelectionModel().getSelectedItem();
        
         model.playMovie(selectedMovie);
    }

    @FXML
    private void clickCategory(MouseEvent event) {
        Category selectedCategory = categoryList.getSelectionModel().getSelectedItem();
        model.getmovieList().setAll(selectedCategory.getListMovies());
    }

    @FXML
    private void showAll(ActionEvent event) {
        model.refreshList();
        model.refreshMList();
        
    }
}
