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
        <button class="b1" onclick="location.href='/addTable/'">Назад</button>
    </div>
    <h2>Work Type Table</h2>
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
        <form method="post" action="/tables/add/typeOfWork?updId=${beanUp.idTypeOfWork}&flag=<%=flag==true?"true":"false"%>">
            <input type="hidden" name="_csrf" value=${_csrf.token} />
            <input required autofocus type="text" name="name" placeholder="Work type name">
            <button type="submit">Add</button>
        </form>
    </div>
    <h2>List of work type</h2>
    <div>
        <form method="post" action="deletePosition" >
            <input type="hidden" name="_csrf" value=${_csrf.token} />
            <table border="1" >
                <tr>
                    <th hidden>id</th>
                    <th>Work type name</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
                <c:forEach  items="${typesOfWork}" var ="typeOfWork"  varStatus="ind">
                    <tr>
                        <td hidden>${typeOfWork.idTypeOfWork}</td>
                        <td>${typeOfWork.name}</td>
                        <td><a href="/tables/typeOfWork?updId=${typeOfWork.idTypeOfWork}&flag=${true}">Update</a></td>
                        <td><a id="delButtom${ind.index}" href="/tables/delete/typeOfWork?updId=${beanUp.idTypeOfWork}&delId=${typeOfWork.idTypeOfWork}&flag=<%=flag==true?"true":"false"%>"  onclick="display(${ind.index}); return false;">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
    <div>
        <form method="post" action="/tables/update/typeOfWork?updId=${beanUp.idTypeOfWork}" style=<%=someOutput(flag)%> >
            <h2>Update record</h2>
            <input type="hidden" name="_csrf" value=${_csrf.token} />
            <table border="1"  >
                <tr>
                    <th hidden>id</th>
                    <th>Work type name</th>
                    <th>Save</th>
                </tr>
                <tr>
                    <td hidden>${beanUp.idTypeOfWork}</td>
                    <td><input type="text" name="updName" placeholder="Work type name" value="${beanUp.name}"></td>
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