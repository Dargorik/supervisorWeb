package supervisorweb.service;

import supervisorweb.domain.Address;
import supervisorweb.domain.Street;

import java.util.List;


public interface AddressService {

    Address findById(Integer id);

    List<Address> findAll();

    String update(Integer updId,
                  Integer cityId,
                  Integer streetId,
                  String houseNumber,
                  String numberFloors,
                  String numberEntrances,
                  Integer priorityListId,
                  Integer regionId);

    String delete(Integer delId);

    String add(Integer cityId,
               Integer streetId,
               String houseNumber,
               String numberFloors,
               String numberEntrances,
               Integer priorityListId,
               Integer regionId);

    Address findByCityAndStreetAndHouseNumberLike(Integer cityId,
                                                  Integer streetId,
                                                  String houseNumber);


}
