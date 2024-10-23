<%-- Document : account.jsp Created on : Oct 17, 2024, 1:40:00 PM Author : admin --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Account Information</title>
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
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f7f7f7;
            }

            /* Sidebar Customization */
            .col-md-3 ul {
                background-color: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                list-style-type: none;
            }

            .col-md-3 ul h3 {
                font-size: 20px;
                margin-bottom: 20px;
                font-weight: bold;
                color: #FC6E51;
            }

            .col-md-3 ul li {
                margin-bottom: 15px;
            }

            .col-md-3 ul li a {
                text-decoration: none;
                font-size: 16px;
                color: #333;
                transition: color 0.2s;
            }

            .col-md-3 ul li a:hover {
                color: #FC6E51;
            }

            /* Account Information Section */
            .account-info {
                background-color: white;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            }

            .account-info h3 {
                font-size: 22px;
                font-weight: bold;
                margin-bottom: 30px;
                color: #333;
            }

            .account-info .info-item {
                margin-bottom: 20px;
            }

            .account-info label {
                font-weight: bold;
                margin-bottom: 5px;
                display: block;
                color: #555;
            }

            .account-info input[type="text"],
            .account-info input[type="email"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
                margin-bottom: 10px;
            }

            .account-info .btn {
                background-color: #FC6E51;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            .account-info .btn:hover {
                background-color: #F56B4C;
            }
        </style>
    </head>

    <body>

        <jsp:include page="header.jsp"></jsp:include>

            <!-- Info Account Section -->
            <div class="container my-5">
                <h3>Tài Khoản</h3>
                <div class="row">
                    <!-- Sidebar -->
                    <div class="col-md-3">
                        <ul>

                            <li><a href="account.jsp">Thông tin tài khoản</a></li>
                            <li><a href="address_management.jsp">Danh sách địa chỉ</a></li>
                            <li><a href="#">Đăng xuất</a></li>
                        </ul>
                    </div>

                    <!-- Account Information -->
                    <div class="col-md-9">
                        <div class="account-info">
                            <h3>Thông tin tài khoản</h3>
                            <div class="info-item">
                                <label for="username">Tên người dùng:</label>
                                <input type="text" id="username" value="Nguyễn Văn A" readonly>
                            </div>
                            <div class="info-item">
                                <label for="email">Email:</label>
                                <input type="email" id="email" value="email@example.com" readonly>
                            </div>
                            <div class="info-item">
                                <label for="phone">Số điện thoại:</label>
                                <input type="text" id="phone" value="035 439 9951" readonly>
                            </div>
                            <div class="info-item mx-auto">
                                <button class="btn">Chỉnh sửa thông tin</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        <jsp:include page="footer.jsp"></jsp:include>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>

</html>