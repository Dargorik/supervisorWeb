package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.City;
import supervisorweb.repos.CityRepos;

import java.util.List;
import java.util.stream.Collectors;

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
        return cityRepos.findAll().stream().sorted((x, y) -> x.getName().compareTo(y.getName())).collect(Collectors.toList());
    }

    @Override
    public String update(String updIdCity, String updName) {
        Integer id = Integer.parseInt(updIdCity);
        if (id == null || updName == null || updName.isEmpty())
            return "Поле с именем заполненно не корректно!";
        City city = cityRepos.findById(id).orElse(null);
        if (city != null) {
            city.setName(updName);
            cityRepos.save(city);
            return "Изменение прошло успешно!";
        } else return "Изменение не произошло!";
    }

    @Override
    public String delete(String delIdCity) {
        Integer id = Integer.parseInt(delIdCity);
        City city = cityRepos.findById(id).orElse(null);
        if (city == null) {
            return "Данный город не найден!";
        } else {
            cityRepos.delete(city);
            return "Город успешно удалён!";
        }
    }

    @Override
    public String add(String name) {
        if (name != null && !name.isEmpty()) {
            City city = new City(name);
            City c = cityRepos.findByName(city.getName());
            if (c == null) {
                cityRepos.save(city);
            } else return "Данный город уже есть в базе данных!";
        } else return "Поле с именем заполненно не корректно!";
        return "Новый город успешно добавлен!";
    }

    @Override
    public City findById(String updId) {
        return cityRepos.findById(Integer.parseInt(updId)).orElse(null);
    }
}
