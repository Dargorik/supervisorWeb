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
<div>
    <button class="b1" onclick="location.href='/'">Назад</button>
</div>
<h2>List of all completed work</h2>
${message}
<div>
    From:
    <input id="fromeData" name="fromeData" type="date" value="${fromeData}">
    To:
    <input id="toData" name="toData" type="date" value="${toData}">
    <input hidden type="text" id="uf" name="userFilter" value="${userFilter}">
    <input hidden type="text" id="rf" name="regionFilter" value="${regionFilter}">
    <input hidden type="text" id="cf" name="cityFilter" value="${cityFilter}">
    <input hidden type="text" id="sf" name="streetFilter" value="${streetFilter}">
    <input hidden type="text" id="tOfw" name="typeOfWorkPerformedFilter" value="${typeOfWorkPerformedFilter}">
</div>
<table border="1" id="t">
    <tr>
        <th hidden>id</th>
        <th><select id="userSelect" name="User">
            <option name="0" value="all"  <c:if test="${userFilter==0}"> selected </c:if>  ><c:out
                    value="All employee"/></option>
            <c:forEach items="${users}" var="user">
                <option name="${user.idusers}" value="${user.firstName} ${user.lastName}" <c:if
                        test="${user.idusers==userFilter}"> selected </c:if>>
                    <c:out value="${user.firstName} ${user.lastName}"/>
                </option>
            </c:forEach>
        </select></th>
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
        <th>Number of entrances completed</th>
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
        <th><select id="typeOfWorkPerformedSelect" name="typeOfWorkPerformed">
            <option name="0" value="all"  <c:if test="${typeOfWorkPerformedFilter==0}"> selected </c:if>>
                <c:out value="All types of work performed"/>
            </option>
            <c:forEach items="${typesOfWorkPerformed}" var="typeOfWorkPerformed">
                <option name="${typeOfWorkPerformed.idTypeOfWorkPerformed}" value="${typeOfWorkPerformed.name}" <c:if
                        test="${typeOfWorkPerformed.idTypeOfWorkPerformed==typeOfWorkPerformedFilter}"> selected </c:if>>
                    <c:out value="${typeOfWorkPerformed.name}"/>
                </option>
            </c:forEach>
        </select></th>
        <th>Comment</th>
        <th>Date</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${completedWorks}" var="completedWork" varStatus="ind">
        <tr>
            <form method="post" action="deletePosition">
                <input type="hidden" name="_csrf" value=${_csrf.token}/>
                <td hidden>${completedWork.idCompletedWork}</td>
                <td id="user${ind.index+1}">${completedWork.user.firstName} ${completedWork.user.lastName}</td>
                <td id="city${ind.index+1}">${completedWork.address.city.name}</td>
                <td id="street${ind.index+1}">${completedWork.address.street.name}</td>
                <td>${completedWork.address.houseNumber}</td>
                <td>${completedWork.address.numberFloors}</td>
                <td>${completedWork.address.numberEntrances}</td>
                <td>${completedWork.numberCompletedEntrances}</td>
                <td id="region${ind.index+1}">${completedWork.address.region.name}</td>
                <td id="typeOfWorkPerformed${ind.index+1}">${completedWork.typeOfWorkPerformed.name}</td>
                <td>${completedWork.comment}</td>
                <td>${completedWork.date}</td>
                <td><a href="&delId=${completedWork.idCompletedWork}" onclick="update(${completedWork.idCompletedWork}); return false;">Delete</a></td>
            </form>
        </tr>
    </c:forEach>
</table>
<script>
    function update(x) {
        var str = "";
        if (fromeData.value != null && fromeData.value != "")
            str += "&fromeData=" + fromeData.value;
        if (toData.value != null && toData.value != "")
            str += "&toData=" + toData.value;
        if (userSelect.options[userSelect.selectedIndex].getAttribute("name") != 0)
            str += "&userFilter=" + userSelect.options[userSelect.selectedIndex].getAttribute("name");
        if (regionSelect.options[regionSelect.selectedIndex].getAttribute("name") != 0)
            str += "&regionFilter=" + regionSelect.options[regionSelect.selectedIndex].getAttribute("name");
        if (citySelect.options[citySelect.selectedIndex].getAttribute("name") != 0)
            str += "&cityFilter=" + citySelect.options[citySelect.selectedIndex].getAttribute("name");
        if (streetSelect.options[streetSelect.selectedIndex].getAttribute("name") != 0)
            str += "&streetFilter=" + streetSelect.options[streetSelect.selectedIndex].getAttribute("name");
        if (typeOfWorkPerformedSelect.options[typeOfWorkPerformedSelect.selectedIndex].getAttribute("name") != 0)
            str += "&typeOfWorkPerformedFilter=" + typeOfWorkPerformedSelect.options[typeOfWorkPerformedSelect.selectedIndex].getAttribute("name");
        if (x != null && x > 0) {
            var isDelete = confirm("Are you sure you want to delete the record?");
            if(isDelete==true)
                location.href = "/monitoring/delete?" + "&delId=" + x + str;
            return;
        }
        location.href = "/monitoring?" + str;
    }

    var fromeData = document.getElementById("fromeData");
    var toData = document.getElementById("toData");

    var userSelect = document.getElementById("userSelect");
    var citySelect = document.getElementById("citySelect");
    var streetSelect = document.getElementById("streetSelect");
    var regionSelect = document.getElementById("regionSelect");
    var typeOfWorkPerformedSelect = document.getElementById("typeOfWorkPerformedSelect");

    function Filter() {
        var sellCity = citySelect.options[citySelect.selectedIndex];
        var sellStreet = streetSelect.options[streetSelect.selectedIndex];
        var sellUser = userSelect.options[userSelect.selectedIndex];
        var sellRegion = regionSelect.options[regionSelect.selectedIndex];
        var sellTypeOfWorkPerformed = typeOfWorkPerformedSelect.options[typeOfWorkPerformedSelect.selectedIndex];
        var table = document.getElementById("t");
        var allRows = table.getElementsByTagName("tr");
        for (var index in allRows) {
            if (index > 0) {
                var fieldUser = document.getElementById("user" + index);
                var fieldCity = document.getElementById("city" + index);
                var fieldStreet = document.getElementById("street" + index);
                var fieldregion = document.getElementById("region" + index);
                var fieldTypeOfWorkPerformed = document.getElementById("typeOfWorkPerformed" + index);
                //alert(fieldUser.innerHTML+"---"+sellUser.value+"+++"+fieldregion.innerHTML+"---"+sellRegion.value);
                if (fieldUser != null && fieldCity != null && fieldStreet != null && fieldregion != null && fieldTypeOfWorkPerformed != null) {
                    allRows[index].removeAttribute("hidden");
                    if (!fieldUser.innerHTML.startsWith(sellUser.value) && !sellUser.value.startsWith("all")) {
                        allRows[index].setAttribute("hidden", "hidden")
                    } else if (!fieldCity.innerHTML.startsWith(sellCity.value) && !sellCity.value.startsWith("all")) {
                        allRows[index].setAttribute("hidden", "hidden")
                    }
                    else if (!fieldStreet.innerHTML.startsWith(sellStreet.value) && !sellStreet.value.startsWith("all")) {
                        allRows[index].setAttribute("hidden", "hidden")
                    }
                    else if (!fieldregion.innerHTML.startsWith(sellRegion.value) && !sellRegion.value.startsWith("all")) {
                        allRows[index].setAttribute("hidden", "hidden")
                    }
                    else if (!fieldTypeOfWorkPerformed.innerHTML.startsWith(sellTypeOfWorkPerformed.value) && !sellTypeOfWorkPerformed.value.startsWith("all")) {
                        allRows[index].setAttribute("hidden", "hidden")
                    }
                }
            }
        }
        document.getElementById("uf").setAttribute("value", userSelect.options[userSelect.selectedIndex].getAttribute("name"));
        document.getElementById("rf").setAttribute("value", regionSelect.options[regionSelect.selectedIndex].getAttribute("name"));
        document.getElementById("cf").setAttribute("value", citySelect.options[citySelect.selectedIndex].getAttribute("name"));
        document.getElementById("sf").setAttribute("value", streetSelect.options[streetSelect.selectedIndex].getAttribute("name"));
        document.getElementById("tOfw").setAttribute("value", typeOfWorkPerformedSelect.options[typeOfWorkPerformedSelect.selectedIndex].getAttribute("name"));
    }

    userSelect.addEventListener("change", Filter);
    citySelect.addEventListener("change", Filter);
    streetSelect.addEventListener("change", Filter);
    regionSelect.addEventListener("change", Filter);
    typeOfWorkPerformedSelect.addEventListener("change", Filter);


    fromeData.addEventListener("change", update);
    toData.addEventListener("change", update);

    Filter();
</script>
</body>
</html>