package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.City;
import supervisorweb.domain.Region;
import supervisorweb.repos.RegionRepos;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionRepos regionRepos;

    @Override
    public Region findByName(String name) {
        return regionRepos.findByName(name);
    }

    @Override
    public List<Region> findAll() {
        return regionRepos.findAll().stream().sorted((x, y) -> x.getName().compareTo(y.getName())).collect(Collectors.toList());
    }

    @Override
    public String update(String updIdRegion, String updName) {
        Integer id = Integer.parseInt(updIdRegion);
        if (id == null || updName == null || updName.isEmpty())
            return "Поле с именем заполненно не корректно!";
        Region region = regionRepos.findById(id).orElse(null);
        if (region != null) {
            region.setName(updName);
            regionRepos.save(region);
            return "Изменение прошло успешно!";
        } else return "Изменение не произошло!";
    }

    @Override
    public String delete(String delIdRegion) {
        Integer id = Integer.parseInt(delIdRegion);
        Region region = regionRepos.findById(id).orElse(null);
        if (region == null) {
            return "Данный регион не найден!";
        } else {
            regionRepos.delete(region);
            return "Регион успешно удалён!";
        }
    }

    @Override
    public String add(String name) {
        if (name != null && !name.isEmpty()) {
            Region region = new Region(name);
            Region r = regionRepos.findByName(region.getName());
            if (r == null) {
                regionRepos.save(region);
            } else return "Данный регион уже есть в базе данных!";
        } else return "Поле с именем заполненно не корректно!";
        return "Новый регион успешно добавлен!";
    }

    @Override
    public Region findById(String updId) {
        return regionRepos.findById(Integer.parseInt(updId)).orElse(null);
    }
}
