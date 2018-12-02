<%@ page import="org.springframework.web.bind.annotation.RequestParam" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Person List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<h1>Person List</h1>

<br/><br/>
<div>
    <%--
    <table border="1">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
        <c:forEach  items="${users}" var ="user">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
            </tr>
        </c:forEach>
    </table>

комментарий --%>

    <form method="post" action="addTable">
        <select name="firstName">
        <c:forEach items="${users}" var="user">
            <option value="${user.firstName}"><c:out value="${user.firstName}" /></option>
        </c:forEach>
        </select>
        <select name="lastName">
            <c:forEach items="${users}" var="user">
                <option value="${user.lastName}"><c:out value="${user.lastName}" /></option>
            </c:forEach>
        </select>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>

</html>