package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.*;
import supervisorweb.repos.ListTypesInPerfomedWorkRepos;
import supervisorweb.repos.TypeOfWorkPerformedRepos;
import supervisorweb.repos.TypeOfWorkRepos;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListTypesInPerfomedWorkServiceImpl implements ListTypesInPerfomedWorkService  {
    @Autowired
    private ListTypesInPerfomedWorkRepos listTypesInPerfomedWorkRepos;
    @Autowired
    private TypeOfWorkPerformedRepos typeOfWorkPerformedRepos;
    @Autowired
    private TypeOfWorkRepos typeOfWorkRepos;

    @Override
    public ListTypesInPerfomedWork findByPostionAndTypeOfWork(Integer idTypeOfWorkPerformed, Integer idTypeOfWork) {
        TypeOfWorkPerformed typeOfWorkPerformed=typeOfWorkPerformedRepos.findById(idTypeOfWorkPerformed).orElse(null);
        TypeOfWork typeOfWork=typeOfWorkRepos.findById(idTypeOfWork).orElse(null);
        return listTypesInPerfomedWorkRepos.findAllByTypeOfWorkPerformedAndTypeOfWork(typeOfWorkPerformed, typeOfWork);
    }

    @Override
    public List<ListTypesInPerfomedWork> findAll() {
        return listTypesInPerfomedWorkRepos.findAll().stream()
                .sorted((x, y) -> x.getTypeOfWork().getName().compareTo(y.getTypeOfWork().getName()))
                .sorted((x, y) -> x.getTypeOfWorkPerformed().getName().compareTo(y.getTypeOfWorkPerformed().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String update(Integer updIdListTypesInPerfomedWork, Integer updIdTypeOfWorkPerformed, Integer updIdTypeOfWork) {
        ListTypesInPerfomedWork listTypesInPerfomedWork;
        try {
            TypeOfWorkPerformed typeOfWorkPerformed=typeOfWorkPerformedRepos.findById(updIdTypeOfWorkPerformed).orElse(null);
            TypeOfWork typeOfWork=typeOfWorkRepos.findById(updIdTypeOfWork).orElse(null);
            listTypesInPerfomedWork=listTypesInPerfomedWorkRepos.findAllByTypeOfWorkPerformedAndTypeOfWork(typeOfWorkPerformed, typeOfWork);
            if(listTypesInPerfomedWork!=null)
                if (!listTypesInPerfomedWork.getIdListTypesInPerfomedWork().equals(updIdListTypesInPerfomedWork))
                    return "This component already exists!";
            listTypesInPerfomedWork  = listTypesInPerfomedWorkRepos.findById(updIdListTypesInPerfomedWork).orElse(null);
            listTypesInPerfomedWork.setTypeOfWorkPerformed(typeOfWorkPerformed);
            listTypesInPerfomedWork.setTypeOfWork(typeOfWork);
            listTypesInPerfomedWorkRepos.save(listTypesInPerfomedWork);
            return "Successful update record!";
        }
        catch (NullPointerException e){
            return "Invalid input!";
        }
    }

    @Override
    public String delete(Integer delIdTypeOfWorkPerformed) {
        ListTypesInPerfomedWork listTypesInPerfomedWork = listTypesInPerfomedWorkRepos.findById(delIdTypeOfWorkPerformed).orElse(null);
        if (listTypesInPerfomedWork == null) {
            return "This component already exists!";
        } else {
            listTypesInPerfomedWorkRepos.delete(listTypesInPerfomedWork);
            return "Successful delete record!";
        }
    }

    @Override
    public String add(Integer idTypeOfWorkPerformed, Integer idTypeOfWork) {
        TypeOfWorkPerformed typeOfWorkPerformed=typeOfWorkPerformedRepos.findById(idTypeOfWorkPerformed).orElse(null);
        TypeOfWork typeOfWork=typeOfWorkRepos.findById(idTypeOfWork).orElse(null);
        ListTypesInPerfomedWork listTypesInPerfomedWork = new ListTypesInPerfomedWork(typeOfWorkPerformed, typeOfWork);
        ListTypesInPerfomedWork ltipw = listTypesInPerfomedWorkRepos.findAllByTypeOfWorkPerformedAndTypeOfWork(typeOfWorkPerformed,typeOfWork);
        if (ltipw == null) {
            listTypesInPerfomedWorkRepos.save(listTypesInPerfomedWork);
        } else return "This component already exists!";
        return "Successful add record!";
    }

    @Override
    public ListTypesInPerfomedWork findById(Integer updId) {
        return listTypesInPerfomedWorkRepos.findById(updId).orElse(null);
    }
}
