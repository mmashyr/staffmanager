<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 15.10.2016
  Time: 17:21
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
        <td>First Name</td>
        <td>Last Name</td>
        <td>Delete</td>
        <td>Edit</td>
        <td>Manage worker's tasks</td>
    </tr>
    </thead>

    <c:forEach var="worker" items="${workersList}">
        <tr>
            <td><c:out value="${worker.firstName}">erne</c:out></td>
            <td><c:out value="${worker.lastName}">erger</c:out></td>
            <td>
                <c:url var="deleteWorker" value="/workers/${worker.id}"/>
                <form:form action="${deleteWorker}" method="DELETE">
                    <input type="submit" value="Delete">
                </form:form>
            </td>
            <td>
                <c:url var="editWorker" value="/workers/edit/${worker.id}"/>
                <form:form action="${editWorker}" method="GET">
                    <input type="submit" value="Edit">
                </form:form>
            </td>
            <td>
                <c:url var="showWorkerTasks" value="/workers/${worker.id}/tasks"/>
                <form:form action="${showWorkerTasks}" method="GET">
                    <input type="submit" value="Manage worker's tasks">
                </form:form>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="<c:url value="/workers/add" />">Add New Employee</a>


</body>
</html>
