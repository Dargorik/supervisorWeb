package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import supervisorweb.domain.TypeOfWork;

import java.util.List;

public interface TypeOfWorkRepos extends JpaRepository<TypeOfWork, Integer>{
    TypeOfWork findByName(String name);

    @Override
    @Query(value="select tOfW from TypeOfWork tOfW ORDER BY name")
    List<TypeOfWork> findAll();
}
