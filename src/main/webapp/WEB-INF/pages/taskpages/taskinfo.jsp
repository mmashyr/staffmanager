<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 22.10.2016
  Time: 16:25
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
<b>Title:</b> ${task.title} <br>
<b>Description:</b> ${task.description} <br>
<b>Deadline:</b> ${task.deadline} <br>
<h4>Workers:</h4>

<table>
    <thead>
    <tr>
        <td>First Name</td>
        <td>Second Name</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="worker" items="${task.workers}">
        <tr>
            <c:url var="infoAboutWorker" value="/workers/${worker.id}/tasks"/>
            <td><a class = "helo" href="${infoAboutWorker}">${worker.firstName}</a></td>
            <td id="description"><c:out value="${worker.lastName}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
