package supervisorweb.repos;

import org.springframework.data.repository.CrudRepository;
import supervisorweb.domain.Position;

public interface PositionRepos extends CrudRepository<Position,Long> {
    Position findByName(String position);
}
