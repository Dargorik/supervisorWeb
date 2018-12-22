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
    @Autowired
    private  WorksBasketService worksBasketService;

    @RequestMapping()
    public String main(@AuthenticationPrincipal User user,
                       @RequestParam(name = "cityFilter", required = false, defaultValue = "0") Integer cityFilter,
                       @RequestParam(name = "streetFilter", required = false, defaultValue = "0") Integer streetFilter,
                       @RequestParam(name = "idTypeOfWorkPerformed", required = false, defaultValue = "0") Integer idTypeOfWorkPerformed,
                       @RequestParam(name = "relevance", required = false, defaultValue = "false") Boolean relevance,
                       Map<String, Object> model) {
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findForUser(user));
        if(relevance==false)
            model.put("addresses", worksBasketService.findOtherAddresses(user));
        else
            model.put("addresses", worksBasketService.findRelevanceOtherAddresses(user, idTypeOfWorkPerformed));
        model.put("streets", streetService.findAll());
        model.put("cities", cityService.findAll());
        model.put("idTypeOfWP", idTypeOfWorkPerformed);
        model.put("relevance", relevance);
        City city=cityService.findById(cityFilter);
        model.put("cityFilter", city==null?0:city.getIdCity());
        Street street= streetService.findById(streetFilter);
        model.put("streetFilter", street==null?0:street.getIdStreet());
        model.put("user", user);
        return "addCompletedWorks";
    }

    @RequestMapping("/add")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam(name = "cityFilter", required = false, defaultValue = "0") Integer cityFilter,
                      @RequestParam(name = "streetFilter", required = false, defaultValue = "0") Integer streetFilter,
                      @RequestParam(name = "idTypeOfWorkPerformed", required = false, defaultValue = "0") Integer idTypeOfWorkPerformed,
                      @RequestParam(name = "relevance", required = false, defaultValue = "false") Boolean relevance,
                      @RequestParam(name = "idAddress", required = false, defaultValue = "") Integer idAddress,
                      @RequestParam(name = "numberCompletedEntrances", required = false, defaultValue = "") String numberCompletedEntrances,
                      @RequestParam(name = "comment", required = false, defaultValue = "") String comment,
                      @RequestParam(name = "ind", required = false, defaultValue = "") Integer ind,
                       Map<String, Object> model) {
        model.put("message", worksBasketService.add(user.getIdusers(),idAddress,numberCompletedEntrances,idTypeOfWorkPerformed,comment));
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findForUser(user));
        model.put("addresses", worksBasketService.findOtherAddresses(user));
        model.put("streets", streetService.findAll());
        model.put("cities", cityService.findAll());
        model.put("idTypeOfWP", idTypeOfWorkPerformed);
        model.put("relevance", relevance);
        City city=cityService.findById(cityFilter);
        model.put("cityFilter", city==null?0:city.getIdCity());
        Street street= streetService.findById(streetFilter);
        model.put("streetFilter", street==null?0:street.getIdStreet());
        model.put("user", user);
        return "addCompletedWorks";
    }

    @RequestMapping("/basket")
    public String listBasket(@AuthenticationPrincipal User user,
                             @RequestParam(name = "cityFilter", required = false, defaultValue = "0") Integer cityFilter,
                             @RequestParam(name = "streetFilter", required = false, defaultValue = "0") Integer streetFilter,
                             Map<String, Object> model) {
        model.put("completedWorks", worksBasketService.findByUser(user));
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findAll());
        model.put("addresses", addressService.findAll());
        model.put("streets", streetService.findAll());
        model.put("cities", cityService.findAll());
        City city=cityService.findById(cityFilter);
        model.put("cityFilter", city==null?0:city.getIdCity());
        Street street= streetService.findById(streetFilter);
        model.put("streetFilter", street==null?0:street.getIdStreet());
        model.put("user", user);
        return "basket";
    }

    @RequestMapping("/basket/delete")
    public String deleteBasket(@AuthenticationPrincipal User user,
                               @RequestParam(name = "delId", required = false, defaultValue = "0") Integer delId,
                               @RequestParam(name = "cityFilter", required = false, defaultValue = "0") Integer cityFilter,
                               @RequestParam(name = "streetFilter", required = false, defaultValue = "0") Integer streetFilter,
                               Map<String, Object> model) {
        model.put("message", worksBasketService.delete(delId));
        model.put("completedWorks", worksBasketService.findByUser(user));
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findAll());
        model.put("addresses", addressService.findAll());
        model.put("streets", streetService.findAll());
        model.put("cities", cityService.findAll());
        City city=cityService.findById(cityFilter);
        model.put("cityFilter", city==null?0:city.getIdCity());
        Street street= streetService.findById(streetFilter);
        model.put("streetFilter", street==null?0:street.getIdStreet());
        model.put("user", user);
        return "basket";
    }

    @RequestMapping("/basket/report")
    public String report(@AuthenticationPrincipal User user,
                               @RequestParam(name = "cityFilter", required = false, defaultValue = "0") Integer cityFilter,
                               @RequestParam(name = "streetFilter", required = false, defaultValue = "0") Integer streetFilter,
                               Map<String, Object> model) {
        model.put("message", completedWorkService.report(user));
        //model.put("message", worksBasketService.delete(delId));
        model.put("completedWorks", worksBasketService.findByUser(user));
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findAll());
        model.put("addresses", addressService.findAll());
        model.put("streets", streetService.findAll());
        model.put("cities", cityService.findAll());
        City city=cityService.findById(cityFilter);
        model.put("cityFilter", city==null?0:city.getIdCity());
        Street street= streetService.findById(streetFilter);
        model.put("streetFilter", street==null?0:street.getIdStreet());
        model.put("user", user);
        return "basket";
    }
}
