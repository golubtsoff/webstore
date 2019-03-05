<%--
  Created by IntelliJ IDEA.
  User: golubtsov
  Date: 16.02.2018
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="util.CurrencyFormat" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Items - Webstore</title>
</head>
<body>
<jsp:useBean id="person" scope="session" type="entity.Person"/>
<c:set var="name" scope="request" value="${person.login}"/>
<p>
    Welcome, ${name}&nbsp;<a href="logout">(logout)</a>
</p>
<hr/>

<section>
    <c:set var="role" scope="request" value="${person.role}"/>
    <c:if test="${role == 'admin'}">
        [Items]&nbsp;[<a href="purchases">Purchases</a>]<br/>
        <hr>
        <%--<a href="items?action=add"><img src="img/add.png"></a>--%>
        <a href="item?action=add">Add item</a>
        <br/><br/>
    </c:if>

    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Item</th>
            <th>Price</th>
            <c:if test="${role == 'admin'}">
                <th>Amount</th>
                <th></th>
            </c:if>
            <th></th>
        </tr>
        <jsp:useBean id="items" scope="request" type="java.util.List"/>
        <c:forEach items="${items}" var="item">
            <jsp:useBean id="item" type="entity.Item"/>
            <tr>
                <td><a href="item?id=${item.id}&action=view">${item.title}</a></td>
                <%--<td>${item.price}</td>--%>
                <td><%=CurrencyFormat.format(item.getPrice())%></td>
                <c:if test="${role == 'admin'}">
                    <%--<td><a href="item?id=${item.id}&action=delete"><img src="img/delete.png"></a></td>--%>
                    <%--<td><a href="item?id=${item.id}&action=edit"><img src="img/pencil.png"></a></td>--%>
                    <td>${item.amount}</td>
                    <td><a href="item?id=${item.id}&action=delete">Delete</a></td>
                    <td><a href="item?id=${item.id}&action=edit">Edit</a></td>
                </c:if>
                <c:if test="${role == 'user'}">
                    <td><a href="item?id=${item.id}&action=buy">Buy</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</section>

</body>
</html>