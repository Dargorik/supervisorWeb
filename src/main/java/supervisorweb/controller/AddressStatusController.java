package supervisorweb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import supervisorweb.domain.*;
import supervisorweb.service.*;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/status")
public class AddressStatusController {

    @Autowired
    private StreetService streetService;
    @Autowired
    private CityService cityService;
    @Autowired
    private TypeOfWorkService typeOfWorkService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private PriorityListService priorityListService;

    @Autowired
    private LastCompletedDateAddressService lastCompletedDateAddressService;

    @RequestMapping()
    public String allWorks(@RequestParam(name = "cityFilter", required = false, defaultValue = "%") String cityFilter,
                           @RequestParam(name = "streetFilter", required = false, defaultValue = "%") String streetFilter,
                           @RequestParam(name = "regionFilter", required = false, defaultValue = "%") String regionFilter,
                           @RequestParam(name = "priorityFilter", required = false, defaultValue = "%") String priorityFilter,
                           @RequestParam(name = "typeOfWorkFilter", required = false, defaultValue = "0") Integer typeOfWorkFilter,
                           @RequestParam(name = "relevance", required = false, defaultValue = "false") Boolean relevance,
                           @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                           Map<String, Object> model) {
        TypeOfWork typeOfWork= typeOfWorkService.findById(typeOfWorkFilter);
        model.put("typeOfWorkFilter", typeOfWork==null?0:typeOfWork.getIdTypeOfWork());
        model.put("url", "/status");

        if(relevance==false) {
            if (typeOfWorkFilter != 0)
                model.put("page", lastCompletedDateAddressService.findAllAddressByTypeWork(typeOfWork, cityFilter, streetFilter, regionFilter, priorityFilter, pageable));
            else
                model.put("page", lastCompletedDateAddressService.findAllAddress(cityFilter, streetFilter, regionFilter, priorityFilter, pageable));
        }
        else
            model.put("page", lastCompletedDateAddressService.findRelevance(typeOfWork, cityFilter, streetFilter, regionFilter, priorityFilter, pageable));

        model.put("typesOfWork", typeOfWorkService.findAll());
        model.put("streets", streetService.findAll());
        model.put("cities", cityService.findAll());
        model.put("regions", regionService.findAll());
        model.put("priorities", priorityListService.findAll());
        model.put("cityFilter", cityFilter);
        model.put("streetFilter", streetFilter);
        model.put("priorityFilter", priorityFilter);
        model.put("regionFilter", regionFilter);
        model.put("relevance", relevance);
        return "addressStat";
    }
}
