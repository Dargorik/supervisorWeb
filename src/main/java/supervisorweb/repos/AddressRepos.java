package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import supervisorweb.domain.Address;
import supervisorweb.domain.City;
import supervisorweb.domain.Region;
import supervisorweb.domain.Street;

import java.util.Collection;
import java.util.List;

public interface AddressRepos extends JpaRepository<Address,Integer> {

    Address findByCityAndStreetAndHouseNumberLike(City city, Street street, String houseNumber);
    Address findByIdAddress(Integer idAddress);


    @Override
    @Query(value="select a from Address a ORDER BY  city DESC , street DESC, houseNumber ASC ")
    List<Address> findAll();

    List<Address> findByRegion(Region region);
}
