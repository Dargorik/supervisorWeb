package supervisorweb.repos;

import org.springframework.data.repository.CrudRepository;
import supervisorweb.domain.ListTypesInPerfomedWork;
import supervisorweb.domain.TypeOfWork;
import supervisorweb.domain.TypeOfWorkPerformed;

public interface ListTypesInPerfomedWorkRepos extends CrudRepository<ListTypesInPerfomedWork, Long>{
    ListTypesInPerfomedWork findAllByTypeOfWorkPerformedAndTypeOfWork(TypeOfWorkPerformed typeOfWorkPerformed, TypeOfWork typeOfWork);
}
