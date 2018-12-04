package supervisorweb.repos;

import org.springframework.data.repository.CrudRepository;
import supervisorweb.domain.Position;
import supervisorweb.domain.PositionDuties;
import supervisorweb.domain.TypeOfWork;

public interface PositionDutiesRepos extends CrudRepository<PositionDuties, Long> {

    PositionDuties findByPositionAndTypeOfWork(Position position, TypeOfWork typeOfWork);
}
