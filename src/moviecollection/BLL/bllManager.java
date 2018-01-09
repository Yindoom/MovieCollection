/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.BLL;

import moviecollection.BE.Movies;
import moviecollection.DAL.dalManager;

/**
 *
 * @author ZeXVex
 */
public class bllManager {
dalManager DAL = new dalManager();
    public static void remove(Movies selectedMovies) {
       return DAL.remove(selectedMovies);
    }    
    public static void getAllMovies (dalManager getAllMovies) {
       return DAL.getAllMovies();
    }
}

