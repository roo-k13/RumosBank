<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <title>Dashboard - NiceAdmin Bootstrap Template</title>
        <meta content="" name="description">
        <meta content="" name="keywords">
        <link href="assets/images/favicon.png" rel="icon">
        <link href="assets/images/apple-touch-icon.png" rel="apple-touch-icon">
        <link href="https://fonts.gstatic.com" rel="preconnect">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
        <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
        <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="assets/css/style.css" rel="stylesheet">
        <link href="assets/css/header.css" rel="stylesheet">
        <link href="assets/css/registration.css" rel="stylesheet">
    </head>
    <body>
        <main>
            <div class="container">
                <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">
                                <div class="d-flex justify-content-center py-4">
                                    <a href="index.jsp" class="login-title">
                                        <img src="assets/images/logo.png" alt="">
                                        <span>Rumos Bank</span>
                                    </a>
                                </div>
                                <div class="card mb-3">
                                    <div class="card-body">
                                        <div class="pt-4 pb-2">
                                            <h5 class="card-title text-center pb-0 fs-4">Create an Account</h5>
                                            <p class="text-center small">Enter your personal details to create account</p>
                                        </div>
                                        <form action="registration" method="get" class="row g-3 needs-validation" novalidate>
                                            <div class="col-12">
                                                <label for="first_name" class="form-label">First Name</label>
                                                <input type="text" name="first_name" class="form-control" id="first_name" required>
                                                <div class="invalid-feedback">Please, enter your first name!</div>
                                            </div>
                                            <div class="col-12">
                                                <label for="last_name" class="form-label">Last Name</label>
                                                <input type="text" name="last_name" class="form-control" id="last_name" required>
                                                <div class="invalid-feedback">Please, enter your last name!</div>
                                            </div>
                                            <div class="col-12">
                                                <label for="birthdate" class="form-label">Birthdate</label>
                                                <div class="input-group has-validation">
                                                    <input type="date" name="birthdate" class="form-control" id="birthdate" required>
                                                    <div class="invalid-feedback">Please choose a birthdate.</div>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <label for="yourEmail" class="form-label">Your Email</label>
                                                <input type="email" name="email" class="form-control" id="yourEmail" required>
                                                <div class="invalid-feedback">Please enter a valid Email address!</div>
                                            </div>
                                            <div class="col-12">
                                                <label for="yourPassword" class="form-label">Password</label>
                                                <input type="password" name="password" class="form-control" id="yourPassword" required>
                                                <div class="invalid-feedback">Please enter your password!</div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-check">
                                                    <input class="form-check-input" name="terms" type="checkbox" value="" id="acceptTerms" required>
                                                    <label class="form-check-label" for="acceptTerms">I agree and accept the <a href="#">terms and conditions</a></label>
                                                    <div class="invalid-feedback">You must agree before submitting.</div>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <button class="registration-button" type="submit">Create Account</button>
                                            </div>
                                            <div class="col-12">
                                                <p class="small mb-0">Already have an account? <a href="index.jsp">Log in</a></p>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </main>
        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
        <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/chart.js/chart.umd.js"></script>
        <script src="assets/vendor/echarts/echarts.min.js"></script>
        <script src="assets/vendor/tinymce/tinymce.min.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>
        <script src="assets/js/main.js"></script>
    </body>
</html>