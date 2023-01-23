package task.autoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import task.autoservice.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT SUM(q.price) FROM (" +
            " SELECT s.price - :ordersCount * 0.02 price FROM orders o" +
            " INNER JOIN orders_car_services os ON o.id = :id" +
            " INNER JOIN car_services        s  ON s.id = os.car_services_id" +
            " UNION" +
            " SELECT p.price - :ordersCount * 0.01 price FROM orders o" +
            " INNER JOIN orders_car_parts    op ON o.id = :id" +
            " INNER JOIN car_parts           p  ON p.id  = op.car_parts_id" +
            " ) q",
            nativeQuery = true)
    Double calculateTotalPriceForClient(Long id, int ordersCount);

    @Query(value = "SELECT COUNT(co.orders_id) FROM orders o" +
            " INNER JOIN car_owners_cars   c  ON c.cars_id       = o.car_id" +
            " INNER JOIN car_owners_orders co ON co.car_owner_id = c.cars_id" +
            " WHERE o.id = ?1", nativeQuery = true)
    int getCarOwnerOrdersCount(Long orderId);
}
