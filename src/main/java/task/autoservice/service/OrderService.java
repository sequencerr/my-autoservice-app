package task.autoservice.service;

import task.autoservice.model.Order;

import java.util.List;

public interface OrderService extends GenericService<Order> {
    List<Order> findAllWithIds(List<Long> ids);
}
