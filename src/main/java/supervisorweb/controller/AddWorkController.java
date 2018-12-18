package supervisorweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import supervisorweb.domain.City;
import supervisorweb.domain.Street;
import supervisorweb.domain.User;
import supervisorweb.service.*;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping("/work")
public class AddWorkController {
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

    @RequestMapping()
    public String main(@AuthenticationPrincipal User user,
                       @RequestParam(name = "idTypeOfWorkPerformed", required = false, defaultValue = "0") Integer idTypeOfWorkPerformed,
                       Map<String, Object> model) {
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findForUser(user));
        model.put("addresses", addressService.findForUser(user));
        model.put("streets", streetService.findAll());
        model.put("cities", cityService.findAll());
        model.put("idTypeOfWP", idTypeOfWorkPerformed);
        model.put("user", user);
        return "addCompletedWorks";
    }
    @RequestMapping("/add")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam(name = "cityFilter", required = false, defaultValue = "0") String cityFilter,
                      @RequestParam(name = "streetFilter", required = false, defaultValue = "0") String streetFilter,
                      @RequestParam(name = "idTypeOfWorkPerformed", required = false, defaultValue = "0") Integer idTypeOfWorkPerformed,
                      @RequestParam(name = "idAddress", required = false, defaultValue = "") Integer idAddress,
                      @RequestParam(name = "numberCompletedEntrances", required = false, defaultValue = "") String numberCompletedEntrances,
                      @RequestParam(name = "comment", required = false, defaultValue = "") String comment,
                      @RequestParam(name = "ind", required = false, defaultValue = "") Integer ind,
                       Map<String, Object> model) {
        //model.put("message", completedWorkService.add(user.getIdusers(),idAddress,numberCompletedEntrances,idTypeOfWorkPerformed,comment));
        System.out.println("idTypeOfWorkPerformed-"+idTypeOfWorkPerformed);
        System.out.println("idAddress-"+idAddress);
        System.out.println("numberCompletedEntrances-"+numberCompletedEntrances);
        System.out.println("comment-"+comment);
        System.out.println("cityFilter-"+cityFilter);
        System.out.println("streetFilter-"+streetFilter);
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findAll());
        model.put("addresses", addressService.findForUser(user));
        model.put("streets", streetService.findAll());
        model.put("cities", cityService.findAll());
        model.put("idTypeOfWP", idTypeOfWorkPerformed);
        City city=cityService.findByName(cityFilter);
        model.put("cityFilter", city==null?0:city);
        Street street= streetService.findByName(streetFilter);
        model.put("streetFilter", street==null?0:street);
        model.put("user", user);
        return "addCompletedWorks";
    }
}
