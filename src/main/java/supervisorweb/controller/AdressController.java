package supervisorweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import supervisorweb.domain.*;
import supervisorweb.repos.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AdressController {
    @Autowired
    private UserRepos userRepos;
    @Autowired
    private PositionRepos positionRepos;


    @Autowired
    private CityRepos cityRepos;
    @Autowired
    private StreetRepos streetRepos;
    @Autowired
    private AddressRepos addressRepos;
    @Autowired
    private PriorityListRepos priorityListRepos;
    @Autowired
    private RegionRepos regionRepos;
    @Autowired
    private UserRegionsRepos userRegionsRepos;
    @Autowired
    private TypeOfWorkPerformedRepos typeOfWorkPerformedRepos;
    @Autowired
    private TypeOfWorkRepos typeOfWorkRepos;
    @Autowired
    private ListTypesInPerfomedWorkRepos listTypesInPerfomedWorkRepos;
    @Autowired
    private PositionDutiesRepos positionDutiesRepos;

    @Autowired
    private CompletedWorkRepos completedWorkRepos;

    @RequestMapping("/add/City")
    public String addCity(
            @RequestParam(name = "action", required = false, defaultValue = "") String action,
            @RequestParam(name = "upId", required = false, defaultValue = "0") String upIdCity,
            @RequestParam(name = "delId", required = false, defaultValue = "0") String delIdCity,
            @RequestParam(name = "flag", required = false, defaultValue = "false") String flag,
            @RequestParam(name = "filter", defaultValue = "") String filter,
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "upName", required = false, defaultValue = "") String upName,
            Map<String, Object>model) {

        switch (action) {
            case "add": {
                System.out.println(name);
                if(name!=null&&!name.isEmpty()){
                    City city = new City(name);
                    City c = cityRepos.findByName(city.getName());
                    if (c == null) {
                        cityRepos.save(city);
                        model.put("message", city.getName()+" успешно добавлен!");
                    }
                    else model.put("message", "Данный город есть в базе данных!");
                }
                break;
            }case "delete": {
                System.out.println(delIdCity);
                if(delIdCity!=null&&!delIdCity.isEmpty()){
                    if(upIdCity.equals(delIdCity)){
                        flag="false";
                    }
                    City city = cityRepos.findById(Integer.parseInt(delIdCity)).orElse(null);
                    if (city == null) {
                        model.put("message", "Данный город не найден!");
                    }
                    else{
                        cityRepos.delete(city);
                        model.put("message", city.getName()+" успешно удалён!");
                    }
                }
                break;
            }case "update": {
                if(upIdCity!=null&&!upIdCity.isEmpty()){
                    City city = cityRepos.findById(Integer.parseInt(upIdCity)).orElse(null);
                    if (city != null) {
                        city.setName(upName);
                        model.put("message", "Изменение прошло успешно!");

                        cityRepos.save(city);
                    } else {
                        model.put("message", "Изменение не произошло!");
                    }
                }
                break;
            }

        }
        if (flag.equals("true"))
            model.put("cytiUp", cityRepos.findById(Integer.parseInt(upIdCity)).orElse(null));
        model.put("idCity", upIdCity);
        model.put("cities", cityRepos.findAll().stream().sorted((x,y)->x.getName().compareTo(y.getName())).collect(Collectors.toList()));
        model.put("getflag", flag);
        model.put("filter",filter);
        return "addCities";
    }

/*
    @RequestMapping("/add/Cities")
    public String addCity(
            @RequestParam(name = "id", required = false, defaultValue = "0") String idCity,
            @RequestParam(name = "flag", required = false, defaultValue = "false") String flag,
            @RequestParam(name = "filter", defaultValue = "") String filter,
            Map<String, Object> model) {

        System.out.println(flag);
        if (flag.equals("true"))
            model.put("cytiUp", cityRepos.findById(Integer.parseInt(idCity)).orElse(null));
        model.put("cities", cityRepos.findAll());
        model.put("idCity", Integer.parseInt(idCity));
        model.put("getflag", flag);
        return "addCities";
    }

    @PostMapping("/add/addCities")
    public String addNewCity(
            @RequestParam(name = "id", required = false, defaultValue = "0") String idCity,
            @RequestParam(name = "flag", required = false, defaultValue = "false") String flag,
            @RequestParam String name, Map<String, Object> model) {

        if (flag.equals("true"))
            model.put("cytiUp", cityRepos.findById(Integer.parseInt(idCity)).orElse(null));
        model.put("cities", cityRepos.findAll());
        model.put("idCity", idCity);
        model.put("flag", flag);

        City city = new City(name);
        City c = cityRepos.findByName(city.getName());
        if (c != null) {
            model.put("message", "Данный город есть в базе данных!");
            model.put("cities", cityRepos.findAll());
            model.put("getflag", flag);
            return "addCities";
        }
        cityRepos.save(city);
        model.put("cities", cityRepos.findAll());
        model.put("getflag", flag);
        return "addCities";
    }

    @PostMapping("/add/upCities")
    public String addUpCity(
            @RequestParam(name = "id", required = false, defaultValue = "0") Integer idCity,
            @RequestParam(name = "flag", required = false, defaultValue = "false") String flag,
            @RequestParam String name, Map<String, Object> model) {


        City city = cityRepos.findById(idCity).orElse(null);
        if (city != null) {
            city.setName(name);
            model.put("message", "Изменение прошло успешно!");

            cityRepos.save(city);
        } else {
            model.put("message", "Изменение не произошло!");
        }
        System.out.println(flag + "--");
        model.put("idCity", idCity);
        model.put("getflag", flag);
        model.put("cities", cityRepos.findAll());
        return "addCities";
    }


    @RequestMapping("/add/deleteCities")
    public String del(
            @RequestParam(name = "id", required = false, defaultValue = "0") String idCity,
            @RequestParam(name = "flag", required = false, defaultValue = "false") String flag,
            Map<String, Object> model) {
        City city = cityRepos.findById(Integer.parseInt(idCity)).orElse(null);
        if (city == null) {
            model.put("message", "Данный город не найден!");
            model.put("cities", cityRepos.findAll());
            model.put("cities", cityRepos.findAll());
            model.put("idCity", idCity);
            model.put("getflag", flag);
            return "addCities";
        }
        cityRepos.delete(city);
        model.put("message", city.getName() + " удалён!");
        model.put("cities", cityRepos.findAll());
        model.put("idCity", idCity);
        model.put("getflag", flag);
        return "addCities";
    }
*/
    @RequestMapping("/add/Street")
    public String addStreet(Map<String, Object> model) {
        model.put("streets", streetRepos.findAll());
        return "addStreet";
    }

    @PostMapping("/add/addStreet")
    public String addNewStreet(@RequestParam String name, Map<String, Object> model) {
        Street street = new Street(name);
        Street st = streetRepos.findByName(street.getName());
        if (st != null) {
            model.put("message", "Данная улица уже есть в базе данных!");
            model.put("streets", streetRepos.findAll());
            return "addStreet";
        }
        streetRepos.save(street);
        model.put("streets", streetRepos.findAll());
        return "addStreet";
    }

    @RequestMapping("/add/Address")
    public String addAddress(Map<String, Object> model) {
        model.put("addresses", addressRepos.findAll());
        model.put("cities", cityRepos.findAll());
        model.put("streets", streetRepos.findAll());
        model.put("prioritiesList", priorityListRepos.findAll());
        model.put("regions", regionRepos.findAll());
        return "addAddress";
    }

    @PostMapping("/add/addAddress")
    public String addNewAddress(@RequestParam String city,
                                @RequestParam String street,
                                @RequestParam String houseNumber,
                                @RequestParam Integer numberFloors,
                                @RequestParam Integer numberEntrances,
                                @RequestParam String priorityList,
                                @RequestParam String region,
                                Map<String, Object> model) {
        System.out.println(region);
        Address address = new Address(cityRepos.findByName(city), streetRepos.findByName(street), houseNumber, numberFloors, numberEntrances, priorityListRepos.findByName(priorityList), regionRepos.findByName(region));
        Address ad = addressRepos.findByCityAndStreetAndHouseNumberLike(address.getCity(), address.getStreet(), address.getHouseNumber());
        if (ad != null) {
            model.put("message", "Данный дом уже есть в базе данных!");
            model.put("addresses", addressRepos.findAll());
            model.put("cities", cityRepos.findAll());
            model.put("streets", streetRepos.findAll());
            model.put("prioritiesList", priorityListRepos.findAll());
            model.put("regions", regionRepos.findAll());
            return "addAddress";
        }
        addressRepos.save(address);
        model.put("addresses", addressRepos.findAll());
        model.put("cities", cityRepos.findAll());
        model.put("streets", streetRepos.findAll());
        model.put("prioritiesList", priorityListRepos.findAll());
        model.put("regions", regionRepos.findAll());
        return "addAddress";
    }

    @RequestMapping("/add/PriorityList")
    public String addPriorityList(Map<String, Object> model) {
        model.put("prioritiesList", priorityListRepos.findAll());
        return "addPriorityList";
    }

    @PostMapping("/add/addPriorityList")
    public String addNewPriorityList(@RequestParam String name,
                                     @RequestParam Integer number,
                                     Map<String, Object> model) {
        PriorityList priorityList = new PriorityList(name, number);
        PriorityList prl = priorityListRepos.findByName(priorityList.getName());
        if (prl != null) {
            model.put("message", "Данная приоритетность уже есть в базе данных!");
            model.put("prioritiesList", priorityListRepos.findAll());
            return "addPriorityList";
        }
        priorityListRepos.save(priorityList);
        model.put("prioritiesList", priorityListRepos.findAll());
        return "addPriorityList";
    }

    @RequestMapping("/add/Region")
    public String addRegion(Map<String, Object> model) {
        model.put("regions", regionRepos.findAll());
        return "addRegion";
    }

    @PostMapping("/add/addRegion")
    public String addNewRegion(@RequestParam String name,
                               Map<String, Object> model) {
        Region region = new Region(name);
        Region rg = regionRepos.findByName(region.getName());
        if (rg != null) {
            model.put("message", "Данный регион уже есть в базе данных!");
            model.put("regions", regionRepos.findAll());
            return "addRegion";
        }
        regionRepos.save(region);
        model.put("regions", regionRepos.findAll());
        return "addRegion";
    }

    @RequestMapping("/add/UserRegions")
    public String addRegionsByUsers(Map<String, Object> model) {
        model.put("usersRegions", userRegionsRepos.findAll());
        model.put("regions", regionRepos.findAll());
        model.put("users", userRepos.findAll());
        return "addUserRegions";
    }

    @PostMapping("/add/addUserRegions")
    public String addNewRegionsByUsers(@RequestParam String user,
                                       @RequestParam String region,
                                       Map<String, Object> model) {
        UserRegions userRegions = new UserRegions(userRepos.findByUsername(user), regionRepos.findByName(region));
        UserRegions usr = userRegionsRepos.findAllByUserAndRegion(userRegions.getUser(), userRegions.getRegion());
        if (usr != null) {
            model.put("message", "Данная зависимость уже есть в базе данных!");
            model.put("usersRegions", userRegionsRepos.findAll());
            model.put("regions", regionRepos.findAll());
            model.put("users", userRepos.findAll());
            return "addUserRegions";
        }
        userRegionsRepos.save(userRegions);
        model.put("usersRegions", userRegionsRepos.findAll());
        model.put("regions", regionRepos.findAll());
        model.put("users", userRepos.findAll());
        return "addUserRegions";
    }

    @RequestMapping("/add/TypeOfWorkPerformed")
    public String addTypeOfWorkPerformed(Map<String, Object> model) {
        model.put("typesOfWorkPerformed", typeOfWorkPerformedRepos.findAll());
        return "addTypeOfWorkPerformed";
    }

    @PostMapping("/add/addTypeOfWorkPerformed")
    public String addNewTypeOfWorkPerformed(@RequestParam String name,
                                            Map<String, Object> model) {
        TypeOfWorkPerformed typeOfWorkPerformed = new TypeOfWorkPerformed(name);
        TypeOfWorkPerformed towp = typeOfWorkPerformedRepos.findByName(name);
        if (towp != null) {
            model.put("message", "Данный вид выполняемых работ уже есть в базе данных!");
            model.put("typesOfWorkPerformed", typeOfWorkPerformedRepos.findAll());
            return "addTypeOfWorkPerformed";
        }

        typeOfWorkPerformedRepos.save(typeOfWorkPerformed);
        model.put("typesOfWorkPerformed", typeOfWorkPerformedRepos.findAll());
        return "addTypeOfWorkPerformed";
    }

    @RequestMapping("/add/TypeOfWork")
    public String addTypeOfWork(Map<String, Object> model) {
        model.put("typesOfWork", typeOfWorkRepos.findAll());
        return "addTypeOfWork";
    }

    @PostMapping("/add/addTypeOfWork")
    public String addNewTypeOfWork(@RequestParam String name,
                                   Map<String, Object> model) {
        TypeOfWork typeOfWork = new TypeOfWork(name);
        TypeOfWork towp = typeOfWorkRepos.findByName(name);
        if (towp != null) {
            model.put("message", "Данный вид выполняемых работ уже есть в базе данных!");
            model.put("typesOfWork", typeOfWorkRepos.findAll());
            return "addTypeOfWork";
        }

        typeOfWorkRepos.save(typeOfWork);
        model.put("typesOfWork", typeOfWorkRepos.findAll());
        return "addTypeOfWork";
    }

    @RequestMapping("/add/ListTypesInPerfomedWork")
    public String addListTypesInPerfomedWork(Map<String, Object> model) {
        model.put("listsTypesInPerfomedWork", listTypesInPerfomedWorkRepos.findAll());
        model.put("typesOfWorkPerformed", typeOfWorkPerformedRepos.findAll());
        model.put("typesOfWork", typeOfWorkRepos.findAll());
        return "addListTypesInPerfomedWork";
    }

    @PostMapping("/add/addListTypesInPerfomedWork")
    public String addNewListTypesInPerfomedWork(@RequestParam String typeOfWorkPerformed,
                                                @RequestParam String typeOfWork,
                                                Map<String, Object> model) {
        ListTypesInPerfomedWork listTypesInPerfomedWork = new ListTypesInPerfomedWork(typeOfWorkPerformedRepos.findByName(typeOfWorkPerformed), typeOfWorkRepos.findByName(typeOfWork));
        ListTypesInPerfomedWork ltinpw = listTypesInPerfomedWorkRepos.findAllByTypeOfWorkPerformedAndTypeOfWork(listTypesInPerfomedWork.getTypeOfWorkPerformed(), listTypesInPerfomedWork.getTypeOfWork());
        if (ltinpw != null) {
            model.put("message", "Данная зависимость уже есть в базе данных!");
            model.put("listsTypesInPerfomedWork", listTypesInPerfomedWorkRepos.findAll());
            model.put("typesOfWorkPerformed", typeOfWorkPerformedRepos.findAll());
            model.put("typesOfWork", typeOfWorkRepos.findAll());
            return "addListTypesInPerfomedWork";
        }
        listTypesInPerfomedWorkRepos.save(listTypesInPerfomedWork);
        model.put("listsTypesInPerfomedWork", listTypesInPerfomedWorkRepos.findAll());
        model.put("typesOfWorkPerformed", typeOfWorkPerformedRepos.findAll());
        model.put("typesOfWork", typeOfWorkRepos.findAll());
        return "addListTypesInPerfomedWork";
    }

    @RequestMapping("/add/PositionDuties")
    public String addPositionDuties(Map<String, Object> model) {
        model.put("positionsDuties", positionDutiesRepos.findAll());
        model.put("positions", positionRepos.findAll());
        model.put("typesOfWork", typeOfWorkRepos.findAll());
        return "addPositionDuties";
    }

    @PostMapping("/add/addPositionDuties")
    public String addNewPositionDuties(@RequestParam String position,
                                       @RequestParam String typeOfWork,
                                       Map<String, Object> model) {
        PositionDuties positionDuties = new PositionDuties(positionRepos.findByName(position), typeOfWorkRepos.findByName(typeOfWork));
        PositionDuties pos = positionDutiesRepos.findByPositionAndTypeOfWork(positionDuties.getPosition(), positionDuties.getTypeOfWork());
        if (pos != null) {
            model.put("message", "Данная зависимость уже есть в базе данных!");
            model.put("positionsDuties", positionDutiesRepos.findAll());
            model.put("positions", positionRepos.findAll());
            model.put("typesOfWork", typeOfWorkRepos.findAll());
            return "addPositionDuties";
        }
        positionDutiesRepos.save(positionDuties);
        model.put("positionsDuties", positionDutiesRepos.findAll());
        model.put("positions", positionRepos.findAll());
        model.put("typesOfWork", typeOfWorkRepos.findAll());
        return "addPositionDuties";
    }

    @RequestMapping("/add/CompletedWorks")
    public String addCompletedWorks(Map<String, Object> model) {
        model.put("completedWorksRepos", completedWorkRepos.findAll());
        model.put("users", userRepos.findAll());
        model.put("addresses", addressRepos.findAll());
        model.put("typesOfWorkPerformed", typeOfWorkPerformedRepos.findAll());
        return "addCompletedWorks";
    }

    @PostMapping("/add/addCompletedWorks")
    public String addNewCompletedWorks(@RequestParam String username,
                                       @RequestParam Integer address,
                                       @RequestParam Integer numberCompletedEntrances,
                                       @RequestParam String typeOfWorkPerformed,
                                       @RequestParam String comment,
                                       Map<String, Object> model) {
        CompletedWork completedWork = new CompletedWork(userRepos.findByUsername(username), addressRepos.findByIdAddress(address), numberCompletedEntrances, typeOfWorkPerformedRepos.findByName(typeOfWorkPerformed), comment, new Timestamp(System.currentTimeMillis()));
        /*CompletedWork cw=completedWorkRepos.
        if(pos!=null){
            model.put("message", "Данная зависимость уже есть в базе данных!");
            model.put("completedWorksRepos", completedWorkRepos.findAll());
            model.put("users", userRepos.findAll());
            model.put("addresses", addressRepos.findAll());
            model.put("typesOfWorkPerformed", typeOfWorkPerformedRepos.findAll());
            return "addCompletedWorks";
        }*/
        completedWorkRepos.save(completedWork);
        model.put("completedWorksRepos", completedWorkRepos.findAll());
        model.put("users", userRepos.findAll());
        model.put("addresses", addressRepos.findAll());
        model.put("typesOfWorkPerformed", typeOfWorkPerformedRepos.findAll());
        return "addCompletedWorks";
    }

}
