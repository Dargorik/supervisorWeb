package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import supervisorweb.domain.Position;

import java.util.List;

public interface PositionRepos extends JpaRepository<Position,Integer> {
    Position findByName(String position);

    @Query(value="select p from Position p where p.name=:name and not p.idPosition=:id")
    Position findByNameAndNotId(@Param("name") String name, @Param("id") Integer id);

    @Override
    @Query(value="select p from Position p ORDER BY name")
    List<Position> findAll();
}
