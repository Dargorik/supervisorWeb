package supervisorweb.service;

import supervisorweb.domain.ListTypesInPerfomedWork;
import supervisorweb.domain.PositionDuties;

import java.util.List;

public interface ListTypesInPerfomedWorkService {
    ListTypesInPerfomedWork findByPostionAndTypeOfWork(Integer idTypeOfWorkPerformed, Integer idTypeOfWork);

    List<ListTypesInPerfomedWork> findAll();


    String update(Integer updIdListTypesInPerfomedWork, Integer updIdTypeOfWorkPerformed, Integer updIdTypeOfWork);

    String delete(Integer delIdTypeOfWorkPerformed);

    String add(Integer idTypeOfWorkPerformed, Integer idTypeOfWork);

    ListTypesInPerfomedWork findById(Integer updId);
}
