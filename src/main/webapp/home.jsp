<%-- Document : home Created on : Oct 16, 2024, 2:49:44 PM Author : admin --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
        <!-- font awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
              integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
              rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/HeaderAndFooter_CSS.css">

    </head>

    <body>
        <!-- Header -->
        <jsp:include page="header.jsp"></jsp:include>

            <!-- Banner -->
            <!-- <header class="header-banner">
                <h1>Banner</h1>
            </header> -->

            <!-- Thanh tìm kiếm và menu trong cùng một form -->
            <form action="home-search" method="GET">
                <!-- Thanh tìm kiếm -->
                <div class="search-bar">
                    <div class="container">
                        <div class="d-flex justify-content-center">
                            <input type="text" name="search" class="form-control search-input" placeholder="Tìm món...">
                            <button type="submit" class="btn search-btn"><i class="fa-solid fa-search"></i></button>
                        </div>
                    </div>
                </div>
            </form>

            <!-- Product Section + Cart -->
            <div class="container my-5">
                <!-- Menu -->
                <div class="menu-nav">
                    <a href="/home" class="col-md">Tất cả</a>
                <c:forEach items="${requestScope.categorys}" var="c">
                    <a href="filter-by-categoryid?categoryid=${c.id}" class="col-md">${c.name}</a>
                </c:forEach>
            </div>
        </div>

        <div class="container-fluid px-5">
            <div class="row">
                <!-- Phần giỏ hàng -->
                <div class="col-md-3">
                    <div class="cart-section">
                        <div class="cart-title">Giỏ Hàng</div>
                        <div class="cart-items">

                            Bạn chưa có món nào.
                        </div>
                        <div class="total-price">
                            Tổng cộng: 0đ
                        </div>
                        <button class="btn btn-danger w-100 mt-3">Thanh toán</button>
                    </div>
                </div>

                <!-- Phần sản phẩm -->
                <div class="col-md-9">
                    <div class="row g-4">
                        <!-- Product card -->
                        <c:forEach items="${requestScope.products}" var="p">
                            <div class="col-md-3">
                                <div class="card product-card">
                                    <!-- Sử dụng p.imageUrl để hiển thị hình ảnh đúng -->
                                    <a href="product_detail.jsp"><img src="${p.imageUrl}" class="card-img-top" alt="${p.name}"></a>
                                    <div class="card-body">
                                        <a href="product_detail.jsp"><h5 class="card-title">${p.name}</h5></a>
                                        <p class="card-text">${p.price}</p>
                                        <a href="#" class="btn btn-primary">Đặt Món</a>
                                    </div>
                                </div>
                            </div>  
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"></jsp:include>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="./Script/Script.js"></script>
    </body>

</html>