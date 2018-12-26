<#import "parts/common.ftl" as c>
<@c.page>

<div class="mx-auto">
    <h2>Table of types of work performed in the form of execution</h2>
</div>

<form action="/tables/add/listTypesInPerfomedWork">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input hidden type="input" name="updId" value="${(beanUp.typeOfWorkPerformedFilter)!}"/>
    <h4 class="mt-4">Add new position duties:</h4>
    <div class="row mb-4">
        <div class="col">
            <select class="custom-select" name="idTypeOfWorkPerformed">
                <option name="0" value="all" disabled >Select type of work performed</option>
                <#list typesOfWorkPerformed as typeOfWorkPerformed >
                    <option value="${typeOfWorkPerformed.idTypeOfWorkPerformed}">
                        ${typeOfWorkPerformed.name}
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
        <input hidden type="text" id="tOfWPf" name="typeOfWorkPerformedFilter" value="${typeOfWorkPerformedFilter}">
        <input hidden type="text" id="tOfWf" name="typeOfWorkFilter" value="${typeOfWorkFilter}">
    </div>
    <#if (message)?has_content>
        <div><label>${message}</label></div>
    </#if>
</form>

<div class="container">
    <table class="table table-bordered table-striped" id="t">
        <h4 class="mt-4">List types of work performed in the form of execution</h4>
        <thead>
        <tr>
            <th hidden>id</th>
            <th scope="col">
                <select class="custom-select" id="typeOfWorkPerformedSelect" name="typeOfWorkPerformed">
                    <option name="0" value="all" selected>All type of work performed</option>
                <#list typesOfWorkPerformed as typeOfWorkPerformed >
                    <option name="${typeOfWorkPerformed.idTypeOfWorkPerformed}" value="${typeOfWorkPerformed.name}"
                            <#if typeOfWorkPerformed.idTypeOfWorkPerformed==typeOfWorkPerformedFilter>selected</#if>>
                        ${typeOfWorkPerformed.name}
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
        <#list typesInPerfomedWork as typeInPerfomedWork >
        <tr>
            <td hidden>${typeInPerfomedWork.idListTypesInPerfomedWork}</td>
            <td id="position${typeInPerfomedWork ? counter}">${typeInPerfomedWork.typeOfWorkPerformed.name}</td>
            <td id="typeOfWork${typeInPerfomedWork ? counter}">${typeInPerfomedWork.typeOfWork.name}</td>
            <td><a id="updButtom${typeInPerfomedWork ? counter}" href="/tables/listTypesInPerfomedWork?updId=${typeInPerfomedWork.idListTypesInPerfomedWork}" onclick="upd(${typeInPerfomedWork ? counter}); return false;">Update</a></td>
            <td><a id="delButtom${typeInPerfomedWork ? counter}" href="/tables/delete/listTypesInPerfomedWork?delId=${typeInPerfomedWork.idListTypesInPerfomedWork}<#if (beanUp)?has_content>&updId=${beanUp.idListTypesInPerfomedWork}</#if>"
                   onclick="del(${typeInPerfomedWork ? counter}); return false;">Delete</a>
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
                <th>Type of work performed</th>
                <th>Type of work</th>
                <th>Save</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <form method="post" action="/tables/update/listTypesInPerfomedWork?updId=${beanUp.idListTypesInPerfomedWork}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <th hidden>${beanUp.idListTypesInPerfomedWork}</th>
                    <th><select class="custom-select" name="updIdTypeOfWorkPerformed">
                        <option name="0" value="all" selected>Select type of work performed</option>
                            <#list typesOfWorkPerformed as typeOfWorkPerformed >
                                <option value="${typeOfWorkPerformed.idTypeOfWorkPerformed}"
                                        <#if typeOfWorkPerformed.idTypeOfWorkPerformed==beanUp.typeOfWorkPerformed.idTypeOfWorkPerformed>selected</#if>>
                                    ${typeOfWorkPerformed.name}
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
                        <input type="text" id="tOfWPfUp" name="typeOfWorkPerformedFilter" value="${typeOfWorkPerformedFilter}">
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
        var typeOfWorkPerformedSelect = document.getElementById("typeOfWorkPerformedSelect");
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
            if (typeOfWorkPerformedSelect.options[typeOfWorkPerformedSelect.selectedIndex].getAttribute("name") != 0)
                str += "&typeOfWorkPerformedFilter="+ typeOfWorkPerformedSelect.options[typeOfWorkPerformedSelect.selectedIndex].getAttribute("name");
            if (typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].getAttribute("name") != 0)
                str += "&typeOfWorkFilter=" + typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].getAttribute("name");
            return str;

        }

        function Filter() {
            var sellTypeOfWorkPerformed = typeOfWorkPerformedSelect.options[typeOfWorkPerformedSelect.selectedIndex];
            var sellTypeOfWork = typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex];
            var table = document.getElementById("t");
            var allRows = table.getElementsByTagName("tr");
            for (var index in allRows) {
                if (index > 0) {
                    var fieldTypeOfWorkPerformed = document.getElementById("position" + index);
                    var fieldTypeOfWork = document.getElementById("typeOfWork" + index);
                    if (fieldTypeOfWorkPerformed != null && fieldTypeOfWork != null) {
                        allRows[index].removeAttribute("hidden");
                        if (!fieldTypeOfWorkPerformed.innerHTML.startsWith(sellTypeOfWorkPerformed.value) && !sellTypeOfWorkPerformed.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                        else if (!fieldTypeOfWork.innerHTML.startsWith(sellTypeOfWork.value) && !sellTypeOfWork.value.startsWith("all")) {
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
</@c.page>