package supervisorweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import supervisorweb.domain.*;
import supervisorweb.service.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/monitoring")
@PreAuthorize("hasAuthority('ADMIN')")
public class MonitoringController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private StreetService streetService;
    @Autowired
    private CityService cityService;
    @Autowired
    private TypeOfWorkPerformedService typeOfWorkPerformedService;
    @Autowired
    private CompletedWorkService completedWorkService;
    @Autowired
    private UserService userService;
    @Autowired
    private RegionService regionService;

    public Date convertData(String s){
        Date date = null;
        if(s!=null&&!s.isEmpty()) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = format.parse(s);
            } catch (ParseException e) {
                return null;
            }
        }
        return date;
    }

    @RequestMapping()
    public String allWorks(@RequestParam(name = "cityFilter", required = false, defaultValue = "0") Integer cityFilter,
                       @RequestParam(name = "streetFilter", required = false, defaultValue = "0") Integer streetFilter,
                       @RequestParam(name = "userFilter", required = false, defaultValue = "0") Integer userFilter,
                       @RequestParam(name = "regionFilter", required = false, defaultValue = "0") Integer regionFilter,
                       @RequestParam(name = "typeOfWorkPerformedFilter", required = false, defaultValue = "0") Integer typeOfWorkPerformedFilter,
                       @RequestParam(name = "fromeData", required = false, defaultValue = "") String fromeData,
                       @RequestParam(name = "toData", required = false, defaultValue = "") String toData,
                       Map<String, Object> model) {
        Date frome, to;
        frome=convertData(fromeData);
        to=convertData(toData);
        model.put("completedWorks", completedWorkService.findByData(frome, to));
        model.put("fromeData", fromeData);
        model.put("toData", toData);
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findAll());
        model.put("users", userService.findAllUser());
        model.put("addresses", addressService.findAll());
        model.put("streets", streetService.findAll());
        model.put("cities", cityService.findAll());
        model.put("regions", regionService.findAll());
        City city=cityService.findById(cityFilter);
        model.put("cityFilter", city==null?0:city.getIdCity());
        Street street= streetService.findById(streetFilter);
        model.put("streetFilter", street==null?0:street.getIdStreet());
        User user =userService.findById(userFilter);
        model.put("userFilter", user==null?0:user.getIdusers());
        Region region=regionService.findById(regionFilter);
        model.put("regionFilter", region==null?0:region.getIdRegion());
        TypeOfWorkPerformed typeOfWorkPerformed= typeOfWorkPerformedService.findById(typeOfWorkPerformedFilter);
        model.put("typeOfWorkPerformedFilter", typeOfWorkPerformed==null?0:typeOfWorkPerformed.getIdTypeOfWorkPerformed());
        return "Monitoring";
    }

    @RequestMapping("/delete")
    public String deleteWork(@RequestParam(name = "delId", required = false, defaultValue = "0") Integer delId,
                             @RequestParam(name = "cityFilter", required = false, defaultValue = "0") Integer cityFilter,
                             @RequestParam(name = "streetFilter", required = false, defaultValue = "0") Integer streetFilter,
                             @RequestParam(name = "userFilter", required = false, defaultValue = "0") Integer userFilter,
                             @RequestParam(name = "regionFilter", required = false, defaultValue = "0") Integer regionFilter,
                             @RequestParam(name = "typeOfWorkPerformedFilter", required = false, defaultValue = "0") Integer typeOfWorkPerformedFilter,
                             @RequestParam(name = "fromeData", required = false, defaultValue = "") String fromeData,
                             @RequestParam(name = "toData", required = false, defaultValue = "") String toData,
                             Map<String, Object> model) {
        model.put("message", completedWorkService.delete(delId));
        Date frome, to;
        frome=convertData(fromeData);
        to=convertData(toData);
        model.put("completedWorks", completedWorkService.findByData(frome, to));
        model.put("fromeData", fromeData);
        model.put("toData", toData);
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findAll());
        model.put("users", userService.findAllUser());
        model.put("addresses", addressService.findAll());
        model.put("streets", streetService.findAll());
        model.put("cities", cityService.findAll());
        model.put("regions", regionService.findAll());
        City city=cityService.findById(cityFilter);
        model.put("cityFilter", city==null?0:city.getIdCity());
        Street street= streetService.findById(streetFilter);
        model.put("streetFilter", street==null?0:street.getIdStreet());
        User user =userService.findById(userFilter);
        model.put("userFilter", user==null?0:user.getIdusers());
        Region region=regionService.findById(regionFilter);
        model.put("regionFilter", region==null?0:region.getIdRegion());
        TypeOfWorkPerformed typeOfWorkPerformed= typeOfWorkPerformedService.findById(typeOfWorkPerformedFilter);
        model.put("typeOfWorkPerformedFilter", typeOfWorkPerformed==null?0:typeOfWorkPerformed.getIdTypeOfWorkPerformed());
        return "Monitoring";
    }
}
