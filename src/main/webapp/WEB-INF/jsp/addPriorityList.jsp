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
    <h2>Priority list table</h2>
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
        <form method="post" action="/tables/add/priorityList?updId=${beanUp.idPriorityList}&flag=<%=flag==true?"true":"false"%>">
            <input type="hidden" name="_csrf" value=${_csrf.token} />
            <input required autofocus type="text" name="name" placeholder="Priority name">
            <input required autofocus type="number" name="number" placeholder="Quantity days">
            <button type="submit">Add</button>
        </form>
    </div>
    <h2>List of priorities</h2>
    <div>
        <form method="post"  >
            <input type="hidden" name="_csrf" value=${_csrf.token} />
            <table border="1" >
                <tr>
                    <th hidden>id</th>
                    <th>Priority name</th>
                    <th>Quantity days</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
                <c:forEach  items="${prioritiesList}" var ="priorityList" varStatus="ind">
                    <tr>
                        <td hidden>${priorityList.idPriorityList}</td>
                        <td>${priorityList.name}</td>
                        <td>${priorityList.number}</td>
                        <td><a href="/tables/priorityList?updId=${priorityList.idPriorityList}&flag=${true}">Update</a></td>
                        <td><a id="delButtom${ind.index}" href="/tables/delete/priorityList?action=delete&updId=${beanUp.idPriorityList}&delId=${priorityList.idPriorityList}&flag=<%=flag==true?"true":"false"%>"   onclick="display(${ind.index}); return false;">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
    <div>
        <form method="post" action="/tables/update/priorityList?updId=${beanUp.idPriorityList}" style=<%=someOutput(flag)%> >
            <h2>Update priority</h2>
            <input type="hidden" name="_csrf" value=${_csrf.token} />
            <table border="1"  >
                <tr>
                    <th hidden>id</th>
                    <th>Priority name</th>
                    <th>Quantity days</th>
                    <th>Save</th>
                </tr>
                <tr>
                    <td hidden>${beanUp.idPriorityList}</td>
                    <td><input type="text" name="updName" placeholder="Название города" value="${beanUp.name}"></td>
                    <td><input type="text" name="updNumber" placeholder="Кол-во дней" value="${beanUp.number}"></td>
                    <td><button type=submit>Save</button></td>
                </tr>
            </table>
        </form>
    </div>
    <script>
        function display(x){
            var isDelete = confirm("Deleting this entry will delete all entries containing this key. Continue deleting?");
            if(isDelete==true) {
                var url = document.getElementById("delButtom" + x)
                location.href = url.getAttribute("href");
            }
        }
    </script>
</body>
</html>