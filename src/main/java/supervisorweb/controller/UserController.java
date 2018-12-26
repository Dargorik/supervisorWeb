package supervisorweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import supervisorweb.domain.Position;
import supervisorweb.domain.Region;
import supervisorweb.domain.Street;
import supervisorweb.domain.User;
import supervisorweb.service.PositionService;
import supervisorweb.service.UserService;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PositionService positionService;

    @RequestMapping("/list")
    public String address(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                          @RequestParam(name = "positionFilter", required = false, defaultValue = "0") Integer positionFilter,
                          @RequestParam(name = "activFilter", required = false, defaultValue = "0") String activFilter,
                          Map<String, Object> model) {
        model.put("users", userService.findAllUser());
        model.put("positions", positionService.findAll());
        model.put("beanUp", userService.findById(updId));
        Position position =positionService.findById(positionFilter);
        model.put("positionFilter", position==null?0:position.getIdPosition());
        model.put("activFilter", activFilter.equals("true")?1:activFilter.equals("false")?0:2);
        return "userTable";
    }

    @RequestMapping("/add")
    public String addAddress(@RequestParam(name = "positionFilter", required = false, defaultValue = "0") Integer positionFilter,
                             @RequestParam(name = "activFilter", required = false, defaultValue = "0") String activFilter,
                             @RequestParam(name = "firstName", required = false, defaultValue = "") String firstName,
                             @RequestParam(name = "lastName", required = false, defaultValue = "") String lastName,
                             @RequestParam(name = "username", required = false, defaultValue = "") String username,
                             @RequestParam(name = "password", required = false, defaultValue = "") String password,
                             @RequestParam(name = "idPosition", required = false, defaultValue = "") Integer idPosition,
                             @RequestParam(name = "updId", required = false, defaultValue = "") Integer updId,
                             Map<String, Object> model) {
        model.put("message", userService.add(firstName, lastName, username, password, idPosition));
        model.put("users", userService.findAllUser());
        model.put("positions", positionService.findAll());
        model.put("beanUp", userService.findById(updId));
        Position position =positionService.findById(positionFilter);
        model.put("positionFilter", position==null?0:position.getIdPosition());
        model.put("activFilter", activFilter.equals("true")?1:activFilter.equals("false")?0:2);
        return "userTable";
    }

    @RequestMapping("/update")
    public String updateAddress(@RequestParam(name = "positionFilter", required = false, defaultValue = "0") Integer positionFilter,
                                @RequestParam(name = "activFilter", required = false, defaultValue = "0") String activFilter,
                                @RequestParam(name = "firstName", required = false, defaultValue = "") String firstName,
                                @RequestParam(name = "lastName", required = false, defaultValue = "") String lastName,
                                @RequestParam(name = "username", required = false, defaultValue = "") String username,
                                @RequestParam(name = "password", required = false, defaultValue = "") String password,
                                @RequestParam(name = "idPosition", required = false, defaultValue = "") Integer idPosition,
                                @RequestParam(name = "updId", required = false, defaultValue = "") Integer updId,
                                @RequestParam(name = "activ", required = false, defaultValue = "false") Boolean activ,
                                Map<String, Object> model) {
        model.put("message", userService.update(updId, firstName, lastName, username, password, idPosition, activ));
        model.put("users", userService.findAllUser());
        model.put("positions", positionService.findAll());
        Position position =positionService.findById(positionFilter);
        model.put("positionFilter", position==null?0:position.getIdPosition());
        model.put("activFilter", activFilter.equals("true")?1:activFilter.equals("false")?0:2);
        return "userTable";
    }

    @RequestMapping("/delete")
    public String deleteAddress(@RequestParam(name = "positionFilter", required = false, defaultValue = "0") Integer positionFilter,
                                @RequestParam(name = "activFilter", required = false, defaultValue = "0") String activFilter,
                                @RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                @RequestParam(name = "delId", required = false, defaultValue = "0") Integer delId,
                                Map<String, Object> model) {
        model.put("message", userService.delete(delId));
        model.put("users", userService.findAllUser());
        model.put("positions", positionService.findAll());
        model.put("beanUp", userService.findById(updId));
        Position position =positionService.findById(positionFilter);
        model.put("positionFilter", position==null?0:position.getIdPosition());
        model.put("activFilter", activFilter.equals("true")?1:activFilter.equals("false")?0:2);
        return "userTable";
    }
}
