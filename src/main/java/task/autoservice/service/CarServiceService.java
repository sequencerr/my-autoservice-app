package task.autoservice.service;

import task.autoservice.model.CarService;
import task.autoservice.repository.CarServiceRepository.ServiceReport;

import java.util.List;

public interface CarServiceService extends GenericService<CarService> {
    List<ServiceReport> getServiceToPay(Long repairerId);

    void markAllPaidById(List<Long> ids);
}
