/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;

/**
 *
 * @author admin
 */
public class AdminAccountDAO extends DAO {

    private static final String SELECT_ALL_ACCOUNTS = "SELECT * FROM Account";
    private static final String DELETE_ACCOUNT = "DELETE FROM Account WHERE ID = ?";
    private static final String ADD_ACCOUNT = "INSERT INTO Account (username, password, email, phone_number, isadmin)\n"
            + "VALUES\n"
            + "(?, ?, ?, ?, ?);";

    public List<Account> selectAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try ( Connection connection = getConnection();  PreparedStatement prepareStatement = connection.prepareStatement(SELECT_ALL_ACCOUNTS);) {
            System.out.println(prepareStatement);
            ResultSet rs = prepareStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Date creat_at = rs.getDate("created_at");
                Boolean isAdmin = rs.getBoolean("isadmin");
                String phone_number = rs.getString("phone_number");
                accounts.add(new Account(id, username, password, email, creat_at, isAdmin, phone_number));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return accounts;
    }

    public boolean deleteAccount(int id) throws SQLException {
        boolean rowDeleted;
        try ( Connection connection = getConnection();  PreparedStatement statement = connection.prepareStatement(DELETE_ACCOUNT);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public void addAccount(String username, String password, String email, String phone_number, int isAdmin) throws SQLException {
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(ADD_ACCOUNT)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone_number);
            preparedStatement.setInt(5, isAdmin);

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;  // Ném ngoại lệ ra để servlet xử lý
        }
    }

    // Phương thức để lấy tài khoản theo ID
    public Account selectAccountById(int idInput) throws SQLException {
        Account account = null;
        String sql = "SELECT * FROM account WHERE id = ?";
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idInput);
            try ( ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    String phoneNumber = rs.getString("phone_number"); // Chú ý tên trường trong SQL
                    Boolean isAdmin = rs.getBoolean("isAdmin"); // Chú ý tên trường trong SQL

                    account = new Account(id, username, password, email, phoneNumber, isAdmin);
                }
            }
        } catch (SQLException e) {
            // Log lỗi nếu có
            e.printStackTrace();
            throw e; // Ném lại ngoại lệ để xử lý ở cấp cao hơn nếu cần
        }
        return account; // Trả về account hoặc null
    }

    // Phương thức để cập nhật tài khoản
    public void updateAccount(int id, String username, String password, String email, String phone_number, int isAdmin) throws SQLException {
        String sql = "UPDATE account SET username = ?, password = ?, email = ?, phone_number = ?, isAdmin = ? WHERE id = ?";
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone_number); // Chú ý tên biến
            preparedStatement.setInt(5, isAdmin); // Sử dụng boolean thay vì int
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Log lỗi nếu có
            e.printStackTrace();
            throw e; // Ném lại ngoại lệ để xử lý ở cấp cao hơn nếu cần
        }
    }

    public List<Account> selectAccountByName(String usernameInput) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM Account WHERE username LIKE ?";
        try ( Connection connection = getConnection();  PreparedStatement prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setString(1, "%" + usernameInput + "%"); // Thêm ký tự % vào chuỗi tìm kiếm
            ResultSet rs = prepareStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Date creat_at = rs.getDate("created_at");
                Boolean isAdmin = rs.getBoolean("isadmin");
                String phone_number = rs.getString("phone_number");
                accounts.add(new Account(id, username, password, email, creat_at, isAdmin, phone_number));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return accounts;
    }
}
