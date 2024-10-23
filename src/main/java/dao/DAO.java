/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Category;
import model.Product;

/**
 *
 * @author admin
 */
public class DAO {

    String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=AnVatDB;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";
    String user = "sa";
    String pass = "123456";

    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM Product";
    private static final String SELECT_ALL_CATEGORYS = "SELECT * FROM Category";
    private static final String SELECT_ALL_PRODUCTS_BY_NAME = "SELECT * FROM Product WHERE name LIKE ?";
    private static final String SELECT_ALL_PRODUCTS_BY_CATEGORYID = "SELECT * FROM Product WHERE categoryid = ?";
    private static final String SELECT_ALL_ACCOUNTS = "SELECT id, username, email, isAdmin FROM Account";

    public DAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionURL, user, pass);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

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

    public List<Account> selectAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try ( Connection connection = getConnection();  PreparedStatement prepareStatement = connection.prepareStatement(SELECT_ALL_ACCOUNTS);) {
            System.out.println(prepareStatement);
            ResultSet rs = prepareStatement.executeQuery();

            while (rs.next()) {
                accounts.add(new Account(
                        rs.getInt("id"), // Lấy cột "id"
                        rs.getString("username"), // Lấy cột "username"
                        rs.getString("email"), // Lấy cột "email"
                        rs.getBoolean("isAdmin") // Lấy cột "isAdmin"
                ));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return accounts;
    }

    public static void main(String[] args) throws SQLException {
        DAO dao = new DAO();
        List<Account> categorys = dao.selectAllAccounts();
        for (Object p : categorys) {
            System.out.println(p);
        }
    }

}
