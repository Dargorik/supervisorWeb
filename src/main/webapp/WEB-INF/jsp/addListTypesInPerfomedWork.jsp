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
        <button class="b1" onclick="location.href='/addTable/'">Back</button></div>
    <h2>Table of types of work performed in the form of execution</h2>
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
        <form method="post" action="/tables/add/listTypesInPerfomedWork?updId=${beanUp.idListTypesInPerfomedWork}&flag=<%=flag==true?"true":"false"%>">
            <input type="hidden" name="_csrf" value=${_csrf.token} />
            <select name="idTypeOfWorkPerformed">
                <option disabled value="all">
                    <c:out value="Select type of work performed"/>
                </option>
                <c:forEach items="${typesOfWorkPerformed}" var="typeOfWorkPerformed">
                    <option  value="${typeOfWorkPerformed.idTypeOfWorkPerformed}">
                        <c:out value="${typeOfWorkPerformed.name}" />
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
            <input hidden type="text" id="tOfWPf" name="typeOfWorkPerformedFilter" value="${typeOfWorkPerformedFilter}">
            <input hidden type="text" id="tOfWf" name="typeOfWorkFilter" value="${typeOfWorkFilter}">
            <button type="submit">Add</button>
        </form>
    </div>
    <h2>List types of work performed in the form of execution</h2>
    <table border="1" id="t">
        <tr>
            <th hidden>id</th>
            <th><select id="typeOfWorkPerformedSelect" name="typeOfWorkPerformed">
                <option name="0" value="all" <c:if test="${typeOfWorkPerformedFilter==0}"> selected </c:if>>
                    <c:out value="All type of work performed"/>
                </option>
                <c:forEach items="${typesOfWorkPerformed}" var="typeOfWorkPerformed">
                    <option name="${typeOfWorkPerformed.idTypeOfWorkPerformed}" value="${typeOfWorkPerformed.name}"<c:if test="${typeOfWorkPerformed.idTypeOfWorkPerformed==typeOfWorkPerformedFilter}"> selected </c:if>>
                        <c:out value="${typeOfWorkPerformed.name}"/>
                    </option>
                </c:forEach>
            </select></th>
            <th><select id="typeOfWorkSelect" name="TypeOfWork">
                <option name="0" value="all" <c:if test="${typeOfWorkFilter==0}"> selected </c:if>>
                    <c:out value="All types of work"/>
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
        <c:forEach  items="${typesInPerfomedWork}" var ="typeInPerfomedWork" varStatus="ind">
            <tr>
                <form name="myForm" method="post" >
                    <input type="hidden" name="_csrf" value=${_csrf.token} />
                    <td hidden>${typeInPerfomedWork.idListTypesInPerfomedWork}</td>
                    <td id="typeOfWorkPerformed${ind.index+1}">${typeInPerfomedWork.typeOfWorkPerformed.name}</td>
                    <td id="typeOfWork${ind.index+1}">${typeInPerfomedWork.typeOfWork.name}</td>
                    <td><a id="updButtom${ind.index}" href="/tables/listTypesInPerfomedWork?updId=${typeInPerfomedWork.idListTypesInPerfomedWork}&flag=${true}" onclick="displayUpd(${ind.index}); return false;">Update</a></td>
                    <td><a id="delButtom${ind.index}" href="/tables/delete/listTypesInPerfomedWork?updId=${beanUp.idListTypesInPerfomedWork}&delId=${typeInPerfomedWork.idListTypesInPerfomedWork}&flag=<%=flag==true?"true":"false"%>" onclick="display(${ind.index}); return false;">Delete</a></td>
                </form>
            </tr>
        </c:forEach>
    </table>
    <div>
        <form method="post" action="/tables/update/listTypesInPerfomedWork?updId=${beanUp.idListTypesInPerfomedWork}" style=<%=someOutput(flag)%> >
            <h2>Update record</h2>
            <input type="hidden" name="_csrf" value=${_csrf.token} />
            <table border="1"  >
                <tr>
                    <th hidden>id</th>
                    <th>Type of work performed</th>
                    <th>Type of work</th>
                    <th>Save</th>
                </tr>
                <tr>
                    <td hidden>${beanUp.idListTypesInPerfomedWork}</td>
                    <td>
                        <select name="updIdTypeOfWorkPerformed">
                            <c:forEach items="${typesOfWorkPerformed}" var="typeOfWorkPerformed">
                                <option <c:if test="${beanUp.typeOfWorkPerformed.idTypeOfWorkPerformed==typeOfWorkPerformed.idTypeOfWorkPerformed}"> selected </c:if> value="${typeOfWorkPerformed.idTypeOfWorkPerformed}"><c:out value="${typeOfWorkPerformed.name}" /></option>
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
                        <input type="text" id="tOfWPfUp" name="typeOfWorkPerformedFilter" value="${typeOfWorkPerformedFilter}">
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
                location.href = url.getAttribute("href")+"&typeOfWorkPerformedFilter="+document.getElementById("tOfWPf").value+"&typeOfWorkFilter="+document.getElementById("tOfWf").value;
            }
        }
        function displayUpd(x){
            var url = document.getElementById("updButtom" + x);
            location.href = url.getAttribute("href")+"&typeOfWorkPerformedFilter="+document.getElementById("tOfWPf").value+"&typeOfWorkFilter="+document.getElementById("tOfWf").value;
        }

        var typeOfWorkPerformedSelect = document.getElementById("typeOfWorkPerformedSelect");
        var typeOfWorkSelect = document.getElementById("typeOfWorkSelect");

        function Filter() {
            var sellTypeOfWorkPerformed = typeOfWorkPerformedSelect.options[typeOfWorkPerformedSelect.selectedIndex];
            var sellTypeOfWork = typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex];
            var table = document.getElementById("t");
            var allRows = table.getElementsByTagName("tr");
            for (var index in allRows) {
                if (index > 0) {
                   // alert("wefsd");
                    var fieldTypeOfWorkPerformed = document.getElementById("typeOfWorkPerformed" + index);
                    var fieldTypeOfWork = document.getElementById("typeOfWork" + index);
                    if (fieldTypeOfWorkPerformed != null && fieldTypeOfWork != null) {
                        allRows[index].removeAttribute("hidden");
                        if (!fieldTypeOfWorkPerformed.innerHTML.startsWith(sellTypeOfWorkPerformed.value) && !sellTypeOfWorkPerformed.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                        else
                        if (!fieldTypeOfWork.innerHTML.startsWith(sellTypeOfWork.value) && !sellTypeOfWork.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                    }
                }
            }
            document.getElementById("tOfWPf").setAttribute("value", typeOfWorkPerformedSelect.options[typeOfWorkPerformedSelect.selectedIndex].getAttribute("name"));
            document.getElementById("tOfWf").setAttribute("value", typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].getAttribute("name"));
            document.getElementById("tOfWPfUp").setAttribute("value", typeOfWorkPerformedSelect.options[typeOfWorkPerformedSelect.selectedIndex].getAttribute("name"));
            document.getElementById("tOfWfUp").setAttribute("value", typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].getAttribute("name"));
        }

        typeOfWorkPerformedSelect.addEventListener("change", Filter);
        typeOfWorkSelect.addEventListener("change", Filter);

        Filter();
    </script>
</body>
</html>