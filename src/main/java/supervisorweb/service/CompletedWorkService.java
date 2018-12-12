package supervisorweb.service;

import supervisorweb.domain.CompletedWork;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

public interface CompletedWorkService {
    List<CompletedWork> findAll();

    List<CompletedWork> findById(Integer id);

    String add(Integer idUsers, Integer idAddress, String numberCompletedEntrances, Integer idTypeOfWorkPerformed, String comment);
}
