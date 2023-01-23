package task.autoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import task.autoservice.model.CarService;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CarServiceRepository extends JpaRepository<CarService, Long> {
    @Query(value = "SELECT cs.id as id, cs.price as price FROM orders o" +
            " JOIN orders_car_services ocs ON ocs.order_id = o.id" +
            " JOIN car_services        cs  ON cs.order_id  = ocs.car_services_id " +
            " JOIN repairers           r   ON r.id         = cs.repairer_id" +
            " WHERE o.status = 4 AND r.id = ?1", nativeQuery = true)
    List<ServiceReport> getServiceToPay(Long repairerId);

    interface ServiceReport {
        Long getId();

        BigDecimal getPrice();
    }

    @Modifying
    @Query(value = "UPDATE car_services s" +
            " SET s.is_paid = TRUE" +
            " WHERE s.id IN :ids AND s.is_paid = FALSE", nativeQuery = true)
    void markAllPaidById(List<Long> ids);
}
