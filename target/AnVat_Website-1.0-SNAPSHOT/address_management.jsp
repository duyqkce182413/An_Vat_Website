<%-- 
    Document   : address_management
    Created on : Oct 17, 2024, 3:47:16 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông Tin Địa Chỉ</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
        integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="./CSS/Style.css">

    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f7f7f7;
        }

        /* Navbar Customization */
        .navbar {
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .navbar-brand img {
            max-width: 150px;
        }

        .nav-link {
            font-size: 16px;
            font-weight: 500;
            color: #333;
        }

        .nav-link:hover {
            color: #FC6E51;
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

        /* Address Management Section */
        .address-card {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .address-card h5 {
            margin-bottom: 10px;
        }

        .address-card p {
            margin-bottom: 5px;
            font-size: 14px;
            color: #666;
        }

        .address-actions {
            display: flex;
            align-items: center;
        }

        .address-actions a {
            margin-right: 10px;
        }

        .btn-primary,
        .btn-danger,
        .btn-success {
            margin-right: 10px;
            background-color: #FC6E51;
            border-color: #FC6E51;
        }

        .btn-primary:hover,
        .btn-danger:hover,
        .btn-success:hover {
            background-color: #F56B4C;
            border-color: #F56B4C;
        }

        /* Default Address */
        .default-address {
            border: 2px solid #FC6E51;
            background-color: #fff3cd;
        }

        /* Form New Address */
        .form-control {
            margin-bottom: 10px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        /* Footer Customization */
        .footer {
            background-color: #333;
            color: white;
            padding: 20px 0;
            margin-top: 40px;
        }

        .footer p {
            margin: 0;
            font-size: 14px;
        }

        .footer strong {
            color: #FC6E51;
        }
    </style>
</head>

<body>
    <jsp:include page="header.jsp"></jsp:include>
    <!-- Address Management Section -->
    <div class="container my-5">
        <h3 class="text-center mb-4">Thông Tin Địa Chỉ</h3>

        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-3">
                <ul>
                    <h3>Tài Khoản</h3>
                    <li><a href="account.jsp">Thông tin tài khoản</a></li>
                    <li><a href="address_management.jsp">Danh sách địa chỉ</a></li>
                    <li><a href="#">Đăng xuất</a></li>
                </ul>
            </div>

            <!-- Address Cards -->
            <div class="col-md-6">
                <div class="address-card default-address">
                    <div>
                        <h5>quach khanh duy duy (Địa chỉ mặc định)</h5>
                        <p>Công ty: -</p>
                        <p>Địa chỉ: -, Vietnam</p>
                        <p>Số điện thoại: -</p>
                    </div>
                    <div class="address-actions">
                        <a href="#" class="btn btn-primary"><i class="fa-solid fa-pen"></i></a>
                        <a href="#" class="btn btn-danger"><i class="fa-solid fa-trash"></i></a>
                    </div>
                </div>

                <div class="address-card">
                    <div>
                        <h5>que que</h5>
                        <p>Công ty: quê</p>
                        <p>Địa chỉ: ruq, rawr, Vietnam</p>
                        <p>Số điện thoại: 023123</p>
                    </div>
                    <div class="address-actions">
                        <a href="#" class="btn btn-primary"><i class="fa-solid fa-pen"></i></a>
                        <a href="#" class="btn btn-danger"><i class="fa-solid fa-trash"></i></a>
                        <a href="#" class="btn btn-success">Đặt Làm Địa Chỉ Mặc Định</a>
                    </div>
                </div>
            </div>

            <!-- New Address Form -->
            <div class="col-md-3">
                <h5>Nhập Địa Chỉ Mới</h5>
                <form action="#" method="post">
                    <input type="text" class="form-control" placeholder="Họ" required>
                    <input type="text" class="form-control" placeholder="Tên" required>
                    <input type="text" class="form-control" placeholder="Công ty">
                    <input type="text" class="form-control" placeholder="Địa chỉ 1" required>
                    <input type="text" class="form-control" placeholder="Địa chỉ 2">
                    <select class="form-control" required>
                        <option value="" disabled selected>- Please Select -</option>
                        <option value="Vietnam">Vietnam</option>
                    </select>
                    <input type="text" class="form-control" placeholder="Số điện thoại" required>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="defaultAddress">
                        <label class="form-check-label" for="defaultAddress">Đặt làm địa chỉ mặc định</label>
                    </div>
                    <button type="submit" class="btn btn-success mt-3">Thêm Mới</button>
                </form>
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
