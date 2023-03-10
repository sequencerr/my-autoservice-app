package task.autoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.autoservice.model.CarOwner;

@Repository
public interface CarOwnerRepository extends JpaRepository<CarOwner, Long> {
}
