package task.autoservice.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.CarPart;

@Service
public class CarPartServiceImpl extends GenericServiceImpl<CarPart> {
    public CarPartServiceImpl(JpaRepository<CarPart, Long> repository) {
        super(repository);
    }
}
