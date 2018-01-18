/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Yindo
 */
public class Category {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final ListProperty<Movies> ListMovies = new SimpleListProperty<>();
    private final StringProperty name = new SimpleStringProperty();

    public Category() {
        ListMovies.set(FXCollections.observableArrayList());
    }
    
    public void addMovie(Movies movie) {
        ListMovies.add(movie);
    }
    
    public ObservableList getListMovies() {
        return ListMovies.get();
    }

    public void setListMovies(ObservableList value) {
        ListMovies.set(value);
    }

    public ListProperty ListMoviesProperty() {
        return ListMovies;
    }
    

    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
    
    
}
