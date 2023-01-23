package task.autoservice.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.CarService;
import task.autoservice.service.CarServiceService;

@Service
public class CarServiceServiceImpl extends GenericServiceImpl<CarService>
        implements CarServiceService {
    public CarServiceServiceImpl(JpaRepository<CarService, Long> repository) {
        super(repository);
    }
}
