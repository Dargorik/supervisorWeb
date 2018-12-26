<#import "parts/common.ftl" as c>
<@c.page>

<div class="mx-auto">
    <h2>City table</h2>
</div>
<#--<#if(beanUp)?has_content>?updId=${beanUp.idPosition}</#if>-->

<form action="/tables/add/city">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input hidden type="input" name="updId" value="${(beanUp.idCity)!}"/>
    <h4 class="mt-4">Add new city:</h4>
    <div class="row mb-4">
        <div class="col">
            <input required autofocus class="form-control form-control-sm" type="text" name="name"
                   placeholder="City name">
        </div>
        <div class="col">
            <button type="submit">Add</button>
        </div>
        <div class="col">
        </div>
    </div>
    <#if (message)?has_content>
        <div><label>${message}</label></div>
    </#if>
</form>

<div class="container">
    <table class="table table-bordered table-striped" id="t">
        <h4 class="mt-4">List of cities</h4>
        <thead>
        <tr>
            <th hidden>id</th>
            <th scope="col">City name</th>
            <th scope="col">Update</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <#list cities as city >
        <tr>
            <td hidden>${city.idCity}</td>
            <td>${city.name}</td>
            <td><a href="/tables/city?updId=${city.idCity}">Update</a></td>
            <td>
                <a href="/tables/delete/city?delId=${city.idCity}<#if (beanUp)?has_content>&updId=${beanUp.idCity}</#if>"
                   onclick="display(${city ? counter}); return false;">Delete</a></td>
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
                <th>City name</th>
                <th>Save</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <form method="post" action="/tables/update/city?updId=${beanUp.idCity}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <td hidden>${beanUp.idCity}</td>
                    <td><input type="text" name="updName" placeholder="City name" value="${beanUp.name}"></td>
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
        function display(x) {
            var isDelete = confirm("Deleting this entry will delete all entries containing this key. Continue deleting?");
            if (isDelete == true) {
                var url = document.getElementById("delButtom" + x)
                location.href = url.getAttribute("href");
            }
        }
    </script>
</@c.page>