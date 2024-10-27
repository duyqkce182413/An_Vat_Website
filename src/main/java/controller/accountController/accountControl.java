/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.accountController;

import dao.AccountDAO;
import dao.LoginDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author tuana
 */
public class accountControl extends HttpServlet {

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
            out.println("<title>Servlet accountControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet accountControl at " + request.getContextPath() + "</h1>");
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
        switch (action) {
            case "login-form":
                LoginForm(request, response);
                break;
            case "/login":
                Login(request, response);
                break;
            case "/logout":
                Logout(request, response);
                break;
            case "/register":
                Register(request, response);
                break;
            case "/updateaccount":
                UpdateAccount(request, response);
                break;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
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
    
    

    private void LoginForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    private void Login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginDAO dao = new LoginDAO();
        Account acc = dao.login(username, password);
        HttpSession session = request.getSession();
        if (acc == null) {
            request.setAttribute("messerror", "Wrong Username or Password");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else {
            session.setAttribute("acc", acc);
            session.setMaxInactiveInterval(1200);
            if (acc.isAdmin() == true) {
                request.getRequestDispatcher("admin-account-crud").forward(request, response);
            }
            if (acc.isAdmin() == false) {
                response.sendRedirect("home");
            }
        }

    }

    private void Logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        session.removeAttribute("acc");
        response.sendRedirect("home");
    }

    private void Register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Xóa các thông báo lỗi trước đó
        session.setAttribute("errorName", null);
        session.setAttribute("errorPassword", null);
        session.setAttribute("errorConfirm", null);
        session.setAttribute("successMessage", null);

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        LoginDAO dao = new LoginDAO();
        Account acc = dao.CheckAccount(username);

        // Xác thực dữ liệu
        boolean hasError = false;

        if (password == null || password.length() < 8) {
            session.setAttribute("errorPassword", "Password must be at least 8 characters long.");
            hasError = true;
        } else if (!password.equals(confirmPassword)) {
            session.setAttribute("errorConfirm", "Passwords do not match.");
            hasError = true;
        }

        if (hasError) {
            response.sendRedirect("register.jsp");
            return;
        }

        if (acc == null) {
            dao.signup(username, password, email);
            session.setAttribute("successMessage", "Account created successfully.");
        } else {
            session.setAttribute("errorName", "Username already exists.");
        }
        response.sendRedirect("register.jsp");
    }

    private void UpdateAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //lay du lieu tu form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone_number = request.getParameter("phone_number");
        AccountDAO dao = new AccountDAO(); //new  SinhVienDAO Account(username, password,email,phone_number);
        Account acc = new Account(username, password, email, phone_number);
        HttpSession session = request.getSession();
        dao.AccountDAO(acc);
        // Cập nhật session với thông tin mới
        session.setAttribute("acc", acc);
        request.getRequestDispatcher("account.jsp").forward(request, response);
    }
}
