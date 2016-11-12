<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <mytags:navbar/>
</head>
<body>
<h2>Welcome to the Staff Manager!</h2>
<c:url value="/registration" var="registration"/>
<a href="${registration}">Register new account</a>
</body>
</html>
