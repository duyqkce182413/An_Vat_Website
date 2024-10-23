<%-- 
    Document   : food_detail
    Created on : Oct 17, 2024, 7:41:02 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chi Tiết Món Ăn</title>
        <!-- Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link rel="stylesheet" href="./CSS/Style.css">

        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f7f7f7;
            }

            .food-img {
                max-width: 100%;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            }

            .food-name {
                font-size: 30px;
                font-weight: bold;
                margin-bottom: 20px;
                color: #FC6E51;
            }

            .food-price {
                font-size: 24px;
                font-weight: bold;
                color: #333;
                margin-bottom: 20px;
            }

            .food-desc {
                font-size: 16px;
                color: #666;
                margin-bottom: 30px;
            }

            .quantity {
                width: 80px;
                text-align: center;
            }

            .btn-cart {
                background-color: #FC6E51;
                border-color: #FC6E51;
            }

            .btn-cart:hover {
                background-color: #F56B4C;
                border-color: #F56B4C;
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
        <!-- Header -->
        <jsp:include page="header.jsp"></jsp:include>

        <div class="container">
            <div class="row">
                <!-- Food Image -->
                <div class="col-md-6">
                    <img src="path_to_image/food_image.jpg" alt="Tên món ăn" class="food-img">
                </div>

                <!-- Food Details -->
                <div class="col-md-6">
                    <h2 class="food-name">Tên Món Ăn</h2>
                    <p class="food-price">Giá: 150,000 VNĐ</p>
                    <p class="food-desc">
                        Đây là mô tả chi tiết về món ăn. Nội dung này bao gồm thông tin về nguyên liệu, cách chế biến và
                        các thông tin khác về món ăn.
                    </p>

                    <!-- Quantity and Add to Cart -->
                    <form action="addToCart" method="post">
                        <div class="form-group">
                            <label for="quantity">Số Lượng:</label>
                            <input type="number" id="quantity" name="quantity" class="form-control quantity" min="1" value="1">
                        </div>
                        <input type="hidden" name="foodId" value="1"> <!-- ID của món ăn -->
                        <button type="submit" class="btn btn-cart mt-3">Thêm Vào Giỏ Hàng</button>
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


