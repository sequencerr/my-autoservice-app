package task.autoservice.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.Car;
import task.autoservice.model.CarOwner;
import task.autoservice.service.CarOwnerService;
import task.autoservice.service.GenericService;

import java.util.Collections;

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
    public CarOwner create(CarOwner carOwner) {
        if (carOwner.getCars() == null) carOwner.setCars(Collections.emptyList());
        if (carOwner.getOrders() == null) carOwner.setOrders(Collections.emptyList());
        return super.create(carOwner);
    }

    @Override
    public void addCar(Long carOwnerId, Long carId) {
        CarOwner carOwner = getById(carOwnerId);
        carOwner.getCars().add(carService.getById(carId));
        update(carOwner);
    }
}
