<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="client" scope="session" type="com.rumos.rumosbank.domain.models.Client"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
    <%@ include file="header.jsp" %>
    <div class="profile-container">
        <h1>User Information</h1>
        <p><strong>Name:</strong> ${client.name}</p>
        <p><strong>Birthdate:</strong> ${client.birthdate}</p>
        <p><strong>NIF:</strong> ${client.nif}</p>
        <p><strong>Phone:</strong> ${client.phone}</p>
        <p><strong>Mobile Phone:</strong> ${client.mobilePhone}</p>
        <p><strong>Profession:</strong> ${client.profession}</p>
        <p><strong>Address:</strong> 123 Main Street, Anytown, USA</p>
        <p><strong>Email Address:</strong> ${client.emailAddress}</p>
    <%@ include file="footer.jsp" %>
    </div>
</body>
</html>