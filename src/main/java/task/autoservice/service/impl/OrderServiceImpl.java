package task.autoservice.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.Order;
import task.autoservice.service.OrderService;

@Service
public class OrderServiceImpl extends GenericServiceImpl<Order> implements OrderService {
    public OrderServiceImpl(JpaRepository<Order, Long> repository) {
        super(repository);
    }
}
