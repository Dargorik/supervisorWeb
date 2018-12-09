package supervisorweb.service;

import supervisorweb.domain.Street;

import java.util.List;

public interface StreetService {

    Street findByName(String name);

    List<Street> findAll();


    String update(String updIdCity, String updName);

    String delete(String delIdCity);

    String add(String name);

    Street findById(String updId);
}
