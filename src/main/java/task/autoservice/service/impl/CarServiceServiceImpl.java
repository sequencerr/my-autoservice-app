package task.autoservice.service.impl;

import org.springframework.stereotype.Service;
import task.autoservice.model.CarService;
import task.autoservice.repository.CarServiceRepository;
import task.autoservice.repository.CarServiceRepository.ServiceReport;
import task.autoservice.service.CarServiceService;

import java.util.List;

@Service
public class CarServiceServiceImpl extends GenericServiceImpl<CarService>
        implements CarServiceService {
    protected final CarServiceRepository repository;

    public CarServiceServiceImpl(CarServiceRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<ServiceReport> getServiceToPay(Long repairerId) {
        return repository.getServiceToPay(repairerId);
    }

    @Override
    public void markAllPaidById(List<Long> ids) {
        repository.markAllPaidById(ids);
    }
}
