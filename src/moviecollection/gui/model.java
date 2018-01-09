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
    bllManager bll = new bllManager();
    
    
    private final ObservableList<Movies> mList
            = FXCollections.observableArrayList(bll.getAllMovies());
    
    
    public ObservableList<Movies> getmovieList()
    {
        return mList;
    }
    
    
    public void remove(Movies selectedMovie) { //deletes PlayList from the PlayList list and DB
        mList.remove(selectedMovie); 
        bll.remove(selectedMovie);
        
    }
    
}
