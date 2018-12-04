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

import java.util.Collections;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserRepos userRepos;

    @Autowired
    private ListOfCompletedWorkRepos listOfCompletedWorkRepos;

    @Autowired
    private PositionRepos positionRepos;

  //  @Autowired
 //   private CityRepos cityRepos;

    @RequestMapping("/addTable")
    public String addPage(Map<String, Object> model) {
        return "add";
    }

    @RequestMapping("/add/User")
    public String addUser(Map<String, Object> model) {
        model.put("users",userRepos.findAll());
        model.put("positions",positionRepos.findAll());
        return "addUser";
    }

    @PostMapping("/add/addUser")
    public String addUser(@RequestParam String firstName,
                          @RequestParam String lastName,
                          @RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String position,
                          Map<String, Object> model) {
        User user=new User(firstName,lastName,username,password,positionRepos.findByName(position));
        User userFromDb = userRepos.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.put("message", "Пользователь с таким логином уже существует!");
            model.put("users",userRepos.findAll());
            model.put("positions",positionRepos.findAll());
            return "addUser";
        }
        user.setActiv(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepos.save(user);
        model.put("users",userRepos.findAll());
        model.put("positions",positionRepos.findAll());
        return "addUser";
    }

    @RequestMapping("/add/Position")
    public String addOutPosition(Map<String, Object> model) {
        Iterable<Position> positions=positionRepos.findAll();
        model.clear();
        model.put("positions",positionRepos.findAll());
        return "addPosition";
    }

    @PostMapping("/add/addPosition")
    public String addPosition(@RequestParam String name, Map<String, Object> model) {
        Position pos=new Position(name);
        positionRepos.save(pos);
        Iterable<Position> positions=positionRepos.findAll();
        model.put("positions",positions);
        return "addPosition";
    }


  /*  @PostMapping("/addListOfWork")
    public String addListOfWork(@AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String tag, Map<String, Object> model) {
        ListOfCompletedWork listOCW = new ListOfCompletedWork(text, user);
        listOfCompletedWorkRepos.save(listOCW);
        model.put("listOfCompletedWork", listOfCompletedWorkRepos.findAll());
        //userRepos.save(user);
        //  Iterable<User> users = userRepos.findAll();
        //  model.put("users", users);
        return "main";
    }*/


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
            Map<String, Object> model) {
        Iterable<ListOfCompletedWork> listOfCompletedWorks=listOfCompletedWorkRepos.findAll();
        if(filter!=null&&!filter.isEmpty()){
            listOfCompletedWorks=listOfCompletedWorkRepos.findByComments(filter);
        }
        model.put("listOfCompletedWork", listOfCompletedWorks);
        model.put("filter", filter);
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