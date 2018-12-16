package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.PriorityList;
import supervisorweb.repos.PriorityListRepos;

import java.util.List;

@Service
public class PriorityListServiceImpl implements PriorityListService {
    @Autowired
    PriorityListRepos priorityListRepos;

    @Override
    public PriorityList findByName(String name) {
        return priorityListRepos.findByName(name);
    }

    @Override
    public List<PriorityList> findAll() {
        return priorityListRepos.findAll();
    }

    @Override
    public String update(Integer updId, String updName, Integer updNumber) {
        if (updNumber == null || updName == null || updName.isEmpty())
            return "Invalid input!";
        PriorityList priorityList = priorityListRepos.findById(updNumber).orElse(null);
        if (priorityList != null) {
            priorityList.setName(updName);
            priorityList.setNumber(updNumber);
            priorityListRepos.save(priorityList);
            return "Successful update record!";
        }
        else return "This component already exists!";
    }

    @Override
    public String delete(Integer delId) {
        PriorityList priorityList = priorityListRepos.findById(delId).orElse(null);
        if (priorityList == null) {
            return "This component already exists!";
        } else {
            priorityListRepos.delete(priorityList);
            return "Successful delete record!";
        }
    }

    @Override
    public String add(String name, Integer number) {
        if (number != null || name != null || !name.isEmpty())
        {
            PriorityList priorityList = new PriorityList(name, number);
            PriorityList pl = priorityListRepos.findByName(priorityList.getName());
            if (pl == null) {
                priorityListRepos.save(priorityList);
            } else return "This component already exists!";
        } else return "Invalid input!";
        return "Successful add record!";
    }

    @Override
    public PriorityList findById(Integer updId) {
        return priorityListRepos.findById(updId).orElse(null);
    }
}
