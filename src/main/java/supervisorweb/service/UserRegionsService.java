package supervisorweb.service;

import supervisorweb.domain.UserRegions;

import java.util.List;

public interface UserRegionsService {
    UserRegions findAllByUserAndRegion(Integer idUser, Integer idRegion);

    List<UserRegions> findAll();


    String update(Integer updIdUserRegions, Integer updIdUser, Integer updIdRegion);

    String delete(Integer delIdUserRegions);

    String add(Integer idUser, Integer idRegion);

    UserRegions findById(Integer updId);
}
