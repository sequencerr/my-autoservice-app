package task.autoservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.Detail;
import task.autoservice.model.Order;
import task.autoservice.model.Order.OrderStatus;
import task.autoservice.service.GenericService;
import task.autoservice.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderServiceImpl extends GenericServiceImpl<Order> implements OrderService {
    private static final double DISCOUNT_PERCENTAGE_PER_OVERHAUL = 0.02;
    private static final double DISCOUNT_PERCENTAGE_PER_DETAIL = 0.01;
    private static final BigDecimal ONLY_DIAGNOSE_PRICE = BigDecimal.valueOf(500);
    private final GenericService<Detail> detailService;

    public OrderServiceImpl(
            JpaRepository<Order, Long> repository,
            GenericService<Detail> detailService) {
        super(repository);
        this.detailService = detailService;
    }

    @Override
    public Order create(Order order) {
        order.setAcceptationDate(LocalDateTime.now());
        order.setStatus(OrderStatus.ACCEPTED);
        return super.create(order);
    }

    @Override
    public Order update(Order order) {
        if (order.getStatus() == OrderStatus.COMPLETED_SUCCESSFULLY
                || order.getStatus() == OrderStatus.COMPLETED_UNSUCCESSFULLY) {
            order.setCompletionDate(LocalDateTime.now());
        }
        return super.update(order);
    }

    @Override
    @Transactional
    public void addDetail(Long id, Long detailId) {
        Order order = getById(id);
        order.getDetails().add(detailService.getById(detailId));
        order.setDetails(order.getDetails());
        update(order);
    }

    @Override
    @Transactional
    public void updateStatus(Long id, String status) {
        Order order = getById(id);
        order.setStatus(OrderStatus.valueOf(status));
        update(order);
    }

    @Override
    @Transactional
    public BigDecimal updateCalculatedPrice(Long id) {
        Order order = getById(id);

        if (order == null) {
            throw new EntityNotFoundException(
                    "Unable to find " + Order.class.getSimpleName() + " with id " + id);
        }

        order.setPrice(calculate(order));
        return update(order).getPrice();
    }

    private BigDecimal calculate(Order order) {
        if (order.getOverhauls().isEmpty() && order.getDetails().isEmpty()) {
            return ONLY_DIAGNOSE_PRICE;
        }

        int ordersCount = order.getCar().getOwner().getOrders().size();
        if (ordersCount > 20) ordersCount = 20;

        double discountPerOverhaul = ordersCount * DISCOUNT_PERCENTAGE_PER_OVERHAUL;
        double discountPerDetail = ordersCount * DISCOUNT_PERCENTAGE_PER_DETAIL;

        return order.getOverhauls().stream().reduce(
                BigDecimal.ZERO,
                (a, c) -> a.add(c.getPrice().subtract(BigDecimal.valueOf(discountPerOverhaul))),
                BigDecimal::add
        ).add(order.getDetails().stream().reduce(
                BigDecimal.ZERO,
                (a, c) -> a.add(c.getPrice().subtract(BigDecimal.valueOf(discountPerDetail))),
                BigDecimal::add
        ));
    }
}
