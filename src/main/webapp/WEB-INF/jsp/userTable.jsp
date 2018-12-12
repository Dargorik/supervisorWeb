<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.lang.Boolean" %>


<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Person List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
    <style>
        .b1 {
            background: beige; /* Синий цвет фона */
            color: black; /* Белые буквы */
            font-size: 9pt; /* Размер шрифта в пунктах */
        }
    </style>
</head>
<body>
<div>
    <button class="b1" onclick="location.href='/addTable/'">Назад</button>
</div>

<h2>Добавление должности</h2>
${message}
<%
    Boolean flag=false;
    String s = (String) request.getAttribute("getflag");
    if(s.equals("true"))
        flag=true;
    else
        flag=false;
%>
<%!
    String someOutput(Boolean flag) {
        if (flag==false)
            return "display:none";
        else
            return "display:block";
    }
%>

<div>
    <form method="post" action="/users/add?updId=${beanUp.idusers}&flag=<%=flag==true?"true":"false"%>">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <input type="text" name="firstName" placeholder="Имя">
        <input type="text" name="lastName" placeholder="Фамилия">
        <input type="text" name="username" placeholder="Логин">
        <input type="text" name="password" placeholder="Пароль">
        <select name="idPosition">
            <c:forEach items="${positions}" var="position">
                <option value="${position.idPosition}"><c:out value="${position.name}" /></option>
            </c:forEach>
        </select>
        <button type="submit">Добавить</button>
    </form>
</div>
<h2>Список должностей</h2>
<div>
    <form method="post" action="deletePosition">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <table border="1">
            <tr>
                <th>id</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Логин</th>
                <th>Пароль</th>
                <th>Должность</th>
                <th>Роль</th>
                <th>Активность</th>
                <th>Изменить</th>
                <th>Удаление</th>
            </tr>
            <c:forEach  items="${users}" var ="user">
                <tr>
                    <td>${user.idusers}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.position.name}</td>
                    <td>${user.roles}</td>
                    <td>${user.activ}</td>
                    <td><a href="/users/list?updId=${user.idusers}&flag=${true}">Редактирвоать</a></td>
                    <td><a href="/users/delete?updId=${beanUp.idusers}&delId=${user.idusers}&flag=<%=flag==true?"true":"false"%>">Удалить</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
<div>
    <form method="post" action="/users/update?updId=${beanUp.idusers}" style=<%=someOutput(flag)%> >
        <h2>Изменение записи</h2>
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <table border="1"  >
            <tr>
                <th>id</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Логин</th>
                <th>Пароль</th>
                <th>Должность</th>
                <th>Активность</th>
            </tr>
            <tr>
                <td>${beanUp.idusers}</td>
                <td><input type="text" name="firstName" placeholder="Имя" value="${beanUp.firstName}"></td>
                <td><input type="text" name="lastName" placeholder="Фамилия" value="${beanUp.lastName}"></td>
                <td><input type="text" name="username" placeholder="Логин" value="${beanUp.username}"></td>
                <td><input type="text" name="password" placeholder="Пароль" value="${beanUp.password}"></td>
                <td><select name="idPosition">
                    <c:forEach items="${positions}" var="position">
                        <option value="${position.idPosition}"><c:out value="${position.name}" /></option>
                    </c:forEach>
                </select></td>
                <td>${beanUp.activ}</td>
                <td><button type=submit>Обновить</button></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>