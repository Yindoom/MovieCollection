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
<<<<<<< HEAD
    
    private ObservableList<Movies> Movies = FXCollections.observableArrayList(new ArrayList<>());
    
    dalManager DAL = new dalManager();
    
    public List<Movies> getAllMovies(){
        return DAL.getAllMovies();
    }

=======
dalManager DAL = new dalManager();
>>>>>>> 3f6716eaf4a3cd4bd71191dc3d13252e6a1615e3
    public static void remove(Movies selectedMovies) {
       return DAL.remove(selectedMovies);
    }    
    public static void getAllMovies (dalManager getAllMovies) {
       return DAL.getAllMovies();
    }
}

