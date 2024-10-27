/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author admin
 */
public class HomeDAO extends DAO {

    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM Product";
    private static final String SELECT_ALL_CATEGORYS = "SELECT * FROM Category";
    private static final String SELECT_ALL_PRODUCTS_BY_NAME = "SELECT * FROM Product WHERE name LIKE ?";
    private static final String SELECT_ALL_PRODUCTS_BY_CATEGORYID = "SELECT * FROM Product WHERE categoryid = ?";

    public List<Product> selectAllProducts() {
        List<Product> products = new ArrayList<>();
        try ( Connection connection = getConnection();  PreparedStatement prepareStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
            System.out.println(prepareStatement);
            ResultSet rs = prepareStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int categoryid = rs.getInt("categoryid");
                String imageUrl = rs.getString("imageUrl");
                products.add(new Product(id, name, price, quantity, categoryid, imageUrl));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return products;
    }

    public List<Category> selectAllCategorys() {
        List<Category> categorys = new ArrayList<>();
        try ( Connection connection = getConnection();  PreparedStatement prepareStatement = connection.prepareStatement(SELECT_ALL_CATEGORYS);) {
            System.out.println(prepareStatement);
            ResultSet rs = prepareStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                categorys.add(new Category(id, name));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return categorys;
    }

    public List<Product> selectProductByName(String nameInput) {
        List<Product> products = new ArrayList<>();

        // Step 1: Establishing a Connection
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS_BY_NAME);) {
            preparedStatement.setString(1, "%" + nameInput + "%");
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int categoryid = rs.getInt("categoryid");
                String imageUrl = rs.getString("imageUrl");
                products.add(new Product(id, name, price, quantity, categoryid, imageUrl));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return products;
    }

    public List<Product> selectProductByCategoryID(int idInput) {
        List<Product> products = new ArrayList<>();

        // Step 1: Establishing a Connection
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS_BY_CATEGORYID);) {
            preparedStatement.setInt(1, idInput);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int categoryid = rs.getInt("categoryid");
                String imageUrl = rs.getString("imageUrl");
                products.add(new Product(id, name, price, quantity, categoryid, imageUrl));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return products;
    }
    
//    public static void main(String[] args) {
//        HomeDAO dao = new HomeDAO();
//        List<Product> products = dao.selectAllProducts();
//        for(Product p : products) {
//            System.out.println(p);
//        }
//    }
}
