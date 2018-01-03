/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import moviecollection.BE.Movies;
import moviecollection.BLL.bllManager;

/**
 *
 * @author Yindo
 */
public class model {
    
    
    private final ObservableList<Movies> mList
            = FXCollections.observableArrayList(bllManager.getAllMovies());
    
    
    public ObservableList<Movies> getmovieList()
    {
        return mList;
    }
    
    
    public void removeMovie(Movies selectedMovie) { //deletes PlayList from the PlayList list and DB
        mList.removeMovie(selectedMovie); 
        bllManager.removeMovie(selectedMovie);
        
    }
    
}
