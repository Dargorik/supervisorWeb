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
    <button class="b1" onclick="location.href='/'">Главное меню</button>
</div>
<div>
    <button class="b1" onclick="location.href='/add/Position'">Таблица должностей</button>
</div>
<div>
    <button class="b1" onclick="location.href='/add/User'">Таблица работников</button>
</div>
<div>
    <button class="b1" onclick="location.href='/add/Cities'">Таблица городов</button>
</div>
<div>
    <button class="b1" onclick="location.href='/add/Street'">Таблица улиц</button>
</div>
<div>
    <button class="b1" onclick="location.href='/add/Address'">Таблица адресов</button>
</div>
<%--<div>
    <button class="b1" onclick="location.href='/add/City'">Таблица работников</button>
</div>--%>

</html>