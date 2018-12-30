<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>
<@c.page>
<h2>List of all completed work</h2>

<table class="table table-striped table-bordered table-sm" id="t">
    <div>
        From:
        <input id="fromeData" name="fromeData" type="date" value="${fromeData}">
        To:
        <input id="toData" name="toData" type="date" value="${toData}">
        <input hidden type="text" id="uf" name="userFilter" value="${userFilter}">
        <input hidden type="text" id="rf" name="regionFilter" value="${regionFilter}">
        <input hidden type="text" id="cf" name="cityFilter" value="${cityFilter}">
        <input hidden type="text" id="sf" name="streetFilter" value="${streetFilter}">
        <input hidden type="text" id="tOfw" name="typeOfWorkPerformedFilter" value="${typeOfWorkPerformedFilter}">
    </div>
    <caption>
    <#if (message)?has_content>
        ${message}
    </#if>
    </caption>
    <thead class="thead">
    <tr>
        <th>
            <select class="custom-select" id="userSelect" name="User">
                <option name="0" value="all" selected>All users</option>
                <#list users as user >
                    <option name="${user.idusers}" value="${user.firstName} ${user.lastName}"
                            <#if user.idusers==userFilter>selected</#if>>
                        ${user.firstName} ${user.lastName}
                    </option>
                </#list>
            </select>
        </th>
        <th><select class="custom-select" id="citySelect" name="City">
            <option name="0" value="all" selected>All city</option>
            <#list cities as city>
                <option name="${city.idCity}" value="${city.name}" <#if city.name==cityFilter>selected</#if>>
                    ${city.name}
                </option>
            </#list>
        </select></th>
        <th><select class="custom-select" id="streetSelect" name="Street">
            <option name="0" value="all" selected>All street</option>
            <#list streets as street>
                <option name="${street.idStreet}" value="${street.name}"
                        <#if street.name==streetFilter>selected</#if>>
                    ${street.name}
                </option>
            </#list>
        </select></th>
        <th>House number</th>
        <th>Number of floors</th>
        <th>Number of entrances</th>
        <th>Number of entrances completed</th>
        <th><select class="custom-select" id="regionSelect" name="Region">
            <option name="0" value="all" selected>All region</option>
                <#list regions as region >
                    <option name="${region.idRegion}" value="${region.name}"
                            <#if region.name==regionFilter>selected</#if>>
                        ${region.name}
                    </option>
                </#list>
        </select></th>
        <th><select class="custom-select" id="typeOfWorkPerformedSelect" name="typeOfWorkPerformed">
            <option name="0" value="all" selected>All type of work performed</option>
                <#list typesOfWorkPerformed as typeOfWorkPerformed >
                    <option name="${typeOfWorkPerformed.idTypeOfWorkPerformed}" value="${typeOfWorkPerformed.name}"
                            <#if typeOfWorkPerformed.name==typeOfWorkPerformedFilter>selected</#if>>
                        ${typeOfWorkPerformed.name}
                    </option>
                </#list>
        </select></th>
        <th>Comment</th>
        <th>Date</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <#if (page)?has_content>
        <#list page.content as completedWork >
        <tr>
            <form name="myForm" method="post" action="/monitoring">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <td hidden>${completedWork.idCompletedWork}</td>
                <td id="user${completedWork ? counter}">${completedWork.user.firstName} ${completedWork.user.lastName}</td>
                <td id="city${completedWork ? counter}">${completedWork.address.city.name}</td>
                <td id="street${completedWork ? counter}">${completedWork.address.street.name}</td>
                <td>${completedWork.address.houseNumber}</td>
                <td>${completedWork.address.numberFloors}</td>
                <td>${completedWork.address.numberEntrances}</td>
                <td>${completedWork.numberCompletedEntrances}</td>
                <td id="region${completedWork ? counter}">${completedWork.address.region.name}</td>
                <td id="typeOfWorkPerformed${completedWork ? counter}">${completedWork.typeOfWorkPerformed.name}</td>
                <td>${completedWork.comment}</td>
                <td>${completedWork.date}</td>
                <td><a href="&delId=${completedWork.idCompletedWork}"
                       onclick="update(${completedWork.idCompletedWork}); return false;">Delete</a></td>
            </form>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
<script>
    function update(x) {
        var str = "";
        str+=chekSelects();
        if (x != null && x > 0) {
            var isDelete = confirm("Are you sure you want to delete the record?");
            if (isDelete == true)
                location.href = "/monitoring/delete?" + "&delId=" + x + str;
            return;
        }
        location.href = "/monitoring?" + str;
    }

    function chekSelects() {
        var str = "";
        if (fromeData.value != null && fromeData.value != "")
            str += "&fromeData=" + fromeData.value;
        if (toData.value != null && toData.value != "")
            str += "&toData=" + toData.value;
        if (userSelect.options[userSelect.selectedIndex].getAttribute("name") != 0)
            str += "&userFilter=" + userSelect.options[userSelect.selectedIndex].getAttribute("name");
        if (regionSelect.options[regionSelect.selectedIndex].getAttribute("name") != 0)
            str += "&regionFilter=" + regionSelect.options[regionSelect.selectedIndex].value;
        if (citySelect.options[citySelect.selectedIndex].getAttribute("name") != 0)
            str += "&cityFilter=" + citySelect.options[citySelect.selectedIndex].value;
        if (streetSelect.options[streetSelect.selectedIndex].getAttribute("name") != 0)
            str += "&streetFilter=" + streetSelect.options[streetSelect.selectedIndex].value;
        if (typeOfWorkPerformedSelect.options[typeOfWorkPerformedSelect.selectedIndex].getAttribute("name") != 0)
            str += "&typeOfWorkPerformedFilter=" + typeOfWorkPerformedSelect.options[typeOfWorkPerformedSelect.selectedIndex].value;
        return str;

    }

    var fromeData = document.getElementById("fromeData");
    var toData = document.getElementById("toData");

    var userSelect = document.getElementById("userSelect");
    var citySelect = document.getElementById("citySelect");
    var streetSelect = document.getElementById("streetSelect");
    var regionSelect = document.getElementById("regionSelect");
    var typeOfWorkPerformedSelect = document.getElementById("typeOfWorkPerformedSelect");

    // function Filter() {
    //     var sellCity = citySelect.options[citySelect.selectedIndex];
    //     var sellStreet = streetSelect.options[streetSelect.selectedIndex];
    //     var sellUser = userSelect.options[userSelect.selectedIndex];
    //     var sellRegion = regionSelect.options[regionSelect.selectedIndex];
    //     var sellTypeOfWorkPerformed = typeOfWorkPerformedSelect.options[typeOfWorkPerformedSelect.selectedIndex];
    //     var table = document.getElementById("t");
    //     var allRows = table.getElementsByTagName("tr");
    //     for (var index in allRows) {
    //         if (index > 0) {
    //             var fieldUser = document.getElementById("user" + index);
    //             var fieldCity = document.getElementById("city" + index);
    //             var fieldStreet = document.getElementById("street" + index);
    //             var fieldregion = document.getElementById("region" + index);
    //             var fieldTypeOfWorkPerformed = document.getElementById("typeOfWorkPerformed" + index);
    //             //alert(fieldUser.innerHTML+"---"+sellUser.value+"+++"+fieldregion.innerHTML+"---"+sellRegion.value);
    //             if (fieldUser != null && fieldCity != null && fieldStreet != null && fieldregion != null && fieldTypeOfWorkPerformed != null) {
    //                 allRows[index].removeAttribute("hidden");
    //                 if (!fieldUser.innerHTML.startsWith(sellUser.value) && !sellUser.value.startsWith("all")) {
    //                     allRows[index].setAttribute("hidden", "hidden")
    //                 } else if (!fieldCity.innerHTML.startsWith(sellCity.value) && !sellCity.value.startsWith("all")) {
    //                     allRows[index].setAttribute("hidden", "hidden")
    //                 }
    //                 else if (!fieldStreet.innerHTML.startsWith(sellStreet.value) && !sellStreet.value.startsWith("all")) {
    //                     allRows[index].setAttribute("hidden", "hidden")
    //                 }
    //                 else if (!fieldregion.innerHTML.startsWith(sellRegion.value) && !sellRegion.value.startsWith("all")) {
    //                     allRows[index].setAttribute("hidden", "hidden")
    //                 }
    //                 else if (!fieldTypeOfWorkPerformed.innerHTML.startsWith(sellTypeOfWorkPerformed.value) && !sellTypeOfWorkPerformed.value.startsWith("all")) {
    //                     allRows[index].setAttribute("hidden", "hidden")
    //                 }
    //             }
    //         }
    //     }
    //     document.getElementById("uf").setAttribute("value", userSelect.options[userSelect.selectedIndex].getAttribute("name"));
    //     document.getElementById("rf").setAttribute("value", regionSelect.options[regionSelect.selectedIndex].getAttribute("name"));
    //     document.getElementById("cf").setAttribute("value", citySelect.options[citySelect.selectedIndex].getAttribute("name"));
    //     document.getElementById("sf").setAttribute("value", streetSelect.options[streetSelect.selectedIndex].getAttribute("name"));
    //     document.getElementById("tOfw").setAttribute("value", typeOfWorkPerformedSelect.options[typeOfWorkPerformedSelect.selectedIndex].getAttribute("name"));
    // }


    function updPage(){
        var str="${url}?page=${page.getNumber()}&size=${page.getSize()}";
        location.href =str +chekSelects() ;
    }

    userSelect.addEventListener("change", updPage);
    citySelect.addEventListener("change", updPage);
    streetSelect.addEventListener("change", updPage);
    regionSelect.addEventListener("change", updPage);
    typeOfWorkPerformedSelect.addEventListener("change", updPage);


    fromeData.addEventListener("change", update);
    toData.addEventListener("change", update);

   // Filter();
</script>
    <@p.pager url page />
</@c.page>