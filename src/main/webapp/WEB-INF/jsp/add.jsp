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
    <button class="b1" onclick="location.href='/tables/position'">Таблица должностей</button>
</div>
<div>
    <button class="b1" onclick="location.href='/add/User'">Таблица работников</button>
</div>
<div>
    <button class="b1" onclick="location.href='/tables/city'">Таблица городов</button>
</div>
<div>
    <button class="b1" onclick="location.href='/tables/street'">Таблица улиц</button>
</div>
<div>
    <button class="b1" onclick="location.href='/tables/priorityList'">Таблица приоритетности</button>
</div>
<div>
    <button class="b1" onclick="location.href='/tables/region'">Таблица регионов</button>
</div>
<div>
    <button class="b1" onclick="location.href='/tables/address'">Таблица адресов</button>
</div>
<div>
    <button class="b1" onclick="location.href='/add/UserRegions'">Задать работникам регионы</button>
</div>
<div>
    <button class="b1" onclick="location.href='/tables/typeOfWorkPerformed'">Задать виды выполняемых работ</button>
</div>
<div>
    <button class="b1" onclick="location.href='/tables/typeOfWork'">Задать тип работ</button>
</div>
<div>
    <button class="b1" onclick="location.href='/tables/listTypesInPerfomedWork'">Задать работы для видов выполнения</button>
</div>
<div>
    <button class="b1" onclick="location.href='/tables/positionDuties'">Задать типы работ для должностей</button>
</div>



<div>
    <button class="b1" onclick="location.href='/add/CompletedWorks'">Добавить выполненную работу</button>
</div>

<%--<div>
    <button class="b1" onclick="location.href='/add/City'">Таблица работников</button>
</div>--%>

</html>