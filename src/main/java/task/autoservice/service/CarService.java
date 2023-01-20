package task.autoservice.service;

import task.autoservice.model.Car;

import java.util.List;

public interface CarService extends GenericService<Car> {
    List<Car> findAllWithIds(List<Long> ids);
}
