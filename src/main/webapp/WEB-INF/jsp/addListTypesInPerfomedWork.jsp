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
    <form method="post" action="/tables/add/listTypesInPerfomedWork?updId=${beanUp.idListTypesInPerfomedWork}&flag=<%=flag==true?"true":"false"%>">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <select name="idTypeOfWorkPerformed">
            <c:forEach items="${typesOfWorkPerformed}" var="typeOfWorkPerformed">
                <option  value="${typeOfWorkPerformed.idTypeOfWorkPerformed}"><c:out value="${typeOfWorkPerformed.name}" /></option>
            </c:forEach>
        </select>
        <select name="idTypeOfWork">
            <c:forEach items="${typesOfWork}" var="typeOfWork">
                <option  value="${typeOfWork.idTypeOfWork}"><c:out value="${typeOfWork.name}" /></option>
            </c:forEach>
        </select>
        <button type="submit">Добавить</button>
    </form>
</div>
<h2>Список должностей</h2>
<div>
    <form method="post" >
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <table border="1" >
            <tr>
                <th>id</th>
                <th>Название должности</th>
                <th>Название типа работы</th>
                <th>Изменить</th>
                <th>Удаление</th>
            </tr>
            <c:forEach  items="${typesInPerfomedWork}" var ="typeInPerfomedWork">
                <tr>
                    <td>${typeInPerfomedWork.idListTypesInPerfomedWork}</td>
                    <td>${typeInPerfomedWork.typeOfWorkPerformed.name}</td>
                    <td>${typeInPerfomedWork.typeOfWork.name}</td>
                    <td><a href="/tables/listTypesInPerfomedWork?updId=${typeInPerfomedWork.idListTypesInPerfomedWork}&flag=${true}">Редактирвоать</a></td>
                    <td><a href="/tables/delete/listTypesInPerfomedWork?updId=${beanUp.idListTypesInPerfomedWork}&delId=${typeInPerfomedWork.idListTypesInPerfomedWork}&flag=<%=flag==true?"true":"false"%>">Удалить</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
<div>
    <form method="post" action="/tables/update/listTypesInPerfomedWork?updId=${beanUp.idListTypesInPerfomedWork}" style=<%=someOutput(flag)%> >
        <h2>Изменение должности</h2>
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <table border="1"  >
            <tr>
                <th>id</th>
                <th>Наименование вида работы</th>
                <th>Наименование типа работы</th>
                <th>Сохранить</th>
            </tr>
            <tr>
                <td >${beanUp.idListTypesInPerfomedWork}</td>
                <td>
                    <select name="updIdTypeOfWorkPerformed">
                        <c:forEach items="${typesOfWorkPerformed}" var="typeOfWorkPerformed">
                            <option <c:if test="${beanUp.typeOfWorkPerformed.idTypeOfWorkPerformed==typeOfWorkPerformed.idTypeOfWorkPerformed}"> selected </c:if> value="${typeOfWorkPerformed.idTypeOfWorkPerformed}"><c:out value="${typeOfWorkPerformed.name}" /></option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select name="updIdTypeOfWork">
                        <c:forEach items="${typesOfWork}" var="typeOfWork">
                            <option <c:if test="${beanUp.typeOfWork.idTypeOfWork==typeOfWork.idTypeOfWork}"> selected </c:if> value="${typeOfWork.idTypeOfWork}"><c:out value="${typeOfWork.name}" /></option>
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