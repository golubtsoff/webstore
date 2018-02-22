<%--
  Created by IntelliJ IDEA.
  User: golubtsov
  Date: 16.02.2018
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Items</title>
</head>
<body>
<section>
    <%--<a href="items?action=add"><img src="img/add.png"></a>--%>
    <a href="items?action=add">Add item</a>

    <br />
    <table border="1" cellpadding="8" cellspacing="0" style="margin: auto">
        <tr>
            <th>Item</th>
            <th>Price</th>
            <th>Amount</th>
            <th></th>
            <th></th>
        </tr>
        <jsp:useBean id="items" scope="request" type="java.util.List"/>
        <c:forEach items="${items}" var="item">
            <jsp:useBean id="item" type="entity.Item"/>
            <tr>
                <td><a href="item?id=${item.id}&action=view">${item.title}</a></td>
                <td>${item.price}</td>
                <td>${item.amount}</td>
                <%--<td><a href="item?id=${item.id}&action=delete"><img src="img/delete.png"></a></td>--%>
                <%--<td><a href="item?id=${item.id}&action=edit"><img src="img/pencil.png"></a></td>--%>
                <td><a href="item?id=${item.id}&action=delete">Delete</a></td>
                <td><a href="item?id=${item.id}&action=edit">Edit</a></td>
            </tr>
        </c:forEach>
    </table>
</section>

</body>
</html>