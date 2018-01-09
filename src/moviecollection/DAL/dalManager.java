/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moviecollection.BE.CatMovie;
import moviecollection.BE.Category;
import moviecollection.BE.Movies;

/**
 *
 * @author ZeXVex
 */
public class dalManager {
    
    private ConnectionManager cm = new ConnectionManager();
    
    public List<Movies> getAllMovies() {
        List<Movies> allMovies
                = new ArrayList();

        try (Connection con = cm.getConnection()) {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM Movie");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Movies movie = new Movies();
                movie.setId(rs.getInt("id"));
                movie.setName(rs.getString("name"));
                movie.setRating(rs.getString("rating"));
                movie.setFileLink(rs.getString("filelink"));
                movie.setLastview(rs.getString("lastview"));

                allMovies.add(movie);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(dalManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allMovies;
    }
    
    public void addMovies(Movies movie) {
        try (Connection con = cm.getConnection()) {
            String sql
                    = "INSERT INTO Movie"
                    + "(name, rating, filelink, lastview) "
                    + "VALUES(?,?,?,?)";
            PreparedStatement pstmt
                    = con.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, movie.getName());
            pstmt.setString(2, movie.getRating());
            pstmt.setString(3, movie.getFileLink());
            pstmt.setString(4, movie.getLastview());

            int affected = pstmt.executeUpdate();
            if (affected<1)
                throw new SQLException("movie could not be added");

            // Get database generated id
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                movie.setId(rs.getInt(1));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(dalManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    
    public List<Category> getAllCategory() {
        List<Category> allCategories
                = new ArrayList();

        try (Connection con = cm.getConnection()) {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM Category");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));

                allCategories.add(category);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(dalManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allCategories;
    }
<<<<<<< HEAD
    
     public void addCategory(Category category) throws SQLServerException, SQLException { 
=======
     public void addCategory(Category category) throws SQLException, SQLException { //Add playlist from the programe to the database
>>>>>>> 8cc808495942c201b3d9dcc319d332db0c057a1e
        try (Connection con = cm.getConnection()) {
            String sql
                    = "INSERT INTO Category"
                    + "(name) "
                    + "VALUES(?)";
            PreparedStatement pstmt
                    = con.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, category.getName());

            int affected = pstmt.executeUpdate();
            if (affected<1)
                throw new SQLException("Category could not be added");

            // Get database generated id
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                category.setId(rs.getInt(1));
            }
        }
<<<<<<< HEAD
    }
     
     public List<CatMovie> getAllCatMovies() {
        List<CatMovie> allCatMovies
                = new ArrayList();

        try (Connection con = cm.getConnection()) {
            PreparedStatement stmt
                    = con.prepareStatement("SELECT * FROM Movie");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CatMovie catMovie = new CatMovie();
                catMovie.setMovieId(rs.getInt("MovieId"));
                catMovie.setCategoryId(rs.getInt("CategoryId"));

                allCatMovies.add(catMovie);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(dalManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return allCatMovies;
    }
     
     public void addCatMovie(Movies movie, Category category) {
        try (Connection con = cm.getConnection()) {
            String sql
                    = "INSERT INTO songsInPlayList"
                    + "(MovieId, CategoryId) "
                    + "VALUES(?,?)";
            PreparedStatement pstmt
                    = con.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, movie.getId());
            pstmt.setInt(2, category.getId());

            int affected = pstmt.executeUpdate();
            
            if (affected<1)
                throw new SQLException("category couldnt be added to movie");

        }
        catch (SQLException ex) {
            Logger.getLogger(dalManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
=======
     }
     
>>>>>>> 8cc808495942c201b3d9dcc319d332db0c057a1e
}

