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
public class AdminProductDAO extends DAO {

    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM Product";
    private static final String SELECT_ALL_CATEGORYS = "SELECT * FROM Category";

    private static final String INSERT_PRODUCT = "INSERT INTO [dbo].[Product] ([name], [price], [quantity], [categoryid], [imageurl]) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE Product "
            + "SET name = ?, price = ?, quantity = ?, categoryid = ?, imageurl = ? "
            + "WHERE id = ?";
    private static final String ALL_PRODUCTS = "Select * from Product where id = ?";
    private static final String DELETE_PRODUCT = "delete from Product" + " where id = ?";

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

    public void insertProduct(Product P) {

        try ( Connection connection = getConnection();  PreparedStatement prepareStatement = connection.prepareStatement(INSERT_PRODUCT);) {
            System.out.println(prepareStatement);

            prepareStatement.setString(1, P.getName());
            prepareStatement.setDouble(2, P.getPrice());
            prepareStatement.setInt(3, P.getQuantity());
            prepareStatement.setInt(4, P.getCategoryid());
            prepareStatement.setString(5, P.getImageUrl());
            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public Product getProductbyid(String id) {

        try ( Connection connection = getConnection();  PreparedStatement prepareStatement = connection.prepareStatement(ALL_PRODUCTS);) {

            System.out.println(prepareStatement);
            prepareStatement.setString(1, id);
            ResultSet rs = prepareStatement.executeQuery();

            while (rs.next()) {
                Product P = new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getInt("categoryid"),
                        rs.getString("imageUrl"));
                return P;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateProduct(Product P) {

        try ( Connection connection = getConnection();  PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_PRODUCT);) {
            System.out.println(prepareStatement);

            prepareStatement.setString(1, P.getName());
            prepareStatement.setDouble(2, P.getPrice());
            prepareStatement.setInt(3, P.getQuantity());
            prepareStatement.setInt(4, P.getCategoryid());
            prepareStatement.setString(5, P.getImageUrl());
            prepareStatement.setInt(6, P.getId());  // Set the product ID in the WHERE clause
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void deleteProduct(int id) {

        try ( Connection connection = getConnection();  PreparedStatement prepareStatement = connection.prepareStatement(DELETE_PRODUCT);) {
            System.out.println(prepareStatement);

            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public List<Product> searchProductsByName(String searchName) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE name LIKE ?";
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + searchName + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"),
                        rs.getInt("quantity"), rs.getInt("categoryid"), rs.getString("imageUrl")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> selectProductsByCategory(String categoryId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE categoryid = ?";
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, categoryId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"),
                        rs.getInt("quantity"), rs.getInt("categoryid"), rs.getString("imageUrl")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

}
