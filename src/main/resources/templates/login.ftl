<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Введите логин и пароль
<@l.login "/login" />
<#--<a href="/registration">Add new user</a>-->
</@c.page>