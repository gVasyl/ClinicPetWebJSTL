<%--
  Created by IntelliJ IDEA.
  User: Vasyl Gotsuliak
  Date: 04.02.2018
  Time: 0:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Pet Clinic</title>
</head>
<body>

    <a href="${pageContext.servletContext.contextPath}/views/client/AddClient.jsp">Add client</a>
    <a href="${pageContext.servletContext.contextPath}/views/client/SearchClient.jsp">Search client</a>

    <table border="2px">
        <tr>
            <th>Client name</th>
            <th>Pet name</th>
            <th>Kind of pet</th>
            <th>Action</th>
        </tr>
        <%--@elvariable id="clients" type="java.util.List"--%>
        <c:forEach items="${clients}" var="client" varStatus="status">
            <tr valign="top">
                <td>${client.clientName}</td>
                <td>${client.petName}</td>
                <td>${client.kindOfPet}</td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/client/edit?id=${client.id}">Edit</a>
                    <a href="${pageContext.servletContext.contextPath}/client/delete?id=${client.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
