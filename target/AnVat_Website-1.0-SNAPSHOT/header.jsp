<%-- 
    Document   : header
    Created on : Oct 17, 2024, 8:06:54 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand logo" href="#"><img src="./image/Logo_AnVat.png" alt="Logo"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">Trang Chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Giới Thiệu</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Menu Món</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Blog</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Liên Hệ</a>
                </li>
                <c:if test="${sessionScope.acc == null}">
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp">Login</a>
                </li>
                </c:if>
                <c:if test="${sessionScope.acc != null}">
                <li class="nav-item">
                    <a class="nav-link" href="logout">Logout</a>
                </li>
                </c:if> 
            </ul>
            <div class="d-flex align-items-center ms-auto">
                <c:out value="${acc.username}"></c:out>
                <a href="#" class="nav-link"><i class="fa-solid fa-user"></i></a>
                <a href="#" class="nav-link"><i class="fa-solid fa-basket-shopping"></i></a>
            </div>
        </div>
    </div>
</nav>