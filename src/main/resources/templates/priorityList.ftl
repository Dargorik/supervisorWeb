<#import "parts/common.ftl" as c>
<@c.page>

<div class="mx-auto">
    <h2>Priority list table</h2>
</div>

<form action="/tables/add/priorityList">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input hidden type="input" name="updId" value="${(beanUp.idPriorityList)!}"/>
    <h4 class="mt-4">Add new street:</h4>
    <div class="row mb-4">
        <div class="col">
            <input required autofocus class="form-control form-control-sm" type="text" name="name" placeholder="Priority name">
        </div>
        <div class="col">
            <input required autofocus class="form-control form-control-sm" type="number" name="number" placeholder="Quantity days">
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
        <h4 class="mt-4">List of priorities</h4>
        <thead>
        <tr>
            <th hidden>id</th>
            <th scope="col">Priority name</th>
            <th scope="col">Quantity days</th>
            <th scope="col">Update</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <#list prioritiesList as priorityList >
        <tr>
            <td hidden>${priorityList.idPriorityList}</td>
            <td>${priorityList.name}</td>
            <td>${priorityList.number}</td>
            <td><a href="/tables/priorityList?updId=${priorityList.idPriorityList}">Update</a></td>
            <td>
                <a href="/tables/delete/priorityList?delId=${priorityList.idPriorityList}<#if (beanUp)?has_content>&updId=${beanUp.idPriorityList}</#if>"
                   onclick="display(${priorityList ? counter}); return false;">Delete</a></td>
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
                <th>Priority name</th>
                <th>Quantity days</th>
                <th>Save</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <form method="post" action="/tables/update/priorityList?updId=${beanUp.idPriorityList}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <td hidden>${beanUp.idPriorityList}</td>
                    <td><input type="text" name="updName" placeholder="Priorirt name" value="${beanUp.name}"></td>
                    <td><input type="number" name="updNumber" placeholder="Quantity days" value="${beanUp.number}"></td>
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