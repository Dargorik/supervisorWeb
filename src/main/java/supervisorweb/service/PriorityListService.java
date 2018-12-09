package supervisorweb.service;

import supervisorweb.domain.PriorityList;

import java.util.List;

public interface PriorityListService {
    PriorityList findByName(String name);

    List<PriorityList> findAll();


    String update(Integer updIdPriorityList, String updName, String updNumber);

    String delete(String delIdPriorityList);

    String add(String name, String number);

    PriorityList findById(Integer updId);
}
