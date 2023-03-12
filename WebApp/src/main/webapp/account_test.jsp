<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="account" scope="session" type="com.rumos.rumosbank.domain.models.BankAccount"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="assets/css/header_test.css">
</head>
<body>
<%@ include file = "header.jsp" %>
  <table class="table">
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
</body>
</html>
