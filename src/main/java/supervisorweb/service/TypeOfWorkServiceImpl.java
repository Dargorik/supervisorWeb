package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.Address;
import supervisorweb.domain.LastComletedDateAddress;
import supervisorweb.domain.TypeOfWork;
import supervisorweb.repos.AddressRepos;
import supervisorweb.repos.LastComletedDateAddressRepos;
import supervisorweb.repos.TypeOfWorkRepos;

import java.util.List;

@Service
public class TypeOfWorkServiceImpl implements  TypeOfWorkService {
    @Autowired
    private TypeOfWorkRepos typeOfWorkRepos;
    @Autowired
    private AddressRepos addressRepos;
    @Autowired
    private LastComletedDateAddressRepos lastComletedDateAddressRepos;

    @Override
    public TypeOfWork findByName(String name) {
        return typeOfWorkRepos.findByName(name);
    }

    @Override
    public List<TypeOfWork> findAll() {
        return typeOfWorkRepos.findAll();
    }

    @Override
    public String update(Integer updId, String updName) {
        if (updName == null || updName.isEmpty())
            return "Invalid input!";
        TypeOfWork typeOfWork = typeOfWorkRepos.findById(updId).orElse(null);
        if (typeOfWork != null) {
            typeOfWork.setName(updName);
            typeOfWorkRepos.save(typeOfWork);
            return "Successful update record!";
        } else return "This component already exists!";
    }

    @Override
    public String delete(Integer delIdCity) {
        TypeOfWork typeOfWork = typeOfWorkRepos.findById(delIdCity).orElse(null);
        if (typeOfWork == null) {
            return "This component does not exist!";
        } else {
            typeOfWorkRepos.delete(typeOfWork);
            return "Successful delete record!";
        }
    }

    @Override
    public String add(String name) {
        if (name != null && !name.isEmpty()) {
            TypeOfWork typeOfWork = new TypeOfWork(name);
            TypeOfWork c = typeOfWorkRepos.findByName(typeOfWork.getName());
            if (c == null) {
                addLastComletedDateAddress(typeOfWork);
                typeOfWorkRepos.save(typeOfWork);
            } else return "This component already exists!";
        } else return "Invalid input!";
        return "Successful add record!";
    }

    @Override
    public TypeOfWork findById(Integer id) {
        return typeOfWorkRepos.findById(id).orElse(null);
    }

    public void addLastComletedDateAddress(TypeOfWork typeOfWork){
        for(Address address: addressRepos.findAll()){
            lastComletedDateAddressRepos.save(new LastComletedDateAddress(address, typeOfWork));
        }
    }

    public void addAllLastComletedDateAddress(){
        for (TypeOfWork typeOfWork: typeOfWorkRepos.findAll())
            for(Address address: addressRepos.findAll())
                lastComletedDateAddressRepos.save(new LastComletedDateAddress(address, typeOfWork));
    }
}
