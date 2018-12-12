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

<h2>Добавление региона пользователю</h2>
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
    <form method="post" action="/tables/add/userRegions?updId=${beanUp.idUserRegions}&flag=<%=flag==true?"true":"false"%>">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <select name="idUser">
            <c:forEach items="${users}" var="user">
                <option  value="${user.idusers}"><c:out value="${user.firstName} ${user.lastName}" /></option>
            </c:forEach>
        </select>
        <select name="idRegion">
            <c:forEach items="${regions}" var="region">
                <option  value="${region.idRegion}"><c:out value="${region.name}" /></option>
            </c:forEach>
        </select>
        <button type="submit">Добавить</button>
    </form>
</div>
<h2>Список резрешённых регионов пользователям</h2>
<div>
    <form method="post" >
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <table border="1" >
            <tr>
                <th>id</th>
                <th>Сотрудник</th>
                <th>Название региона</th>
                <th>Изменить</th>
                <th>Удаление</th>
            </tr>
            <c:forEach  items="${usersRegions}" var ="usersRegion">
                <tr>
                    <td>${usersRegion.idUserRegions}</td>
                    <td>${usersRegion.user.firstName} ${usersRegion.user.lastName}</td>
                    <td>${usersRegion.region.name}</td>
                    <td><a href="/tables/userRegions?updId=${usersRegion.idUserRegions}&flag=${true}">Редактирвоать</a></td>
                    <td><a href="/tables/delete/userRegions?updId=${beanUp.idUserRegions}&delId=${usersRegion.idUserRegions}&flag=<%=flag==true?"true":"false"%>">Удалить</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
<div>
    <form method="post" action="/tables/update/userRegions?updId=${beanUp.idUserRegions}" style=<%=someOutput(flag)%> >
        <h2>Изменение резрешённого региона дял сотрудника</h2>
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <table border="1"  >
            <tr>
                <th>id</th>
                <th>Сотрудник</th>
                <th>Наименование региона</th>
                <th>Сохранить</th>
            </tr>
            <tr>
                <td >${beanUp.idUserRegions}</td>
                <td>
                    <select name="updIdUser">
                        <c:forEach items="${users}" var="user">
                            <option <c:if test="${beanUp.user.idusers==user.idusers}"> selected </c:if> value="${user.idusers}"><c:out value="${user.firstName} ${user.lastName}" /></option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select name="updIdRegion">
                        <c:forEach items="${regions}" var="region">
                            <option <c:if test="${beanUp.region.idRegion==region.idRegion}"> selected </c:if> value="${region.idRegion}"><c:out value="${region.name}" /></option>
                        </c:forEach>
                    </select>
                </td>
                <td><button type=submit>Обновить</button></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>