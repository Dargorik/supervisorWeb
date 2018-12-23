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
        if (updId == null){
            throw new IllegalArgumentException("Адреса с тамим ID не найденн!");
        }
        Address address =addressRepos.findByIdAddress(updId);
        if (address == null){
            throw new IllegalArgumentException("Адреса с тамим ID  не найденн!");
        }
            return address;
    }

    @Override
    public List<Address> findAll() {
        return addressRepos.findAll();
    }

    @Override
    public String update(Integer updId, Integer updCityId, Integer updStreetId, String updHouseNumber, String updNumberFloors, String updNumberEntrances, Integer updPriorityListId, Integer updRegionId) {
        Integer numFloors, numEntrances;
        Address address;
        try {
            numFloors = Integer.parseInt(updNumberFloors);
            numEntrances = Integer.parseInt(updNumberEntrances);
            if (numFloors <= 0 || numEntrances <= 0 || updHouseNumber == null || updHouseNumber.isEmpty())
                throw new NullPointerException();
            address=findByCityAndStreetAndHouseNumberLike(updCityId, updStreetId, updHouseNumber);
            if(address!=null)
                if(!address.getIdAddress().equals(updId))
                    return "Данное изменение невозможно, так как есть такаой адрес в базе данных!";
            address = addressRepos.findById(updId).orElseThrow(() -> new NullPointerException());
            address.setCity(cityRepos.findById(updCityId).orElseThrow(() -> new NullPointerException()));
            address.setStreet(streetRepos.findById(updStreetId).orElseThrow(() -> new NullPointerException()));
            address.setHouseNumber(updHouseNumber);
            address.setNumberFloors(numFloors);
            address.setNumberEntrances(numEntrances);
            address.setPriorityList(priorityListRepos.findById(updPriorityListId).orElseThrow(() -> new NullPointerException()));
            address.setRegion(regionRepos.findById(updRegionId).orElseThrow(() -> new NullPointerException()));
            addressRepos.save(address);
            return "Изменение прошло успешно!";
        }
        catch (NullPointerException e){
            return "Введенны некорректные данные";
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
            return "Данный адрес не удлалён!";
        }
        addressRepos.delete(address);
        return "Адрес успешно удлалён!";
    }

    @Override
    public String add(Integer cityId, Integer streetId, String houseNumber, String numberFloors, String numberEntrances, Integer priorityListId, Integer regionId) {
        Integer numFloors, numEntrances;
        try{
            numFloors=Integer.parseInt(numberFloors);
            numEntrances=Integer.parseInt(numberEntrances);
        }
        catch (NullPointerException e){
            return "Введенны некорректные данные";
        }
        if(numFloors<=0||numEntrances<=0)
            return "Введенны некорректные данные";
        Address address=findByCityAndStreetAndHouseNumberLike(cityId, streetId, houseNumber);
        if(address!=null)
            return "Такой адрес уже есть в базе данных";
        address=new Address(cityRepos.findById(cityId).orElse(null),streetRepos.findById(streetId).orElse(null),houseNumber,numFloors,numEntrances,priorityListRepos.findById(priorityListId).orElse(null),regionRepos.findById(regionId).orElse(null));
        addLastComletedDateAddress(address);
        addressRepos.save(address);
        return "Новый адрес добавлен";
    }

    @Override
    public Address findByCityAndStreetAndHouseNumberLike(Integer cityId, Integer streetId, String houseNumber) {
        return addressRepos.findByCityAndStreetAndHouseNumberLike(cityRepos.findById(cityId).orElse(null),streetRepos.findById(streetId).orElse(null),houseNumber );
    }

    @Override
    public List<Address> findForUser(User user) {
        List<Address> address=new ArrayList<>();
        for(UserRegions userRegions: userRegionsRepos.findByUser(user)){
            address.addAll(addressRepos.findByRegion(userRegions.getRegion()));
        }
        return address;
    }

    public void addLastComletedDateAddress(Address address){
        for(TypeOfWork typeOfWork: typeOfWorkRepos.findAll()){
            lastCompletedDateAddressRepos.save(new LastCompletedDateAddress(address, typeOfWork));
        }
    }
}
