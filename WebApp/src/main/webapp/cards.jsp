<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="cards" scope="session" type="java.util.List<com.rumos.rumosbank.domain.models.cards.Card>"/>
<jsp:useBean id="client" scope="session" type="com.rumos.rumosbank.domain.models.Client"/>
<html>
<head>
    <title>Cards</title>
    <link href="assets/css/cards.css" rel="stylesheet">
</head>
<body>
<div class="cards-container">
    <c:forEach items="${cards}" var="card">
        <div class="card">
            <div class="card-title">Debit Card</div>
            <div class="card-body">
                <div><strong>Card Number:</strong> <span class="card-number">${card.number}</span></div>
                <div><strong>Expiration Date:</strong> <span class="expiration-date">${card.expirationDate}</span></div>
                <div><strong>Cardholder Name:</strong> <span class="cardholder-name">${client.name}</span></div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
