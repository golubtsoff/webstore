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
            <td></td>
            <td><input type="submit" value="Sign in"/>&nbsp;<a href="signup">Sign up</a></td>
        </tr>
    </table>
    <%--@elvariable id="exception" type="exception"--%>
    <c:if test="${not empty exception}">

        <p style="color:red;">
                ${exception}
        </p>
    </c:if>
    <hr/>
    <p>

        <strong>Tip:</strong> &nbsp;Administrator's login: <em>root</em> &nbsp;password: <em>123</em>
    </p>
</form>
</body>
</html>
