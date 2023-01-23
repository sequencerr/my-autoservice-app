package task.autoservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.Order;
import task.autoservice.model.Order.OrderStatus;
import task.autoservice.repository.OrderRepository;
import task.autoservice.service.CarPartService;
import task.autoservice.service.OrderService;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl extends GenericServiceImpl<Order> implements OrderService {
    private static final double DISCOUNT_PERCENTAGE_PER_SERVICE = 0.02;
    private static final double DISCOUNT_PERCENTAGE_PER_PART = 0.01;
    private static final BigDecimal ONLY_DIAGNOSE_PRICE = BigDecimal.valueOf(500);
    private final CarPartService carPartService;

    public OrderServiceImpl(JpaRepository<Order, Long> repository, CarPartService carPartService) {
        super(repository);
        this.carPartService = carPartService;
    }

    @Override
    @Transactional
    public void addPart(Long id, Long partId) {
        Order order = getById(id);
        order.getCarParts().add(carPartService.getById(partId));
        order.setCarParts(order.getCarParts());
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
    public BigDecimal calculateTotalPriceForClient(Long id) {
        Order order = getById(id);

        if (order == null) {
            throw new EntityNotFoundException(
                    "Unable to find " + Order.class.getSimpleName() + " with id " + id);
        }

        if (order.getCarServices().isEmpty() && order.getCarParts().isEmpty()) {
            order.setTotalPriceForClient(ONLY_DIAGNOSE_PRICE);
            update(order);
            return ONLY_DIAGNOSE_PRICE;
        }

        int ordersCount = order.getCar().getOwner().getOrders().size();
        if (ordersCount > 20) ordersCount = 20;
        double discountPerService = ordersCount * DISCOUNT_PERCENTAGE_PER_SERVICE;
        double discountPerPart = ordersCount * DISCOUNT_PERCENTAGE_PER_PART;

        BigDecimal servicesTotalPrice = order.getCarServices().stream().reduce(
                BigDecimal.ZERO,
                (a, c) -> a.add(c.getPrice().subtract(BigDecimal.valueOf(discountPerService))),
                BigDecimal::add
        );
        BigDecimal partsTotalPrice = order.getCarParts().stream().reduce(
                BigDecimal.ZERO,
                (a, c) -> a.add(c.getPrice().subtract(BigDecimal.valueOf(discountPerPart))),
                BigDecimal::add
        );
        BigDecimal priceForClient = servicesTotalPrice.add(partsTotalPrice);

        order.setTotalPriceForClient(priceForClient);
        update(order);

        return priceForClient;
    }
}
