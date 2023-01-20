package task.autoservice.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.Car;
import task.autoservice.service.CarService;

import java.util.List;

@Service
public class CarServiceImpl extends GenericServiceImpl<Car> implements CarService {
    public CarServiceImpl(JpaRepository<Car, Long> repository) {
        super(repository);
    }

    @Override
    public List<Car> findAllWithIds(List<Long> ids) {
        return repository.findAllById(ids);
    }
}
