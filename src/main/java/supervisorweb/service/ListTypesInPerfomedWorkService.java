package supervisorweb.service;

import supervisorweb.domain.ListTypesInPerfomedWork;
import supervisorweb.domain.PositionDuties;

import java.util.List;

public interface ListTypesInPerfomedWorkService {
    ListTypesInPerfomedWork findByPostionAndTypeOfWork(Integer idTypeOfWorkPerformed, Integer idTypeOfWork);

    List<ListTypesInPerfomedWork> findAll();


    String update(Integer updId, Integer updIdTypeOfWorkPerformed, Integer updIdTypeOfWork);

    String delete(Integer delId);

    String add(Integer idTypeOfWorkPerformed, Integer idTypeOfWork);

    ListTypesInPerfomedWork findById(Integer updId);
}
