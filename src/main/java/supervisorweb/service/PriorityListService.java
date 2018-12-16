package supervisorweb.service;

import supervisorweb.domain.PriorityList;

import java.util.List;

public interface PriorityListService {
    PriorityList findByName(String name);

    List<PriorityList> findAll();


    String update(Integer updId, String updName, Integer updNumber);

    String delete(Integer delId);

    String add(String name, Integer number);

    PriorityList findById(Integer updId);
}
