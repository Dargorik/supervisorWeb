package supervisorweb.service;

import supervisorweb.domain.Street;
import supervisorweb.domain.User;

import java.util.List;

public interface StreetService {

    Street findByName(String name);

    List<Street> findAll();


    String update(Integer updId, String updName);

    String delete(Integer delIdCity);

    String add(String name);

    Street findById(Integer updId);

    List<Street> findByUserRegions(User user);
}
