package supervisorweb.service;

import supervisorweb.domain.LastCompletedDateAddress;
import supervisorweb.domain.TypeOfWork;

import java.util.List;

public interface LastCompletedDateAddressService {

    List<LastCompletedDateAddress> findAllAddress();

    List<LastCompletedDateAddress> findAllAddressByTypeWork(TypeOfWork typeOfWork);

    List<LastCompletedDateAddress> findRelevance(Integer idTypeOfWorkPerformed);
}
