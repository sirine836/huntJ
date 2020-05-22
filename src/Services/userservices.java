/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import interfaces.IUsers;
import Entities.User;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author thepoet
 */
public class userservices implements IUsers{
    

    Connection connection=DataBase.getInstance().getCnx();

    public userservices() {
        
    }
    
    
    @Override
    public void add(User u) {
      String req = "INSERT INTO fos_user (username,username_canonical,email,email_canonical,enabled,password,roles) values (?,?,?,?,?,?,?)"; 
       PreparedStatement preparedStatement;
       u.setRoles("Member");
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, u.getNom());
            preparedStatement.setString(2, u.getNom());
            preparedStatement.setString(3, u.getEmail());
            preparedStatement.setString(4, u.getEmail());
            preparedStatement.setString(5, "1");
            preparedStatement.setString(6, u.getPassword());
            preparedStatement.setString(7, u.getRoles());
            
           
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(userservices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User u) {
        String req = "UPDATE fos_user set username=?,email=?,password=? WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, u.getNom());
            preparedStatement.setString(2, u.getEmail());
            preparedStatement.setString(3, u.getPassword());
            
            preparedStatement.setInt(4, u.getId());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(userservices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(User u) {
        String req = "DELETE FROM fos_user WHERE email =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1,u.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(userservices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public User findById(Integer user_id) {
           User u = null;
        String req = "SELECT * FROM fos_user WHERE id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               u = new User(resultSet.getInt("id"), resultSet.getString("username")
                         ,resultSet.getString("email")
                        ,resultSet.getString("password"),resultSet.getString("roles"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(userservices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    @Override
    public User findByMail(String email) {
        User u = null;
        String req = "SELECT * FROM fos_user WHERE email =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                u = new User(resultSet.getInt("id"), resultSet.getString("username")
                        ,resultSet.getString("email")
                       ,resultSet.getString("password"),resultSet.getString("roles"),resultSet.getInt("enabled"));
 }
        } catch (SQLException ex) {
            Logger.getLogger(userservices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
   
    
    @Override
    public List<User> getAll() {
        List<User> usersList = new ArrayList<>();
        String req = "SELECT * FROM fos_user";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
             User   u = new User(resultSet.getInt("id"), resultSet.getString("username")
                       ,resultSet.getString("email")
                       ,resultSet.getString("password"),resultSet.getString("roles"));
   usersList.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(userservices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usersList;
    }
    
    public int nbusers() {
       int nb=0;
       String req = "SELECT COUNT(id) from fos_user";
       PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            nb=resultSet.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(userservices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  nb;
    }
 
     public List<User> getLogin(String lastlogin) {
        List<User> usersList = new ArrayList<>();
        String req = "SELECT * FROM fos_user where username =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, lastlogin);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
           User     u = new User(resultSet.getInt("id"), resultSet.getString("username")
                       ,resultSet.getString("email")
                       ,resultSet.getString("password"),resultSet.getString("roles"));
    usersList.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(userservices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usersList;
    }
     
     public List<User> getEmail(String email) {
        List<User> usersList = new ArrayList<>();
        String req = "SELECT * FROM fos_user where email = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
              User  u = new User(resultSet.getInt("id"), resultSet.getString("username")
                       ,resultSet.getString("email")
                       ,resultSet.getString("password"),resultSet.getString("roles"));
      usersList.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(userservices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usersList;
    }
}
