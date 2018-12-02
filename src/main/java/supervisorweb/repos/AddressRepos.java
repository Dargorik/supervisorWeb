package supervisorweb.repos;

import org.springframework.data.repository.CrudRepository;
import supervisorweb.domain.Address;
import supervisorweb.domain.City;
import supervisorweb.domain.Street;

public interface AddressRepos extends CrudRepository<Address,Long> {

    Address findByCityAndStreetAndHouseNumberLike(City city, Street street, String houseNumber);

}
