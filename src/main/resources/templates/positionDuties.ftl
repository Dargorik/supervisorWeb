<#import "parts/common.ftl" as c>
<@c.page>

<div class="mx-auto">
    <h2>Table of positions duties</h2>
</div>

<form action="/tables/add/positionDuties">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input hidden type="input" name="updId" value="${(beanUp.idPositionDuties)!}"/>
    <h4 class="mt-4">Add new position duties:</h4>
    <div class="row mb-4">
        <div class="col">
            <select class="custom-select" name="idPosition">
                <option name="0" value="all" disabled >Select position</option>
                <#list positions as position >
                    <option value="${position.idPosition}">
                        ${position.name}
                    </option>
                </#list>
            </select>
        </div>
        <div class="col">
            <select class="custom-select" name="idTypeOfWork">
                <option name="0" value="all" disabled >Select type of work</option>
                <#list typesOfWork as typeOfWork >
                    <option value="${typeOfWork.idTypeOfWork}">
                        ${typeOfWork.name}
                    </option>
                </#list>
            </select>
        </div>
        <div class="col">
            <button type="submit">Add</button>
        </div>
        <div class="col">
        </div>
        <input hidden type="text" id="pf" name="positionFilter" value="${positionFilter}">
        <input hidden type="text" id="tOfWf" name="typeOfWorkFilter" value="${typeOfWorkFilter}">
    </div>
    <#if (message)?has_content>
        <div><label>${message}</label></div>
    </#if>
</form>

<div class="container">
    <table class="table table-bordered table-striped" id="t">
        <h4 class="mt-4">List of positions duties</h4>
        <thead>
        <tr>
            <th hidden>id</th>
            <th scope="col">
                <select class="custom-select" id="positionSelect" name="Position">
                    <option name="0" value="all" selected>All position</option>
                <#list positions as position >
                    <option name="${position.idPosition}" value="${position.name}"
                            <#if position.idPosition==positionFilter>selected</#if>>
                        ${position.name}
                    </option>
                </#list>
                </select>
            </th>
            <th scope="col">
                <select class="custom-select" id="typeOfWorkSelect" name="TypeOfWork">
                    <option name="0" value="all" selected>All types of work</option>
                <#list typesOfWork as typeOfWork >
                    <option name="${typeOfWork.idTypeOfWork}" value="${typeOfWork.name}"
                            <#if typeOfWork.idTypeOfWork==typeOfWorkFilter>selected</#if>>
                        ${typeOfWork.name}
                    </option>
                </#list>
                </select>
            </th>
            <th scope="col">Update</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <#list positionsDuties as positionDuties >
        <tr>
            <td hidden>${positionDuties.idPositionDuties}</td>
            <td id="position${positionDuties ? counter}">${positionDuties.position.name}</td>
            <td id="typeOfWork${positionDuties ? counter}">${positionDuties.typeOfWork.name}</td>
            <td><a id="updButtom${positionDuties ? counter}" href="/tables/positionDuties?updId=${positionDuties.idPositionDuties}" onclick="upd(${positionDuties ? counter}); return false;">Update</a></td>
            <td><a id="delButtom${positionDuties ? counter}" href="/tables/delete/positionDuties?delId=${positionDuties.idPositionDuties}<#if (beanUp)?has_content>&updId=${beanUp.idPositionDuties}</#if>"
                   onclick="del(${positionDuties ? counter}); return false;">Delete</a>
            </td>
        </tr>
        </#list>
        </tbody>
    <#if (beanUp)?has_content>
        <table class="table table-bordered table-striped " id="t">
            <h4 class="mt-4">Update record</h4>
            <thead>
            <tr>
                <th hidden>id</th>
                <th>Psition</th>
                <th>Type of work</th>
                <th>Save</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <form method="post" action="/tables/update/positionDuties?updId=${beanUp.idPositionDuties}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <th hidden>${beanUp.idPositionDuties}</th>
                    <th><select class="custom-select" name="updIdPosition">
                        <option name="0" value="all" selected>Select position</option>
                            <#list positions as position >
                                <option value="${position.idPosition}"
                                        <#if position.idPosition==beanUp.position.idPosition>selected</#if>>
                                    ${position.name}
                                </option>
                            </#list>
                    </select></th>
                    <th><select class="custom-select" name="updIdTypeOfWork">
                        <option name="0" value="all" selected>Select type of work</option>
                        <#list typesOfWork as typeOfWork >
                            <option value="${typeOfWork.idTypeOfWork}"
                                    <#if typeOfWork.idTypeOfWork==beanUp.typeOfWork.idTypeOfWork>selected</#if>>
                                ${typeOfWork.name}
                            </option>
                        </#list>
                    </select></th>
                    <td hidden>
                        <input type="text" id="pfUp" name="positionFilter" value="${positionFilter}">
                        <input type="text" id="tOfWfUp" name="typeOfWorkFilter" value="${typeOfWorkFilter}">
                    </td>
                    <th>
                        <button type=submit>Save</button>
                    </th>
                </form>
            </tr>
            </tbody>
        </table>
    </#if>
</div>
    <script>
        var positionSelect = document.getElementById("positionSelect");
        var typeOfWorkSelect = document.getElementById("typeOfWorkSelect");

        function del(x) {
            var isDelete = confirm("Deleting this entry will delete all entries containing this key. Continue deleting?");
            if (isDelete == true) {
                var url = document.getElementById("delButtom" + x);
                location.href = url.getAttribute("href") + chekSelects();
            }
        }

        function upd(x) {
            var url = document.getElementById("updButtom" + x);
            location.href = url.getAttribute("href") + chekSelects();
        }

        function chekSelects() {
            var str = "";
            if (positionSelect.options[positionSelect.selectedIndex].getAttribute("name") != 0)
                str += "&positionFilter="+ positionSelect.options[positionSelect.selectedIndex].getAttribute("name");
            if (typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].getAttribute("name") != 0)
                str += "&typeOfWorkFilter=" + typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].getAttribute("name");
            return str;

        }

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
                        else if (!fieldTypeOfWork.innerHTML.startsWith(sellTypeOfWork.value) && !sellTypeOfWork.value.startsWith("all")) {
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
</@c.page>