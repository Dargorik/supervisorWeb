<#import "parts/common.ftl" as c>
<@c.page>
<h2>List of work done</h2>
<div hidden>
    <input type="text" id="cf" name="cityFilter" value="${cityFilter}">
    <input type="text" id="sf" name="streetFilter" value="${streetFilter}">
</div>


<form name="myForm" method="post" action="/work/basket/report">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit" class="btn btn-primary  btn-sm">Send report</button>
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
        </select></>
        <th>House number</th>
        <th>Number of entrances completed</th>
        <th>Type of work performed</th>
        <th>Comment</th>
        <th>Delete</th>
    </thead>
    <tbody>
        <#list completedWorks as completedWork >
        <tr>
            <form name="myForm" method="post" action="/work/basket/delete">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="hidden" name="delId" value="${completedWork.idCompletedWork}">
                <th id="city${completedWork ? counter}">${completedWork.address.city.name}</th>
                <th id="street${completedWork ? counter}">${completedWork.address.street.name}</th>
                <th>${completedWork.address.houseNumber}</th>
                <th>${completedWork.numberCompletedEntrances}</th>
                <th>${completedWork.typeOfWorkPerformed.name}</th>
                <th>${completedWork.comment}</th>
                <th>
                    <button class="container-fluid" name="but" value="" type=submit>delete</button>
                </th>
            </form>
        </tr>
        </#list>
    </tbody>
</table>
    <script>
        var citySelect = document.getElementById("citySelect");
        var streetSelect = document.getElementById("streetSelect");

        function dell(x) {
            var url = document.getElementById("delButtom" + x);
            location.href = url.getAttribute("href") + "&cityFilter=" + document.getElementById("cf").value + "&streetFilter=" + document.getElementById("sf").value;
        }

        function cityFilter() {
            var sellCity = citySelect.options[citySelect.selectedIndex];
            var sellStreet = streetSelect.options[streetSelect.selectedIndex];
            var table = document.getElementById("t");
            var allRows = table.getElementsByTagName("tr");
            for (var index in allRows) {
                if (index > 0) {
                    var fieldCity = document.getElementById("city" + index);
                    var fieldStreet = document.getElementById("street" + index);
                    if (fieldCity != null && fieldStreet != null) {
                        allRows[index].removeAttribute("hidden");
                        if (!fieldCity.innerHTML.startsWith(sellCity.value) && !sellCity.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                        else if (!fieldStreet.innerHTML.startsWith(sellStreet.value) && !sellStreet.value.startsWith("all")) {
                            allRows[index].setAttribute("hidden", "hidden")
                        }
                    }
                }
            }
            document.getElementById("cf").setAttribute("value", citySelect.options[citySelect.selectedIndex].getAttribute("name"))
            document.getElementById("sf").setAttribute("value", streetSelect.options[streetSelect.selectedIndex].getAttribute("name"))
        }

        citySelect.addEventListener("change", cityFilter);
        streetSelect.addEventListener("change", cityFilter);

        cityFilter();
    </script>
</@c.page>