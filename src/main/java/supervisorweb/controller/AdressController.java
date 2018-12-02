package supervisorweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import supervisorweb.domain.*;
import supervisorweb.repos.*;

import java.util.Map;

@Controller
public class AdressController  {
    @Autowired
    private UserRepos userRepos;

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

    @RequestMapping("/add/Cities")
    public String addCity(Map<String, Object> model) {
        model.put("cities",cityRepos.findAll());
        return "addCities";
    }

    @PostMapping("/add/addCities")
    public String addNewCity(@RequestParam String name, Map<String, Object> model) {
        City city=new City(name);
        City c=cityRepos.findByName(city.getName());
        if(c!=null){
            model.put("message", "Данный город есть в базе данных!");
            model.put("cities",cityRepos.findAll());
            return "addCities";
        }
        cityRepos.save(city);
        model.put("cities",cityRepos.findAll());
        return "addCities";
    }

    @RequestMapping("/add/Street")
    public String addStreet(Map<String, Object> model) {
        model.put("streets",streetRepos.findAll());
        return "addStreet";
    }

    @PostMapping("/add/addStreet")
    public String addNewStreet(@RequestParam String name, Map<String, Object> model) {
        Street street=new Street(name);
        Street st=streetRepos.findByName(street.getName());
        if(st!=null){
            model.put("message", "Данная улица уже есть в базе данных!");
            model.put("streets",streetRepos.findAll());
            return "addStreet";
        }
        streetRepos.save(street);
        model.put("streets",streetRepos.findAll());
        return "addStreet";
    }

    @RequestMapping("/add/Address")
    public String addAddress(Map<String, Object> model) {
        model.put("addresses",addressRepos.findAll());
        model.put("cities",cityRepos.findAll());
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
        Address address=new Address(cityRepos.findByName(city),streetRepos.findByName(street), houseNumber, numberFloors, numberEntrances, priorityListRepos.findByName(priorityList), regionRepos.findByName(region));
        Address ad=addressRepos.findByCityAndStreetAndHouseNumberLike(address.getCity(),address.getStreet(), address.getHouseNumber());
        if(ad!=null){
            model.put("message", "Данный дом уже есть в базе данных!");
            model.put("addresses",addressRepos.findAll());
            model.put("cities",cityRepos.findAll());
            model.put("streets", streetRepos.findAll());
            model.put("prioritiesList", priorityListRepos.findAll());
            model.put("regions", regionRepos.findAll());
            return "addAddress";
        }
        addressRepos.save(address);
        model.put("addresses",addressRepos.findAll());
        model.put("cities",cityRepos.findAll());
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
        PriorityList priorityList=new PriorityList(name,number);
        PriorityList prl=priorityListRepos.findByName(priorityList.getName());
        if(prl!=null){
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
        Region region=new Region(name);
        Region rg=regionRepos.findByName(region.getName());
        if(rg!=null){
            model.put("message", "Данный регион уже есть в базе данных!");
            model.put("regions", regionRepos.findAll());
            return "addRegion";
        }
        regionRepos.save(region);
        model.put("regions", regionRepos.findAll());
        return "addRegion";
    }

    @RequestMapping("/add/RegionsByUsers")
    public String addRegionsByUsers(Map<String, Object> model) {
        model.put("regions", regionRepos.findAll());
        model.put("users", userRepos.findAll());
        return "addRegion";
    }

    @PostMapping("/add/addRegionsByUsers")
    public String addNewRegionsByUsers(Map<String, Object> model) {
        model.put("regions", regionRepos.findAll());
        model.put("users", userRepos.findAll());
        return "addRegion";
    }

}
