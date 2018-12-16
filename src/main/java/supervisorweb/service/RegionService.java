package supervisorweb.service;

import supervisorweb.domain.Region;

import java.util.List;

public interface RegionService {

    Region findByName(String name);

    List<Region> findAll();

    String update(Integer updId, String updName);

    String delete(Integer delId);

    String add(String name);

    Region findById(Integer updId);
}
