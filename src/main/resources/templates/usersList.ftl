<#import "parts/common.ftl" as c>
<@c.page>

<div class="mx-auto">
    <h2>Table of employees</h2>
</div>

<form action="/users/add">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input hidden type="input" name="updId" value="${(beanUp.idAddress)!}"/>
    <h4 class="mt-4">Add new employee:</h4>
    <div class="row mb-4">
        <div class="col">
            <input required autofocus class="form-control form-control-sm" type="text" name="firstName"
                   placeholder="First name">
        </div>
        <div class="col">
            <input required autofocus class="form-control form-control-sm" type="text" name="lastName"
                   placeholder="Last name">
        </div>
        <div class="col">
            <input required autofocus class="form-control form-control-sm" type="text" name="username"
                   placeholder="Username">
        </div>
        <div class="col">
            <input required autofocus class="form-control form-control-sm" type="text" name="password"
                   placeholder="Password">
        </div>
        <div class="col">
            <select class="custom-select" name="idPosition">
                <option name="0" value="all" disabled>Select position</option>
                <#list positions as position >
                    <option value="${position.idPosition}">
                        ${position.name}
                    </option>
                </#list>
            </select>
        </div>
        <div class="col">
            <button type="submit">Add</button>
        </div>
    </div>
    <input hidden type="text" id="pf" name="positionFilter" value="${positionFilter}">
    <input hidden type="text" id="af" name="activFilter" value="${activFilter}">
    <#if (message)?has_content>
        <div><label>${message}</label></div>
    </#if>
</form>

<div class="container">
    <table class="table table-bordered table-striped" id="t">
        <h4 class="mt-4">List of users</h4>
        <thead>
        <tr>
            <th hidden>id</th>
            <th scope="col">First name</th>
            <th scope="col">Last name</th>
            <th scope="col">Login</th>
            <th scope="col">Password</th>
            <th scope="col"><select class="custom-select" id="positionSelect" name="Position">
                <option name="0" value="all" selected>All positions</option>
                <#list positions as position >
                    <option name="${position.idPosition}" value="${position.name}"
                            <#if position.idPosition==positionFilter>selected</#if>>
                        ${position.name}
                    </option>
                </#list>
            </select></th>
            <th scope="col"><select class="custom-select" id="activiesSelect" name="Activies">
                <option name="0" value="all" selected>All activies</option>
                <option name="true" value="true"  <#if activFilter==1>selected</#if>>true</option>
                <option name="false" value="false" <#if activFilter==0>selected</#if>>false</option>
            </select></th>
            <th scope="col">Update</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user >
        <tr>
            <td hidden>${user.idusers}}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td id="position${user ? counter}">${user.position.name}</td>
            <td><input disabled type="checkbox" name="a" value="true" <#if user.activ==true>checked</#if>></td>
            <td hidden id="activies${user ? counter}">${user.activ?c}</td>
            <td><a id="updButtom${user ? counter}" href="/users/list?updId=${user.idusers}" onclick="upd(${user ? counter}); return false;">Update</a></td>
            <td>
                <a id="delButtom${user ? counter}" href="/users/delete?delId=${user.idusers} <#if (beanUp)?has_content>&updId=${beanUp.idusers}</#if>"
                   onclick="del(${user ? counter}); return false;">Delete</a></td>
        </tr>
        </#list>
        </tbody>
    </table>
    <#if (beanUp)?has_content>
        <table class="table table-bordered table-striped " id="t">
            <h4 class="mt-4">Update record</h4>
            <thead>
            <tr>
                <th hidden>id</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Login</th>
                <th>Password</th>
                <th>Position</th>
                <th>activity</th>
                <th>Save</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <form method="post" action="/users/update/?updId=${beanUp.idusers}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <td hidden>${beanUp.idusers}</td>
                    <td><input required autofocus class="form-control form-control-sm" type="text" name="firstName"
                               placeholder="First name" value="${beanUp.firstName}"></td>
                    <td><input required autofocus class="form-control form-control-sm" type="text" name="lastName"
                               placeholder="Last name" value="${beanUp.lastName}"></td>
                    <td><input required autofocus class="form-control form-control-sm" type="text" name="username"
                               placeholder="Login" value="${beanUp.username}"></td>
                    <td><input required autofocus class="form-control form-control-sm" type="text" name="password"
                               placeholder="Password" value="${beanUp.password}"></td>
                    <td><select class="custom-select" name="idPosition">
                        <option name="0" value="all" disabled>Select position</option>
                        <#list positions as position >
                            <option value="${position.idPosition}">
                                ${position.name}
                            </option>
                        </#list>
                    </select></td>
                    <td><input type="checkbox" name="activ" value="true" <#if beanUp.activ==true>checked</#if>></td>
                    <td hidden>
                        <input  type="text" id="pfUp" name="positionFilter" value="${positionFilter}">
                        <input  type="text" id="afUp" name="activFilter" value="${activFilter}">
                    </td>
                    <td>
                        <button type=submit>Save</button>
                    </td>
                </form>
            </tr>
            </tbody>
        </table>
    </#if>
</div>
    <script>
        var positionSelect = document.getElementById("positionSelect");
        var activiesSelect = document.getElementById("activiesSelect");

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
            if (activiesSelect.options[activiesSelect.selectedIndex].getAttribute("name") != 0)
                str += "&activFilter=" + activiesSelect.options[activiesSelect.selectedIndex].getAttribute("name");
            return str;
        }

        function Filter() {
            var sellPosition = positionSelect.options[positionSelect.selectedIndex];
            var sellActivies = activiesSelect.options[activiesSelect.selectedIndex];
            var table = document.getElementById("t");
            var allRows = table.getElementsByTagName("tr");
            for (var index in allRows) {
                if (index > 0) {
                    var fieldPosition = document.getElementById("position" + index);
                    var fieldActivies = document.getElementById("activies" + index);
                    if (fieldPosition != null && fieldActivies) {
                        allRows[index].removeAttribute("hidden");
                        if (!fieldPosition.innerHTML.startsWith(sellPosition.value) && !sellPosition.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                        else if (!fieldActivies.innerHTML.startsWith(sellActivies.value) && !sellActivies.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                    }
                }
            }
            document.getElementById("pf").setAttribute("value", positionSelect.options[positionSelect.selectedIndex].getAttribute("name"))
            document.getElementById("af").setAttribute("value", activiesSelect.options[activiesSelect.selectedIndex].getAttribute("name"))
            document.getElementById("pfUp").setAttribute("value", positionSelect.options[positionSelect.selectedIndex].getAttribute("name"))
            document.getElementById("afUp").setAttribute("value", activiesSelect.options[activiesSelect.selectedIndex].getAttribute("name"))
        }

        positionSelect.addEventListener("change", Filter);
        activiesSelect.addEventListener("change", Filter);

        Filter();
    </script>
</@c.page>