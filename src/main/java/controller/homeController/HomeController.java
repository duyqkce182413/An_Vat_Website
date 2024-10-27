/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.homeController;

import dao.HomeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author admin
 */

@WebServlet("/")
public class HomeController extends HttpServlet {

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
            out.println("<title>Servlet HomeController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeController at " + request.getContextPath() + "</h1>");
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
                case "/filter-by-categoryid":
                    getProductByCategoryID(request, response);
                    break;
                case "/home-search":
                    getProductByName(request, response);
                    break;
                
                default:
                    getAllProducts(request, response);
                    break;
            }
        } catch (ServletException | IOException e) {
            System.out.println(e);
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
        processRequest(request, response);
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

    protected void getAllProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HomeDAO dao = new HomeDAO();
        List<Product> products = dao.selectAllProducts();
        List<Category> categorys = dao.selectAllCategorys();
        if (products != null || categorys != null) {
            request.setAttribute("products", products);
            request.setAttribute("categorys", categorys);
        } else {
            request.setAttribute("products", new ArrayList<>()); // Truyền một danh sách rỗng nếu không có sản phẩm nào
            request.setAttribute("categorys", new ArrayList<>()); // Truyền một danh sách rỗng nếu không có sản phẩm nào
        }

        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    protected void getProductByCategoryID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryIdStr = request.getParameter("categoryid");
        int categoryId = Integer.parseInt(categoryIdStr);
        HomeDAO dao = new HomeDAO();

        List<Category> categorys = dao.selectAllCategorys();
        List<Product> products = dao.selectProductByCategoryID(categoryId);

        request.setAttribute("categorys", categorys);
        request.setAttribute("products", products);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
    protected void getProductByName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nameInput = request.getParameter("search");
        HomeDAO dao = new HomeDAO();
        List<Product> products = dao.selectProductByName(nameInput);
        List<Category> categorys = dao.selectAllCategorys();
        request.setAttribute("products", products);
        request.setAttribute("categorys", categorys);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
    
    
}
