package task.autoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import task.autoservice.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT COUNT(oos.orders_id)"
            + " FROM orders o"
            + " JOIN car_owners_cars   ocs ON ocs.cars_id = o.car_id"
            + " JOIN car_owners_orders oos ON oos.car_owner_id = ocs.car_owner_id"
            + " WHERE o.id = ?1", nativeQuery = true)
    Integer getUserOrdersCount(Long orderId);
}
