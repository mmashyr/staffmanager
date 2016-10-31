<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Mark
  Date: 15.10.2016
  Time: 16:18
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
<b>First Name:</b> ${worker.firstName} <br>
<b>Second Name:</b> ${worker.lastName} <br>
<h4>Tasks:</h4>

<table>
    <thead>
    <tr>
        <td>Title</td>
        <td>Description</td>
        <td>Deadline</td>
        <td>Remove task</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="task" items="${worker.tasks}">
        <tr>
            <c:url var="infoAboutTask" value="/tasks/${task.id}/workers"/>
            <td><a class = "helo" href="${infoAboutTask}" >${task.title}</a></td>
            <td id="description"><c:out value="${task.description}"></c:out></td>
            <td>${task.deadline}</td>
            <td>
                <c:url var="removeTask" value="/workers/${worker.id}/tasks/${task.id}"/>
                <form:form action="${removeTask}" method="DELETE">
                    <input type="submit" value="Remove Task">
                </form:form>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>

<fieldset>
    <legend>Choose task to be added</legend>
    <c:url var="addExistingTask" value="/workers/${worker.id}/tasks/add/"/>
    <form:form method="POST" action="${addExistingTask}" modelAttribute="task">
        <table>
            <tr>
                <td>Task :</td>
                <td><form:select path="id">
                    <form:options items="${tasksList}" itemValue="id" itemLabel="title"/>
                </form:select>
                    <button class="btn btn-reg">Add task</button>
                </td>
            </tr>
            <tr>
            </tr>
        </table>
    </form:form>
</fieldset>
<c:url var="createNewTask" value="/tasks/add"/>
Or you can <a href="${createNewTask}">create a new Task.</a> <br>


<c:url value="/workers/" var="workersList"/>
<a href="${workersList}">Get back to the list of all workers</a>

</body>
</html>
