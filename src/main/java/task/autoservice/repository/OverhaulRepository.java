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
    @Query(value = "SELECT os.id id, os.price price, os.is_paid is_paid,"
            + " os.order_id order_id, os.repairer_id repairer_id"
            + " FROM orders o"
            + " JOIN orders_overhauls oos ON oos.order_id = o.id"
            + " JOIN overhauls        os  ON os.order_id  = oos.overhauls_id "
            + " JOIN repairers        r   ON r.id         = os.repairer_id"
            + " WHERE o.status = ?2 AND r.id = ?1", nativeQuery = true)
    List<Overhaul> getOverhaulsByRepairerAndOrderStatus(Long repairerId, OrderStatus status);

    @Modifying
    @Query(value = "UPDATE overhauls os"
            + " SET os.is_paid = TRUE"
            + " WHERE os.id IN :ids AND os.is_paid = FALSE", nativeQuery = true)
    void markAllPaidById(List<Long> ids);
}
