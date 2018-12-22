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
    <h2>Table of employee-related regions</h2>
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
        <form method="post" action="/tables/add/userRegions?updId=${beanUp.idUserRegions}&flag=<%=flag==true?"true":"false"%>">
            <input type="hidden" name="_csrf" value=${_csrf.token} />
            <select name="idUser">
                <option disabled value="all">
                    <c:out value="Select employee"/>
                </option>
                <c:forEach items="${users}" var="user">
                    <option  value="${user.idusers}">
                        <c:out value="${user.firstName} ${user.lastName}" />
                    </option>
                </c:forEach>
            </select>
            <select name="idRegion">
                <option disabled value="all">
                    <c:out value="Select region"/>
                </option>
                <c:forEach items="${regions}" var="region">
                    <option  value="${region.idRegion}">
                        <c:out value="${region.name}" />
                    </option>
                </c:forEach>
            </select>
            <input hidden type="text" id="uf" name="userFilter" value="${userFilter}">
            <input hidden type="text" id="rf" name="regionFilter" value="${regionFilter}">
            <button type="submit">Add</button>
        </form>
    </div>
    <h2>List of regions allowed employees</h2>
        <table border="1" id="t">
            <tr>
                <th hidden>id</th>
                <th><select id="userSelect" name="User">
                    <option name="0" value="all"  <c:if test="${userFilter==0}"> selected </c:if>  ><c:out value="All employee"/></option>
                    <c:forEach items="${users}" var="user">
                        <option name="${user.idusers}" value="${user.firstName} ${user.lastName}" <c:if test="${user.idusers==userFilter}"> selected </c:if>>
                            <c:out value="${user.firstName} ${user.lastName}"/>
                        </option>
                    </c:forEach>
                </select></th>
                <th><select id="regionSelect" name="Region">
                    <option name="0" value="all"  <c:if test="${regionFilter==0}"> selected </c:if>  ><c:out value="All region"/></option>
                    <c:forEach items="${regions}" var="region">
                        <option name="${region.idRegion}" value="${region.name}" <c:if test="${region.idRegion==regionFilter}"> selected </c:if>>
                            <c:out value="${region.name}"/>
                        </option>
                    </c:forEach>
                </select></th>
                <th>Update</th>
                <th>Delete</th>
                <%--<th>flag</th>--%>
            </tr>
            <c:forEach  items="${usersRegions}" var ="usersRegion"  varStatus="ind">
                <tr>
                    <form name="myForm" method="post" >
                        <input type="hidden" name="_csrf" value=${_csrf.token} />
                        <td hidden>${usersRegion.idUserRegions}</td>
                        <td id="user${ind.index+1}">${usersRegion.user.firstName} ${usersRegion.user.lastName}</td>
                        <td id="region${ind.index+1}">${usersRegion.region.name}</td>
                        <td><a id="updButtom${ind.index}" href="/tables/userRegions?updId=${usersRegion.idUserRegions}&flag=${true}" onclick="displayUpd(${ind.index}); return false;">Update</a></td>
                        <td><a id="delButtom${ind.index}" href="/tables/delete/userRegions?updId=${beanUp.idUserRegions}&delId=${usersRegion.idUserRegions}&flag=<%=flag==true?"true":"false"%>" onclick="display(${ind.index}); return false;">Delete</a></td>
                    </form>
                </tr>
            </c:forEach>
        </table>
    <div>
        <form method="post" action="/tables/update/userRegions?updId=${beanUp.idUserRegions}" style=<%=someOutput(flag)%> >
            <h2>ИUpdate record</h2>
            <input type="hidden" name="_csrf" value=${_csrf.token} />
            <table border="1">
                <tr>
                    <th hidden>id</th>
                    <th>Employee</th>
                    <th>Region</th>
                    <th>Save</th>
                </tr>
                <tr>
                    <td hidden>${beanUp.idUserRegions}</td>
                    <td>
                        <select name="updIdUser">
                            <c:forEach items="${users}" var="user">
                                <option <c:if test="${beanUp.user.idusers==user.idusers}"> selected </c:if> value="${user.idusers}"><c:out value="${user.firstName} ${user.lastName}" /></option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select name="updIdRegion">
                            <c:forEach items="${regions}" var="region">
                                <option <c:if test="${beanUp.region.idRegion==region.idRegion}"> selected </c:if> value="${region.idRegion}"><c:out value="${region.name}" /></option>
                            </c:forEach>
                        </select>
                    </td>
                    <td hidden>
                        <input type="text" id="ufUp" name="userFilter" value="${userFilter}">
                        <input type="text" id="rfUp" name="regionFilter" value="${regionFilter}">
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
                location.href = url.getAttribute("href")+"&userFilter="+document.getElementById("uf").value+"&regionFilter="+document.getElementById("rf").value;
            }
        }
        function displayUpd(x){
             var url = document.getElementById("updButtom" + x);
             location.href = url.getAttribute("href")+"&userFilter="+document.getElementById("uf").value+"&regionFilter="+document.getElementById("rf").value;
        }

        var userSelect = document.getElementById("userSelect");
        var regionSelect = document.getElementById("regionSelect");

        function Filter() {
            var sellUser = userSelect.options[userSelect.selectedIndex];
            var sellRegion = regionSelect.options[regionSelect.selectedIndex];
            var table = document.getElementById("t");
            var allRows = table.getElementsByTagName("tr");
            for (var index in allRows) {
                if (index > 0) {
                    var fieldUser = document.getElementById("user" + index);
                    var fieldregion = document.getElementById("region" + index);
                    //alert(fieldUser.innerHTML+"---"+sellUser.value+"+++"+fieldregion.innerHTML+"---"+sellRegion.value);
                    if (fieldUser != null && fieldregion != null) {
                        allRows[index].removeAttribute("hidden");
                        if (!fieldUser.innerHTML.startsWith(sellUser.value) && !sellUser.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                        else
                        if (!fieldregion.innerHTML.startsWith(sellRegion.value) && !sellRegion.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                    }
                    // var userF = document.getElementById("uf" + index);
                    // userF.setAttribute("value", userSelect.options[userSelect.selectedIndex].value);
                    // var regionF = document.getElementById("rf" + index);
                    // regionF.setAttribute("value", regionSelect.options[regionSelect.selectedIndex].value);
                   // alert( document.getElementById("uf" + index).value+"---"+document.getElementById("rf" + index).value);
                }
            }
            var userF = document.getElementById("uf");
            userF.setAttribute("value", userSelect.options[userSelect.selectedIndex].getAttribute("name"));
            var regionF = document.getElementById("rf");
            regionF.setAttribute("value", regionSelect.options[regionSelect.selectedIndex].getAttribute("name"));
            var userF = document.getElementById("ufUp");
            userF.setAttribute("value", userSelect.options[userSelect.selectedIndex].getAttribute("name"));
            var regionF = document.getElementById("rfUp");
            regionF.setAttribute("value", regionSelect.options[regionSelect.selectedIndex].getAttribute("name"));
        }

        userSelect.addEventListener("change", Filter);
        regionSelect.addEventListener("change", Filter);

        Filter();
    </script>
</body>
</html>