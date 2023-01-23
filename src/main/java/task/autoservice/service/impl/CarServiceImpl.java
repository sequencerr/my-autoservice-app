package task.autoservice.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.Car;
import task.autoservice.service.CarService;

@Service
public class CarServiceImpl extends GenericServiceImpl<Car> implements CarService {
    public CarServiceImpl(JpaRepository<Car, Long> repository) {
        super(repository);
    }
}
