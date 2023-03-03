<jsp:useBean id="account" scope="session" type="com.rumos.rumosbank.domain.models.BankAccount"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>${account} - Rumos Bank</title>
    </head>
    <body>
        ${account}
    </body>
</html>
