<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Login - Rumos Bank</title>

    <!-- Favicon and touch icons -->
    <link href="assets/images/favicon.png" rel="icon">
    <link href="assets/images/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/profile.css" rel="stylesheet">
    <link href="assets/css/header.css" rel="stylesheet">
    <link href="assets/css/login.css" rel="stylesheet">
</head>

<body>
<main>
    <!-- Login container -->
    <div class="login-container">
        <!-- Logo -->
        <a href="index.jsp" class="logo">
            <img src="assets/images/logo.png" alt="">
            <span>Rumos Bank</span>
        </a>

        <!-- Login form -->
        <h5 class="title">Login to Your Account</h5>
        <p class="subtitle">Enter your email & password to login</p>
        <form action="login" method="post">
            <label for="email" class="form-label">Email</label>
            <input type="text" name="email" class="form-control" id="email" required>
            <label for="yourPassword" class="form-label">Password</label>
            <input type="password" name="password" class="form-control" id="yourPassword" required>
            <button class="login-button" type="submit">Login</button>
            <p>Don't have an account? <a href="registration.jsp">Create an account</a></p>
        </form>
    </div>
</main>
</body>
</html>