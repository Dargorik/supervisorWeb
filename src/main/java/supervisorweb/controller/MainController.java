package supervisorweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import supervisorweb.domain.Role;
import supervisorweb.domain.User;
import supervisorweb.service.UserService;
import supervisorweb.service.WorksBasketService;

import java.util.Collections;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private WorksBasketService worksBasketService;

    @RequestMapping("/addTable")
    public String addPage(Map<String, Object> model) {
        return "add";
    }

    @RequestMapping("/reset")
    public String reset(Map<String, Object> model) {
        model.put("message", userService.resetAdminPasssword());
        return "login";
    }

    @RequestMapping("/resetPassword/{code}")
    public String resetPassord(@PathVariable String code,
                               Map<String, Object> model) {
        boolean isReset = userService.resetPassord(code);
        if(isReset)
            model.put("message", "Password has been successfully reset!");
        else
            model.put("message", "User is not found");
        return "login";
    }

    @GetMapping("/")
    public String main(@AuthenticationPrincipal User user,
                       @RequestParam(required = false, defaultValue = "") String filter,
            Map<String, Object> model) {
        userService.checkAdminProfile();
        model.put("filter", filter);
        model.put("user",user);
        if (user!=null)
            if (user.getRoles().equals(Collections.singleton(Role.ADMIN)))
                model.put("role", "admin");
            else
                model.put("role", "user");
        model.put("sizeBasket", worksBasketService.findCount(user));
        return "main";
    }



    @PostMapping("add")
    public String add(@AuthenticationPrincipal User user,
            @RequestParam String comments, Map<String, Object> model) {
        model.put("sizeBasket", worksBasketService.findCount(user));
        return "main";
    }
}