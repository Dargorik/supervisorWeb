<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.lang.Boolean" %>


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
    <div>
        <button class="b1" onclick="location.href='/addTable/'">Back</button>
    </div>
    <h2>Table of positions duties</h2>
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
        <form method="post" action="/tables/add/positionDuties?updId=${beanUp.idPositionDuties}&flag=<%=flag==true?"true":"false"%>">
            <input type="hidden" name="_csrf" value=${_csrf.token} />
            <select name="idPosition">
                <option disabled value="all">
                    <c:out value="Select position"/>
                </option>
                <c:forEach items="${positions}" var="position">
                    <option  value="${position.idPosition}">
                        <c:out value="${position.name}" />
                    </option>
                </c:forEach>
            </select>
            <select name="idTypeOfWork">
                <option disabled value="all">
                    <c:out value="Select type of work"/>
                </option>
                <c:forEach items="${typesOfWork}" var="typeOfWork">
                    <option  value="${typeOfWork.idTypeOfWork}">
                        <c:out value="${typeOfWork.name}" />
                    </option>
                </c:forEach>
            </select>
            <input hidden type="text" id="pf" name="positionFilter" value="${positionFilter}">
            <input hidden type="text" id="tOfWf" name="typeOfWorkFilter" value="${typeOfWorkFilter}">
            <button type="submit">Add</button>
        </form>
    </div>
    <h2>List of positions duties</h2>
    <table border="1" id="t">
            <tr>
                <th hidden>id</th>
                <th><select id="positionSelect" name="Position">
                    <option name="0" value="all" <c:if test="${positionFilter==0}"> selected </c:if>>
                        <c:out value="All positions"/>
                    </option>
                    <c:forEach items="${positions}" var="position">
                        <option name="${position.idPosition}" value="${position.name}"<c:if test="${position.idPosition==positionFilter}"> selected </c:if>>
                            <c:out value="${position.name}"/>
                        </option>
                    </c:forEach>
                </select></th>
                <th><select id="typeOfWorkSelect" name="TypeOfWork">
                    <option name="0" value="all" <c:if test="${typeOfWorkFilter==0}"> selected </c:if>>
                        <c:out value="All topes of work"/>
                    </option>
                    <c:forEach items="${typesOfWork}" var="typeOfWork">
                        <option name="${typeOfWork.idTypeOfWork}" value="${typeOfWork.name}" <c:if test="${typeOfWork.idTypeOfWork==typeOfWorkFilter}"> selected </c:if>>
                            <c:out value="${typeOfWork.name}"/>
                        </option>
                    </c:forEach>
                </select></th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
        <c:forEach  items="${positionsDuties}" var ="positionDuties" varStatus="ind">
            <tr>
                <form name="myForm" method="post" >
                    <input type="hidden" name="_csrf" value=${_csrf.token} />
                        <td hidden>${positionDuties.idPositionDuties}</td>
                        <td id="position${ind.index+1}">${positionDuties.position.name}</td>
                        <td id="typeOfWork${ind.index+1}">${positionDuties.typeOfWork.name}</td>
                        <td><a id="updButtom${ind.index}" href="/tables/positionDuties?updId=${positionDuties.idPositionDuties}&flag=${true}" onclick="displayUpd(${ind.index}); return false;">Update</a></td>
                        <td><a id="delButtom${ind.index}" href="/tables/delete/positionDuties?updId=${beanUp.idPositionDuties}&delId=${positionDuties.idPositionDuties}&flag=<%=flag==true?"true":"false"%>" onclick="display(${ind.index}); return false;">Delete</a></td>
                </form>
            </tr>
        </c:forEach>
    </table>
    <div>
        <form method="post" action="/tables/update/positionDuties?updId=${beanUp.idPositionDuties}" style=<%=someOutput(flag)%> >
            <h2>Update record</h2>
            <input type="hidden" name="_csrf" value=${_csrf.token} />
            <table border="1"  >
                <tr>
                    <th hidden>id</th>
                    <th>Psition</th>
                    <th>Type of work</th>
                    <th>Save</th>
                </tr>
                <tr>
                    <td hidden>${beanUp.idPositionDuties}</td>
                    <td>
                        <select name="updIdPosition">
                            <c:forEach items="${positions}" var="position">
                                <option <c:if test="${beanUp.position.idPosition==position.idPosition}"> selected </c:if> value="${position.idPosition}"><c:out value="${position.name}" /></option>
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
                    <td hidden>
                        <input type="text" id="pfUp" name="positionFilter" value="${positionFilter}">
                        <input type="text" id="tOfWfUp" name="typeOfWorkFilter" value="${typeOfWorkFilter}">
                    </td>
                    <td><button type=submit>Save</button></td>
                </tr>
            </table>
        </form>
    </div>
    <script>
        function display(x){
            var isDelete = confirm("Deleting this entry will delete all entries containing this key. Continue deleting?");
            if(isDelete==true) {
                var url = document.getElementById("delButtom" + x);
                location.href = url.getAttribute("href")+"&positionFilter="+document.getElementById("pf").value+"&typeOfWorkFilter="+document.getElementById("tOfWf").value;
            }
        }
        function displayUpd(x){
            var url = document.getElementById("updButtom" + x);
            location.href = url.getAttribute("href")+"&positionFilter="+document.getElementById("pf").value+"&typeOfWorkFilter="+document.getElementById("tOfWf").value;
        }

        var positionSelect = document.getElementById("positionSelect");
        var typeOfWorkSelect = document.getElementById("typeOfWorkSelect");

        function Filter() {
            var sellPosition = positionSelect.options[positionSelect.selectedIndex];
            var sellTypeOfWork = typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex];
            var table = document.getElementById("t");
            var allRows = table.getElementsByTagName("tr");
            for (var index in allRows) {
                if (index > 0) {
                    var fieldPosition = document.getElementById("position" + index);
                    var fieldTypeOfWork = document.getElementById("typeOfWork" + index);
                    if (fieldPosition != null && fieldTypeOfWork != null) {
                        allRows[index].removeAttribute("hidden");
                        if (!fieldPosition.innerHTML.startsWith(sellPosition.value) && !sellPosition.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                        else
                        if (!fieldTypeOfWork.innerHTML.startsWith(sellTypeOfWork.value) && !sellTypeOfWork.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                    }
                }
            }
            document.getElementById("pf").setAttribute("value", positionSelect.options[positionSelect.selectedIndex].getAttribute("name"));
            document.getElementById("tOfWf").setAttribute("value", typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].getAttribute("name"));
            document.getElementById("pfUp").setAttribute("value", positionSelect.options[positionSelect.selectedIndex].getAttribute("name"));
            document.getElementById("tOfWfUp").setAttribute("value", typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].getAttribute("name"));
        }

        positionSelect.addEventListener("change", Filter);
        typeOfWorkSelect.addEventListener("change", Filter);

        Filter();
    </script>
</body>
</html>