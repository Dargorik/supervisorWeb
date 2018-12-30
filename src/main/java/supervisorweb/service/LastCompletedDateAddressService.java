package supervisorweb.service;

import org.springframework.data.domain.Page;
import supervisorweb.domain.LastCompletedDateAddress;
import supervisorweb.domain.TypeOfWork;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface LastCompletedDateAddressService {

    Page<LastCompletedDateAddress> findAllAddress(String city, String streetFilter, String regionFilter, String priorityFilter, Pageable pageable);

    Page<LastCompletedDateAddress> findAllAddressByTypeWork(TypeOfWork typeOfWork, String city, String streetFilter, String regionFilter, String priorityFilter, Pageable pageable);

    Page<LastCompletedDateAddress> findRelevance(TypeOfWork typeOfWork, String city, String streetFilter, String regionFilter, String priorityFilter, Pageable pageable);
}
