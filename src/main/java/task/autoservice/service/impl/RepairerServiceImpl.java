package task.autoservice.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.Repairer;
import task.autoservice.service.RepairerService;

@Service
public class RepairerServiceImpl extends GenericServiceImpl<Repairer> implements RepairerService {
    public RepairerServiceImpl(JpaRepository<Repairer, Long> repository) {
        super(repository);
    }
}
