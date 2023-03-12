<%--
  Created by IntelliJ IDEA.
  User: BNP Paribas
  Date: 12/03/2023
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="cards" scope="session" type="java.util.List<com.rumos.rumosbank.domain.models.cards.Card>"/>
<jsp:useBean id="client" scope="session" type="com.rumos.rumosbank.domain.models.Client"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="row">
    <c:forEach items="${cards}" var="card">
        <div class="col-md-4">
            <div class="card">
                <div class="card-title">
                    <h5>Debit Card</h5>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <h6>Card Number</h6>
                            <h6 class="card-number">${card.number}</h6>
                        </div>
                        <div class="col-sm-6">
                            <h6>Expiration Date</h6>
                            <h6 class="expiration-date">${card.expirationDate}</h6>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <h6>Cardholder Name</h6>
                            <h6 class="cardholder-name">${client.name}</h6>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
