<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div>
        <@l.logout />
    </div>
    <div>
        <form method="post" action="/addTable">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Редактирование таблиц</button>
        </form>
    </div>
Список сообщений
    <div>
        <form method="post" action="add">
            <input type="text" name="comments" placeholder="введите комментарий">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">добавить комментарий</button>
        </form>
    </div>
    <div>
        <form method="get" action="/main">
            <input type="text" name="filter" value="${filter}">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Найти</button>
        </form>
    </div>
    <#list listOfCompletedWork as list>
    <div>
        <b>${list.id}</b>
        <span>${list.comments}</span>
        <i>${list.user.firstName}</i>
    </div>
    <#else>
        No message
    </#list>

</@c.page>