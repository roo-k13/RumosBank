<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="account" scope="session" type="com.rumos.rumosbank.domain.models.BankAccount" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="assets/css/header.css">
  <link rel="stylesheet" href="assets/css/tables_test.css">
</head>
<body>
<%-- Include the header.jsp file --%>
<%@ include file="header.jsp" %>

<%-- Display the current account --%>
<h1>${account}</h1>

<%-- Display a table with the account movements --%>
<div class="table-container">
  <h2 class="table-title">Bank Account Movements</h2>
  <table class="table">
    <thead>
    <tr>
      <th scope="col">Description</th>
      <th scope="col">Amount</th>
      <th scope="col">Timestamp</th>
    </tr>
    </thead>
    <tbody>
    <%-- Loop through the movements of the account --%>
    <c:forEach var="movement" items="${account.movements}" varStatus="status">
      <%-- Display only the first 10 movements --%>
      <c:if test="${status.index lt 10}">
        <tr>
            <%-- Display a link to the movement details --%>
          <th scope="row">
            <a href="account?number=${movement.longDate}">${movement.type}</a>
          </th>
            <%-- Display the amount and timestamp of the movement --%>
          <td>${movement.amount}</td>
          <td>${movement.longDate}</td>
        </tr>
      </c:if>
    </c:forEach>
    </tbody>
  </table>
</div>

<%@ include file="cards.jsp" %>
</body>
</html>