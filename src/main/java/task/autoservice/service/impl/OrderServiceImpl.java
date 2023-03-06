package task.autoservice.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import task.autoservice.model.CarOwner;
import task.autoservice.model.Detail;
import task.autoservice.model.Order;
import task.autoservice.model.Order.OrderStatus;
import task.autoservice.model.Overhaul;
import task.autoservice.model.Repairer;
import task.autoservice.service.CarOwnerService;
import task.autoservice.service.GenericService;
import task.autoservice.service.OrderService;
import task.autoservice.service.OverhaulService;
import task.autoservice.service.RepairerService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static task.autoservice.model.Order.OrderStatus.ACCEPTED;
import static task.autoservice.model.Order.OrderStatus.COMPLETED_SUCCESSFULLY;
import static task.autoservice.model.Order.OrderStatus.COMPLETED_UNSUCCESSFULLY;
import static task.autoservice.model.Order.OrderStatus.PAID;
import static task.autoservice.model.Order.OrderStatus.valueOf;

@Service
public class OrderServiceImpl extends GenericServiceImpl<Order> implements OrderService {
    private static final double DISCOUNT_PERCENTAGE_PER_OVERHAUL = 0.02;
    private static final double DISCOUNT_PERCENTAGE_PER_DETAIL = 0.01;
    private static final BigDecimal ONLY_DIAGNOSE_PRICE = BigDecimal.valueOf(500);
    private static final int MAX_COUNT_MULTIPLIER = 20;
    private final GenericService<Detail> detailService;
    private final OverhaulService overhaulService;
    private final RepairerService repairerService;
    private final CarOwnerService carOwnerService;

    public OrderServiceImpl(
            JpaRepository<Order, Long> repository,
            GenericService<Detail> detailService,
            OverhaulService overhaulService,
            RepairerService repairerService,
            CarOwnerService carOwnerService
    ) {
        super(repository);
        this.detailService = detailService;
        this.overhaulService = overhaulService;
        this.repairerService = repairerService;
        this.carOwnerService = carOwnerService;
    }

    @Override
    public Order create(Order order) {
        order.setAcceptationDate(LocalDateTime.now());
        order.setStatus(ACCEPTED);
        if (order.getOverhauls() == null) order.setOverhauls(Collections.emptyList());
        if (order.getDetails() == null) order.setDetails(Collections.emptyList());
        return super.create(order);
    }

    @Override
    public void addOverhaul(Long id, Long overhaulId) {
        Order order = getById(id);
        order.getOverhauls().add(overhaulService.getById(overhaulId));
        update(order);
    }

    @Override
    public void addDetail(Long id, Long detailId) {
        Order order = getById(id);
        order.getDetails().add(detailService.getById(detailId));
        update(order);
    }

    @Override
    public void updateStatus(Long id, String status) {
        Order order = getById(id);
        OrderStatus statusBefore = order.getStatus();
        OrderStatus statusAfter = valueOf(status);
        if (statusAfter.ordinal() <= statusBefore.ordinal()) {
            throw new RuntimeException(
                    "Cannot update status to the same or to previous by progress stage");
        }
        if ((statusAfter == COMPLETED_SUCCESSFULLY || statusAfter == COMPLETED_UNSUCCESSFULLY)
                && statusBefore != COMPLETED_SUCCESSFULLY) {
            order.setCompletionDate(LocalDateTime.now());
            addOrderToRepairers(order);
        }
        if (statusAfter == PAID) {
            CarOwner carOwner = carOwnerService.getById(order.getCar().getOwner().getId());
            carOwner.getOrders().add(order);
            carOwnerService.update(carOwner);
        }
        order.setStatus(statusAfter);
        update(order);
    }

    @Override
    public BigDecimal getPrice(Long id) {
        Order order = getById(id);
        order.setPrice(calculate(order));
        return update(order).getPrice();
    }

    private void addOrderToRepairers(Order order) {
        Set<Long> uniqueIds = new HashSet<>();
        for (Overhaul overhaul : order.getOverhauls()) {
            Repairer r = overhaul.getRepairer();
            if (uniqueIds.add(r.getId())) {
                r.getCompletedOrders().add(order);
                repairerService.update(r);
            }
        }
    }

    private BigDecimal calculate(Order order) {
        if (order.getOverhauls().isEmpty() && order.getDetails().isEmpty()) {
            return ONLY_DIAGNOSE_PRICE;
        }

        int ordersCount = order.getCar().getOwner().getOrders().size();
        if (ordersCount > MAX_COUNT_MULTIPLIER) ordersCount = MAX_COUNT_MULTIPLIER;

        BigDecimal discountPerOverhaul =
                BigDecimal.valueOf(ordersCount * DISCOUNT_PERCENTAGE_PER_OVERHAUL);
        BigDecimal discountPerDetail =
                BigDecimal.valueOf(ordersCount * DISCOUNT_PERCENTAGE_PER_DETAIL);

        BigDecimal accumulator = BigDecimal.ZERO;
        for (Detail detail : order.getDetails()) {
            accumulator = accumulator.add(detail.getPrice().subtract(discountPerDetail));
        }
        for (Overhaul overhaul : order.getOverhauls()) {
            accumulator = accumulator.add(overhaul.getPrice().subtract(discountPerOverhaul));
        }
        return accumulator;
    }
}
