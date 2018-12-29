<#include "security.ftl">

<nav class="navbar navbar-expand-sm navbar-light bg-light">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</span></a>
            </li>
            <#if isUser>
                    <li class="nav-item">
                        <a class="nav-link" href="/work">Make work</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/work/basket">Basket<#if (sizeBasket)?has_content >(${sizeBasket})</#if></span></a>
                    </li>
            <#else>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link" href="/monitoring">Work monitoring</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/status">Address Status</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/tables/userRegions">Table of employee-related regions</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            Database editing
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="/users/list">Table of employees</a>
                            <a class="dropdown-item" href="/tables/position">Position table</a>
                            <a class="dropdown-item" href="/tables/city">Cities table</a>
                            <a class="dropdown-item" href="/tables/street">Streets table</a>
                            <a class="dropdown-item" href="/tables/priorityList">Priority list table</a>
                            <a class="dropdown-item" href="/tables/region">Regions table</a>
                            <a class="dropdown-item" href="/tables/address">Addresses table</a>
                            <a class="dropdown-item" href="/tables/typeOfWork">Work type Table</a>
                            <a class="dropdown-item" href="/tables/typeOfWorkPerformed">Table of the types of work
                                performed</a>
                            <a class="dropdown-item" href="/tables/positionDuties">Table of positions duties</a>
                            <a class="dropdown-item" href="/tables/listTypesInPerfomedWork">Table of types of work
                                performed in the form of execution</a>
                        </div>
                    </li>
                </#if>
            </#if>
        </ul>
    </div>

    <#if (user)?has_content>
        <a href="/users/settings" class="nav-link">${name}</span></a>
        <form class="form-inline" action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary  btn-sm">Sign out</button>
        </form>
    <#else>
        <a class="nav-link">Pleas login in!</span></a>
        <form class="form-inline" action="/login" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" class="btn btn-primary  btn-sm" value="Sign in"/>
        </form>
    </#if>
</nav>