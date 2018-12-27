<#import "parts/common.ftl" as c>
<@c.page>

<div class="mx-auto">
    <h2>Address table</h2>
</div>

<form action="/tables/add/address">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input hidden type="input" name="updId" value="${(beanUp.idAddress)!}"/>
    <h4 class="mt-4">Add new city:</h4>
    <div class="row mb-4">
        <div class="col">
            <select class="custom-select" name="idCity">
                <option name="0" value="all" disabled>Select city</option>
                <#list cities as city>
                    <option value="${city.idCity}">
                        ${city.name}
                    </option>
                </#list>
            </select>
        </div>
        <div class="col">
            <select class="custom-select" name="idStreet">
                <option name="0" value="all" disabled>Select street</option>
                <#list streets as street>
                    <option value="${street.idStreet}">
                        ${street.name}
                    </option>
                </#list>
            </select>
        </div>
        <div class="col">
            <input required autofocus class="form-control form-control-sm" type="text" name="houseNumber"
                   placeholder="Number house">
        </div>
        <div class="col">
            <input required autofocus class="form-control form-control-sm" type="number" name="numberFloors"
                   placeholder="Number floors">
        </div>
        <div class="col">
            <input required autofocus class="form-control form-control-sm" type="number" name="numberEntrances"
                   placeholder="Number entrances">
        </div>
        <div class="col">
            <select class="custom-select" name="idPriorityList">
                <option name="0" value="all" disabled>Select priority</option>
                <#list priorities as priority >
                    <option value="${priority.idPriorityList}">
                        ${priority.name}
                    </option>
                </#list>
            </select>
        </div>
        <div class="col">
            <select class="custom-select" name="idRegion">
                <option name="0" value="all" disabled>Select type of work</option>
                <#list regions as region >
                    <option value="${region.idRegion}">
                        ${region.name}
                    </option>
                </#list>
            </select>
        </div>
        <div class="col">
            <button type="submit">Add</button>
        </div>
        <div class="col">
        </div>
    </div>
    <input hidden type="text" id="cf" name="cityFilter" value="${cityFilter}">
    <input hidden type="text" id="sf" name="streetFilter" value="${streetFilter}">
    <input hidden type="text" id="pf" name="priorityFilter" value="${priorityFilter}">
    <input hidden type="text" id="rf" name="regionFilter" value="${regionFilter}">
    <#if (message)?has_content>
        <div><label>${message}</label></div>
    </#if>
</form>

<div class="container">
    <table class="table table-bordered table-striped" id="t">
        <h4 class="mt-4">List of addresses</h4>
        <thead>
        <tr>
            <th hidden>id</th>
            <th><select class="custom-select" id="citySelect" name="City">
                <option name="0" value="all" selected>All city</option>
            <#list cities as city>
                <option name="${city.idCity}" value="${city.name}" <#if city.idCity==cityFilter>selected</#if>>
                    ${city.name}
                </option>
            </#list>
            </select></th>
            <th><select class="custom-select" id="streetSelect" name="Street">
                <option name="0" value="all" selected>All street</option>
            <#list streets as street>
                <option name="${street.idStreet}" value="${street.name}" <#if street.idStreet==streetFilter>selected</#if>>
                    ${street.name}
                </option>
            </#list>
            </select></th>
            <th scope="col">Number house</th>
            <th scope="col">Number floors</th>
            <th scope="col">Number entrances</th>
            <th scope="col"><select class="custom-select" id="prioritySelect" name="Priority">
                <option name="0" value="all" selected>All priorities</option>
                <#list priorities as priority >
                    <option name="${priority.idPriorityList}" value="${priority.name}"
                            <#if priority.idPriorityList==priorityFilter>selected</#if>>
                        ${priority.name}
                    </option>
                </#list>
                </select></th>
            <th scope="col"><select class="custom-select" id="regionSelect" name="Region">
                <option name="0" value="all" selected>All region</option>
                <#list regions as region >
                    <option name="${region.idRegion}" value="${region.name}"
                            <#if region.idRegion==regionFilter>selected</#if>>
                        ${region.name}
                    </option>
                </#list>
                </select></th>
            <th scope="col">Update</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <#list addresses as address >
        <tr>
            <td hidden>${address.idAddress}}</td>
            <td id="city${address ? counter}">${address.city.name}</td>
            <td id="street${address ? counter}">${address.street.name}</td>
            <td>${address.houseNumber}</td>
            <td>${address.numberFloors}</td>
            <td>${address.numberEntrances}</td>
            <td id="priority${address ? counter}">${address.priorityList.name}</td>
            <td id="region${address ? counter}">${address.region.name}</td>
            <td><a id="updButtom${address ? counter}" href="/tables/address?updId=${address.idAddress}" onclick="upd(${address ? counter}); return false;">Update</a></td>
            <td>
                <a id="delButtom${address ? counter}" href="/tables/delete/address?delId=${address.idAddress}<#if (beanUp)?has_content>&updId=${beanUp.idAddress}</#if>"
                   onclick="del(${address ? counter}); return false;">Delete</a></td>
        </tr>
        </#list>
        </tbody>
    </table>
    <#if (beanUp)?has_content>
        <table class="table table-bordered table-striped " id="t">
            <h4 class="mt-4">Update record</h4>
            <thead>
            <tr>
                <th hidden>id</th>
                <th>City</th>
                <th>street</th>
                <th>Number house</th>
                <th>Number floors</th>
                <th>Number entrances</th>
                <th>Priority</th>
                <th>Region</th>
                <th>Save</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <form method="post" action="/tables/update/address?updId=${beanUp.idAddress}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <td hidden>${beanUp.idAddress}</td>
                    <td><select class="custom-select" name="idCity">
                        <option name="0" value="all" disabled>Select city</option>
                        <#list cities as city>
                            <option value="${city.idCity}" <#if city.idCity==beanUp.city.idCity>selected</#if>>
                                ${city.name}
                            </option>
                        </#list>
                    </select></td>
                    <td><select class="custom-select" name="idStreet">
                        <option name="0" value="all" disabled>Select street</option>
                        <#list streets as street>
                            <option value="${street.idStreet}" <#if street.idStreet==beanUp.street.idStreet>selected</#if>>
                                ${street.name}
                            </option>
                        </#list>
                    </select></td>
                    <td><input required autofocus class="form-control form-control-sm" type="text" name="houseNumber"
                               placeholder="Number house" value="${beanUp.houseNumber}"></td>
                    <td><input required autofocus class="form-control form-control-sm" type="number" name="numberFloors"
                               placeholder="Number floors" value="${beanUp.numberFloors}"></td>
                    <td><input required autofocus class="form-control form-control-sm" type="number" name="numberEntrances"
                        placeholder="Number entrances" value="${beanUp.numberEntrances}"></td>
                    <td><select class="custom-select" name="idPriorityList">
                        <option name="0" value="all" disabled>Select priority</option>
                        <#list priorities as priority >
                            <option value="${priority.idPriorityList}" <#if priority.idPriorityList==beanUp.priorityList.idPriorityList>selected</#if>>
                                ${priority.name}
                            </option>
                        </#list>
                    </select></td>
                    <td><select class="custom-select" name="idRegion">
                        <option name="0" value="all" disabled>Select type of work</option>
                        <#list regions as region >
                            <option value="${region.idRegion}" <#if region.idRegion==beanUp.region.idRegion>selected</#if>>
                                ${region.name}
                            </option>
                        </#list>
                    </select></td>
                    <td hidden>
                        <input  type="text" id="cfUp" name="cityFilter" value="${cityFilter}">
                        <input  type="text" id="sfUp" name="streetFilter" value="${streetFilter}">
                        <input  type="text" id="pfUp" name="priorityFilter" value="${priorityFilter}">
                        <input  type="text" id="rfUp" name="regionFilter" value="${regionFilter}">
                    </td>
                    <td>
                        <button type=submit>Save</button>
                    </td>
                </form>
            </tr>
            </tbody>
        </table>
    </#if>
</div>
    <script>
        var citySelect = document.getElementById("citySelect");
        var streetSelect = document.getElementById("streetSelect");
        var prioritySelect = document.getElementById("prioritySelect");
        var regionSelect = document.getElementById("regionSelect");

        function del(x) {
            var isDelete = confirm("Deleting this entry will delete all entries containing this key. Continue deleting?");
            if (isDelete == true) {
                var url = document.getElementById("delButtom" + x);
                location.href = url.getAttribute("href") + chekSelects();
            }
        }

        function upd(x) {
            var url = document.getElementById("updButtom" + x);
            location.href = url.getAttribute("href") + chekSelects();
        }

        function chekSelects() {
            var str = "";
            if (citySelect.options[citySelect.selectedIndex].getAttribute("name") != 0)
                str += "&cityFilter="+ citySelect.options[citySelect.selectedIndex].getAttribute("name");
            if (streetSelect.options[streetSelect.selectedIndex].getAttribute("name") != 0)
                str += "&streetFilter=" + streetSelect.options[streetSelect.selectedIndex].getAttribute("name");
            if (prioritySelect.options[prioritySelect.selectedIndex].getAttribute("name") != 0)
                str += "&priorityFilter="+ prioritySelect.options[prioritySelect.selectedIndex].getAttribute("name");
            if (regionSelect.options[regionSelect.selectedIndex].getAttribute("name") != 0)
                str += "&regionFilter=" + regionSelect.options[regionSelect.selectedIndex].getAttribute("name");
            return str;
        }

        function Filter() {
            var sellCity = citySelect.options[citySelect.selectedIndex];
            var sellStreet = streetSelect.options[streetSelect.selectedIndex];
            var sellPriority = prioritySelect.options[prioritySelect.selectedIndex];
            var sellRegion = regionSelect.options[regionSelect.selectedIndex];
            var table = document.getElementById("t");
            var allRows = table.getElementsByTagName("tr");
            for (var index in allRows) {
                if (index > 0) {
                    var fieldCity = document.getElementById("city" + index);
                    var fieldStreet = document.getElementById("street" + index);
                    var fieldPriority = document.getElementById("priority" + index);
                    var fieldRegion = document.getElementById("region" + index);
                    if (fieldCity != null && fieldStreet != null && fieldPriority != null && fieldRegion != null) {
                        allRows[index].removeAttribute("hidden");
                        if (!fieldCity.innerHTML.startsWith(sellCity.value) && !sellCity.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                        else if (!fieldStreet.innerHTML.startsWith(sellStreet.value) && !sellStreet.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                        else if (!fieldPriority.innerHTML.startsWith(sellPriority.value) && !sellPriority.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                        else if (!fieldRegion.innerHTML.startsWith(sellRegion.value) && !sellRegion.value.startsWith("all")) {

                            allRows[index].setAttribute("hidden", "hidden")
                        }
                    }
                }
            }
            document.getElementById("cf").setAttribute("value", citySelect.options[citySelect.selectedIndex].getAttribute("name"))
            document.getElementById("sf").setAttribute("value", streetSelect.options[streetSelect.selectedIndex].getAttribute("name"))
            document.getElementById("pf").setAttribute("value", prioritySelect.options[prioritySelect.selectedIndex].getAttribute("name"))
            document.getElementById("rf").setAttribute("value", regionSelect.options[regionSelect.selectedIndex].getAttribute("name"))
            document.getElementById("cfUp").setAttribute("value", citySelect.options[citySelect.selectedIndex].getAttribute("name"))
            document.getElementById("sfUp").setAttribute("value", streetSelect.options[streetSelect.selectedIndex].getAttribute("name"))
            document.getElementById("pfUp").setAttribute("value", prioritySelect.options[prioritySelect.selectedIndex].getAttribute("name"))
            document.getElementById("rfUp").setAttribute("value", regionSelect.options[regionSelect.selectedIndex].getAttribute("name"))
        }

        function selectAction() {
            changeOption();
            if (typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].value != "all"&&relevance.checked==true)
                location.href = "/work?idTypeOfWorkPerformed=" + typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].value +
                        "&relevance=" + relevance.checked +
                        "&cityFilter=" + citySelect.options[citySelect.selectedIndex].getAttribute("name") +
                        "&streetFilter=" + streetSelect.options[streetSelect.selectedIndex].getAttribute("name");
        }

        citySelect.addEventListener("change", Filter);
        streetSelect.addEventListener("change", Filter);
        prioritySelect.addEventListener("change", Filter);
        regionSelect.addEventListener("change", Filter);

        Filter();
    </script>
</@c.page>