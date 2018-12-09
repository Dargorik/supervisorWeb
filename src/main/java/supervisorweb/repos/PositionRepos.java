package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import supervisorweb.domain.Position;

public interface PositionRepos extends JpaRepository<Position,Integer> {
    Position findByName(String position);
}
