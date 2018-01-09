/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Yindo
 */
public class CatMovie {

    private final IntegerProperty movieId = new SimpleIntegerProperty();
    private final IntegerProperty categoryId = new SimpleIntegerProperty();

    public int getCategoryId() {
        return categoryId.get();
    }

    public void setCategoryId(int value) {
        categoryId.set(value);
    }

    public IntegerProperty categoryIdProperty() {
        return categoryId;
    }
    

    public int getMovieId() {
        return movieId.get();
    }

    public void setMovieId(int value) {
        movieId.set(value);
    }

    public IntegerProperty movieIdProperty() {
        return movieId;
    }
    
}
