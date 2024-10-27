<%-- 
    Document   : admin_edit_product
    Created on : Oct 18, 2024, 12:44:33 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Product</title>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
              integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
              rel="stylesheet">
        <!-- Custom CSS -->
        <link rel="stylesheet" href="./CSS/Style.css" />
        <link rel="stylesheet" href="./CSS/HeaderAndFooter_CSS.css" />
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div class="container mt-5">
                <h1>Edit Product</h1>
           
            <!-- Form to edit product -->
            <form action="edit" method="post">
                <!-- Product ID (readonly) -->
                <div class="mb-3">
                    <label for="id" class="form-label" value="" >Product ID</label>
                    <input type="text" class="form-control" id="id" name="id" value="${product.id}" readonly>
                </div>

                <!-- Product Name -->
                <div class="mb-3">
                    <label for="name" class="form-label">Product Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="${product.name}" required>
                </div>

                <!-- Price -->
                <div class="mb-3">
                    <label for="price" class="form-label">Price</label>
                    <input type="number" step="0.01" class="form-control" id="price" name="price" value="${product.price}" required>
                </div>

                <!-- Quantity -->
                <div class="mb-3">
                    <label for="quantity" class="form-label">Quantity</label>
                    <input type="number" class="form-control" id="quantity" name="quantity" value="${product.quantity}" required>
                </div>

                <!-- Category -->
                <div class="mb-3">
                    <label for="category" class="form-label">Category</label>
                    <select class="form-select" id="category" name="categoryid" required>
                        <option value="">Select Category</option>
                        <c:forEach var="category" items="${requestScope.categoryList}">
                            
                            <option value="${category.id}" ${category.id == product.categoryid ? 'selected="selected"' : ''}>${category.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Image URL -->
                <div class="mb-3">
                    <label for="imageUrl" class="form-label">Image URL</label>
                    <input type="text" class="form-control" id="imageUrl" name="imageUrl" value="${product.imageUrl}">
                </div>

                <button type="submit" class="btn btn-primary"><i class="fas fa-save"></i> Update Product</button>
            </form>
        </div>

        <jsp:include page="footer.jsp"></jsp:include>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

