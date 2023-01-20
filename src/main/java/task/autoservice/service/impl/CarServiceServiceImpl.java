package task.autoservice.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.CarService;
import task.autoservice.service.CarServiceService;

import java.util.List;

@Service
public class CarServiceServiceImpl extends GenericServiceImpl<CarService>
        implements CarServiceService {
    public CarServiceServiceImpl(JpaRepository<CarService, Long> repository) {
        super(repository);
    }

    @Override
    public List<CarService> findAllWithIds(List<Long> ids) {
        return repository.findAllById(ids);
    }
}
