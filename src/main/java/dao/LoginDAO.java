/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Account;

/**
 *
 * @author tuana
 */
public class LoginDAO extends DAO{
    public Account login(String username, String password){
        String query = "select * from Account\n" +
                        "where username = ?\n" +
                        "and password = ?";
        try {
            Connection connect = new DAO().getConnection();
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getBoolean(6),
                        rs.getString(7));
            }
            return null; 
        } catch (Exception e) {
        }
        return null;
    }
    
    public Account CheckAccount(String username){
        String query = "select * from Account\n" +
                        "where username = ?\n";
        try {
            Connection connect = new DAO().getConnection();
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, username);           
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getBoolean(6),
                        rs.getString(7));
                
            }
            return null; 
        } catch (Exception e) {
        }
        return null;
    }
        public void signup(String username, String password){
        String query = "insert into Account \n" +
                       "values (?,?,?,?,?,?)";
            try {
                Connection connect = new DAO().getConnection();
                PreparedStatement ps = connect.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);           
                ps.executeUpdate();
            } catch (Exception e) {
            }
    
    }
}
