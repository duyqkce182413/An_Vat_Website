/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.productController;

import dao.AdminAccountDAO;
import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author admin
 */
public class adminAccountCRUDController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet adminAccountCRUDController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adminAccountCRUDController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/admin-add-account":
                    addAccount(request, response);
                    break;
                case "/admin-delete-account":
                    deleteAccount(request, response);
                    break;
                case "/admin-edit-account": // Thêm trường hợp cho edit
                    editAccount(request, response);
                    break;
                case "/admin-search-account":
                    getAccountByName(request, response);
                    break;
                default:
                    getAllAccounts(request, response);
                    break;
            }
        } catch (ServletException | IOException e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/admin-update-account": // Thêm trường hợp cho update
                updateAccount(request, response);
                break;
            default:
                doGet(request, response);
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    protected void getAllAccounts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminAccountDAO dao = new AdminAccountDAO();
        List<Account> accounts = dao.selectAllAccounts();
        request.setAttribute("accounts", accounts);
        request.getRequestDispatcher("admin_account_crud.jsp").forward(request, response);
    }

    protected void deleteAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idString = request.getParameter("id");
            AdminAccountDAO dao = new AdminAccountDAO();
            int id = Integer.parseInt(idString);
            dao.deleteAccount(id);
            response.sendRedirect("admin-account-crud");
        } catch (SQLException ex) {
            Logger.getLogger(adminAccountCRUDController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void addAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean hasError = false;  // Cờ để theo dõi lỗi

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone_number = request.getParameter("phone_number");
            String isAdminStr = request.getParameter("isAdmin");

            int isAdmin = Integer.parseInt(isAdminStr);
            AdminAccountDAO dao = new AdminAccountDAO();

            // Gọi phương thức để thêm tài khoản vào cơ sở dữ liệu
            dao.addAccount(username, password, email, phone_number, isAdmin);

            // Nếu thành công và không có lỗi
            request.setAttribute("message", "Account added successfully.");
        } catch (Exception e) {
            hasError = true;  // Đặt cờ báo lỗi
            String errorMessage = e.getMessage();

            // Kiểm tra lỗi trùng lặp email
            if (errorMessage.contains("Violation of UNIQUE KEY constraint") && errorMessage.contains("Account__AB6E6164")) {
                request.setAttribute("error", "Account creation failed: Email already exists. Please use a unique email.");
            } // Kiểm tra lỗi trùng lặp cho các trường hợp khác (username, số điện thoại)
            else if (errorMessage.contains("Violation of UNIQUE KEY constraint")) {
                request.setAttribute("error", "Account creation failed: Duplicate entry detected. Please use unique values.");
            } else {
                request.setAttribute("error", "Account creation failed: " + errorMessage);
            }
        }

        // Chuyển tiếp với thông báo phù hợp
        if (hasError) {
            request.removeAttribute("message");  // Đảm bảo xóa thông báo thành công nếu có lỗi
        }
        request.getRequestDispatcher("admin_add_account.jsp").forward(request, response);
    }

    protected void editAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminAccountDAO dao = new AdminAccountDAO();
        try {
            String idString = request.getParameter("id");
            int id = Integer.parseInt(idString);

            // Lấy thông tin tài khoản từ cơ sở dữ liệu
            Account account = dao.selectAccountById(id);
            request.setAttribute("account", account);

            // Chuyển tiếp tới trang chỉnh sửa tài khoản
            request.getRequestDispatcher("admin_edit_account.jsp").forward(request, response);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    protected void updateAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean hasError = false;  // Cờ để theo dõi lỗi
        Account account = new Account(); // Tạo một đối tượng Account mới để lưu thông tin

        try {
            String idString = request.getParameter("id");
            int id = Integer.parseInt(idString);
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone_number = request.getParameter("phone_number");
            String isAdminStr = request.getParameter("isAdmin");
            int isAdmin = Integer.parseInt(isAdminStr);

            AdminAccountDAO dao = new AdminAccountDAO();

            // Gọi phương thức để cập nhật tài khoản vào cơ sở dữ liệu
            dao.updateAccount(id, username, password, email, phone_number, isAdmin);

            // Cập nhật thông tin tài khoản vào đối tượng
            account.setId(id);
            account.setUsername(username);
            account.setPassword(password);
            account.setEmail(email);
            account.setPhone_number(phone_number);
            if(isAdmin == 1) {
                account.setAdmin(true);
            } else {
                account.setAdmin(false);
            }

            // Nếu thành công và không có lỗi
            request.setAttribute("message", "Account updated successfully.");
        } catch (Exception e) {
            hasError = true;  // Đặt cờ báo lỗi
            request.setAttribute("error", "Account update failed: " + e.getMessage());
        }

        // Chuyển tiếp với thông tin tài khoản
        if (hasError) {
            request.removeAttribute("message");  // Đảm bảo xóa thông báo thành công nếu có lỗi
        } else {
            // Nếu không có lỗi, lưu thông tin tài khoản để hiển thị
            request.setAttribute("account", account);
        }
        request.getRequestDispatcher("admin_edit_account.jsp").forward(request, response);
    }
    
    protected void getAccountByName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("search");
        AdminAccountDAO dao = new AdminAccountDAO();
        List<Account> accounts = dao.selectAccountByName(username);
        request.setAttribute("accounts", accounts);
        request.getRequestDispatcher("admin_account_crud.jsp").forward(request, response);
    }
}
