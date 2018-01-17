/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.BLL;

import java.awt.Desktop;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import moviecollection.BE.Movies;
import moviecollection.DAL.dalManager;
import java.io.File;
import java.io.IOException;
import javafx.scene.media.Media;

/**
 *
 * @author ZeXVex
 */
public class bllManager {
    
    private ObservableList<Movies> Movies = FXCollections.observableArrayList(new ArrayList<>());
    
    String filepath;
    Movies movie;
    Media media;
    dalManager DAL = new dalManager();
    
    public List<Movies> getAllMovies(){
        return DAL.getAllMovies();
    }
    
    public void remove(Movies selectedMovie) {
       DAL.removeMovie(selectedMovie);
    }
    public String setDate(int year, int month, int day)   {
        String string = String.join("/", Integer.toString(year), Integer.toString(month), Integer.toString(day));
        return string;
    }

    public void add(Movies movie) {
        DAL.addMovies(movie);
    }

    public void playMovie(Movies selectedMovie) throws IOException {
        movie = selectedMovie; 
        filepath = movie.getFileLink();
        File file = new File(filepath);
        filepath = file.toURI().toString();
        Desktop.getDesktop().open(file);
       
       
    }
}

