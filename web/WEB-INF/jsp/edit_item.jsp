<%--
  Created by IntelliJ IDEA.
  User: golubtsov
  Date: 16.02.2018
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:useBean id="item" scope="request" type="entity.Item"/>
    <title>${item.title} - Webstore</title>
</head>
<body>
<jsp:useBean id="person" scope="session" type="entity.Person"/>
<c:set var="name" scope="request" value="${person.login}"/>
<p>
    Welcome, ${name}&nbsp;<a href="logout">(logout)</a>
</p>
<section>
    <c:set var="role" scope="request" value="${person.role}"/>
    <c:if test="${role == 'admin'}">
        <form method="post" action="items" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="id" value="${item.id}">
            <table cellpadding="2">
                <tr>
                    <td><strong>Title</strong></td>
                    <td><input required type="text" name="title" size=30 value="${item.title}"></td>
                </tr>
                <tr>
                    <td><strong>Description</strong></td>
                    <td><textarea name="description" cols="30" rows="10">${item.description}</textarea>
                    </td>
                </tr>
                <tr>
                    <td><strong>Price</strong></td>
                    <td><input required type="number" name="price" size=30 value="${item.price}" step="0.01" min="0"></td>
                </tr>
                <tr>
                    <td><strong>Amount</strong></td>
                    <td><input required type="number" name="amount" size=30 value="${item.amount}" min="0"></td>
                </tr>
            </table>
            <hr/>
            <button type="submit">Save</button>
            <button type="button" onclick="window.history.back()">Cancel</button>
        </form>
    </c:if>
</section>
</body>
</html>
