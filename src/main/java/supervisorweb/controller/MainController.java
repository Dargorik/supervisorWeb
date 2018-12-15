package supervisorweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import supervisorweb.domain.ListOfCompletedWork;
import supervisorweb.domain.Position;
import supervisorweb.domain.Role;
import supervisorweb.domain.User;
import supervisorweb.repos.ListOfCompletedWorkRepos;
import supervisorweb.repos.PositionRepos;
import supervisorweb.repos.UserRepos;
import supervisorweb.service.UserService;

import java.util.Collections;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private ListOfCompletedWorkRepos listOfCompletedWorkRepos;
    @Autowired
    private UserService userService;


    @RequestMapping("/addTable")
    public String addPage(Map<String, Object> model) {
        return "add";
    }


   /* @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        userService.checkAdminProfile();
        return "greeting";
    }*/

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
        return "main";
    }



    @PostMapping("add")
    public String add(@AuthenticationPrincipal User user,
            @RequestParam String comments, Map<String, Object> model) {
        ListOfCompletedWork listOCW = new ListOfCompletedWork(comments, user);
        listOfCompletedWorkRepos.save(listOCW);
        model.put("listOfCompletedWork",listOfCompletedWorkRepos.findAll());
        System.out.println("123");
        //userRepos.save(user);
      //  Iterable<User> users = userRepos.findAll();
      //  model.put("users", users);
        return "main";
    }
/*
    @PostMapping("filter")
    public String filter(@RequestParam String fFirstName, @RequestParam String fLastName, Map<String, Object> model) {
        Iterable<User> users;
        if((fFirstName == null || fFirstName.isEmpty()) && (fLastName == null || fLastName.isEmpty())){
            users = userRepos.findAll();
        }
        else
        if(fFirstName != null && !fFirstName.isEmpty() && (fLastName == null || fLastName.isEmpty())){
            users = userRepos.findByFirstNameLike("%"+fFirstName+"%");
        }
        else
        if((fFirstName == null || fFirstName.isEmpty()) && fLastName != null && !fLastName.isEmpty()){
            users = userRepos.findByLastNameLike("%"+fLastName+"%");
        }
        else
            users = userRepos.findByFirstNameLikeAndLastNameLike("%"+fFirstName+"%","%"+fLastName+"%");
        model.put("users", users);
        return "main";
    }*/





/*
    @PostMapping("filter")
    public String filter(@RequestParam String fFirstName, @RequestParam String fLastName, Map<String, Object> model) {
        Iterable<User> users;
        if((fFirstName == null || fFirstName.isEmpty()) && (fLastName == null || fLastName.isEmpty())){
            users = userRepos.findAll();
        }
        else
        if(fFirstName != null && !fFirstName.isEmpty() && (fLastName == null || fLastName.isEmpty())){
            users = userRepos.findByFirstNameLike("%"+fFirstName+"%");
        }
        else
        if((fFirstName == null || fFirstName.isEmpty()) && fLastName != null && !fLastName.isEmpty()){
            users = userRepos.findByLastNameLike("%"+fLastName+"%");
        }
        else
            users = userRepos.findByFirstNameLikeAndLastNameLike("%"+fFirstName+"%","%"+fLastName+"%");


       /* if (firstName != null && !firstName.isEmpty()) {
            users = userRepos.findByFirstNameLike("%"+firstName+"%");
        } else {
            users = userRepos.findAll();
        }*/
/*
        model.put("users", users);


        return "main";
    }*/

}