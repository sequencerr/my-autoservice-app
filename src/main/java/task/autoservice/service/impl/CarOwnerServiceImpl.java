package task.autoservice.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.CarOwner;
import task.autoservice.service.CarOwnerService;

@Service
public class CarOwnerServiceImpl extends GenericServiceImpl<CarOwner> implements CarOwnerService {
    public CarOwnerServiceImpl(JpaRepository<CarOwner, Long> repository) {
        super(repository);
    }
}
