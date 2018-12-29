<#macro login path>
<form action="${path}" method="post">
    <div><label> Логин: <input type="text" name="username"/> </label></div>
    <div><label> Пароль: <input type="password" name="password"/> </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div><input type="submit" value="Войти"/></div>
</form>
<a href="/reset" class="nav-link">Reset password (admin only)</span></a>
    <#if (message)?has_content>
                <div><label>${message}</label></div>
    </#if>
</#macro>

<#macro logout>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Sign out"/>
        </form>
    </div>
</#macro>