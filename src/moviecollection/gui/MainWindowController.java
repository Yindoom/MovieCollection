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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private Button button;
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
    private TableColumn<Movies, String> columnLastSeen;
    @FXML
    private TableColumn<Movies, String> columnCategory2;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnName.setCellValueFactory(
                new PropertyValueFactory("name"));
        columnCategory.setCellValueFactory(
                new PropertyValueFactory("category"));
        columnRating.setCellValueFactory(
                new PropertyValueFactory("rating"));
        columnLastSeen.setCellValueFactory(
                new PropertyValueFactory("lastview"));
        
        columnCategory2.setCellValueFactory(
                new PropertyValueFactory("name"));
        
        movieList.setItems(model.getmovieList());
        
        categoryList.setItems(model.getCategoryList());
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
        private void editMovie(javafx.event.ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AddMovie.fxml"));
        
        Parent root = fxLoader.load();
        AddMovieController stc = fxLoader.getController();
        stc.setModel(model);
        
        Movies selectedMovies
                = movieList.getSelectionModel().getSelectedItem();
        stc.setMovie(selectedMovies);
        
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    @FXML
    private void deleteMovie(ActionEvent event) { //deletes a song
        Movies selectedMovies
                = movieList.getSelectionModel().getSelectedItem();

        model.remove(selectedMovies);
    }

    @FXML
    private void showAllMovies(ActionEvent event) {
    }

    @FXML
    private void editMovie(ActionEvent event) {
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
}
