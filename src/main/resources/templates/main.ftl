<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <#if (user)?has_content>
        <h1>${user.firstName} ${user.lastName}, welcome in system control work!</h1>
    <#else>
        <h1>Pleas login in!</h1>
    </#if>

    <#--<#if (user)?has_content>-->
        <#--<#if (role)?has_content>-->
            <#--<#if role=="admin">-->
                <#--<div>-->
                    <#--<form method="post" action="/addTable" >-->
                        <#--<input type="hidden" name="_csrf" value="${_csrf.token}" />-->
                        <#--<button type="submit">Редактирование таблиц</button>-->
                    <#--</form>-->
                <#--</div>-->
                <#--<div>-->
                    <#--<form method="post" action="/monitoring" >-->
                        <#--<input type="hidden" name="_csrf" value="${_csrf.token}" />-->
                        <#--<button type="submit">Мониторинг работ</button>-->
                    <#--</form>-->
                <#--</div>-->
                <#--<div>-->
                    <#--<form method="post" action="/status" >-->
                        <#--<input type="hidden" name="_csrf" value="${_csrf.token}" />-->
                        <#--<button type="submit">Статус всех домов</button>-->
                    <#--</form>-->
                <#--</div>-->
            <#--<#else>-->
                <#--<div>-->
                    <#--<form method="post" action="/work?userId=${user.getIdusers()}" >-->
                        <#--<input type="hidden" name="_csrf" value="${_csrf.token}" />-->
                        <#--<button type="submit">Добавление записей о проделанной работе</button>-->
                    <#--</form>-->
                <#--</div>-->
                <#--<div>-->
                    <#--<form method="post" action="/work/basket?userId=${user.getIdusers()}" >-->
                        <#--<input type="hidden" name="_csrf" value="${_csrf.token}" />-->
                        <#--<button type="submit">Корзина</button>-->
                    <#--</form>-->
                <#--</div>-->
            <#--</#if>-->
         <#--</#if>-->
    <#--</#if>-->
</@c.page>