package task.autoservice.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.Detail;

@Service
public class DetailServiceImpl extends GenericServiceImpl<Detail> {
    public DetailServiceImpl(JpaRepository<Detail, Long> repository) {
        super(repository);
    }
}
