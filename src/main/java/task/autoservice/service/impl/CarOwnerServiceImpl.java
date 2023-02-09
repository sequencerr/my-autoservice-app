package task.autoservice.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.Car;
import task.autoservice.model.CarOwner;
import task.autoservice.service.CarOwnerService;
import task.autoservice.service.GenericService;

@Service
public class CarOwnerServiceImpl extends GenericServiceImpl<CarOwner> implements CarOwnerService {
    private final GenericService<Car> carService;

    public CarOwnerServiceImpl(
            JpaRepository<CarOwner, Long> repository,
            GenericService<Car> carService) {
        super(repository);
        this.carService = carService;
    }

    @Override
    public void addCar(Long carOwnerId, Long carId) {
        CarOwner carOwner = getById(carOwnerId);
        carOwner.getCars().add(carService.getById(carId));
        update(carOwner);
    }
}
