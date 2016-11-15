<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 15.11.2016
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login page</title>

</head>

<body>


<c:url var="loginUrl" value="/login"/>
<form action="${loginUrl}" method="post">
    <c:if test="${param.error != null}">
        <p>Invalid username and password.</p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p>You have been logged out successfully.</p>
    </c:if>

    <label for="username"><i class="fa fa-user"></i></label>
    <input type="text" id="username" name="login" placeholder="Enter Login" required>


    <label for="password"><i></i></label>
    <input type="password" id="password" name="password" placeholder="Enter Password" required>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <input type="submit"
           value="Log in">

</form>


</body>
</html>