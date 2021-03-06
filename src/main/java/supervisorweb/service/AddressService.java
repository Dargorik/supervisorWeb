package supervisorweb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import supervisorweb.domain.Address;
import supervisorweb.domain.Street;
import supervisorweb.domain.User;

import java.util.List;


public interface AddressService {

    Address findById(Integer id);

    List<Address> findAll();

    String update(Integer updId,
                  Integer cityId,
                  Integer streetId,
                  String houseNumber,
                  Integer numberFloors,
                  Integer numberEntrances,
                  Integer priorityListId,
                  Integer regionId);

    String delete(Integer delId);

    String add(Integer cityId,
               Integer streetId,
               String houseNumber,
               Integer numberFloors,
               Integer numberEntrances,
               Integer priorityListId,
               Integer regionId);

    Address findByCityAndStreetAndHouseNumberLike(Integer cityId,
                                                  Integer streetId,
                                                  String houseNumber);


    List<Address> findForUser(User user);

    Page<Address> findAllPage(String cityFilter, String streetFilter, String priorityFilter, String regionFilter, Pageable pageable);
}
