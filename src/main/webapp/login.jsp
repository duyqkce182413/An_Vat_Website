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
                 .error {
                color: red; /* Changed to red for better visibility */
                margin-bottom: 10px;
                font-size: 14px;
            }
            </style>
        
    </head>
    <body>
        
        <jsp:include page="header.jsp"></jsp:include>
        
        <div class="login-form">
            <h2>Login</h2>
            <form action="loginControl" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary">Login</button>
                    <p class ="error"> ${messerror}</p>
                </div>
                <div class="form-text">
                    Don't have an account? <a href="register.jsp">Register Here</a>
                </div>
            </form>
        </div>

        <jsp:include page="footer.jsp"></jsp:include>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
