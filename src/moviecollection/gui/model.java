/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import moviecollection.BE.Category;
import moviecollection.BE.Movies;
import moviecollection.BLL.bllManager;

/**
 *
 * @author Yindo
 */
public class model {
    bllManager bll = new bllManager();
    
    LocalDate date = LocalDate.now();
    
    private final ObservableList<Movies> mList
            = FXCollections.observableArrayList(bll.getAllMovies());
    public final ObservableList<Category> cList
            = FXCollections.observableArrayList(bll.getAllCategories());
    
    
    public ObservableList<Movies> getmovieList()
    {
        return mList;
    }
    
    public ObservableList<Category> getCategoryList()
    {
        return cList;
    }
    
    public void remove(Movies selectedMovie) { //deletes PlayList from the PlayList list and DB
        mList.remove(selectedMovie); 
        bll.remove(selectedMovie);        
    }
    
    public void remove(Category selectedCategory) {
        cList.remove(selectedCategory);
        bll.remove(selectedCategory);
    }

    public Date getDate() {
        return bll.setDate();
    }

    public void add(Movies movie) {
        mList.add(movie);
        bll.add(movie);
    }

    public void add(Category category) {
        cList.add(category);
        bll.add(category);
    }
    
    void loadAll() {
        mList.clear();
        mList.addAll(bll.getAllMovies());
    }
    
    void update(Movies movie) {
        bll.update(movie);
        mList.clear();
        mList.addAll(bll.getAllMovies());
    }
    
    public List<Category> getCategories() {
        return bll.getAllCategories();
    }

    void checkDate() {
        for (Movies movies : mList) {
        Date date = movies.getLocalDate();
        int checkNumber = bll.checkDate((java.sql.Date) date);
        if(checkNumber >= 2)
                System.out.println(movies);
        }
    }
    
}
