<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="client" scope="session" type="com.rumos.rumosbank.domain.models.Client"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="assets/css/profile.css">
    <link rel="stylesheet" type="text/css" href="assets/css/main.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    <div class="profile-container">
        <h1>User Information</h1>
        <form action="profile" method="post">
            <fieldset disabled>
                <div>
                    <label for="name">Name:</label>
                    <p id="name">${client.name}</p>
                </div>
                <div>
                    <label for="birthdate">Birthdate:</label>
                    <p id="birthdate">${client.birthdate}</p>
                </div>
                <div>
                    <label for="nif">NIF:</label>
                    <p id="nif">${client.nif}</p>
                </div>
            </fieldset>
            <div>
                <label for="phone">Phone:</label>
                <input type="tel" id="phone" name="phone" value="${client.phone}" required>
            </div>
            <div>
                <label for="mobilePhone">Mobile Phone:</label>
                <input type="tel" id="mobilePhone" name="mobilePhone" value="${client.mobilePhone}" required>
            </div>
            <div>
                <label for="profession">Profession:</label>
                <input type="text" id="profession" name="profession" value="${client.profession}" required>
            </div>
            <div>
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="123 Main Street, Anytown, USA" required>
            </div>
            <div>
                <label for="emailAddress">Email Address:</label>
                <input type="email" id="emailAddress" name="emailAddress" value="${client.emailAddress}" required>
            </div>
            <input type="submit" value="Submit">
        </form>

    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>