package supervisorweb.service;

import supervisorweb.domain.City;
import supervisorweb.domain.User;

import java.util.List;

public interface CityService {

    City findByName(String name);

    List<City> findAll();


    String update(Integer updId, String updName);

    String delete(Integer delId);

    String add(String name);

    City findById(Integer updId);

    List<City> findByUserRegions(User user);
}
