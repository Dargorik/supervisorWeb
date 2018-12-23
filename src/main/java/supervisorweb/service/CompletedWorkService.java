package supervisorweb.service;

import supervisorweb.domain.CompletedWork;
import supervisorweb.domain.User;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface CompletedWorkService {
    List<CompletedWork> findAll();

    List<CompletedWork> findById(Integer id);

    String add(Integer idUsers, Integer idAddress, String numberCompletedEntrances, Integer idTypeOfWorkPerformed, String comment);

    String report(User user);

    List<CompletedWork> findByData(Date frome, Date to);

    String delete(Integer delId);
}
