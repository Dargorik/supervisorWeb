<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Add new user
<form action="/registration" method="post">
    <div><label> Имя: <input type="text" name="firstName"/> </label></div>
    <div><label> Фамилия: <input type="text" name="lastName"/> </label></div>
    <div><label> Логин: <input type="text" name="username"/> </label></div>
    <div><label> Пароль: <input type="password" name="password"/> </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div><input type="submit" value="Создать пользователя"/></div>
</form>
</@c.page>