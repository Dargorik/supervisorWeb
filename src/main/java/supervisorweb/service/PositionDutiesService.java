package supervisorweb.service;

import supervisorweb.domain.PositionDuties;

import java.util.List;

public interface PositionDutiesService {
    PositionDuties findByPostionAndTypeOfWork(Integer idPosition, Integer idTypeOfWork);

    List<PositionDuties> findAll();


    String update(Integer updIdPositionDuties, Integer updIdPosition, Integer updIdTypeOfWork);

    String delete(Integer delIdPositionDuties);

    String add(Integer idPosition, Integer idTypeOfWork);

    PositionDuties findById(Integer updId);
}
