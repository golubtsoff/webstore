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
    <title>Login</title>
</head>
<body>
<form method="POST" action="login">
    <table>
        <tr>
            <td colspan="2">Login to the WebStore application:</td>
        </tr>
        <tr>
            <td>Login:</td>
            <td><input name="login"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Go"/></td>
        </tr>
    </table>
</form>
</body>
</html>
