<%--
  Created by IntelliJ IDEA.
  User: golubtsov
  Date: 16.02.2018
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="util.CurrencyFormat" %>
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
    <h1>${item.title}</h1>
    <c:if test="${role == 'admin'}">
        <%--<a href="items?action=add"><img src="img/add.png"></a>--%>
        <a href="item?id=${item.id}&action=edit">Edit</a>
        <br/>
    </c:if>
    <c:if test="${role == 'user'}">
        <%--<a href="items?action=add"><img src="img/add.png"></a>--%>
        <a href="item?id=${item.id}&action=buy">Buy</a>
        <br/>
    </c:if>
    <hr/>
    <table cellpadding="2">
        <tr>
            <td><strong>Description</strong></td>
            <td>${item.description}</td>
        </tr>
        <tr>
            <td><strong>Price</strong></td>
            <%--<td>${item.price}</td>--%>
            <td><%=CurrencyFormat.format(item.getPrice())%>
            </td>
        </tr>
        <c:if test="${role == 'admin'}">
            <tr>
                <td><strong>Amount</strong></td>
                <td>${item.amount}</td>
            </tr>
        </c:if>
    </table>
    <hr/>
    <button onclick="window.history.back()">Back</button>
</section>
</body>
</html>
