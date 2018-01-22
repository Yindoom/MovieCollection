/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui;

import java.io.IOException;
import java.sql.SQLException;
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
    private final ArrayList<Movies> tempList = new ArrayList<>(); // used for the search method, didnt want to mess with the original list 
    private final ObservableList<Movies> dList  // adds movies that have been added for 2 years or more and adds movie with rating of 6 and below  
            = FXCollections.observableArrayList(new ArrayList<>()); // is displayed at the start of the program if there are any
    
    int x = 0; // is used for the switch on the search() //better if it was a boolean.....
    int y = 0; // one method in setAllCatMovies() is used and when we use the RefreshMlist() that method is not called again //could also be a boolean....
    
    public ObservableList<Movies> refreshList() {
        mList.setAll(bll.getAllMovies());
        return mList;
    }
    
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
        for (Movies mov : mList) { //remove from every list from Movies.java containing that category
            if(mov.getCategories().contains(selectedCategory))
                mov.removeCat(selectedCategory);
        }
        refreshMList();
    }

    public Date getDate() { // returns date 
        return bll.setDate();
    }

    public void add(Movies movie) {  
        for (Movies movies : mList) {
            if(movies.getFileLink().equalsIgnoreCase(movie.getFileLink()))
            return;
        }
        mList.add(movie);
        bll.add(movie);
        tempList.add(movie);
    }

    public void add(Category category) {
        for (Category category1 : cList) {
            if(category1.getName().equalsIgnoreCase(category.getName()))
                return;
        }
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
        refreshMList();
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

    public void setAllCatMovies(){ //my triple for-loop!!! it was used again!
        
        for (CatMovie catMovie : bll.getAllCatMovies()) {
            int catID = catMovie.getCategoryId();
            int movID = catMovie.getMovieId();
            
            for (Movies  movie : mList) {
                
                    for (Category category : cList) {
                        
                        if (catID == category.getId() && movID == movie.getId()) {
                            movie.add(category);
                            if(y == 0)
                            category.addMovie(movie); // dont want to used this method when we call refreshMlist() //could have been overwriten
                        }                            
                    }
                }
            }                             // ^
        }                                 // |
                                          // |
    public void refreshMList()  {         // |
        mList.removeAll();                // |
        mList.setAll(bll.getAllMovies()); // |
        y = 1; // this is how i got around this
        setAllCatMovies();
        y = 0;
    }

    void removeAllCats(Movies movie) throws SQLException {
        bll.removeAllCats(movie);
    }
}
