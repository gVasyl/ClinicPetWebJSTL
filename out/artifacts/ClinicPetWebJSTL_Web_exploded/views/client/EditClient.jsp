<%--@elvariable id="client" type="com.models.Client"--%>
<%--
  Created by IntelliJ IDEA.
  User: Vasyl Gotsuliak
  Date: 24.02.2018
  Time: 23:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Edit client</title>
</head>
<body>
    <a href="${pageContext.servletContext.contextPath}/client/view">Main page</a><br/><br/>
    <form action="${pageContext.servletContext.contextPath}/client/edit" method="post">
    Client name:<br>
    <input type="text" name="clientName" value="${client.clientName}"><br>
    Pet name:<br>
    <input type="text" name="petName" value="${client.petName}"><br><br>
    <select size = "1" required size = "1" name = "kindOfPet">
        <option value = "Cat">Cat</option>
        <option value = "Bird">Pet</option>
        <option value = "Dog">Dog</option>
    </select><br/>
    <input type="submit" value="Submit">
</form>
</body>
</html>
