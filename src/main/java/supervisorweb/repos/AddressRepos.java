package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import supervisorweb.domain.*;

import java.util.Collection;
import java.util.List;

public interface AddressRepos extends JpaRepository<Address,Integer> {

    Address findByCityAndStreetAndHouseNumberLike(City city, Street street, String houseNumber);
    Address findByIdAddress(Integer idAddress);

    @Override
    @Query(value="select a from Address a ORDER BY a.city.name,a.street.name,a.houseNumber")
    List<Address> findAll();

    List<Address> findByRegion(Region region);

    @Query(value="select a from Address a WHERE a.region in (select region from UserRegions where user=:user) AND NOT a.idAddress in (select wb.address from WorksBasket wb where wb.user=:user) order by a.city.name,a.street.name,a.houseNumber")
    List<Address> findByUserRegions(@Param("user") User user);
}
