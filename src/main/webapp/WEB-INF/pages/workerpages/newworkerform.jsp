<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 15.10.2016
  Time: 13:49
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
<c:url var="addWorker" value="/workers/add"/>
<fieldset>
    <legend>Add new Worker</legend>
    <form:form modelAttribute="worker" method="POST" action="${addWorker}">

    <label for="firstName"> First Name: </label>
    <form:input path="firstName"/>
    <form:errors path="firstName" cssClass="error"/><br>

    <label for="lastName">Second Name: </label>
    <form:input path="lastName"/>
    <form:errors path="lastName" cssClass="error"/><br>

    <input type="submit" value="Submit"/>
</fieldset>
</form:form>

</body>
</html>
