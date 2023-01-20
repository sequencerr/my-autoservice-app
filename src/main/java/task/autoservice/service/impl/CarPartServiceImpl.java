package task.autoservice.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.CarPart;
import task.autoservice.service.CarPartService;

import java.util.List;

@Service
public class CarPartServiceImpl extends GenericServiceImpl<CarPart> implements CarPartService {
    public CarPartServiceImpl(JpaRepository<CarPart, Long> repository) {
        super(repository);
    }

    @Override
    public List<CarPart> findAllWithIds(List<Long> ids) {
        return repository.findAllById(ids);
    }
}
