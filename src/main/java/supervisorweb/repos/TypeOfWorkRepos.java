package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import supervisorweb.domain.TypeOfWork;

public interface TypeOfWorkRepos extends JpaRepository<TypeOfWork, Integer>{
    TypeOfWork findByName(String name);
}
