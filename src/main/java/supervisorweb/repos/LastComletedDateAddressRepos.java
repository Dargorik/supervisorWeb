package supervisorweb.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import supervisorweb.domain.Address;
import supervisorweb.domain.LastComletedDateAddress;
import supervisorweb.domain.TypeOfWork;

public interface LastComletedDateAddressRepos extends JpaRepository<LastComletedDateAddress, Integer> {
    LastComletedDateAddress findByAddressAndTypeOfWork(Address address, TypeOfWork typeOfWork);
}
