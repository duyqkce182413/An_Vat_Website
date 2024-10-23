<%-- 
    Document   : cart.jsp
    Created on : Oct 17, 2024, 2:32:04 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.yourproject.model.Product"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="./CSS/Style.css">

    <style>
        /* Add your styles here */
    </style>
</head>

<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <!-- Navbar content -->
    </nav>

    <!-- Shopping Cart Section -->
    <div class="container my-5">
        <h3 class="text-center mb-4">Giỏ Hàng Của Bạn</h3>
        <div class="cart-table">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Sản Phẩm</th>
                        <th>Hình Ảnh</th>
                        <th>Số Lượng</th>
                        <th>Giá</th>
                        <th>Tổng</th>
                        <th>Hành Động</th>
                    </tr>
                </thead>
                <tbody>
                    
                    <tr>
                        <td><%= product.getName() %></td>
                        <td><img src="<%= product.getImage() %>" alt="<%= product.getName() %>"></td>
                        <td><input type="number" class="form-control" value="<%= quantity %>"></td>
                        <td><%= String.format("%,.0f", price) %> VND</td>
                        <td><%= String.format("%,.0f", total) %> VND</td>
                        <td><button class="btn btn-danger">Xóa</button></td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="6" class="text-center">Giỏ hàng của bạn đang trống!</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

            <!-- Hiển thị tổng số lượng và tổng giá -->
            <div class="cart-summary">
                <p><strong>Tổng số lượng: 3 sản phẩm</p>
                <p><strong>Tổng số tiền: 3000 %> VND</p>
            </div>

            <div class="cart-actions">
                <a href="#" class="btn">Tiếp Tục Mua Sắm</a>
                <a href="#" class="btn">Thanh Toán</a>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="footer text-center">
        <div class="container">
            <p>Liên hệ để đặt món: <strong>035 439 9951</strong></p>
            <p>Thời gian mở cửa: 8:30am - 23:00pm</p>
            <p>Copyright &copy; Group 3</p>
        </div>
    </footer>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>


