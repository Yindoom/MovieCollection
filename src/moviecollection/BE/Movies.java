/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 *
 * @author ZeXVex
 */
public class Movies {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty rating = new SimpleStringProperty();
    private final StringProperty fileLink = new SimpleStringProperty();
    private final StringProperty lastview = new SimpleStringProperty();
    
    public String getLastview() {
        return lastview.get();
    }

    public void setLastview(String value) {
        lastview.set(value);
    }

    public StringProperty lastviewProperty() {
        return lastview;
    }
    

    public String getFileLink() {
        return fileLink.get();
    }

    public void setFileLink(String value) {
        fileLink.set(value);
    }

    public StringProperty fileLinkProperty() {
        return fileLink;
    }
    

    public String getRating() {
        return rating.get();
    }

    public void setRating(String value) {
        rating.set(value);
    }

    public StringProperty ratingProperty() {
        return rating;
    }
    
    

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String value) {
        title.set(value);
    }

    public StringProperty titleProperty() {
        return title;
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
    
    
}
