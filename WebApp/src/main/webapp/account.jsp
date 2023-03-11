<jsp:useBean id="account" scope="session" type="com.rumos.rumosbank.domain.models.BankAccount"/>
<jsp:useBean id="client" scope="session" type="com.rumos.rumosbank.domain.models.Client"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
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
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
        <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
        <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
        <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
        <link href="assets/css/style.css" rel="stylesheet">
    </head>
    <body>
        <header id="header" class="header fixed-top d-flex align-items-center">
            <div class="d-flex align-items-center justify-content-between">
                <a href="index.jsp" class="logo d-flex align-items-center">
                    <img src="assets/images/logo.png" alt="">
                    <span class="d-none d-lg-block">RumosBank</span>
                </a>
            </div>
        </header>
        <aside id="sidebar" class="sidebar">
            <ul class="sidebar-nav" id="sidebar-nav">
                <li class="nav-item">
                    <a class="nav-link collapsed" href="profile.jsp">
                        <i class="bi bi-person"></i>
                        <span>Profile</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link collapsed" href="accounts.jsp">
                        <i class="bi bi-question-circle"></i>
                        <span>Accounts</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link collapsed" href="index.jsp">
                        <i class="bi bi-file-earmark"></i>
                        <span>Logout</span>
                    </a>
                </li>
            </ul>
        </aside>
        <main id="main" class="main">
            <div class="pagetitle">
                <h1>Accounts</h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="accounts.jsp">Home</a></li>
                        <li class="breadcrumb-item"><a href="accounts.jsp">Home</a></li>
                        <li class="breadcrumb-item active">${account}</li>
                    </ol>
                </nav>
            </div>
            <div class="card-body pb-0">
                <table class="table table-borderless">
                    <thead>
                    <tr>
                        <th scope="col">Description</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Timestamp</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="movement" items="${account.movements}" varStatus="status">
                    <c:if test="${status.index lt 10}">
                    <tr>
                        <th scope="row"><a href="account?number=${movement.longDate}">${movement.type}</a></th>
                        <td>${movement.amount}</td>
                        <td>${movement.longDate}</td>
                    <tr>
                        </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div style="width: 300px; height: 180px; background-color: #fff; border-radius: 10px; box-shadow: 0px 2px 10px rgba(0,0,0,0.2); display: flex; flex-direction: column;">
                <div style="background-color: #0b74c8; height: 50px; display: flex; justify-content: center; align-items: center; border-top-left-radius: 10px; border-top-right-radius: 10px;">
                    <h5 style="font-size: 1.25rem; font-weight: bold; color: #fff;">Debit Card</h5>
                </div>
                <div style="padding: 20px; flex: 1;">
                    <div style="display: flex; justify-content: space-between; margin-bottom: 1rem;">
                        <div>
                            <h6 style="font-size: 0.875rem; color: #999;">Card Number</h6>
                            <h6 style="font-size: 1rem; font-weight: bold; color: #222;">${account.debitCards[0].number}</h6>
                        </div>
                        <div>
                            <h6 style="font-size: 0.875rem; color: #999;">Expiration Date</h6>
                            <h6 style="font-size: 1rem; font-weight: bold; color: #222;">01/23</h6>
                        </div>
                    </div>
                    <div>
                        <h6 style="font-size: 0.875rem; color: #999;">Name</h6>
                        <h6 style="font-size: 1rem; font-weight: bold; color: #222;">${client.name}</h6>
                    </div>
                </div>
            </div>

        </main>
        <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/chart.js/chart.umd.js"></script>
        <script src="assets/vendor/echarts/echarts.min.js"></script>
        <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
        <script src="assets/vendor/tinymce/tinymce.min.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>
        <script src="assets/js/main.js"></script>
    </body>
</html>
