<#import "parts/common.ftl" as c>
<@c.page>

<div class="mx-auto">
    <h2>Table of the types of work performed</h2>
</div>
<#--<#if(beanUp)?has_content>?updId=${beanUp.idPosition}</#if>-->

<form action="/tables/add/typeOfWorkPerformed">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input hidden type="input" name="updId" value="${(beanUp.idTypeOfWorkPerformed)!}"/>
    <h4 class="mt-4">Add new type of work performed:</h4>
    <div class="row mb-4">
        <div class="col">
            <input required autofocus class="form-control form-control-sm" type="text" name="name"
                   placeholder="Name type of work performed">
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
        <h4 class="mt-4">List of types of work performed</h4>
        <thead>
        <tr>
            <th hidden>id</th>
            <th scope="col">Name type of work performed</th>
            <th scope="col">Update</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <#list typesOfWorkPerformed as typeOfWorkPerformed >
        <tr>
            <td hidden>${typeOfWorkPerformed.idTypeOfWorkPerformed}</td>
            <td>${typeOfWorkPerformed.name}</td>
            <td><a href="/tables/typeOfWorkPerformed?updId=${typeOfWorkPerformed.idTypeOfWorkPerformed}">Update</a></td>
            <td>
                <a href="/tables/delete/typeOfWorkPerformed?delId=${typeOfWorkPerformed.idTypeOfWorkPerformed}<#if (beanUp)?has_content>&updId=${beanUp.idTypeOfWorkPerformed}</#if>"
                   onclick="display(${typeOfWorkPerformed ? counter}); return false;">Delete</a></td>
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
                <th>Name type of work performed</th>
                <th>Save</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <form method="post" action="/tables/update/typeOfWorkPerformed?updId=${beanUp.idTypeOfWorkPerformed}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <td hidden>${beanUp.idTypeOfWorkPerformed}</td>
                    <td><input type="text" name="updName" placeholder="Name type of work performed" value="${beanUp.name}"></td>
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