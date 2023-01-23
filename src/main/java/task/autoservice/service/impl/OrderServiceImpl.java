package task.autoservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import task.autoservice.model.Order;
import task.autoservice.model.Order.OrderStatus;
import task.autoservice.repository.OrderRepository;
import task.autoservice.service.CarPartService;
import task.autoservice.service.OrderService;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl extends GenericServiceImpl<Order> implements OrderService {
    protected final OrderRepository repository;
    private final CarPartService carPartService;

    public OrderServiceImpl(OrderRepository repository, CarPartService carPartService) {
        super(repository);
        this.repository = repository;
        this.carPartService = carPartService;
    }

    @Override
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

        int ordersCount = repository.getCarOwnerOrdersCount(id);
        Double priceForClient = repository.calculateTotalPriceForClient(id, ordersCount);
        if (priceForClient == null) {
            if (order == null) {
                throw new EntityNotFoundException(
                        "Unable to find " + Order.class.getSimpleName() + " with id " + id);
            }
            priceForClient = 0d; // If all the prices were unset (null) in car parts/services, total priceForClient could be null
        }

        BigDecimal priceBigDecimal = BigDecimal.valueOf(priceForClient);
        order.setTotalPriceForClient(priceBigDecimal);
        update(order);

        return priceBigDecimal;
    }
}
