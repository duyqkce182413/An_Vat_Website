<%-- 
    Document   : register
    Created on : Oct 16, 2024, 2:23:35 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register</title>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
              integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
              rel="stylesheet">
        <!-- Header and Footer CSS -->
        <link rel="stylesheet" href="./CSS/HeaderAndFooter_CSS.css" />
        <!-- Custom CSS -->
        <link rel="stylesheet" href="./CSS/Style.css" />
        <style>
            p.error {
                color: red;
                font-size: 14px;
                margin-bottom: 15px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <div class="register-form">
                <h2>Register</h2>               
                <form action="register">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="username" name="username" required>
                    <c:if test="${not empty sessionScope.errorName}">
                        <p class="error"><c:out value="${sessionScope.errorName}" /></p>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                    <c:if test="${not empty sessionScope.errorPassword}">
                        <p class="error"><c:out value="${sessionScope.errorPassword}" /></p>
                    </c:if>
                </div>

                <div class="mb-3">
                    <label for="confirmPassword" class="form-label">Confirm Password</label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                    <c:if test="${not empty sessionScope.errorConfirm}">
                        <p class="error"><c:out value="${sessionScope.errorConfirm}" /></p>
                    </c:if>
                </div>
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary">Register</button>
                </div>
                <c:if test="${not empty sessionScope.successMessage}">
                    <div class="alert alert-success mt-3">${sessionScope.successMessage}</div>
                </c:if>
                <div class="form-text">
                    Already have an account? <a href="login.jsp">Login Here</a>
                </div>
            </form>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <%
            session.removeAttribute("errorName");
            session.removeAttribute("errorPassword");
            session.removeAttribute("errorConfirm");
            session.removeAttribute("successMessage");
        %>
    </body>
</html>

