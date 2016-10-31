<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 22.10.2016
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hi</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <mytags:navbar/>
</head>
<body>
<table>
    <thead>
    <tr>
        <td>Title</td>
        <td>Description</td>
        <td>Deadline</td>
        <td>Delete</td>
        <td>Edit</td>
        <td>Manage assigned workers</td>
    </tr>
    </thead>

    <c:forEach var="task" items="${tasksList}">
        <tr>
            <td><c:out value="${task.title}"></c:out></td>
            <td id="description"><c:out value="${task.description}"></c:out></td>
            <td><c:out value="${task.deadline}"></c:out></td>
            <td>
                <c:url var="deleteTask" value="/tasks/${task.id}"/>
                <form:form action="${deleteTask}" method="DELETE">
                    <input type="submit" value="Delete">
                </form:form>
            </td>
            <td>
                <c:url var="editTask" value="/tasks/edit/${task.id}"/>
                <form:form action="${editTask}" method="GET">
                    <input type="submit" value="Edit">
                </form:form>
            </td>
            <td>
                <c:url var="showTask" value="/tasks/${task.id}/workers"/>
                <form:form action="${showTask}" method="GET">
                    <input type="submit" value="Show attached workers">
                </form:form>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="<c:url value="/tasks/add" />">Add New Task</a>


</body>
</html>
