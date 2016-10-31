<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 23.10.2016
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
<c:url var="editTask" value="/tasks/edit/${task.id}"/>
<fieldset>
    <legend>Edit existing task</legend>
    <form:form modelAttribute="task" method="POST" action="${editTask}">

        First Name:
        <form:input path="title" value="${task.title}"/>
        <form:errors path="title" cssClass="error"/><br>

        Description: <form:input path="description" value="${task.description}"/>
        <form:errors path="description" cssClass="error"/><br>

        Deadline: <form:input path="deadline" value="${task.deadline}"/>
        <form:errors path="deadline" cssClass="error"/><br>


        <input type="submit" value="Submit"/>
    </form:form>
</fieldset>
</body>
</html>