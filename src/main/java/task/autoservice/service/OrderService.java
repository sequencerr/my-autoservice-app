package task.autoservice.service;

import task.autoservice.model.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService extends GenericService<Order> {
    List<Order> findAllWithIds(List<Long> ids);

    void addPart(Long id, Long partId);

    void updateStatus(Long id, String status);

    BigDecimal calculateTotalPriceForClient(Long id);
}
