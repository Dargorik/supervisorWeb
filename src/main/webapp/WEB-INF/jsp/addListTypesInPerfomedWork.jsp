<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Person List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
    <style>
        .b1 {
            background: beige; /* Синий цвет фона */
            color: black; /* Белые буквы */
            font-size: 9pt; /* Размер шрифта в пунктах */
        }
    </style>
</head>
<body>
</body>
<div>
    <button class="b1" onclick="location.href='/addTable/'">Назад</button>
</div>

<h2>Добавление работ входящих в виды выполнения</h2>
${message}
<div>
    <form method="post" action="/add/addListTypesInPerfomedWork">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <select name="typeOfWorkPerformed">
            <c:forEach items="${typesOfWorkPerformed}" var="typeOfWorkPerformed">
                <option value="${typeOfWorkPerformed.name}"><c:out value="${typeOfWorkPerformed.name}" /></option>
            </c:forEach>
        </select><select name="typeOfWork">
        <c:forEach items="${typesOfWork}" var="typeOfWork">
            <option value="${typeOfWork.name}"><c:out value="${typeOfWork.name}" /></option>
        </c:forEach>
    </select>
        <button type="submit">Добавить</button>
    </form>
</div>
<h2>Список работ входящих в виды выполнения</h2>
<div>
    <form method="post" action="deletePosition">
        <input type="hidden" name="_csrf" value=${_csrf.token} />
        <table border="1">
            <tr>
                <th>id</th>
                <th>Работник</th>
                <th>Название региона</th>
            </tr>
            <c:forEach  items="${listsTypesInPerfomedWork}" var ="listTypesInPerfomedWork">
                <tr>
                    <td>${listTypesInPerfomedWork.idListTypesInPerfomedWork}</td>
                    <td>${listTypesInPerfomedWork.typeOfWorkPerformed.name}</td>
                    <td>${listTypesInPerfomedWork.typeOfWork.name}</td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</html>