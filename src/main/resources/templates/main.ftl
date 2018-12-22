<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <#if (user)?has_content>${user.firstName} ${user.lastName}
        <div>
            <@l.logout />
        </div>
    <#else>
    <h2>Необходимо авторизоваться!</h2>
        <form action="/login" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="submit" value="Войти"/>
        </form>
    </#if>

    <#if (user)?has_content>
        <#if (role)?has_content>
            <#if role=="admin">
                <div>
                    <form method="post" action="/addTable" >
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <button type="submit">Редактирование таблиц</button>
                    </form>
                </div>
                <div>
                    <form method="post" action="/monitoring?userId=${user.getIdusers()}" >
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <button type="submit">Мониторинг работ</button>
                    </form>
                </div>
            <#else>
                <div>
                    <form method="post" action="/work?userId=${user.getIdusers()}" >
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <button type="submit">Добавление записей о проделанной работе</button>
                    </form>
                </div>
                <div>
                    <form method="post" action="/work/basket?userId=${user.getIdusers()}" >
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <button type="submit">Корзина</button>
                    </form>
                </div>
            </#if>
         </#if>
    </#if>
</@c.page>
<#--Список сообщений-->
    <#--<div>-->
        <#--<form method="post" action="add">-->
            <#--<input type="text" name="comments" placeholder="введите комментарий">-->
            <#--<input type="hidden" name="_csrf" value="${_csrf.token}" />-->
            <#--<button type="submit">добавить комментарий</button>-->
        <#--</form>-->
    <#--</div>-->
    <#--<div>-->
        <#--<form method="get" action="/main">-->
            <#--<input type="text" name="filter" value="${filter}">-->
            <#--<input type="hidden" name="_csrf" value="${_csrf.token}" />-->
            <#--<button type="submit">Найти</button>-->
        <#--</form>-->
    <#--</div>-->
    <#--<#list listOfCompletedWork as list>-->
    <#--<div>-->
        <#--<b>${list.id}</b>-->
        <#--<span>${list.comments}</span>-->
        <#--<i>${list.user.firstName}</i>-->
    <#--</div>-->
    <#--<#else>-->
        <#--No message-->
    <#--</#list>-->