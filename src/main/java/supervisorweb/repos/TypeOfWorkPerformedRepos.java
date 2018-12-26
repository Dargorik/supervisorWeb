package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import supervisorweb.domain.TypeOfWorkPerformed;

import java.util.List;

public interface TypeOfWorkPerformedRepos extends JpaRepository<TypeOfWorkPerformed, Integer> {
    TypeOfWorkPerformed findByName(String name);

    @Query(value="select tOfWP from TypeOfWorkPerformed tOfWP where tOfWP.name=:name and not tOfWP.idTypeOfWorkPerformed=:id")
    TypeOfWorkPerformed findByNameAndNotId(@Param("name") String name, @Param("id") Integer id);

    @Override
    @Query(value="select tOfWP from TypeOfWorkPerformed tOfWP ORDER BY name")
    List<TypeOfWorkPerformed> findAll();

}
