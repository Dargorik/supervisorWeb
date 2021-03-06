<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>
<@c.page>
<h2>Address status list</h2>

<form>
    Select the type of work to indicate their due date:
    <select class="custom-select" id="typeOfWorkSelect">
        <option name="0" disabled value="all" selected> Select</option>
        <#list typesOfWork as typeOfWork>
            <option value="${typeOfWork.idTypeOfWork}"
                    <#if typeOfWork.idTypeOfWork==typeOfWorkFilter>selected</#if>>
                ${typeOfWork.name}
            </option>
        </#list>
    </select>
    Only relevance:
    <input type="checkbox" id="relevance" name="relevance" <#if relevance >checked</#if>>
</form>

<table class="table  table-striped table-bordered table-sm" id="t">
    <caption>
    <#if (message)?has_content>
        ${message}
    </#if>
    </caption>
    <thead class="thead">
    <th hidden>id</th>
    <th><select class="custom-select" id="citySelect" name="City">
        <option name="0" value="all"" selected>All city</option>
            <#list cities as city>
                <option name="${city.idCity}" value="${city.name}" <#if city.name==cityFilter>selected</#if>>
                    ${city.name}
                </option>
            </#list>
    </select></th>
    <th><select class="custom-select" id="streetSelect" name="Street">
        <option name="0" value="all" selected>All street</option>
            <#list streets as street>
                <option name="${street.idStreet}" value="${street.name}" <#if street.name==streetFilter>selected</#if>>
                    ${street.name}
                </option>
            </#list>
    </select></>
    <th>House number</th>
    <th>Number of floors</th>
    <th>Number of entrances</th>
    <th>
        <select class="custom-select" id="regionSelect" name="Region">
            <option name="0" value="all" selected>All region</option>
                <#list regions as region >
                    <option name="${region.idRegion}" value="${region.name}"
                            <#if region.name==regionFilter>selected</#if>>
                        ${region.name}
                    </option>
                </#list>
        </select>
    </th>
    <th>
        <select class="custom-select" id="prioritySelect" name="Priority">
            <option name="0" value="all" selected>All priority</option>
                <#list priorities as priority >
                    <option name="${priority.idPriorityList}" value="${priority.name}"
                            <#if priority.name==priorityFilter>selected</#if>>
                        ${priority.name}
                    </option>
                </#list>
        </select>
    </th>
    <th>Last data</th>
    </thead>
    <tbody>
        <#list page.content as lastCompletedDateAddress >
        <tr>
            <form name="myForm" method="post" action="/work/basket/delete">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <td id="city${lastCompletedDateAddress ? counter}">${lastCompletedDateAddress.address.city.name}</td>
                <td id="street${lastCompletedDateAddress ? counter}">${lastCompletedDateAddress.address.street.name}</td>
                <td>${lastCompletedDateAddress.address.houseNumber}</td>
                <td>${lastCompletedDateAddress.address.numberFloors}</td>
                <td>${lastCompletedDateAddress.address.numberEntrances}</td>
                <td id="region${lastCompletedDateAddress ? counter}">${lastCompletedDateAddress.address.region.name}</td>
                <td id="priority${lastCompletedDateAddress ? counter}">${lastCompletedDateAddress.address.priorityList.name}</td>
                <td><input readonly id="date${lastCompletedDateAddress ? counter}" value="<#if typeOfWorkFilter==0>---<#else>${lastCompletedDateAddress.date}</#if>"></td>
            </form>
        </tr>
        </#list>
    </tbody>
</table>
<script>
    var typeOfWorkSelect = document.getElementById("typeOfWorkSelect");
    var citySelect = document.getElementById("citySelect");
    var streetSelect = document.getElementById("streetSelect");
    var regionSelect = document.getElementById("regionSelect");
    var prioritySelect = document.getElementById("prioritySelect");
    var relevance = document.getElementById("relevance");

    function relevanceCheck(e) {
        var enabled = e.target.checked;
        if (typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].value != "all")
            location.href = "${url}?page=${page.getNumber()}&size=${page.getSize()}" + chekSelects()
        else
            relevance.checked=false;
    }

    function chekSelects() {
        var str = "";
        if (typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].getAttribute("name") != 0)
            str += "&typeOfWorkFilter="+ typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].value;
        if (citySelect.options[citySelect.selectedIndex].getAttribute("name") != 0)
            str += "&cityFilter=" + citySelect.options[citySelect.selectedIndex].value;
        if (streetSelect.options[streetSelect.selectedIndex].getAttribute("name") != 0)
            str += "&streetFilter=" + streetSelect.options[streetSelect.selectedIndex].value;
        if (regionSelect.options[regionSelect.selectedIndex].getAttribute("name") != 0)
            str += "&regionFilter=" + regionSelect.options[regionSelect.selectedIndex].value;
        if (prioritySelect.options[prioritySelect.selectedIndex].getAttribute("name") != 0)
            str += "&priorityFilter=" + prioritySelect.options[prioritySelect.selectedIndex].value;
        if (relevance.checked==true)
            str += "&relevance="+relevance.checked;
        return str;

    }

    function selectAction() {
        if (typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].value != "all") {
            location.href = "${url}?page=${page.getNumber()}&size=${page.getSize()}"+ chekSelects();
        }
    }

    function updPage(){
        var str="${url}?page=${page.getNumber()}&size=${page.getSize()}";
        location.href =str +chekSelects() ;
    }

    typeOfWorkSelect.addEventListener("change", selectAction);

    citySelect.addEventListener("change", updPage);
    streetSelect.addEventListener("change", updPage);
    regionSelect.addEventListener("change", updPage);
    prioritySelect.addEventListener("change", updPage);
    relevance.addEventListener("click", relevanceCheck);

</script>
<@p.pager url page />
</@c.page>