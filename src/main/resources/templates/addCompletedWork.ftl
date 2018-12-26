<#import "parts/common.ftl" as c>
<@c.page>
<h2>Adding completed work</h2>
<form>
    Select the type of work performed:
    <select class="custom-select" id="typeWorkSelect">
        <option disabled value="all" selected> Select</option>
        <#list typesOfWorkPerformed as typeOfWorkPerformed>
            <option value="${typeOfWorkPerformed.idTypeOfWorkPerformed}"
                    <#if typeOfWorkPerformed.idTypeOfWorkPerformed==idTypeOfWP>selected</#if>>
                ${typeOfWorkPerformed.name}
            </option>
        </#list>
    </select>
    Only relevance:
    <input type="checkbox" id="relevance" name="relevance" <#if relevance >checked</#if>>
</form>

<table hidden class="table table-striped table-bordered table-sm" id="t">
    <caption>
    <#if (message)?has_content>
        ${message}
    </#if>
    </caption>
    <thead class="thead">
    <tr>
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
        </select></>
        <th>House number</th>
        <th>Number of entrances completed</th>
        <th>Comment</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
        <#list addresses as address >
        <tr>
            <form name="myForm" method="post" action="/work/add?idUser=${user.idusers}">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="hidden" name="idAddress" value="${address.idAddress}">
                <th id="city${address ? counter}">${address.city.name}</th>
                <th id="street${address ? counter}">${address.street.name}</th>
                <th>${address.houseNumber}</th>
                <th><input class="container-fluid" required type="text" name="numberCompletedEntrances"
                           placeholder="Number completed entrances">
                </th>
                <th><input class="container-fluid" type="text" name="comment" placeholder="Comment"
                           placeholder="Comment"></th>
                <th>
                    <button class="container-fluid" name="but" value="" type=submit>add</button>
                </th>
                <th hidden>
                    <input type="text" id="relevance${address ? counter}" name="relevance" value=${relevance?c}>>
                    <input type="text" id="idTypeOfWorkPerformed${address ? counter}" name="idTypeOfWorkPerformed" value="${idTypeOfWP}">
                    <input type="text" id="cf${address ? counter}" name="cityFilter" value="${cityFilter}">
                    <input type="text" id="sf${address ? counter}" name="streetFilter" value="${streetFilter}">
                </th>
            </form>
        </tr>
        </#list>
    </tbody>
</table>
    <script>
        var typeOfWorkSelect = document.getElementById("typeWorkSelect");
        var citySelect = document.getElementById("citySelect");
        var streetSelect = document.getElementById("streetSelect");
        var relevance = document.getElementById("relevance");

        function openTable() {
            var selectedOption = typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex];
            var table = document.getElementById("t");
            if (!selectedOption.value.startsWith("all"))
                table.removeAttribute("hidden")
        }

        function relevanceCheck(e) {
            if (typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].value != "all")
                location.href = "/work?idTypeOfWorkPerformed=" + typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].value +
                        "&relevance=" + relevance.checked +
                        "&cityFilter=" + citySelect.options[citySelect.selectedIndex].getAttribute("name") +
                        "&streetFilter=" + streetSelect.options[streetSelect.selectedIndex].getAttribute("name");
            else
                relevance.checked = false;
        }

        function changeOption() {
            var selectedOption = typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex];
            if (!selectedOption.value.startsWith("all")) {
                openTable();
                var table = document.getElementById("t");
                var allRows = table.getElementsByTagName("tr");
                for (var index in allRows) {
                    if (index > 0) {
                        var p = document.getElementById("idTypeOfWorkPerformed"+index);
                        p.setAttribute("value", selectedOption.value);
                    }
                }
            }
        }

        function Filter() {
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
                document.getElementById("cf"+index).setAttribute("value", citySelect.options[citySelect.selectedIndex].getAttribute("name"))
                document.getElementById("sf"+index).setAttribute("value", streetSelect.options[streetSelect.selectedIndex].getAttribute("name"))
                }
            }
        }

        function selectAction() {
            changeOption();
            if (typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].value != "all"&&relevance.checked==true)
                location.href = "/work?idTypeOfWorkPerformed=" + typeOfWorkSelect.options[typeOfWorkSelect.selectedIndex].value +
                        "&relevance=" + relevance.checked +
                        "&cityFilter=" + citySelect.options[citySelect.selectedIndex].getAttribute("name") +
                        "&streetFilter=" + streetSelect.options[streetSelect.selectedIndex].getAttribute("name");
        }

        typeOfWorkSelect.addEventListener("change", selectAction);
        citySelect.addEventListener("change", Filter);
        streetSelect.addEventListener("change", Filter);
        relevance.addEventListener("click", relevanceCheck);

        //changeOption();
        openTable();
        Filter();
    </script>

</@c.page>