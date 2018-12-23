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
    <h2>Table of users</h2>
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
        <form method="post" action="/users/add?updId=${beanUp.idusers}&flag=<%=flag==true?"true":"false"%>">
            <input type="hidden" name="_csrf" value=${_csrf.token} />
            <input required type="text" name="firstName" placeholder="Имя">
            <input required type="text" name="lastName" placeholder="Фамилия">
            <input required type="text" name="username" placeholder="Логин">
            <input required type="text" name="password" placeholder="Пароль">
            <select name="idPosition">
                <option disabled value="all">
                    <c:out value="Select region"/>
                </option>
                <c:forEach items="${positions}" var="position">
                    <option value="${position.idPosition}"><c:out value="${position.name}" /></option>
                </c:forEach>
            </select>
            <input hidden type="text" id="pf" name="positionFilter" value="${positionFilter}">
            <input hidden type="text" id="af" name="activFilter" value="${activFilter}">
            <button type="submit">Добавить</button>
        </form>
    </div>
    <h2>List of users</h2>
    <table border="1"  id="t">
        <tr>
            <th hidden>id</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Login</th>
            <th>Password</th>
            <th><select id="positionSelect" name="Position">
                    <option name="0" value="all"  <c:if test="${positionFilter==0}"> selected </c:if>  ><c:out value="All positions"/></option>
                    <c:forEach items="${positions}" var="position">
                        <option name="${position.idPosition}" value="${position.name}" <c:if test="${position.idPosition==positionFilter}"> selected </c:if>>
                            <c:out value="${position.name}"/>
                        </option>
                    </c:forEach>
                </select></th>
                <th><select id="activitySelect" name="Activity">
                    <option name="0" value="all" selected><c:out value="All activities"/></option>
                    <option name="true" value="true"  <c:if test="${activFilter==1}"> selected </c:if>><c:out value="true"/></option>
                    <option name="false" value="false"  <c:if test="${activFilter==0}"> selected </c:if>><c:out value="false"/></option>
                </select></th>
            <th id="">Activ</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <c:forEach  items="${users}" var ="user"  varStatus="ind">
            <tr>
                <form method="post" action="deletePosition">
                <input type="hidden" name="_csrf" value=${_csrf.token} />
                <td hidden>${user.idusers}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td id="position${ind.index+1}">${user.position.name}</td>
                <td hidden id="activity${ind.index+1}">${user.activ}</td>
                <td><input disabled type="checkbox" name="a" value="true" <c:if test="${user.activ=='true'}"> checked </c:if>></td>
                <td><a id="updButtom${ind.index}" href="/users/list?updId=${user.idusers}&flag=${true}" onclick="displayUpd(${ind.index}); return false;">Update</a></td>
                <td><a id="delButtom${ind.index}" href="/users/delete?updId=${beanUp.idusers}&delId=${user.idusers}&flag=<%=flag==true?"true":"false"%>" onclick="display(${ind.index}); return false;">Delete</a></td>
                </form>
            </tr>
        </c:forEach>
    </table>
    <div>
        <form method="post" action="/users/update?updId=${beanUp.idusers}" style=<%=someOutput(flag)%>>
            <h2>Update record</h2>
            <input type="hidden" name="_csrf" value=${_csrf.token} />
            <table border="1">
                <tr>
                    <th hidden>id</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Login</th>
                    <th>Password</th>
                    <th>Position</th>
                    <th>Activity</th>
                    <th>Save</th>
                </tr>
                <tr>
                    <td hidden>${beanUp.idusers}</td>
                    <td><input required type="text" name="firstName" placeholder="First name" value="${beanUp.firstName}"></td>
                    <td><input required type="text" name="lastName" placeholder="Last name" value="${beanUp.lastName}"></td>
                    <td><input required type="text" name="username" placeholder="Login" value="${beanUp.username}"></td>
                    <td><input required type="text" name="password" placeholder="Password" value="${beanUp.password}"></td>
                    <td><select name="idPosition">
                        <c:forEach items="${positions}" var="position">
                            <option <c:if test="${position.idPosition==beanUp.position.idPosition}"> selected </c:if> value="${position.idPosition}"><c:out value="${position.name}" /></option>
                        </c:forEach>
                    </select></td>
                    <td><input type="checkbox" name="activ" value="true" <c:if test="${beanUp.activ=='true'}"> checked </c:if>></td>
                    <td><button type=submit>Save</button></td>
                    <td hidden>
                        <input type="text" id="pfUp" name="positionFilter" value="${positionFilter}">
                        <input type="text" id="afUp" name="activFilter" value="${activFilter}">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <script>
        function display(x){
            var isDelete = confirm("Deleting this entry will delete all entries containing this key. Continue deleting?");
            if(isDelete==true) {
                var url = document.getElementById("delButtom" + x);
                location.href = url.getAttribute("href")+"&positionFilter="+document.getElementById("pf").value+"&activFilter="+document.getElementById("af").value;
            }
        }
        function displayUpd(x){
            var url = document.getElementById("updButtom" + x);
            location.href = url.getAttribute("href")+"&positionFilter="+document.getElementById("pf").value+"&activFilter="+document.getElementById("af").value;
        }

        var positionSelect = document.getElementById("positionSelect");
        var activitySelect = document.getElementById("activitySelect");

        function Filter() {
            var sellPosition = positionSelect.options[positionSelect.selectedIndex];
            var sellActivity = activitySelect.options[activitySelect.selectedIndex];
            var table = document.getElementById("t");
            var allRows = table.getElementsByTagName("tr");
            for (var index in allRows) {
                if (index > 0) {
                    var fieldPosition = document.getElementById("position" + index);
                    var fieldActivity = document.getElementById("activity" + index);
                    //alert(fieldPosition.innerHTML+"---"+sellPosition.value+"+++"+fieldActivity.innerHTML+"---"+sellActivity.value);
                    if (fieldPosition != null && fieldActivity != null) {
                        allRows[index].removeAttribute("hidden");
                        if (!fieldPosition.innerHTML.startsWith(sellPosition.value) && !sellPosition.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                        else if (!fieldActivity.innerHTML.startsWith(sellActivity.value) && !sellActivity.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                    }
                }
            }
            document.getElementById("pf").setAttribute("value", positionSelect.options[positionSelect.selectedIndex].getAttribute("name"));
            document.getElementById("af").setAttribute("value", activitySelect.options[activitySelect.selectedIndex].getAttribute("name"));
            document.getElementById("pfUp").setAttribute("value", positionSelect.options[positionSelect.selectedIndex].getAttribute("name"));
            document.getElementById("afUp").setAttribute("value", activitySelect.options[activitySelect.selectedIndex].getAttribute("name"));
        }

        positionSelect.addEventListener("change", Filter);
        activitySelect.addEventListener("change", Filter);

        Filter();
    </script>
</body>
</html>