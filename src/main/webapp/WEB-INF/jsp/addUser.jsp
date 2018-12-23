<%--<%@ page import="org.springframework.web.bind.annotation.RequestParam" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" %>--%>

<%--<!DOCTYPE HTML>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta charset="UTF-8" />--%>
    <%--<title>Person List</title>--%>
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>--%>
    <%--<style>--%>
        <%--.b1 {--%>
            <%--background: beige; /* Синий цвет фона */--%>
            <%--color: black; /* Белые буквы */--%>
            <%--font-size: 9pt; /* Размер шрифта в пунктах */--%>
        <%--}--%>
    <%--</style>--%>
<%--</head>--%>
    <%--<body>--%>
        <%--<div>--%>
            <%--<button class="b1" onclick="location.href='/addTable/'">Назад</button>--%>
        <%--</div>--%>
        <%--<h2>Добавление должности</h2>--%>
        <%--${message}--%>
        <%--<div>--%>
            <%--<form method="post" action="/add/addUser">--%>
                <%--<input type="hidden" name="_csrf" value=${_csrf.token} />--%>
                <%--<input type="text" name="firstName" placeholder="Имя">--%>
                <%--<input type="text" name="lastName" placeholder="Фамилия">--%>
                <%--<input type="text" name="username" placeholder="Логин">--%>
                <%--<input type="text" name="password" placeholder="Пароль">--%>
                <%--<select name="position">--%>
                    <%--<c:forEach items="${positions}" var="position">--%>
                        <%--<option value="${position.name}"><c:out value="${position.name}" /></option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
                <%--<button type="submit">Добавить</button>--%>
            <%--</form>--%>
        <%--</div>--%>
        <%--<h2>Список должностей</h2>--%>
        <%--<div>--%>
            <%--<form method="post" action="deletePosition">--%>
                <%--<input type="hidden" name="_csrf" value=${_csrf.token} />--%>
                <%--<table border="1">--%>
                    <%--<tr>--%>
                        <%--<th>id</th>--%>
                        <%--<th>Имя</th>--%>
                        <%--<th>Фамилия</th>--%>
                        <%--<th>Логин</th>--%>
                        <%--<th>Пароль</th>--%>
                        <%--<th>Должность</th>--%>
                        <%--<th>Роль</th>--%>
                        <%--<th>Активность</th>--%>
                    <%--</tr>--%>
                    <%--<c:forEach  items="${users}" var ="user">--%>
                        <%--<tr>--%>
                            <%--<td>${user.idusers}</td>--%>
                            <%--<td>${user.firstName}</td>--%>
                            <%--<td>${user.lastName}</td>--%>
                            <%--<td>${user.username}</td>--%>
                            <%--<td>${user.password}</td>--%>
                            <%--<td>${user.position.name}</td>--%>
                            <%--<td>${user.roles}</td>--%>
                            <%--<td>${user.activ}</td>--%>
                        <%--</tr>--%>
                    <%--</c:forEach>--%>
                <%--</table>--%>
            <%--</form>--%>
        <%--</div>--%>
    <%--</body>--%>
<%--</html>--%>