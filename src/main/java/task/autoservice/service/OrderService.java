package task.autoservice.service;

import task.autoservice.model.Order;

import java.math.BigDecimal;

public interface OrderService extends GenericService<Order> {
    void addOverhaul(Long id, Long overhaulId);

    void addDetail(Long id, Long detailId);

    void updateStatus(Long id, String status);

    BigDecimal getPrice(Long id);
}
