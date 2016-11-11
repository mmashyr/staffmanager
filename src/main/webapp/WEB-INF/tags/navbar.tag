<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <link href="<c:url value="/resources/css/navbarStyle.css" />" rel="stylesheet">
</head>
<body>

<ul>
    <li><a href="<c:url value="/workers"/>">Manage workers</a></li>
    <li><a href="<c:url value="/tasks"/>">Manage tasks</a></li>
    <c:url value="/logout" var="logout"/>
    <form:form action="${logout}" method="post">
        <input id="logout" type="submit" value="Log out">
    </form:form>
</ul>
</body>
</html>