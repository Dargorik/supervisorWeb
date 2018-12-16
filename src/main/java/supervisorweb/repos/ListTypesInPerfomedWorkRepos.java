package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import supervisorweb.domain.ListTypesInPerfomedWork;
import supervisorweb.domain.TypeOfWork;
import supervisorweb.domain.TypeOfWorkPerformed;

import java.util.List;

public interface ListTypesInPerfomedWorkRepos extends JpaRepository<ListTypesInPerfomedWork, Integer> {

    List<ListTypesInPerfomedWork> findByTypeOfWorkPerformed(TypeOfWorkPerformed typeOfWorkPerformed);

    ListTypesInPerfomedWork findAllByTypeOfWorkPerformedAndTypeOfWork(TypeOfWorkPerformed typeOfWorkPerformed, TypeOfWork typeOfWork);
}
