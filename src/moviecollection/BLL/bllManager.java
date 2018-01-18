/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.BLL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import moviecollection.BE.CatMovie;
import moviecollection.BE.Category;
import moviecollection.BE.Movies;
import moviecollection.DAL.dalManager;

/**
 *
 * @author ZeXVex
 */
public class bllManager {
    
    private ObservableList<Movies> Movies = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Category> Categories = FXCollections.observableArrayList(new ArrayList<>());
    
    dalManager DAL = new dalManager();
    
    public List<Movies> getAllMovies(){
        return DAL.getAllMovies();
    }
    
    public List<Category> getAllCategories() {
        return DAL.getAllCategory();
    }
    
    public void remove(Movies selectedMovie) {
       DAL.removeMovie(selectedMovie);
    }
    
    public void remove(Category selectedCategory) {
        DAL.removeCategory(selectedCategory);
    }
    
    public String setDate(int year, int month, int day)   {
        String string = String.join("/", Integer.toString(year), Integer.toString(month), Integer.toString(day));
        return string;
    }

    public void add(Movies movie) {
        DAL.addMovies(movie);
    }

    public void add(Category category) {
        DAL.addCategory(category);
    }

    
   
    public void update(Movies movie) {
        DAL.updateMovie(movie);
    }

    public int searchCats(String string) {
        
        int id = 0;
        for (Category Category : Categories) {
            if ( Category.getName().equals(string) ) {
                id = Category.getId();
            }
        }
        return id;
    }

    public int searchMovies(String MovieName) {
        
        int id = 0;
        for (Movies Movie : Movies) {
            if ( Movie.getName().equals(MovieName) ) {
                id = Movie.getId();
            }
        }
        return id;
    }

    public void add(CatMovie catmovie) {
        DAL.addCatMovie(catmovie);
    }
}        