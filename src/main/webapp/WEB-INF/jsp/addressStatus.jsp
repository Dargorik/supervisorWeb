<%@ page import="supervisorweb.domain.Street" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Person List</title>
    <style>
        .b1 {
            background: beige; /* Синий цвет фона */
            color: black; /* Белые буквы */
            font-size: 9pt; /* Размер шрифта в пунктах */
        }
    </style>
</head>
<body>
<div name="back">
    <button class="b1" onclick="location.href='/'">Назад</button>
</div>

<div name="mess">
    <h2>Address status list</h2>
    ${message}
</div>
<form name="sel">
    Select the type of work to indicate their due date:
    <select required id="typeOfWorkSelect">
        <option disabled <c:if test="${typeOfWorkFilter==0}"> selected </c:if> value="all"><c:out
                value="Select"/></option>
        <c:forEach items="${typesOfWork}" var="typeOfWork">
            <option  <c:if test="${typeOfWork.idTypeOfWork==typeOfWorkFilter}"> selected </c:if>
                    value="${typeOfWork.idTypeOfWork}">
                <c:out value="${typeOfWork.name}"/>
            </option>
        </c:forEach>
    </select>
    <input type="checkbox" id="relevance" name="relevance" <c:if test="${relevance=='true'}"> checked </c:if>> only
    relevance
</form>

<table border="1" id="t">
    <tr>
        <th hidden>id</th>
        <th><select id="citySelect" name="City">
            <option name="0" value="all"  <c:if test="${cityFilter==0}"> selected </c:if>  ><c:out
                    value="All city"/></option>
            <c:forEach items="${cities}" var="city">
                <option name="${city.idCity}" value="${city.name}" <c:if
                        test="${city.idCity==cityFilter}"> selected </c:if> >
                    <c:out value="${city.name}"/>
                </option>
            </c:forEach>
        </select></th>
        <td><select id="streetSelect" name="Street">
            <option name="0" value="all"  <c:if test="${streetFilter==0}"> selected </c:if>  ><c:out
                    value="All street"/></option>
            <c:forEach items="${streets}" var="street">
                <option name="${street.idStreet}" value="${street.name}" <c:if
                        test="${street.idStreet==streetFilter}"> selected </c:if>>
                    <c:out value="${street.name}"/>
                </option>
            </c:forEach>
        </select></td>
        <th>House number</th>
        <th>Number of floors</th>
        <th>Number of entrances</th>
        <th><select id="regionSelect" name="Region">
            <option name="0" value="all"  <c:if test="${regionFilter==0}"> selected </c:if>>
                <c:out value="All region"/>
            </option>
            <c:forEach items="${regions}" var="region">
                <option name="${region.idRegion}" value="${region.name}" <c:if
                        test="${region.idRegion==regionFilter}"> selected </c:if>>
                    <c:out value="${region.name}"/>
                </option>
            </c:forEach>
        </select></th>
        <th><select id="prioritySelect" name="Priority">
            <option name="0" value="all"  <c:if test="${priorityFilter==0}"> selected </c:if>>
                <c:out value="All priority"/>
            </option>
            <c:forEach items="${priorities}" var="priority">
                <option name="${priority.idPriorityList}" value="${priority.name}" <c:if
                        test="${priority.idPriorityList==priorityFilter}"> selected </c:if>>
                    <c:out value="${priority.name}"/>
                </option>
            </c:forEach>
        </select></th>
        <th>Last data</th>
    </tr>
    <c:forEach items="${lastCompletedDateAddresses}" var="lastCompletedDateAddress" varStatus="ind">
        <tr>
            <input type="hidden" name="idAddress" value="${lastCompletedDateAddress.address.idAddress}">
            <td id="city${ind.index+1}">${lastCompletedDateAddress.address.city.name}</td>
            <td id="street${ind.index+1}">${lastCompletedDateAddress.address.street.name}</td>
            <td>${lastCompletedDateAddress.address.houseNumber}</td>
            <td>${lastCompletedDateAddress.address.numberFloors}</td>
            <td>${lastCompletedDateAddress.address.numberEntrances}</td>
            <td id="region${ind.index+1}">${lastCompletedDateAddress.address.region.name}</td>
            <td id="priority${ind.index+1}">${lastCompletedDateAddress.address.priorityList.name}</td>
            <td><input readonly id="date${ind.index+1}" value="${lastCompletedDateAddress.date}"></td>
        </tr>
    </c:forEach>
</table>
<script>
    var typeOfWorkSelect = document.getElementById("typeOfWorkSelect");
    var citySelect = document.getElementById("citySelect");
    var streetSelect = document.getElementById("streetSelect");
    var regionSelect = document.getElementById("regionSelect");
    var prioritySelect = document.getElementById("prioritySelect");
    var relevance = document.getElementById("relevance");

    function relevanceCheck(e) {
        var enabled = e.target.checked;
        if (typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].value != "all")
            location.href = "/status?" + chekSelects()
        else
            relevance.checked=false;
    }


    function Filter() {
        var sellCity = citySelect.options[citySelect.selectedIndex];
        var sellStreet = streetSelect.options[streetSelect.selectedIndex];
        var sellRegion = regionSelect.options[regionSelect.selectedIndex];
        var sellPriority = prioritySelect.options[prioritySelect.selectedIndex];
        var table = document.getElementById("t");
        var allRows = table.getElementsByTagName("tr");
        for (var index in allRows) {
            if (index > 0) {
                var fieldCity = document.getElementById("city" + index);
                var fieldStreet = document.getElementById("street" + index);
                var fieldregion = document.getElementById("region" + index);
                var fieldPriority = document.getElementById("priority" + index);
                if (fieldCity != null && fieldStreet != null && fieldregion != null && fieldPriority != null) {
                    allRows[index].removeAttribute("hidden");
                    if (!fieldCity.innerHTML.startsWith(sellCity.value) && !sellCity.value.startsWith("all")) {
                        allRows[index].setAttribute("hidden", "hidden")
                    }
                    else if (!fieldStreet.innerHTML.startsWith(sellStreet.value) && !sellStreet.value.startsWith("all")) {
                        allRows[index].setAttribute("hidden", "hidden")
                    }
                    else if (!fieldregion.innerHTML.startsWith(sellRegion.value) && !sellRegion.value.startsWith("all")) {
                        allRows[index].setAttribute("hidden", "hidden")
                    }
                    else if (!fieldPriority.innerHTML.startsWith(sellPriority.value) && !sellPriority.value.startsWith("all")) {
                        allRows[index].setAttribute("hidden", "hidden")
                    }
                }
                if (typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].value == "all") {
                    //alert("33333")
                    document.getElementById("date" + index).setAttribute("value", "----");
                }
            }
        }

    }

    function chekSelects() {
        var str = "";
        if (typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].getAttribute("name") != 0)
            str += "&typeOfWorkFilter="+ typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].value;
        if (citySelect.options[citySelect.selectedIndex].getAttribute("name") != 0)
            str += "&cityFilter=" + citySelect.options[citySelect.selectedIndex].getAttribute("name");
        if (streetSelect.options[streetSelect.selectedIndex].getAttribute("name") != 0)
            str += "&streetFilter=" + streetSelect.options[streetSelect.selectedIndex].getAttribute("name");
        if (regionSelect.options[regionSelect.selectedIndex].getAttribute("name") != 0)
            str += "&regionFilter=" + regionSelect.options[regionSelect.selectedIndex].getAttribute("name");
        if (prioritySelect.options[prioritySelect.selectedIndex].getAttribute("name") != 0)
            str += "&priorityFilter=" + prioritySelect.options[prioritySelect.selectedIndex].getAttribute("name");
        if (relevance.checked==true)
            str += "&relevance="+relevance.checked;
        return str;

    }

    function selectAction() {
        if (typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].value != "all") {
            location.href = "/status?"+ chekSelects();
        }
    }

    typeOfWorkSelect.addEventListener("change", selectAction);

    citySelect.addEventListener("change", Filter);
    streetSelect.addEventListener("change", Filter);
    regionSelect.addEventListener("change", Filter);
    prioritySelect.addEventListener("change", Filter);
    relevance.addEventListener("click", relevanceCheck);

    Filter();
</script>

</body>
</html>