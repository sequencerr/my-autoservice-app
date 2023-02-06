package task.autoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import task.autoservice.model.Order.OrderStatus;
import task.autoservice.model.Overhaul;

import java.util.List;

@Repository
public interface OverhaulRepository extends JpaRepository<Overhaul, Long> {
    @Query(value = "SELECT cs.id id, cs.price price, cs.is_paid is_paid,"
            + " cs.order_id order_id, cs.repairer_id repairer_id"
            + " FROM orders o"
            + " JOIN orders_overhauls ocs ON ocs.order_id = o.id"
            + " JOIN overhauls        cs  ON cs.order_id  = ocs.overhauls_id "
            + " JOIN repairers           r   ON r.id         = cs.repairer_id"
            + " WHERE o.status = ?2 AND r.id = ?1", nativeQuery = true)
    List<Overhaul> getOverhaulsByRepairerAndOrderStatus(Long repairerId, OrderStatus status);

    @Modifying
    @Query(value = "UPDATE overhauls s"
            + " SET s.is_paid = TRUE"
            + " WHERE s.id IN :ids AND s.is_paid = FALSE", nativeQuery = true)
    void markAllPaidById(List<Long> ids);
}