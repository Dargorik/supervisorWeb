package supervisorweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import supervisorweb.domain.User;
import supervisorweb.service.*;

import java.util.Map;

@Controller
@RequestMapping("/monitoring")
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

    @RequestMapping()
    public String main(@AuthenticationPrincipal User user,
                       Map<String, Object> model) {
        model.put("completedWorks", completedWorkService.findAll());
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findAll());
        model.put("addresses", addressService.findAll());
        model.put("streets", streetService.findAll());
        model.put("cities", cityService.findAll());
        model.put("user", user);
        return "Monitoring";
    }
}
