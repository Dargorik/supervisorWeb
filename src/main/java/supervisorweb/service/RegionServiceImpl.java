package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.Address;
import supervisorweb.domain.Region;
import supervisorweb.repos.AddressRepos;
import supervisorweb.repos.RegionRepos;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionRepos regionRepos;
    @Autowired
    private AddressRepos addressRepos;

    @Override
    public Region findByName(String name) {
        return regionRepos.findByName(name);
    }

    @Override
    public List<Region> findAll() {
        return regionRepos.findAll();
    }

    @Override
    public String update(Integer updId, String updName) {
        if (updId == null || updName == null || updName.isEmpty())
            return "Invalid input!";
        if(regionRepos.findByNameAndNotId(updName,updId)!=null)
            return "This component already exists!";
        Region region = regionRepos.findById(updId).orElse(null);
        if (region != null) {
            region.setName(updName);
            regionRepos.save(region);
            return "Successful update record!";
        }
        else return "This component does not exist!";
    }

    @Override
    public String delete(Integer delId) {
        Region region = regionRepos.findById(delId).orElse(null);
        if (region == null) {
            return "This component does not exist!";
        } else {
//            for(Address address:addressRepos.findByRegion(region))
//                address.setRegion(null);
            regionRepos.delete(region);
            return "Successful delete record!";
        }
    }

    @Override
    public String add(String name) {
        if (name != null && !name.isEmpty()) {
            Region region = new Region(name);
            Region r = regionRepos.findByName(region.getName());
            if (r == null) {
                regionRepos.save(region);
            } else return "This component already exists!";
        } else return "Invalid input!";
        return "Successful add record!";
    }

    @Override
    public Region findById(Integer updId) {
        return regionRepos.findById(updId).orElse(null);
    }
}
