/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.BLL;

import moviecollection.BE.Movies;

/**
 *
 * @author ZeXVex
 */
public class bllManager {

    public static void remove(Movies selectedMovies) {
        DAL.remove(selectedMovies);
    }    
}
