/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Account;

/**
 *
 * @author tuana
 */
public class AccountDAO extends DAO{
      public void AccountDAO(Account c) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [password] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[phone_number] = ?\n"
                + " WHERE [username] = ?";
        try ( Connection connection = new DAO().getConnection();
                PreparedStatement prepareStatement = connection.prepareStatement(sql);) {
            //PreparedStatement st =connection.prepareStatement(sql);
       
            prepareStatement.setString(1, c.getPassword());
            prepareStatement.setString(2, c.getEmail());
            prepareStatement.setString(3, c.getPhone_number());
            prepareStatement.setString(4, c.getUsername());
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
 
}