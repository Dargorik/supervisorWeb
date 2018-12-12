package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.*;
import supervisorweb.repos.RegionRepos;
import supervisorweb.repos.UserRegionsRepos;
import supervisorweb.repos.UserRepos;

import java.util.List;

@Service
public class UserRegionsServiceImpl implements UserRegionsService {
    @Autowired
    private UserRegionsRepos userRegionsRepos;
    @Autowired
    private RegionRepos regionRepos;
    @Autowired
    private UserRepos userRepos;

    @Override
    public UserRegions findAllByUserAndRegion(Integer idUser, Integer idRegion) {
        User user=userRepos.findById(idUser).orElse(null);
        Region region=regionRepos.findById(idRegion).orElse(null);
        return userRegionsRepos.findAllByUserAndRegion(user, region);
    }

    @Override
    public List<UserRegions> findAll() {
        return userRegionsRepos.findAll();
    }

    @Override
    public String update(Integer updIdUserRegions, Integer updIdUser, Integer updIdRegion) {
        UserRegions userRegions;
        try {
            User user=userRepos.findById(updIdUser).orElse(null);
            Region region=regionRepos.findById(updIdRegion).orElse(null);
            userRegions=userRegionsRepos.findAllByUserAndRegion(user, region);
            if(userRegions!=null)
                if (!userRegions.getIdUserRegions().equals(updIdUserRegions))
                    return "Изменение не произошло!";
            userRegions  = userRegionsRepos.findById(updIdUserRegions).orElse(null);
            userRegions.setUser(user);
            userRegions.setRegion(region);
            userRegionsRepos.save(userRegions);
            return "Изменение прошло успешно!";
        }
        catch (NullPointerException e){
            return "Введенны некорректные данные";
        }
    }

    @Override
    public String delete(Integer delIdUserRegions) {
        UserRegions userRegions = userRegionsRepos.findById(delIdUserRegions).orElse(null);
        if (userRegions == null) {
            return "Данная связка не найден!";
        } else {
            userRegionsRepos.delete(userRegions);
            return "Связка успешно удалён!";
        }
    }

    @Override
    public String add(Integer idUser, Integer idRegion) {
        User user=userRepos.findById(idUser).orElse(null);
        Region region=regionRepos.findById(idRegion).orElse(null);
        UserRegions userRegions = new UserRegions(user, region);
        UserRegions ur = userRegionsRepos.findAllByUserAndRegion(user,region);
        if (ur == null) {
            userRegionsRepos.save(userRegions);
        } else return "Данная связка уже есть в базе данных!";
        return "Новый регион разрешен сотруднику!";
    }

    @Override
    public UserRegions findById(Integer updId) {
        return userRegionsRepos.findById(updId).orElse(null);
    }
}
