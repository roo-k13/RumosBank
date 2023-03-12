<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Rumos Bank - Login</title>
    <link rel="stylesheet" type="text/css" href="assets/css/login.css">
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

            <!-- Container title -->
            <h5 class="title">Login to Your Account</h5>
            <p class="subtitle">Enter your email & password to login</p>

            <!-- Login form -->
            <form action="login" method="post">
                <!-- Email field -->
                <label for="email" class="form-label">Email</label>
                <input type="text" name="email" class="form-control" id="email" required>

                <!-- Password Field -->
                <label for="yourPassword" class="form-label">Password</label>
                <input type="password" name="password" class="form-control" id="yourPassword" required>

                <!-- Login Button -->
                <button class="login-button" type="submit">Login</button>

                <!-- Register Link -->
                <p>Don't have an account? <a href="registration.jsp">Create an account</a></p>
            </form>
        </div>
    </main>
</body>

</html>