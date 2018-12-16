package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import supervisorweb.domain.TypeOfWorkPerformed;

import java.util.List;

public interface TypeOfWorkPerformedRepos extends JpaRepository<TypeOfWorkPerformed, Integer> {
    TypeOfWorkPerformed findByName(String name);

    @Override
    @Query(value="select tOfWP from TypeOfWorkPerformed tOfWP ORDER BY name")
    List<TypeOfWorkPerformed> findAll();

}
