<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 17.10.2016
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
<c:url var="editWorker" value="/workers/edit/${worker.id}"/>
<fieldset>
    <legend>Edit existing workers's information</legend>
    <form:form modelAttribute="worker" method="POST" action="${editWorker}">

        First Name:
        <form:input path="firstName" value="${worker.firstName}"/>
        <form:errors path="firstName" cssClass="error"/><br>

        Second Name<form:input path="lastName" value="${worker.lastName}"/>
        <form:errors path="lastName" cssClass="error"/><br>

        <input type="submit" value="Submit"/>
    </form:form>
</fieldset>
</body>
</html>