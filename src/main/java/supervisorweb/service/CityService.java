package supervisorweb.service;

import supervisorweb.domain.City;
import java.util.List;

public interface CityService {

    City findByName(String name);

    List<City> findAll();


    String update(String updIdCity, String updName);

    String delete(String delIdCity);

    String add(String name);

    City findById(String updId);
}
