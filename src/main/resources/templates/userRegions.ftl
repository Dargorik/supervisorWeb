<#import "parts/common.ftl" as c>
<@c.page>

<div class="mx-auto">
    <h2>Table of employee-related regions</h2>
</div>

<form action="/tables/add/userRegions">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input hidden type="input" name="updId" value="${(beanUp.idUserRegions)!}"/>
    <h4 class="mt-4">Add new employee-related regions:</h4>
    <div class="row mb-4">
        <div class="col">
            <select class="custom-select" name="idUser">
                <option name="0" value="all" disabled >Select employee</option>
                <#list users as user >
                    <option value="${user.idusers}">
                        ${user.firstName} ${user.lastName}
                    </option>
                </#list>
            </select>
        </div>
        <div class="col">
            <select class="custom-select" name="idRegion">
                <option name="0" value="all" disabled >Select region</option>
                <#list regions as region >
                    <option value="${region.idRegion}">
                        ${region.name}
                    </option>
                </#list>
            </select>
        </div>
        <div class="col">
            <button type="submit">Add</button>
        </div>
        <div class="col">
        </div>
        <input hidden type="text" id="uf" name="userFilter" value="${userFilter}">
        <input hidden type="text" id="rf" name="regionFilter" value="${regionFilter}">
    </div>
    <#if (message)?has_content>
        <div><label>${message}</label></div>
    </#if>
</form>

<div class="container">
    <table class="table table-bordered table-striped" id="t">
        <h4 class="mt-4">List of regions allowed employees</h4>
        <thead>
        <tr>
            <th hidden>id</th>
            <th>
                <select class="custom-select" id="userSelect" name="User">
                    <option name="0" value="all" selected>All users</option>
                <#list users as user >
                    <option name="${user.idusers}" value="${user.firstName} ${user.lastName}"
                            <#if user.idusers==userFilter>selected</#if>>
                        ${user.firstName} ${user.lastName}
                    </option>
                </#list>
                </select>
            </th>
            <th>
                <select class="custom-select" id="regionSelect" name="Region">
                    <option name="0" value="all" selected>All region</option>
                <#list regions as region >
                    <option name="${region.idRegion}" value="${region.name}"
                            <#if region.idRegion==regionFilter>selected</#if>>
                        ${region.name}
                    </option>
                </#list>
                </select>
            </th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <#list usersRegions as usersRegion >
        <tr>
            <td hidden>${usersRegion.idUserRegions}</td>
            <td id="user${usersRegion ? counter}">${usersRegion.user.firstName} ${usersRegion.user.lastName}</td>
            <td id="region${usersRegion ? counter}">${usersRegion.region.name}</td>
            <td><a id="updButtom${usersRegion ? counter}" href="/tables/userRegions?updId=${usersRegion.idUserRegions}" onclick="upd(${usersRegion ? counter}); return false;">Update</a></td>
            <td><a id="delButtom${usersRegion ? counter}" href="/tables/delete/userRegions?delId=${usersRegion.idUserRegions}<#if (beanUp)?has_content>&updId=${beanUp.idUserRegions}</#if>"
                   onclick="del(${usersRegion ? counter}); return false;">Delete</a>
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
                <th>Employee</th>
                <th>Region</th>
                <th>Save</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <form method="post" action="/tables/update/userRegions?updId=${beanUp.idUserRegions}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <th hidden>${beanUp.idUserRegions}</th>
                    <th><select class="custom-select" name="updIdUser">
                        <option name="0" value="all" disabled selected>Select user</option>
                            <#list users as user >
                                <option value="${user.idusers}"
                                        <#if user.idusers==beanUp.user.idusers>selected</#if>>
                                    ${user.firstName} ${user.lastName}
                                </option>
                            </#list>
                    </select></th>
                    <th><select class="custom-select" name="updIdRegion">
                        <option name="0" value="all" disabled selected>Select region</option>
                        <#list regions as region >
                            <option value="${region.idRegion}"
                                    <#if region.idRegion==beanUp.region.idRegion>selected</#if>>
                                ${region.name}
                            </option>
                        </#list>
                    </select></th>
                    <td hidden>
                        <input type="text" id="ufUp" name="userFilter" value="${userFilter}">
                        <input type="text" id="rfUp" name="regionFilter" value="${regionFilter}">
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
        var userSelect = document.getElementById("userSelect");
        var regionSelect = document.getElementById("regionSelect");

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
            if (userSelect.options[userSelect.selectedIndex].getAttribute("name") != 0)
                str += "&userFilter="+ userSelect.options[userSelect.selectedIndex].getAttribute("name");
            if (regionSelect.options[regionSelect.selectedIndex].getAttribute("name") != 0)
                str += "&regionFilter=" + regionSelect.options[regionSelect.selectedIndex].getAttribute("name");
            return str;

        }

        function Filter() {
            var sellUser = userSelect.options[userSelect.selectedIndex];
            var sellRegion = regionSelect.options[regionSelect.selectedIndex];
            var table = document.getElementById("t");
            var allRows = table.getElementsByTagName("tr");
            for (var index in allRows) {
                if (index > 0) {
                    var fieldUser = document.getElementById("user" + index);
                    var fieldregion = document.getElementById("region" + index);
                    if (fieldUser != null && fieldregion != null) {
                        allRows[index].removeAttribute("hidden");
                        if (!fieldUser.innerHTML.startsWith(sellUser.value) && !sellUser.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                        else if (!fieldregion.innerHTML.startsWith(sellRegion.value) && !sellRegion.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                    }
                }
            }
            document.getElementById("uf").setAttribute("value", userSelect.options[userSelect.selectedIndex].getAttribute("name"));
            document.getElementById("rf").setAttribute("value", regionSelect.options[regionSelect.selectedIndex].getAttribute("name"));
            document.getElementById("ufUp").setAttribute("value", userSelect.options[userSelect.selectedIndex].getAttribute("name"));
            document.getElementById("rfUp").setAttribute("value", regionSelect.options[regionSelect.selectedIndex].getAttribute("name"));
        }

        userSelect.addEventListener("change", Filter);
        regionSelect.addEventListener("change", Filter);

        Filter();
    </script>
</@c.page>