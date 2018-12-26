<#import "parts/common.ftl" as c>
<@c.page>

<div class="mx-auto">
    <h2>Region table</h2>
</div>
<#--<#if(beanUp)?has_content>?updId=${beanUp.idPosition}</#if>-->

<form action="/tables/add/region">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input hidden type="input" name="updId" value="${(beanUp.idRegion)!}"/>
    <h4 class="mt-4">Add new region:</h4>
    <div class="row mb-4">
        <div class="col">
            <input required autofocus class="form-control form-control-sm" type="text" name="name"
                   placeholder="Region name">
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
        <h4 class="mt-4">List of regions</h4>
        <thead>
        <tr>
            <th hidden>id</th>
            <th scope="col">Region name</th>
            <th scope="col">Update</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <#list regions as region >
        <tr>
            <td hidden>${region.idRegion}</td>
            <td>${region.name}</td>
            <td><a href="/tables/region?updId=${region.idRegion}">Update</a></td>
            <td>
                <a href="/tables/delete/region?delId=${region.idRegion}<#if (beanUp)?has_content>&updId=${beanUp.idRegion}</#if>"
                   onclick="display(${region ? counter}); return false;">Delete</a></td>
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
                <th>Region name</th>
                <th>Save</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <form method="post" action="/tables/update/region?updId=${beanUp.idRegion}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <td hidden>${beanUp.idRegion}</td>
                    <td><input type="text" name="updName" placeholder="Region name" value="${beanUp.name}"></td>
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