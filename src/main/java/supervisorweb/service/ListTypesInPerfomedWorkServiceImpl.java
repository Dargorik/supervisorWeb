package supervisorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supervisorweb.domain.*;
import supervisorweb.repos.ListTypesInPerfomedWorkRepos;
import supervisorweb.repos.TypeOfWorkPerformedRepos;
import supervisorweb.repos.TypeOfWorkRepos;

import java.util.List;

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
        return listTypesInPerfomedWorkRepos.findAll();
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
                    return "Изменение не произошло!";
            listTypesInPerfomedWork  = listTypesInPerfomedWorkRepos.findById(updIdListTypesInPerfomedWork).orElse(null);
            listTypesInPerfomedWork.setTypeOfWorkPerformed(typeOfWorkPerformed);
            listTypesInPerfomedWork.setTypeOfWork(typeOfWork);
            listTypesInPerfomedWorkRepos.save(listTypesInPerfomedWork);
            return "Изменение прошло успешно!";
        }
        catch (NullPointerException e){
            return "Введенны некорректные данные";
        }
    }

    @Override
    public String delete(Integer delIdTypeOfWorkPerformed) {
        ListTypesInPerfomedWork listTypesInPerfomedWork = listTypesInPerfomedWorkRepos.findById(delIdTypeOfWorkPerformed).orElse(null);
        if (listTypesInPerfomedWork == null) {
            return "Данная связка не найден!";
        } else {
            listTypesInPerfomedWorkRepos.delete(listTypesInPerfomedWork);
            return "Связка успешно удалён!";
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
        } else return "Данная связка уже есть в базе данных!";
        return "Новый тип работы для вида выполнения работ добавлен!";

    }

    @Override
    public ListTypesInPerfomedWork findById(Integer updId) {
        return listTypesInPerfomedWorkRepos.findById(updId).orElse(null);
    }
}
