package supervisorweb.service;

import supervisorweb.domain.UserRegions;

import java.util.List;

public interface UserRegionsService {
    UserRegions findAllByUserAndRegion(Integer idUser, Integer idRegion);

    List<UserRegions> findAll();

    String update(Integer updId, Integer updIdUser, Integer updIdRegion);

    String delete(Integer delId);

    String add(Integer idUser, Integer idRegion);

    UserRegions findById(Integer updId);
}
