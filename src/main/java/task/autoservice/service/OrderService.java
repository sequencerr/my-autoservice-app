package task.autoservice.service;

import task.autoservice.model.Order;

import java.math.BigDecimal;

public interface OrderService extends GenericService<Order> {
    void addDetail(Long id, Long detailId);

    void updateStatus(Long id, String status);

    BigDecimal updateCalculatedPrice(Long id);
}
