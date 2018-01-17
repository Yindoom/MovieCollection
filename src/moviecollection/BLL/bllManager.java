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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
//    private ObservableList<Movies> Movies = FXCollections.observableArrayList(new ArrayList<>());
//    private ObservableList<Category> Categories = FXCollections.observableArrayList(new ArrayList<>());
    
    String filepath;
    Movies movie;
    Media media;
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
    
    public Date setDate()   {
        LocalDate date = LocalDate.now();
        Date datedate = Date.valueOf(date);
        java.sql.Date sDate = convertUtilToSql(datedate);
        return sDate;
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

    private Date convertUtilToSql(Date datedate) {
        java.sql.Date sDate = new java.sql.Date(datedate.getTime());
        return sDate;
    }

    public int checkDate(Date date) {
            Calendar file = getCalendar(date);
            Calendar now = getCalendar(Date.valueOf(LocalDate.now()));
            int diff = now.get(1)-file.get(1);
            if (file.get(2) > now.get(2) || 
            file.get(2) == now.get(2) && file.get(5) > now.get(5)) {
            diff--;
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
        filepath = file.toURI().toString();
        Desktop.getDesktop().open(file);
       
       
    }
}

