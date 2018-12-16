package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.*;
import supervisorweb.repos.RegionRepos;
import supervisorweb.repos.UserRegionsRepos;
import supervisorweb.repos.UserRepos;

import java.util.List;
import java.util.stream.Collectors;

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
        return userRegionsRepos.findAll().stream()
                .sorted((x, y) -> x.getRegion().getName().compareTo(y.getRegion().getName()))
                .sorted((x, y) -> x.getUser().getFirstName().compareTo(y.getUser().getFirstName()))
                .collect(Collectors.toList());
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
                    return "This component already exists!";
            userRegions  = userRegionsRepos.findById(updIdUserRegions).orElse(null);
            userRegions.setUser(user);
            userRegions.setRegion(region);
            userRegionsRepos.save(userRegions);
            return "Successful update record!";
        }
        catch (NullPointerException e){
            return "Invalid input!";
        }
    }

    @Override
    public String delete(Integer delIdUserRegions) {
        UserRegions userRegions = userRegionsRepos.findById(delIdUserRegions).orElse(null);
        if (userRegions == null) {
            return "This component already exists!";
        } else {
            userRegionsRepos.delete(userRegions);
            return "Successful delete record!";
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
        } else return "This component already exists!";
        return "Successful add record!";
    }

    @Override
    public UserRegions findById(Integer updId) {
        return userRegionsRepos.findById(updId).orElse(null);
    }
}
