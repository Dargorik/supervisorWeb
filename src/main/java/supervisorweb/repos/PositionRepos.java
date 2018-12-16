package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import supervisorweb.domain.Position;

import java.util.List;

public interface PositionRepos extends JpaRepository<Position,Integer> {
    Position findByName(String position);

    @Override
    @Query(value="select p from Position p ORDER BY name")
    List<Position> findAll();
}
