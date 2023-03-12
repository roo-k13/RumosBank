<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="client" scope="session" type="com.rumos.rumosbank.domain.models.Client"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>Profile</title>
    <link rel="stylesheet" href="assets/css/header.css">
    <link rel="stylesheet" href="assets/css/tables_test.css">
</head>

<body>

    <header>
        <%@ include file="header.jsp" %>
    </header>

    <main>

        <!-- Accounts Table -->
        <div class="table-container">
            <h2 class="table-title">Bank Accounts</h2>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Number</th>
                    <th scope="col">Name</th>
                    <th scope="col">Balance</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="bankAccount" items="${client.bankAccounts}" varStatus="status">
                <tr>
                    <th scope="row"><a href="account?number=${bankAccount.number}">${bankAccount.number}</a></th>
                    <td>${bankAccount.name}</td>
                    <td>${bankAccount.balance}€</td>
                <tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>

    <header>
        <%@ include file="footer.jsp" %>
    </header>

</body>
</html>