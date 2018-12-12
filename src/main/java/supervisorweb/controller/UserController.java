package supervisorweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String address(@RequestParam(name = "updId", required = false, defaultValue = "") Integer updId,
                          @RequestParam(name = "flag", required = false, defaultValue = "false") String flag, Map<String, Object> model) {
        if (flag.equals("true")) {
            model.put("beanUp", userService.findById(updId));
        }
        model.put("users", userService.findAll());
        model.put("positions", positionService.findAll());
        model.put("getflag", flag);
        return "userTable";
    }

    @RequestMapping("/add")
    public String addAddress(@RequestParam(name = "firstName", required = false, defaultValue = "") String firstName,
                             @RequestParam(name = "lastName", required = false, defaultValue = "") String lastName,
                             @RequestParam(name = "username", required = false, defaultValue = "") String username,
                             @RequestParam(name = "password", required = false, defaultValue = "") String password,
                             @RequestParam(name = "idPosition", required = false, defaultValue = "") Integer idPosition,
                             @RequestParam(name = "updId", required = false, defaultValue = "") Integer updId,
                             @RequestParam(name = "flag", required = false, defaultValue = "false") String flag,
                             Map<String, Object> model) {
        model.put("message", userService.add(firstName, lastName, username, password, idPosition));
        if (flag.equals("true"))
            model.put("beanUp", userService.findById(updId));
        model.put("users", userService.findAll());
        model.put("positions", positionService.findAll());
        model.put("getflag", flag);
        return "userTable";
    }

    @RequestMapping("/update")
    public String updateAddress(@RequestParam(name = "firstName", required = false, defaultValue = "") String firstName,
                                @RequestParam(name = "lastName", required = false, defaultValue = "") String lastName,
                                @RequestParam(name = "username", required = false, defaultValue = "") String username,
                                @RequestParam(name = "password", required = false, defaultValue = "") String password,
                                @RequestParam(name = "idPosition", required = false, defaultValue = "") Integer idPosition,
                                @RequestParam(name = "updId", required = false, defaultValue = "") Integer updId,
                                @RequestParam(name = "flag", required = false, defaultValue = "false") String flag,
                                Map<String, Object> model) {
        model.put("message", userService.update(updId, firstName, lastName, username, password, idPosition));
        if (flag.equals("true"))
            model.put("beanUp", userService.findById(updId));
        model.put("users", userService.findAll());
        model.put("positions", positionService.findAll());
        model.put("getflag", flag);
        return "userTable";
    }

    @RequestMapping("/delete")
    public String deleteAddress(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                @RequestParam(name = "delId", required = false, defaultValue = "0") Integer delId,
                                @RequestParam(name = "flag", required = false, defaultValue = "false") String flag,
                                Map<String, Object> model) {
        model.put("message", userService.delete(delId));
        if (updId.equals(delId)) {
            model.put("updId", updId);
            flag = "false";
        }
        if (flag.equals("true"))
            model.put("beanUp", userService.findById(updId));
        model.put("users", userService.findAll());
        model.put("positions", positionService.findAll());
        model.put("getflag", flag);
        return "userTable";
    }
}
