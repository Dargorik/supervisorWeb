<%@ page import="org.springframework.web.bind.annotation.RequestParam" %>

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
        <div>
            <button class="b1" onclick="location.href='/addTable/'">Назад</button>
        </div>
        <h2>Добавление адреса</h2>
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
            <form method="post" action="/tables/add/address?updId=${beanUp.idAddress}&flag=<%=flag==true?"true":"false"%>">
                <input type="hidden" name="_csrf" value=${_csrf.token} />
                <select name="idCity">
                    <c:forEach items="${cities}" var="city">
                        <option  value="${city.idCity}"><c:out value="${city.name}" /></option>
                    </c:forEach>
                </select>
                <select name="idStreet">
                    <c:forEach items="${streets}" var="street">
                        <option  value="${street.idStreet}"><c:out value="${street.name}" /></option>
                    </c:forEach>
                </select>
                <input type="text" name="houseNumber" placeholder="Номер дома">
                <input type="text" name="numberFloors" placeholder="Кол-во этажей">
                <input type="text" name="numberEntrances" placeholder="Кол-во подъездов" >
                <select name="idPriorityList">
                    <c:forEach items="${prioritiesList}" var="priorityList">
                        <option value="${priorityList.idPriorityList}"><c:out value="${priorityList.name}" /></option>
                    </c:forEach>
                </select>
                <select name="idRegion">
                    <c:forEach items="${regions}" var="region">
                        <option value="${region.idRegion}"><c:out value="${region.name}" /></option>
                    </c:forEach>
                </select>
                <button type="submit">Добавить</button>
            </form>
        </div>
        <h2>Список адресов</h2>
        <div>
            <form method="post">
                <input type="hidden" name="_csrf" value=${_csrf.token} />
                <table border="1">
                    <tr>
                        <th>id</th>
                        <th>Город</th>
                        <th>Улица</th>
                        <th>Номер дома</th>
                        <th>Кол-во этажей</th>
                        <th>Кол-во подъездов</th>
                        <th>Приоритетность</th>
                        <th>Регион</th>
                        <th>Изменить</th>
                        <th>Удаление</th>
                    </tr>
                    <c:forEach  items="${addresses}" var ="address">
                        <tr>
                            <td>${address.idAddress}</td>
                            <td>${address.city.name}</td>
                            <td>${address.street.name}</td>
                            <td>${address.houseNumber}</td>
                            <td>${address.numberFloors}</td>
                            <td>${address.numberEntrances}</td>
                            <td>${address.priorityList.name}</td>
                            <td>${address.region.name}</td>
                            <td><a href="/tables/address?updId=${address.idAddress}&flag=${true}">Редактирвоать</a></td>
                            <td><a href="/tables/delete/address?updId=${beanUp.idAddress}&delId=${address.idAddress}&flag=<%=flag==true?"true":"false"%>">Удалить</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </form>
        </div>
        <div>
            <form method="post" action="/tables/update/address?updId=${beanUp.idAddress}" style=<%=someOutput(flag)%> >
                <h2>Изменение записи</h2>
                <input type="hidden" name="_csrf" value=${_csrf.token} />
                <table border="1"  >
                    <tr>
                        <th>id</th>
                        <th>Город</th>
                        <th>Улица</th>
                        <th>Номер дома</th>
                        <th>Кол-во этажей</th>
                        <th>Кол-во подъездов</th>
                        <th>Приоритетность</th>
                        <th>Регион</th>
                        <th>Изменить</th>
                    </tr>
                    <tr>
                        <td>${beanUp.idAddress}</td>
                        <td><select name="updCityId">
                            <c:forEach items="${cities}" var="city">
                                <option value="${city.idCity}" <c:if test="${beanUp.city.idCity==city.idCity}"> selected </c:if> ><c:out value="${city.name}" /></option>
                            </c:forEach>
                        </select></td>
                        <td><select name="updStreetId">
                            <c:forEach items="${streets}" var="street">
                                <option value="${street.idStreet}" <c:if test="${beanUp.street.idStreet==street.idStreet}"> selected </c:if>><c:out value="${street.name}" /></option>
                            </c:forEach>
                        </select></td>
                        <td><input type="text" name="updHouseNumber" placeholder="Номер дома" value="${beanUp.houseNumber}"></td>
                        <td><input type="text" name="updNumberFloors" placeholder="Кол-во этажей" value="${beanUp.numberFloors}"></td>
                        <td><input type="text" name="updNumberEntrances" placeholder="Кол-во подъездов" value="${beanUp.numberEntrances}"></td>
                        <td><select name="updPriorityListId">
                            <c:forEach items="${prioritiesList}" var="priorityList">
                                <option <c:if test="${beanUp.priorityList.idPriorityList==priorityList.idPriorityList}"> selected </c:if> value="${priorityList.idPriorityList}"><c:out value="${priorityList.name}" /></option>
                            </c:forEach>
                        </select></td>
                        <td><select name="updRegionId">
                            <c:forEach items="${regions}" var="region">
                                <option <c:if test="${beanUp.region.idRegion==region.idRegion}"> selected </c:if> value="${region.idRegion}"><c:out value="${region.name}" /></option>
                            </c:forEach>
                        </select></td>
                        <td><button type=submit>Обновить</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>