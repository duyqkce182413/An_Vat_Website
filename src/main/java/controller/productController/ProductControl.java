/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.productController;

import dao.AdminProductDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import jakarta.servlet.annotation.WebServlet;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author HP
 */
public class ProductControl extends HttpServlet {

    private static final long serialVersionUID = 1;
    private AdminProductDAO dao;

    public void init() {
        dao = new AdminProductDAO();
    }

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
            out.println("<title>Servlet ProductControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductControl at " + request.getContextPath() + "</h1>");
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
            case "/searchProduct":
                searchProductByName(request, response);
                break;
            case "/filterByCategory":
                filterProductsByCategory(request, response);
                break;
            case "/new":
                categoryForAddProduct(request, response);
                showNewForm(request, response);
                break;
            case "/insert":
                addProduct(request, response);
                break;
            case "/delete":
                delete(request, response);
                break;
            case "/newedit":
                newedit(request, response);
                break;
            case "/edit":
                edit(request, response);
                break;
            default:
                adminProduct(request, response);
                break;
        }
    } catch (SQLException ex) {
        System.out.println(ex);
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

    private void adminProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Product> products = dao.selectAllProducts();
        List<Category> categorys = dao.selectAllCategorys();

        request.setAttribute("categorys", categorys);
        request.setAttribute("products", products);
        request.getRequestDispatcher("admin_product_crud.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_add_product.jsp");
        dispatcher.forward(request, response);
    }

    private void newedit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminProductDAO dao = new AdminProductDAO();
        String id = request.getParameter("id");
        List<Category> categorys = dao.selectAllCategorys();
        Product products = dao.getProductbyid(id);
        request.setAttribute("categoryList", categorys);
        request.setAttribute("product", products);
        request.getRequestDispatcher("admin_edit_product.jsp").forward(request, response);
    }

    private void categoryForAddProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Category> categorys = dao.selectAllCategorys();
        request.setAttribute("categorys", categorys);
        request.getRequestDispatcher("admin_add_product.jsp").forward(request, response);
    }

    

    private void delete(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        AdminProductDAO dao = new AdminProductDAO();
        dao.deleteProduct(id);
        response.sendRedirect("productcontrol");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int categoryid = Integer.parseInt(request.getParameter("categoryid"));
        String imageurl = request.getParameter("imageUrl");
        AdminProductDAO dao = new AdminProductDAO();
        Product product = new Product(id, name, price, quantity, categoryid, imageurl);
        dao.updateProduct(product);
        response.sendRedirect("productcontrol");
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int categoryid = Integer.parseInt(request.getParameter("categoryid"));
        String imageurl = request.getParameter("imageUrl");
        Product product = new Product(0, name, price, quantity, categoryid, imageurl);

        AdminProductDAO dao = new AdminProductDAO();
        dao.insertProduct(product);
        response.sendRedirect("productcontrol");
    }
    
   private void searchProductByName(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    String searchName = request.getParameter("search");
    List<Product> products = dao.searchProductsByName(searchName);
    List<Category> categories = dao.selectAllCategorys();

    request.setAttribute("products", products);
    request.setAttribute("categorys", categories);
    request.getRequestDispatcher("admin_product_crud.jsp").forward(request, response);
}
   
   private void filterProductsByCategory(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    String categoryId = request.getParameter("category");
    List<Product> products;

    if (categoryId != null && !categoryId.isEmpty()) {
        products = dao.selectProductsByCategory(categoryId);
    } else {
        products = dao.selectAllProducts();
    }
    
    List<Category> categories = dao.selectAllCategorys();
    request.setAttribute("products", products);
    request.setAttribute("categorys", categories);
    request.getRequestDispatcher("admin_product_crud.jsp").forward(request, response);
}

//    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, ServletException, IOException {
//        String id = request.getParameter("id");
//        List<Category> categorys = dao.selectAllCategorys();
//        Product products = dao.getProductbyid(id);
//        request.setAttribute("categoryList", categorys);
//        request.setAttribute("product", products);
//        request.getRequestDispatcher("admin_edit_product.jsp").forward(request, response);
//
//    }
//    private void editProduct(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException, ServletException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        String name = request.getParameter("name");
//        double price = Double.parseDouble(request.getParameter("price"));
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
//        int categoryid = Integer.parseInt(request.getParameter("categoryid"));
//        String imageurl = request.getParameter("imageUrl");
//        DAO dao = new DAO();
//        Product product = new Product(id, name, price, quantity, categoryid, imageurl);
//        dao.update(product);
//        response.sendRedirect("adminproduct");
//    }
}
