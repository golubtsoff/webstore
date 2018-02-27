<%--
  Created by IntelliJ IDEA.
  User: Gauss
  Date: 17.02.2018
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in - Webstore</title>
</head>
<body>
<form method="POST" action="signin">
    <table>
        <tr>
            <td colspan="2">Sign in to the WebStore application:</td>
        </tr>
        <tr>
            <td>Login:</td>
            <td><input required type="text" name="login"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input required type="password" name="password"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Sign in"/>&nbsp;<a href="signup">Sign up</a></td>
        </tr>
    </table>
    <c:if test="${not empty exception}">
        <p style="color:red;">
                ${exception}
        </p>
    </c:if>
</form>
</body>
</html>
