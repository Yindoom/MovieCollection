/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui;

import com.sun.deploy.util.StringUtils;
import java.awt.Desktop;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import moviecollection.BE.CatMovie;
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
    private final ObservableList<Category> cList
            = FXCollections.observableArrayList(bll.getAllCategories());
    private final ArrayList<Movies> tempList = new ArrayList<>();
    private final ObservableList<Movies> dList
            = FXCollections.observableArrayList(new ArrayList<>());
    
    int x = 0; // is used for the switch on the search()
    
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
        for (Movies mov : mList) {
            if(mov.getCategories().contains(selectedCategory))
                mov.removeCat(selectedCategory);
        }
        refreshMList();
    }

    public Date getDate() {
        return bll.setDate();
    }

    public void add(Movies movie) {
        mList.add(movie);
        bll.add(movie);
        tempList.add(movie);
    }

    public void add(Category category) {
        cList.add(category);
        bll.add(category);
    }
    
    public void loadAll() {
        mList.clear();
        mList.addAll(bll.getAllMovies());
    }
    
    public void update(Movies movie) {
        bll.update(movie);
        refreshMList();
    }
    
    public List<Category> getCategories() {
        return bll.getAllCategories();
    }

    public void search(String search) { //searches for a song in the mlist has a variable equal to the search term
        
        ArrayList<Movies> tempList2 = new ArrayList();
        switch (x){
            case 0 :
                tempList.clear();
                tempList.addAll(mList);
                for (Movies movies : mList) {
                    if(isNumeric(search) == false)  {
                    if(movies.getName().toLowerCase().contains(search.toLowerCase())){
                    tempList2.add(movies);
                    }
                }
                    else {
                        if(movies.getRating() >= Float.parseFloat(search))
                            tempList2.add(movies);
                        }
                    
                        
            }
            mList.clear();
            mList.addAll(tempList2);
            x = 1;
            break;
            
            case 1 :
            mList.clear();
            mList.setAll(tempList);
            x= 0;
            break;
        }
    }
    
    public static boolean isNumeric(String search)  {  
    try  
    {  
        float help = Float.parseFloat(search);  
    }  
    catch(NumberFormatException nfe)  
    {  
        return false;  
    }  
    return true;  
    }

    public void addCat(CatMovie catmovie) {
        bll.add(catmovie);
    }
    
    public void checkDelete() {
        for (Movies movies : mList) {
        Date date = movies.getLocalDate();
        int checkNumber = bll.checkDate((java.sql.Date) date);
        if(checkNumber >= 2 || movies.getRating()<=6)
                dList.add(movies);
        }
    }
    
    public ObservableList<Movies> getDeleteMovies() {
        return dList;
    }

    public void playMovie(Movies selectedMovie) throws IOException {
        bll.playMovie(selectedMovie);
    }
    
    public Category SearchCategory(String string){
        for (Category cats : cList) {
            if (cats.getName().equalsIgnoreCase(string))
            {
                return cats;
            }
        }
        return null;
    }

    public void setAllCatMovies(){
        
        for (CatMovie catMovie : bll.getAllCatMovies()) {
            int catID = catMovie.getCategoryId();
            int movID = catMovie.getMovieId();
            
            for (Movies  movie : mList) {
                
                    for (Category category : cList) {
                        
                        if (catID == category.getId() && movID == movie.getId())
                            movie.add(category);
                    }
                }
            }
        }
    
    public void refreshMList()  {
        mList.clear();
        mList.addAll(bll.getAllMovies());
        setAllCatMovies();
    }
}
