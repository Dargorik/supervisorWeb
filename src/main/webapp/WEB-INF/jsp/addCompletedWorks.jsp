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
    <h2>Добавление выполненных работ</h2>
    ${message}
</div>

<form name="sel">
    Выберите тип работы:
    <select required name="selec">
        <option disabled <c:if test="${idTypeOfWP==0}"> selected </c:if> value="all"><c:out
                value="Выберите из вариантов"/></option>
        <c:forEach items="${typesOfWorkPerformed}" var="typeOfWorkPerformed">
            <option <c:if test="${typeOfWorkPerformed.idTypeOfWorkPerformed==idTypeOfWP}"> selected </c:if>
                    value="${typeOfWorkPerformed.idTypeOfWorkPerformed}"><c:out
                    value="${typeOfWorkPerformed.name}"/></option>
        </c:forEach>
    </select>
</form>

<table hidden  border="1" id="t">
    <tr>
        <th hidden>id</th>
        <th><select id="citySelect" name="City">
            <option value="all"  <c:if test="${cityFiler==0}"> selected </c:if>  ><c:out value="Все города"/></option>
            <c:forEach items="${cities}" var="city">
                <option value="${city.name}" <c:if test="${city.idCity==cityFiler}"> selected </c:if> ><c:out
                        value="${city.name}"/></option>
            </c:forEach>
        </select></th>
        <td><select id="streetSelect" name="Street">
            <option value="all"  <c:if test="${cityFiler==0}"> selected </c:if>  ><c:out value="Все улицы"/></option>
            <c:forEach items="${streets}" var="street">
                <option value="${street.name}" <c:if test="${street.idStreet==streetFiler}"> selected </c:if>><c:out
                        value="${street.name}"/></option>
            </c:forEach>
        </select></td>
        <th>Номер дома</th>
        <th>Кол-во выполненных подъездов</th>
        <th>Комментарий</th>
        <th>Отправить</th>
        <th hidden>флаг</th>
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
                    <button name="but" value="" type=submit>Выполненно</button></td>
                <td hidden>
                    <input type="text" id="${ind.index+1}" name="idTypeOfWorkPerformed" value="${ind.index+10}">
                    <input type="text" id="cf${ind.index+1}" name="cityFiler" value="${cityFiler}">
                    <input type="text" id="sf${ind.index+1}" name="streetFiler" value="${streetFiler}">
                </td>
            </form>
        </tr>
    </c:forEach>
</table>
<script>
    var selection = document.getElementById("selection");
    var languagesSelect = sel.selec;
    var citySelect = document.getElementById("citySelect");
    var streetSelect = document.getElementById("streetSelect");

    function openTable() {
        var selectedOption = languagesSelect.options[languagesSelect.selectedIndex];
        var table = document.getElementById("t");
        if (!selectedOption.value.startsWith("all"))
            table.removeAttribute("hidden")
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
    languagesSelect.addEventListener("change", changeOption);


    function cityFilter() {
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
                var cityF = document.getElementById("cf" + index);
                cityF.setAttribute("value", citySelect.options[citySelect.selectedIndex].value)
                var streetF = document.getElementById("sf" + index);
                streetF.setAttribute("value", streetSelect.options[streetSelect.selectedIndex].value)
            }
        }
    }

    citySelect.addEventListener("change", cityFilter);
    streetSelect.addEventListener("change", cityFilter);

    changeOption();
    openTable();
    cityFilter();
</script>

</body>
</html>