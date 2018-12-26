package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import supervisorweb.domain.Street;
import supervisorweb.domain.User;

import java.util.List;

public interface StreetRepos extends JpaRepository<Street, Integer> {
    Street findByName(String name);

    @Query(value="select s from Street s where s.name=:name and not s.idStreet=:id")
    Street findByNameAndNotId(@Param("name") String name, @Param("id") Integer id);

    @Override
    @Query(value="select s from Street s ORDER BY name")
    List<Street> findAll();

    @Query(value="select s from Street s where s.idStreet in (select a.street.idStreet from Address a WHERE a.region in (select region from UserRegions where user=:user) AND NOT a.idAddress in (select wb.address from WorksBasket wb where wb.user=:user)) order by s.name")
    List<Street> findByUserRegions(@Param("user") User user);
}
