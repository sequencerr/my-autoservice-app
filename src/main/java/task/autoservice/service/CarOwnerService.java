package task.autoservice.service;

import task.autoservice.model.CarOwner;

public interface CarOwnerService extends GenericService<CarOwner> {
    void addCar(Long carOwnerId, Long carId);
}
