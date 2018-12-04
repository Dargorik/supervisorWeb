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
    System.out.println(request.getAttribute("filter"));
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
    <form method="post" action="/add/City?action=add&upId=${cytiUp.idCity}&flag=<%=flag==true?"true":"false"%>">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <input autofocus type="text" name="name" placeholder="Название города">
        <button type="submit">Добавить</button>
    </form>
</div>
<h2>Список должностей</h2>
<div>
    <form method="post" action="deletePosition" >
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <table border="1" >
            <tr>
                <th>id</th>
                <th>Названия городов</th>
                    <%--<select name="filterName" onChange="location.href='/add/City?filter='+this.options[this.selectedIndex].value+'&upId=${cytiUp.idCity}&flag=<%=flag==true?"true":"false"%>'">
                        <option value="" selected="${filter==null||filter.isEmpty()?"selected":""}" /></option>
                        <c:forEach items="${cities}" var="city">
                            <option selected="${filter eq city.name?"selected":""}" value="${city.name}"><c:out value="${city.name}" /></option>
                        </c:forEach>
                    </select>--%>

                <th>Изменить</th>
                <th>Удаление</th>
            </tr>
            <c:forEach  items="${cities}" var ="city">
                <tr>
                    <td >${city.idCity}</td>
                    <td>${city.name}</td>
                    <td><a href="/add/City?upId=${city.idCity}&flag=${true}">Редактирвоать</a></td></td>
                    <td><a href="/add/City?action=delete&upId=${cytiUp.idCity}&delId=${city.idCity}&flag=<%=flag==true?"true":"false"%>">Удалить</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
<div>
    <form method="post" action="/add/City?action=update&upId=${cytiUp.idCity}" style=<%=someOutput(flag)%> >
        <h2>Изменение записи</h2>
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <table border="1"  >
            <tr>
                <th>id</th>
                <th>Наименование</th>
                <th>Сохранить</th>
            </tr>
            <tr>
                <td >${cytiUp.idCity}</td>
                <td><input type="text" name="upName" placeholder="Название города" value="${cytiUp.name}"></td>
                <td><button type=submit>Обновить</button></td>
            </tr>
        </table>
    </form>
</div>
</html>