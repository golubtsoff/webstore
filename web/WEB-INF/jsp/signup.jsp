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
    <title>Sign up</title>
</head>
<body>
<form method="POST" action="signup">
    <table>
        <tr>
            <td colspan="2">Sign up to the WebStore application:</td>
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
            <td>Retype password:</td>
            <td><input type="password" name="retype_password"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Sign up"/></td>
            <td><a href="signin">Sign in</a></td>
        </tr>
    </table>
</form>
</body>
</html>
