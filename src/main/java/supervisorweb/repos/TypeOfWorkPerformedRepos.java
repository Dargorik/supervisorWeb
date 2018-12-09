package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import supervisorweb.domain.TypeOfWorkPerformed;

public interface TypeOfWorkPerformedRepos extends JpaRepository<TypeOfWorkPerformed, Integer> {
    TypeOfWorkPerformed findByName(String name);

}
