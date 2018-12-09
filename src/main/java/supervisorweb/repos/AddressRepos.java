package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import supervisorweb.domain.Address;
import supervisorweb.domain.City;
import supervisorweb.domain.Street;

public interface AddressRepos extends JpaRepository<Address,Integer> {

    Address findByCityAndStreetAndHouseNumberLike(City city, Street street, String houseNumber);
    Address findByIdAddress(Integer idAddress);


}
