<%@ page import="supervisorweb.domain.Street" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>

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
</body>
<div>
    <button class="b1" onclick="location.href='/'">Назад</button>
</div>

<h2>Добавление работ работ доступных должностям</h2>
${message}
<%--<div>--%>
    <%--<form method="post" action="/add/addCompletedWorks">--%>
        <%--<input type="hidden" name="_csrf" value=${_csrf.token} /><select name="address">--%>
        <%--<c:forEach items="${addresses}" var="address">--%>
            <%--<option value="${address.idAddress}"><c:out value="${address.city.name} ${address.street.name} ${address.houseNumber}" /></option>--%>
        <%--</c:forEach>--%>
        <%--<input type="text" name="numberCompletedEntrances" placeholder="кол-во сделанных подъездов">--%>
        <%--</select>--%>
        <%--<select name="typeOfWorkPerformed">--%>
        <%--<c:forEach items="${typesOfWorkPerformed}" var="typeOfWorkPerformed">--%>
            <%--<option value="${typeOfWorkPerformed.name}"><c:out value="${typeOfWorkPerformed.name}" /></option>--%>
        <%--</c:forEach>--%>
        <%--<input type="text" name="comment" placeholder="комментарий">--%>
        <%--</select>--%>
        <%--<button type="submit">Добавить</button>--%>
    <%--</form>--%>
<%--</div>--%>
<div>
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        Выберите тип работы:
        <select required form="add" name="idTypeOfWorkPerformed" >
            <option disabled selected value=""><c:out value="Выберите из вариантов" /></option>
            <c:forEach items="${typesOfWorkPerformed}" var="typeOfWorkPerformed">
                <option value="${typeOfWorkPerformed.idTypeOfWorkPerformed}"><c:out value="${typeOfWorkPerformed.name}" /></option>
            </c:forEach>
        </select>
        <table border="1">
            <tr>
                <th hidden>id</th>
                <th><select name="City">
                    <c:forEach items="${cities}" var="city">
                        <option value="${city.idCity}" <c:if test="${beanUp.city.idCity==city.idCity}"> selected </c:if> ><c:out value="${city.name}" /></option>
                    </c:forEach>
                </select></th>
                <td><select name="Street">
                    <c:forEach items="${streets}" var="street">
                        <option value="${street.idStreet}" <c:if test="${beanUp.street.idStreet==street.idStreet}"> selected </c:if>><c:out value="${street.name}" /></option>
                    </c:forEach>
                </select></td>
                <th>Номер дома</th>
                <th>Кол-во выполненных подъездов</th>
                <th>Комментарий</th>
                <th>Комментарий</th>
            </tr>
            <c:forEach  items="${addresses}" var ="address" varStatus="ind" >
                <tr>
                    <form id="add" method="post" action="/work/add?idUser=${user.idusers}" />
                    <input form="add" type="hidden" name="_csrf" value=${_csrf.token} />
                    <td hidden>
                        <input form="add" type="text" name="idAddress" value="${address.idAddress}">
                    </td>
                    <td>${address.city.name}</td>
                    <td>${address.street.name}</td>
                    <td>${address.houseNumber}</td>
                    <td>
                        <input form="add" type="text" name="numberCompletedEntrances" placeholder="Кол-во выполненных подлъездов">
                    </td>
                    <td>
                        <input form="add" type="text" name="comment" placeholder="Комментарий">
                    </td>
                     <td><button name="ind" value="${ind.index}" type=submit >Выполненно</button></td>
                </tr>
            </c:forEach>
        </table>
<%--</div>--%>
<%--<script>--%>
    <%--function myFunc(e){--%>
        <%--var row = e.parentElement.parentElement;--%>
        <%--console.log(row.querySelector('input[type=number]').value)--%>
    <%--}--%>
<%--</script>--%>
</html>