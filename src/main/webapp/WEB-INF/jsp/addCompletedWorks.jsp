<%@ page import="supervisorweb.domain.Street" %>
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
            <button class="b1" onclick="location.href='/'">Назад</button>
        </div>

        <div name="mess">
        <h2>Добавление работ работ доступных должностям</h2>
        ${message}
        </div>

        <form name="sel">
            Выберите тип работы:
            <select  required name="selec" >
                <option disabled selected value=""><c:out value="Выберите из вариантов" /></option>
                <c:forEach items="${typesOfWorkPerformed}" var="typeOfWorkPerformed">
                    <option value="${typeOfWorkPerformed.idTypeOfWorkPerformed}"><c:out value="${typeOfWorkPerformed.name}" /></option>
                </c:forEach>
            </select>
        </form>

        <%--<div name="tabl">--%>
                <table  border="1" id="t">
                    <tr>
                        <th hidden>id</th>
                        <th><select name="City">
                            <c:forEach items="${cities}" var="city">
                                <option value="${city.idCity}" <c:if test="${beanUp.city.idCity==city.idCity}"> selected </c:if> ><c:out value="${city.name}" /></option>
                            </c:forEach>
                        </select></th>
                        <td><select name="Street">
                            <c:forEach items="${streets}" var="street">
                                <option value="${street.idStreet}" <c:if test="${beanUp.street.idStreet==street.idStreet}"> selected </c:if>><c:out value="${street.name}" /></option>
                            </c:forEach>
                        </select></td>
                        <th>Номер дома</th>
                        <th>Кол-во выполненных подъездов</th>
                        <th>Комментарий</th>
                        <th>Отправить</th>
                    </tr>
                    <c:forEach  items="${addresses}" var ="address" varStatus="ind" >
                        <tr>
                            <form name="myForm" method="post" action="/work/add?idUser=${user.idusers}" >
                                <input type="hidden" name="_csrf" value=${_csrf.token} />
                                    <input  type="hidden" name="idAddress" value="${address.idAddress}">
                                <td>${address.city.name}</td>
                                <td>${address.street.name}</td>
                                <td>${address.houseNumber}</td>
                                <td>
                                    <input type="text" name="numberCompletedEntrances" placeholder="Кол-во выполненных подлъездов">
                                </td>
                                <td>
                                    <input type="text" name="comment" placeholder="Комментарий">
                                </td>
                                <td><button name="but" value="" type=submit>Выполненно</button></td>

                                <td>
                                    <input  type="text" name="idTypeOfWorkPerformed"  value="non">
                                </td>
                            </form>
                        </tr>
                    </c:forEach>
                </table>
        <%--</div>--%>
        <div id="selection"></div>
        <script>
            var languagesSelect = sel.selec;
            function changeOption(){

                    var selection = document.getElementById("selection");
                    var selectedOption = languagesSelect.options[languagesSelect.selectedIndex];
                    selection.textContent = "Вы выбрали: " + selectedOption.text;
                var table=document.getElementById("t");
                var allRows = table.getElementsByTagName("tr");
                for(var index in allRows){
                    if(index>0){
                        tr1 = allRows[index];
                        allColom=tr1.getElementsByTagName("td");
                        tr= allColom[6];
                        tr.value=Number(selectedOption.value);
                        tr.textContent=selectedOption.value;
                        tr.setAttribute('value', selectedOption.value);
                        alert( tr.value );
                    }
                }
            }
            languagesSelect.addEventListener("change", changeOption);



            // tr1 = allRows[5];
            // var allColom=tr1.getElementsByTagName("td");
            // tr= allColom[6];
            // tr.innerHTML="abvc";
            // tr1 = allRows[4];
            // var allColom=tr1.getElementsByTagName("td");
            // tr= allColom[6];
            // tr.innerHTML="aaaa";
           // tr1.parentNode.removeChild(tr1);
            //tabl.table.rows[0].cols[4].innerHTML="zzzzzz";


            // function printForm(){
            //    printButton.textContent=languagesSelect.options[languagesSelect.selectedIndex];
            //}

            //var printButton =  document.table.myForm.but;
            //var printButton = document.getElementsByName("idTypeOfWorkPerformed");
            //var selection = document.getElementById("selection");
            //selection.textContent = printButton.length;

            //printButton.addEventListener("click", printForm);
        </script>

    </body>
</html>