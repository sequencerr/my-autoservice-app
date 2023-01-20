package task.autoservice.service;

import task.autoservice.model.CarService;

import java.util.List;

public interface CarServiceService extends GenericService<CarService> {
    List<CarService> findAllWithIds(List<Long> ids);
}
