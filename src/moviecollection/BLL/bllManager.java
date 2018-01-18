/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.BLL;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.Desktop;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import moviecollection.BE.CatMovie;
import moviecollection.BE.Category;
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
    
    String filepath;
    Movies movie;
    Media media;
    dalManager DAL = new dalManager();
    
    public List<Movies> getAllMovies(){  //returns the list of all movies
        
        return DAL.getAllMovies();
    }
    
    
    public List<Category> getAllCategories() { //returns the list of all categories
        return DAL.getAllCategory();
    }
    
    public void remove(Movies selectedMovie) { //removes one selectedmovie from database
       DAL.removeMovie(selectedMovie);
    }
    
    public void remove(Category selectedCategory) { //removes one selectedcategoryfrom database
        DAL.removeCategory(selectedCategory);
    }
    
    public Date setDate()   {
        LocalDate date = LocalDate.now(); //saves local date 
        Date datedate = Date.valueOf(date); // save value of local date
        java.sql.Date sDate = convertUtilToSql(datedate); //method used to convert it to a sql data type
        return sDate;
    }

    public void add(Movies movie) { //add one movie to database
        DAL.addMovies(movie);
    }

    public void add(Category category) { // add one category to database
        DAL.addCategory(category);
    }

    
   
    public void update(Movies movie) { //updates a movie from database 
        DAL.updateMovie(movie);
    }

    private Date convertUtilToSql(Date datedate) { 
        java.sql.Date sDate = new java.sql.Date(datedate.getTime()); // code for converting the date value to a sql data type that we can save 
        return sDate;
    }

    public int checkDate(Date date) { // bastian was the genius behind all of the date code. shout out to him! 
            Calendar file = getCalendar(date); // saves a calendar we want to compare to current time to "file"
            Calendar now = getCalendar(Date.valueOf(LocalDate.now())); 
            int diff = now.get(1)-file.get(1); // [1] is the year from the calendar class
            if (file.get(2) > now.get(2) ||  // [2] is the month 
            file.get(2) == now.get(2) && file.get(5) > now.get(5)) { // [5] is the day 
            diff--; // anything < than 1 
            }
            return diff;
    }

    public static Calendar getCalendar(Date date) {
    Calendar cal = Calendar.getInstance(Locale.US);
    cal.setTime(date);
    return cal;
    }
    
    public void playMovie(Movies selectedMovie) throws IOException {
        movie = selectedMovie; 
        filepath = movie.getFileLink(); 
        File file = new File(filepath); 
        filepath = file.toURI().toString(); //needs to be an URI to function
        Desktop.getDesktop().open(file);
       
       
    }
    
    public void add(CatMovie catmovie) { //adds one catMovie to database with the id of category and id of movie 
        DAL.addCatMovie(catmovie);
    }

    public List<CatMovie> getAllCatMovies() { //returns all catMovies from database
        return DAL.getAllCatMovies();
    }

    public void removeAllCats(Movies movie) throws SQLException { //delete all cats 
        DAL.removeAllCats(movie);
    }
    
}        