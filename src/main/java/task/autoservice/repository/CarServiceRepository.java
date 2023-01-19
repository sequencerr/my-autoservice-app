package task.autoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.autoservice.model.CarService;

@Repository
public interface CarServiceRepository extends JpaRepository<CarService, Long> {
}
