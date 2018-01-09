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
     public void addCategory(Category category) throws SQLServerException, SQLException { //Add playlist from the programe to the database
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
                throw new SQLException("PlayList could not be added");

            // Get database generated id
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                category.setId(rs.getInt(1));
            }
        }
     }
}

