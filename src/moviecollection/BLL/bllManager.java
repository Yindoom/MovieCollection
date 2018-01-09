/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.BLL;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import moviecollection.BE.Movies;
import moviecollection.DAL.dalManager;

/**
 *
 * @author ZeXVex
 */
public class bllManager {
    
    private ObservableList<Movies> Movies = FXCollections.observableArrayList(new ArrayList<>());
    
    dalManager DAL = new dalManager();
    
    public List<Movies> getAllMovies(){
        return DAL.getAllMovies();
    }
    
<<<<<<< HEAD
    public static void removeMovie(Movies selectedMovie) {
       return DAL.remove(selectedMovie);
=======
    public void remove(Movies selectedMovie) {
       DAL.removeMovie(selectedMovie);
    }
    public String setDate(int year, int month, int day)   {
        String string = String.join("/", Integer.toString(year), Integer.toString(month), Integer.toString(day));
        return string;
    }

    public void add(Movies movie) {
        DAL.addMovies(movie);
>>>>>>> master
    }
}

