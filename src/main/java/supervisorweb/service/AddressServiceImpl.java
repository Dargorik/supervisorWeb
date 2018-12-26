package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.*;
import supervisorweb.repos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepos addressRepos;
    @Autowired
    private CityRepos cityRepos;
    @Autowired
    private StreetRepos streetRepos;
    @Autowired
    private PriorityListRepos priorityListRepos;
    @Autowired
    private RegionRepos regionRepos;
    @Autowired
    private UserRegionsRepos userRegionsRepos;
    @Autowired
    private TypeOfWorkRepos typeOfWorkRepos;
    @Autowired
    private LastCompletedDateAddressRepos lastCompletedDateAddressRepos;


    @Override
    public Address findById(Integer updId) {
            return addressRepos.findByIdAddress(updId);
    }

    @Override
    public List<Address> findAll() {
        return addressRepos.findAll();
    }

    @Override
    public String update(Integer updId, Integer updCityId, Integer updStreetId, String updHouseNumber, Integer updNumberFloors, Integer updNumberEntrances, Integer updPriorityListId, Integer updRegionId) {
        Address address;
        try {
            if(updNumberFloors<=0||updNumberEntrances<=0)
                return "Invalid input!";
            City city=cityRepos.findById(updCityId).orElseThrow(() -> new NullPointerException());
            Street street=streetRepos.findById(updStreetId).orElseThrow(() -> new NullPointerException());
            if(addressRepos.findByCityAndStreetAndHouseNumberAndNotId(city,street,updHouseNumber,updId)!=null)
                return "This component already exists!";
            address = addressRepos.findById(updId).orElseThrow(() -> new NullPointerException());
            address.setCity(city);
            address.setStreet(street);
            address.setHouseNumber(updHouseNumber);
            address.setNumberFloors(updNumberFloors);
            address.setNumberEntrances(updNumberEntrances);
            address.setPriorityList(priorityListRepos.findById(updPriorityListId).orElseThrow(() -> new NullPointerException()));
            address.setRegion(regionRepos.findById(updRegionId).orElseThrow(() -> new NullPointerException()));
            addressRepos.save(address);
            return "Successful update record!";
        }
        catch (NullPointerException e){
            return "This component does not exist!";
        }
    }

    @Override
    public String delete(Integer delId) {
        Address address;
        try {
            address=addressRepos.findById(delId).orElse(null);
            if(address==null)
                throw new NullPointerException();
        }catch (NullPointerException e){
            return "This component does not exist!";
        }
        addressRepos.delete(address);
        return "Successful delete record!";
    }

    @Override
    public String add(Integer cityId, Integer streetId, String houseNumber, Integer numberFloors, Integer numberEntrances, Integer priorityListId, Integer regionId) {
        Address address;
        try {
            if(numberFloors<=0||numberEntrances<=0)
                return "Invalid input!";

            City city=cityRepos.findById(cityId).orElseThrow(() -> new NullPointerException());
            Street street=streetRepos.findById(streetId).orElseThrow(() -> new NullPointerException());
            if(addressRepos.findByCityAndStreetAndHouseNumberLike(city,street,houseNumber)!=null)
                return "This component already exists!";
            address = new Address();
            address.setCity(city);
            address.setStreet(street);
            address.setHouseNumber(houseNumber);
            address.setNumberFloors(numberFloors);
            address.setNumberEntrances(numberEntrances);
            address.setPriorityList(priorityListRepos.findById(priorityListId).orElseThrow(() -> new NullPointerException()));
            address.setRegion(regionRepos.findById(regionId).orElseThrow(() -> new NullPointerException()));
            addressRepos.save(address);
            addLastComletedDateAddress(address);
            return "Successful update record!";
        }
        catch (NullPointerException e){
            return "This component does not exist!";
        }
    }

    @Override
    public Address findByCityAndStreetAndHouseNumberLike(Integer cityId, Integer streetId, String houseNumber) {
        return addressRepos.findByCityAndStreetAndHouseNumberLike(cityRepos.findById(cityId).orElse(null),streetRepos.findById(streetId).orElse(null),houseNumber );
    }

    @Override
    public List<Address> findForUser(User user) {
        return addressRepos.findByUserRegions(user);
    }

    public void addLastComletedDateAddress(Address address){
        for(TypeOfWork typeOfWork: typeOfWorkRepos.findAll()){
            lastCompletedDateAddressRepos.save(new LastCompletedDateAddress(address, typeOfWork));
        }
    }
}
