<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 12.11.2016
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <mytags:navbar/>
</head>
<body>
<c:url var="register" value="/registration"/>
<fieldset>
    <legend>Create new account</legend>
    <form:form modelAttribute="user" method="POST" action="${register}">

    <label for="login"> Login: </label>
    <form:input path="login"/>
    <form:errors path="login" cssClass="error"/><br>

    <label for="password">Password: </label>
    <form:input path="password"/>
    <form:errors path="password" cssClass="error"/><br>

    <select name="chosenRole">
        <c:forEach items="${roleNames}" var="roleName">
            <option>
                <c:out value="${roleName}"/>
            </option>
        </c:forEach>
    </select>

    <input type="submit" value="Submit"/>
</fieldset>
</form:form>
</body>