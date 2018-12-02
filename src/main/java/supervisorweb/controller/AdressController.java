package supervisorweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import supervisorweb.domain.*;
import supervisorweb.repos.AddressRepos;
import supervisorweb.repos.CityRepos;
import supervisorweb.repos.StreetRepos;

import java.util.Collections;
import java.util.Map;

@Controller
public class AdressController  {

    @Autowired
    private CityRepos cityRepos;
    @Autowired
    private StreetRepos streetRepos;
    @Autowired
    private AddressRepos addressRepos;

    @RequestMapping("/add/Cities")
    public String addCity(Map<String, Object> model) {
        model.put("cities",cityRepos.findAll());
        return "addCities";
    }

    @PostMapping("/add/addCities")
    public String addNewCity(@RequestParam String name, Map<String, Object> model) {
        City c=new City(name);
        cityRepos.save(c);
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
        Street c=new Street(name);
        streetRepos.save(c);
        model.put("streets",streetRepos.findAll());
        return "addStreet";
    }

    @RequestMapping("/add/Address")
    public String addAddress(Map<String, Object> model) {
        model.put("addresses",addressRepos.findAll());
        model.put("cities",cityRepos.findAll());
        model.put("streets", streetRepos.findAll());
        return "addAddress";
    }

    @PostMapping("/add/addAddress")
    public String addNewAddress(@RequestParam String city,
                                @RequestParam String street,
                                @RequestParam String houseNumber,
                                @RequestParam Integer numberFloors,
                                @RequestParam Integer numberEntrances,
                                Map<String, Object> model) {
        Address address=new Address(cityRepos.findByName(city),streetRepos.findByName(street), houseNumber, numberFloors, numberEntrances);
        Address ad=addressRepos.findByCityAndStreetAndHouseNumberLike(address.getCity(),address.getStreet(), address.getHouseNumber());
        if(ad!=null){
            model.put("message", "Данный дом уже есть в базеданных!");
            model.put("addresses",addressRepos.findAll());
            model.put("cities",cityRepos.findAll());
            model.put("streets", streetRepos.findAll());
            return "addAddress";
        }
        addressRepos.save(address);
        model.put("addresses",addressRepos.findAll());
        model.put("cities",cityRepos.findAll());
        model.put("streets", streetRepos.findAll());
        return "addAddress";
    }

}
