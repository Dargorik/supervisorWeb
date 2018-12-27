package supervisorweb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import supervisorweb.domain.*;
import supervisorweb.service.*;

import java.util.Date;
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
    public String allWorks(@RequestParam(name = "cityFilter", required = false, defaultValue = "0") Integer cityFilter,
                           @RequestParam(name = "streetFilter", required = false, defaultValue = "0") Integer streetFilter,
                           @RequestParam(name = "regionFilter", required = false, defaultValue = "0") Integer regionFilter,
                           @RequestParam(name = "priorityFilter", required = false, defaultValue = "0") Integer priorityFilter,
                           @RequestParam(name = "typeOfWorkFilter", required = false, defaultValue = "0") Integer typeOfWorkFilter,
                           @RequestParam(name = "relevance", required = false, defaultValue = "false") Boolean relevance,
                           Map<String, Object> model) {
        TypeOfWork typeOfWork= typeOfWorkService.findById(typeOfWorkFilter);
        model.put("typeOfWorkFilter", typeOfWork==null?0:typeOfWork.getIdTypeOfWork());


        if(relevance==false) {
            if (typeOfWorkFilter != 0)
                model.put("lastCompletedDateAddresses", lastCompletedDateAddressService.findAllAddressByTypeWork(typeOfWork));
            else
                model.put("lastCompletedDateAddresses", lastCompletedDateAddressService.findAllAddress());
        }
        else
            model.put("lastCompletedDateAddresses", lastCompletedDateAddressService.findRelevance(typeOfWorkFilter));
        model.put("typesOfWork", typeOfWorkService.findAll());
        model.put("streets", streetService.findAll());
        model.put("cities", cityService.findAll());
        model.put("regions", regionService.findAll());
        model.put("priorities", priorityListService.findAll());
        City city=cityService.findById(cityFilter);
        model.put("cityFilter", city==null?0:city.getIdCity());
        Street street= streetService.findById(streetFilter);
        model.put("streetFilter", street==null?0:street.getIdStreet());
        PriorityList priorityList1 =priorityListService.findById(priorityFilter);
        model.put("priorityFilter", priorityList1==null?0:priorityList1.getIdPriorityList());
        Region region=regionService.findById(regionFilter);
        model.put("regionFilter", region==null?0:region.getIdRegion());
        model.put("relevance", relevance);
        return "addressStat";
    }
}
