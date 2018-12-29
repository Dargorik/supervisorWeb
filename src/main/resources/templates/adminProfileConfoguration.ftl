<#import "parts/common.ftl" as c>
<@c.page>

<div class="mx-auto">
    <h2>Admin profile</h2>
</div>    <#if (user)?has_content>
        <table class="table table-bordered table-striped " id="t">
            <#if (message)?has_content>
                <div><label>${message}</label></div>
            </#if>
            <h4 class="mt-4">Update record</h4>
            <thead>
            <tr>
                <th hidden>id</th>
                <th>First name</th>
                <th>Last name</th>
                <th>E-mail</th>
                <th>Password</th>
                <th>Save</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <form method="post" action="/users/settings/save">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <td hidden>${user.idusers}</td>
                    <td><input required autofocus class="form-control form-control-sm" type="text" name="firstName"
                               placeholder="First name" value="${user.firstName}"></td>
                    <td><input required autofocus class="form-control form-control-sm" type="text" name="lastName"
                               placeholder="Last name" value="${user.lastName}"></td>
                    <td><input required autofocus class="form-control form-control-sm" type="email" name="email"
                               placeholder="E-mail" value="<#if (user.email)?has_content>${user.email}</#if>"></td>
                    <td><input required autofocus class="form-control form-control-sm" type="text" name="password"
                               placeholder="Password" value="${user.password}"></td>
                    <td>
                        <button type=submit>Save</button>
                    </td>
                </form>
            </tr>
            </tbody>
        </table>
    </#if>
</div>
</@c.page>