package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.*;
import supervisorweb.repos.ListTypesInPerfomedWorkRepos;
import supervisorweb.repos.PositionDutiesRepos;
import supervisorweb.repos.TypeOfWorkPerformedRepos;
import supervisorweb.repos.TypeOfWorkRepos;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeOfWorkPerformedServiceImpl implements TypeOfWorkPerformedService {
    @Autowired
    private TypeOfWorkPerformedRepos typeOfWorkPerformedRepos;
    @Autowired
    private PositionDutiesRepos positionDutiesRepos;
    @Autowired
    private ListTypesInPerfomedWorkRepos listTypesInPerfomedWorkRepos;
    @Autowired
    private TypeOfWorkRepos typeOfWorkRepos;

    @Override
    public TypeOfWorkPerformed findByName(String name) {
        return typeOfWorkPerformedRepos.findByName(name);
    }

    @Override
    public List<TypeOfWorkPerformed> findAll() {
        return typeOfWorkPerformedRepos.findAll();
    }

    @Override
    public String update(Integer updId, String updName) {
        if (updName == null || updName.isEmpty())
            return "Invalid input!";
        if (typeOfWorkPerformedRepos.findByNameAndNotId(updName, updId) != null)
            return "This component already exists!";
        TypeOfWorkPerformed typeOfWorkPerformed = typeOfWorkPerformedRepos.findById(updId).orElse(null);
        if (typeOfWorkPerformed != null) {
            typeOfWorkPerformed.setName(updName);
            typeOfWorkPerformedRepos.save(typeOfWorkPerformed);
            return "Successful update record!";
        } else return "This component does not exist!";
    }

    @Override
    public String delete(Integer delId) {
        TypeOfWorkPerformed typeOfWorkPerformed = typeOfWorkPerformedRepos.findById(delId).orElse(null);
        if (typeOfWorkPerformed == null) {
            return "This component does not exist!";
        } else {
            typeOfWorkPerformedRepos.delete(typeOfWorkPerformed);
            return "Successful delete record!";
        }
    }

    @Override
    public String add(String name) {
        if (name != null && !name.isEmpty()) {
            TypeOfWorkPerformed typeOfWorkPerformed = new TypeOfWorkPerformed(name);
            TypeOfWorkPerformed c = typeOfWorkPerformedRepos.findByName(typeOfWorkPerformed.getName());
            if (c == null) {
                typeOfWorkPerformedRepos.save(typeOfWorkPerformed);
            } else return "This component already exists!";
        } else return "Invalid input!";
        return "Successful add record!";
    }

    @Override
    public TypeOfWorkPerformed findById(Integer id) {
        return typeOfWorkPerformedRepos.findById(id).orElse(null);
    }

    @Override
    public List<TypeOfWorkPerformed> findForUser(User user) {
        List<TypeOfWorkPerformed> list = new ArrayList<>();
        List<TypeOfWork> listTypeOfWorkUser = typeOfWorkRepos.findByUsersPosition(user.getPosition());
        if (listTypeOfWorkUser.size() != 0) {
            for (TypeOfWorkPerformed typeOfWorkPerformed : typeOfWorkPerformedRepos.findAll()) {
                boolean flag = true;
                for (ListTypesInPerfomedWork listTypesInPerfomedWork : listTypesInPerfomedWorkRepos.findByTypeOfWorkPerformed(typeOfWorkPerformed)) {
                    if (!listTypeOfWorkUser.contains(listTypesInPerfomedWork.getTypeOfWork())) {
                        flag = false;
                        System.out.println(flag);
                        break;
                    }
                }
                if (flag == true)
                    list.add(typeOfWorkPerformed);
            }
        }
        return list;
    }
}
