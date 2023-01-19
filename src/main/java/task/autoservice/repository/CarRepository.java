package task.autoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.autoservice.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
