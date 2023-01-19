package task.autoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.autoservice.model.CarPart;

@Repository
public interface CarPartRepository extends JpaRepository<CarPart, Long> {
}
