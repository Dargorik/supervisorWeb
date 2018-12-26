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
        <button class="b1" onclick="location.href='/'">Back</button>
        <button class="b1" onclick="location.href='/work/basket/report'">Send report</button>
    </div>
    <div hidden>
        <input type="text" id="cf" name="cityFilter" value="${cityFilter}">
        <input type="text" id="sf" name="streetFilter" value="${streetFilter}">
    </div>
    <h2>List of work done</h2>
    <table border="1" id="t">
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
            <option name="0" value="all"  <c:if test="${cityFilter==0}"> selected </c:if>  ><c:out value="All street"/></option>
            <c:forEach items="${streets}" var="street">
                <option name="${street.idStreet}" value="${street.name}" <c:if test="${street.idStreet==streetFilter}"> selected </c:if>><c:out
                        value="${street.name}"/></option>
            </c:forEach>
        </select></td>
            <th>House number</th>
            <th>Number of entrances completed</th>
            <th>Type of work performed</th>
            <th>Comment</th>
            <th>Delete</th>
        </tr>
        <c:forEach  items="${completedWorks}" var ="completedWork" varStatus="ind">
            <tr>
                <form method="post">
                    <input type="hidden" name="_csrf" value=${_csrf.token} />
                    <td hidden>${completedWork.idCompletedWork}</td>
                    <td hidden>${completedWork.user.firstName} ${completedWork.user.lastName}</td>
                    <td id="city${ind.index+1}">${completedWork.address.city.name}</td>
                    <td id="street${ind.index+1}">${completedWork.address.street.name}</td>
                    <td>${completedWork.address.houseNumber}</td>
                    <td>${completedWork.numberCompletedEntrances}</td>
                    <td>${completedWork.typeOfWorkPerformed.name}</td>
                    <td>${completedWork.comment}</td>
                    <td><a id="delButtom${ind.index}" href="/work/basket/delete?idUser=${user.idusers}&delId=${completedWork.idCompletedWork}" onclick="dell(${ind.index}); return false;">Delete</a></td>
                </form>
            </tr>
        </c:forEach>
    </table>
    <script>
        var citySelect = document.getElementById("citySelect");
        var streetSelect = document.getElementById("streetSelect");

        function dell(x){
            var url = document.getElementById("delButtom" + x);
            location.href = url.getAttribute("href") + "&cityFilter=" + document.getElementById("cf").value + "&streetFilter=" + document.getElementById("sf").value;
        }

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
                }
            }
            document.getElementById("cf").setAttribute("value", citySelect.options[citySelect.selectedIndex].getAttribute("name"))
            document.getElementById("sf").setAttribute("value", streetSelect.options[streetSelect.selectedIndex].getAttribute("name"))
        }

        citySelect.addEventListener("change", cityFilter);
        streetSelect.addEventListener("change", cityFilter);

        cityFilter();
    </script>
</body>
</html>