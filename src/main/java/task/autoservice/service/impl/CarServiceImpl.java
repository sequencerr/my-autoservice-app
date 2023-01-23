package task.autoservice.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.Car;

@Service
public class CarServiceImpl extends GenericServiceImpl<Car> {
    public CarServiceImpl(JpaRepository<Car, Long> repository) {
        super(repository);
    }
}
