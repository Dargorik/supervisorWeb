package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import supervisorweb.domain.City;
import supervisorweb.domain.User;

import java.util.List;

public interface CityRepos extends JpaRepository<City, Integer> {
    City findByName(String name);

    @Query(value="select c from City c where c.name=:name and not c.idCity=:id")
    City findByNameAndNotId(@Param("name") String name, @Param("id") Integer id);

    @Override
    @Query(value="select c from City c ORDER BY name")
    List<City> findAll();

    @Query(value="select c from City c where c.idCity in (select a.city.idCity from Address a WHERE a.region in (select region from UserRegions where user=:user) AND NOT a.idAddress in (select wb.address from WorksBasket wb where wb.user=:user)) order by c.name")
    List<City> findByUserRegions(@Param("user") User user);
}
