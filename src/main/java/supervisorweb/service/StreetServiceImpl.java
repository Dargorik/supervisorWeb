package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.Street;
import supervisorweb.domain.User;
import supervisorweb.repos.StreetRepos;

import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    StreetRepos streetRepos;

    @Override
    public Street findByName(String name) {
        return streetRepos.findByName(name);
    }

    @Override
    public List<Street> findAll() {
        return streetRepos.findAll();
    }

    @Override
    public String update(Integer updId, String updName) {
        if (updId == null || updName == null || updName.isEmpty())
            return "Invalid input!";
        if(streetRepos.findByNameAndNotId(updName, updId)!=null)
            return "This component already exists!";
        Street street = streetRepos.findById(updId).orElse(null);
        if (street != null) {
            street.setName(updName);
            streetRepos.save(street);
            return "Successful update record!";
        }
        else return "This component does not exist!";
    }

    @Override
    public String delete(Integer delIdy) {
        Street street = streetRepos.findById(delIdy).orElse(null);
        if (street == null) {
            return "This component does not exist!";
        } else {
            streetRepos.delete(street);
            return "Successful delete record!";
        }
    }

    @Override
    public String add(String name) {
        if (name != null && !name.isEmpty()) {
            Street street = new Street(name);
            Street s = streetRepos.findByName(street.getName());
            if (s == null) {
                streetRepos.save(street);
            } else return "This component already exists!";
        } else return "Invalid input!";
        return "Successful add record!";
    }

    @Override
    public Street findById(Integer updId) {
        return streetRepos.findById(updId).orElse(null);
    }

    @Override
    public List<Street> findByUserRegions(User user) {
        return streetRepos.findByUserRegions(user);
    }
}
