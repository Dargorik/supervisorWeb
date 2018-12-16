package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import supervisorweb.domain.Position;
import supervisorweb.domain.PositionDuties;
import supervisorweb.domain.TypeOfWork;

import java.util.List;

public interface PositionDutiesRepos extends JpaRepository<PositionDuties, Integer> {

    PositionDuties findByPositionAndTypeOfWork(Position position, TypeOfWork typeOfWork);

    List<PositionDuties> findByPosition(Position position);
}
