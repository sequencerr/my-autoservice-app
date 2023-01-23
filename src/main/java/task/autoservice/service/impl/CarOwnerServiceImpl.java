package task.autoservice.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.CarOwner;

@Service
public class CarOwnerServiceImpl extends GenericServiceImpl<CarOwner> {
    public CarOwnerServiceImpl(JpaRepository<CarOwner, Long> repository) {
        super(repository);
    }
}
