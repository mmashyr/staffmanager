<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 22.10.2016
  Time: 13:44
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
<c:url var="addTask" value="/tasks/add"/>
<fieldset>
    <legend>Add new Task</legend>
    <form:form modelAttribute="task" method="POST" action="${addTask}">

    <label for="title"> Title: </label>
    <form:input path="title"/>
    <form:errors path="title" cssClass="error"/><br>

    <label for="description">Description: </label>
    <form:input path="description"/>
    <form:errors path="description" cssClass="error"/><br>

    <label for="deadline">Deadline: </label>
    <form:input path="deadline"/>
    <form:errors path="deadline" cssClass="error"/><br>

    <input type="submit" value="Submit"/>
</fieldset>
</form:form>

</body>
</html>
