<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Gauss
  Date: 17.02.2018
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Sign up - Webstore</title>
</head>
<body>
<form method="POST" action="signup">
    <table>
        <tr>
            <td colspan="2">Sign up to the WebStore application:</td>
        </tr>
        <tr>
            <td>Login:</td>
            <td><label>
                <input required type="text" name="login"/>
            </label></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><label>
                <input required type="password" name="password"/>
            </label></td>
        </tr>
        <tr>
            <td>Retype password:</td>
            <td><label>
                <input required type="password" name="retype_password"/>
            </label></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Sign up"/>&nbsp;<a href="signin">Sign in</a></td>
        </tr>
    </table>
    <%--@elvariable id="exception" type="exception"--%>
    <c:if test="${not empty exception}">
        <p style="color:red;">
            ${exception}
        </p>
    </c:if>
</form>
</body>
</html>
