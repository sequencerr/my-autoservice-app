package task.autoservice.service;

import task.autoservice.model.Order;

import java.math.BigDecimal;

public interface OrderService extends GenericService<Order> {
    void addPart(Long id, Long partId);

    void updateStatus(Long id, String status);

    BigDecimal calculateTotalPriceForClient(Long id);
}
