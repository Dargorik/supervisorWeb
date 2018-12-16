package supervisorweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import supervisorweb.domain.User;
import supervisorweb.repos.CompletedWorkRepos;
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
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findAll());
        model.put("addresses", addressService.findAll());
        model.put("streets", streetService.findAll());
        model.put("cities", cityService.findAll());
        model.put("idTypeOfWP", idTypeOfWorkPerformed);
        model.put("user", user);
        return "addCompletedWorks";
    }
    @RequestMapping("/add")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam(name = "cityFiler", required = false, defaultValue = "0") String cityFiler,
                      @RequestParam(name = "streetFiler", required = false, defaultValue = "0") String streetFiler,
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
        System.out.println("cityFiler-"+cityFiler);
        System.out.println("streetFiler-"+streetFiler);
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findAll());
        model.put("addresses", addressService.findAll());
        model.put("streets", streetService.findAll());
        model.put("cities", cityService.findAll());
        model.put("idTypeOfWP", idTypeOfWorkPerformed);
        model.put("cityFiler", cityService.findByName(cityFiler)==null?0:cityService.findByName(cityFiler).getIdCity());
        model.put("streetFiler", streetService.findByName(streetFiler)==null?0:streetService.findByName(streetFiler).getIdStreet());
        model.put("user", user);
        return "addCompletedWorks";
    }
}
