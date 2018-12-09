package supervisorweb.service;

import supervisorweb.domain.Region;

import java.util.List;

public interface RegionService {

    Region findByName(String name);

    List<Region> findAll();


    String update(String updIdRegion, String updName);

    String delete(String delIdRegion);

    String add(String name);

    Region findById(String updId);
}
