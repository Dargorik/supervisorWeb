package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.City;
import supervisorweb.repos.CityRepos;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityRepos cityRepos;

    @Override
    public City findByName(String name) {
        return cityRepos.findByName(name);
    }

    @Override
    public List<City> findAll() {
        return cityRepos.findAll();
    }

    @Override
    public String update(Integer updId, String updName) {
        if (updId == null || updName == null || updName.isEmpty())
            return "Invalid input!";
        City city = cityRepos.findById(updId).orElse(null);
        if (city != null) {
            city.setName(updName);
            cityRepos.save(city);
            return "Successful update record!";
        } else return "This component already exists!";
    }

    @Override
    public String delete(Integer delId) {
        City city = cityRepos.findById(delId).orElse(null);
        if (city == null) {
            return "This component already exists!";
        } else {
            cityRepos.delete(city);
            return "Successful delete record!";
        }
    }

    @Override
    public String add(String name) {
        if (name != null && !name.isEmpty()) {
            City city = new City(name);
            City c = cityRepos.findByName(city.getName());
            if (c == null) {
                cityRepos.save(city);
            } else return "This component already exists!";
        } else return "Invalid input!";
        return "Successful add record!";
    }

    @Override
    public City findById(Integer updId) {
        return cityRepos.findById(updId).orElse(null);
    }
}
