/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.BE;

import java.time.LocalDate;
import java.util.Date;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 *
 * @author ZeXVex
 */
public class Movies {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final FloatProperty rating = new SimpleFloatProperty();
    private final StringProperty fileLink = new SimpleStringProperty();
    private final ObjectProperty<Date> LocalDate = new SimpleObjectProperty<>();
    

    public Date getLocalDate() {
        return LocalDate.get();
    }

    public void setLocalDate(Date value) {
        LocalDate.set(value);
    }

    public ObjectProperty LocalDateProperty() {
        return LocalDate;
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
    

    public Float getRating() {
        return rating.get();
    }

    public void setRating(Float value) {
        rating.set(value);
    }

    public FloatProperty ratingProperty() {
        return rating;
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
    

    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    @Override
    public String toString() {
        return "Movies{" + "id=" + id + ", name=" + name + ", rating=" + rating + ", fileLink=" + fileLink + ", lastview=" + LocalDate + '}';
    }
    
    
}
