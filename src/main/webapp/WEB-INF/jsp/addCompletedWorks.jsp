<%@ page import="supervisorweb.domain.Street" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
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
    <h2>Adding completed work</h2>
    ${message}
</div>

<form name="sel">
    Select the type of work performed:
    <select required name="selec">
        <option disabled <c:if test="${idTypeOfWP==0}"> selected </c:if> value="all"><c:out
                value="Select"/></option>
        <c:forEach items="${typesOfWorkPerformed}" var="typeOfWorkPerformed">
            <option <c:if test="${typeOfWorkPerformed.idTypeOfWorkPerformed==idTypeOfWP}"> selected </c:if>
                    value="${typeOfWorkPerformed.idTypeOfWorkPerformed}"><c:out
                    value="${typeOfWorkPerformed.name}"/></option>
        </c:forEach>
    </select>
    <input type="checkbox" id="relevance" name="relevance" <c:if test="${relevance=='true'}"> checked </c:if>> only relevance
</form>

<table hidden  border="1" id="t">
    <tr>
        <th hidden>id</th>
        <th><select id="citySelect" name="City">
            <option name="0" value="all"  <c:if test="${cityFilter==0}"> selected </c:if>  ><c:out value="All city"/></option>
            <c:forEach items="${cities}" var="city">
                <option name="${city.idCity}" value="${city.name}" <c:if test="${city.idCity==cityFilter}"> selected </c:if> ><c:out
                        value="${city.name}"/></option>
            </c:forEach>
        </select></th>
        <td><select id="streetSelect" name="Street">
            <option name="0" value="all"  <c:if test="${streetFilter==0}"> selected </c:if>  ><c:out value="All street"/></option>
            <c:forEach items="${streets}" var="street">
                <option name="${street.idStreet}" value="${street.name}" <c:if test="${street.idStreet==streetFilter}"> selected </c:if>><c:out
                        value="${street.name}"/></option>
            </c:forEach>
        </select></td>
        <th>House number</th>
        <th>Number of entrances completed</th>
        <th>Comment</th>
        <th>Action</th>
    </tr>
    <c:forEach  items="${addresses}" var ="address" varStatus="ind" >
        <tr>
            <form name="myForm" method="post" action="/work/add?idUser=${user.idusers}" >
                <input type="hidden" name="_csrf" value=${_csrf.token} />
                <input type="hidden" name="idAddress" value="${address.idAddress}">
                <td id="city${ind.index+1}">${address.city.name}</td>
                <td id="street${ind.index+1}">${address.street.name}</td>
                <td>${address.houseNumber}</td>
                <td>
                    <input type="text" name="numberCompletedEntrances" placeholder="Кол-во выполненных подлъездов">
                </td>
                <td>
                    <input type="text" name="comment" placeholder="Комментарий">
                </td>
                <td>
                    <button name="but" value="" type=submit>add</button></td>
                <td hidden>
                    <input type="text" id="relevance${ind.index+1}" name="relevance" value=${relevance}>>
                    <input type="text" id="${ind.index+1}" name="idTypeOfWorkPerformed" value="${ind.index}">
                    <input type="text" id="cf${ind.index+1}" name="cityFilter" value="${cityFilter}">
                    <input type="text" id="sf${ind.index+1}" name="streetFilter" value="${streetFilter}">
                </td>
            </form>
        </tr>
    </c:forEach>
</table>
<script>
    var languagesSelect = sel.selec;
    var citySelect = document.getElementById("citySelect");
    var streetSelect = document.getElementById("streetSelect");
    var relevance = document.getElementById("relevance");

    function openTable() {
        var selectedOption = languagesSelect.options[languagesSelect.selectedIndex];
        var table = document.getElementById("t");
        if (!selectedOption.value.startsWith("all"))
            table.removeAttribute("hidden")
    }

    function relevanceCheck(e) {
        var enabled = e.target.checked;
        var table = document.getElementById("t");
        var allRows = table.getElementsByTagName("tr");
        for (var index in allRows) {
            if (index > 0) {
                document.getElementById("relevance" + index).setAttribute("value", enabled);
            }
        }
        if(languagesSelect.options[languagesSelect.selectedIndex].value!="all")
            location.href = "/work?idTypeOfWorkPerformed="+languagesSelect.options[languagesSelect.selectedIndex].value+
                "&relevance="+enabled+
                "&cityFilter="+citySelect.options[citySelect.selectedIndex].getAttribute("name")+
                "&streetFilter="+streetSelect.options[streetSelect.selectedIndex].getAttribute("name");
        else
            relevance.checked=false;
    }

    function changeOption() {
        var selectedOption = languagesSelect.options[languagesSelect.selectedIndex];
        if (!selectedOption.value.startsWith("all")) {
            openTable();
            var table = document.getElementById("t");
            var allRows = table.getElementsByTagName("tr");
            for (var index in allRows) {
                if (index > 0) {
                    var p = document.getElementById(index);
                    p.setAttribute("value", selectedOption.value);
                }
            }
        }
    }

    function Filter() {
        var sellCity = citySelect.options[citySelect.selectedIndex];
        var sellStreet = streetSelect.options[streetSelect.selectedIndex];
        var table = document.getElementById("t");
        var allRows = table.getElementsByTagName("tr");
        for (var index in allRows) {
            if (index > 0) {
                var fieldCity = document.getElementById("city" + index);
                var fieldStreet = document.getElementById("street" + index);
                if (fieldCity != null && fieldStreet != null) {
                    allRows[index].removeAttribute("hidden");
                    if (!fieldCity.innerHTML.startsWith(sellCity.value) && !sellCity.value.startsWith("all")) {
                        allRows[index].setAttribute("hidden", "hidden")
                    }
                    else
                    if (!fieldStreet.innerHTML.startsWith(sellStreet.value) && !sellStreet.value.startsWith("all")) {
                        allRows[index].setAttribute("hidden", "hidden")
                    }
                }
                document.getElementById("cf" + index).setAttribute("value", citySelect.options[citySelect.selectedIndex].getAttribute("name"))
                document.getElementById("sf" + index).setAttribute("value", streetSelect.options[streetSelect.selectedIndex].getAttribute("name"))
            }
        }
    }

    function selectAction() {
        if(languagesSelect.options[languagesSelect.selectedIndex].value!="all")
           location.href = "/work?idTypeOfWorkPerformed="+languagesSelect.options[languagesSelect.selectedIndex].value+
                "&relevance="+relevance.checked+
                "&cityFilter="+citySelect.options[citySelect.selectedIndex].getAttribute("name")+
                "&streetFilter="+streetSelect.options[streetSelect.selectedIndex].getAttribute("name");
    }

    languagesSelect.addEventListener("change", selectAction);
    citySelect.addEventListener("change", Filter);
    streetSelect.addEventListener("change", Filter);
    relevance.addEventListener("click",relevanceCheck);

    changeOption();
    openTable();
    Filter();
</script>

</body>
</html>