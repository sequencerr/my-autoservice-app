package task.autoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.autoservice.model.Repairer;

@Repository
public interface RepairerRepository extends JpaRepository<Repairer, Long> {
}
