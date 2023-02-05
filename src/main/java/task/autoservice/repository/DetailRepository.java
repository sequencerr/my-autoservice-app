package task.autoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.autoservice.model.Detail;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
}
