package task.autoservice.service;

import task.autoservice.model.CarPart;

import java.util.List;

public interface CarPartService extends GenericService<CarPart> {
    List<CarPart> findAllWithIds(List<Long> ids);
}
