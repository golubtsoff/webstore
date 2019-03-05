<%--
  Created by IntelliJ IDEA.
  User: golubtsov
  Date: 16.02.2018
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="util.DateUtil" %>
<%@ page import="util.CurrencyFormat" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Purchases - Webstore</title>
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
        [<a href="items">Items</a>]&nbsp;[Purchases]<br/>
        <hr>
        <table border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>Buyer</th>
                <th>Item</th>
                <th>Date</th>
                <th>Cost</th>
                <th>Amount</th>
            </tr>
            <jsp:useBean id="purchases" scope="request" type="java.util.List"/>
            <c:forEach items="${purchases}" var="purchase">
                <jsp:useBean id="purchase" type="entity.Purchase"/>
                <tr>
                    <td>${purchase.person.login}</td>
                    <td><a href="item?id=${purchase.item.id}&action=view">${purchase.item.title}</a></td>
                    <%--<td>${purchase.dateTime}</td>--%>
                    <td><%=DateUtil.format(purchase.getDateTime())%></td>
                    <%--<td>${purchase.cost}</td>--%>
                    <td><%=CurrencyFormat.format(purchase.getCost())%></td>
                    <td>${purchase.amount}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</section>
</body>
</html>
