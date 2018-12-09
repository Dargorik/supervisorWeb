package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import supervisorweb.domain.ListTypesInPerfomedWork;
import supervisorweb.domain.TypeOfWork;
import supervisorweb.domain.TypeOfWorkPerformed;

public interface ListTypesInPerfomedWorkRepos extends JpaRepository<ListTypesInPerfomedWork, Integer> {
    ListTypesInPerfomedWork findAllByTypeOfWorkPerformedAndTypeOfWork(TypeOfWorkPerformed typeOfWorkPerformed, TypeOfWork typeOfWork);
}
