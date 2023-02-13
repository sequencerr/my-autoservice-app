package task.autoservice.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import task.autoservice.model.Car;
import task.autoservice.model.CarOwner;
import task.autoservice.model.Detail;
import task.autoservice.model.Order;
import task.autoservice.model.Overhaul;
import task.autoservice.model.Repairer;
import task.autoservice.repository.OrderRepository;
import task.autoservice.service.CarOwnerService;
import task.autoservice.service.GenericService;
import task.autoservice.service.OrderService;
import task.autoservice.service.OverhaulService;
import task.autoservice.service.RepairerService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static task.autoservice.model.Order.OrderStatus.ACCEPTED;
import static task.autoservice.model.Order.OrderStatus.COMPLETED_SUCCESSFULLY;
import static task.autoservice.model.Order.OrderStatus.COMPLETED_UNSUCCESSFULLY;
import static task.autoservice.model.Order.OrderStatus.IN_PROGRESS;
import static task.autoservice.model.Order.OrderStatus.PAID;

class OrderServiceTest {
    private static final String STATUS_ORDER_UNEXISTING = "Unknown";
    private static final long ID_FIRST_ITEM = 1L;
    private static final long ID_SECOND_ITEM = 2L;
    private static final int ORDERS_UNCHANGED_SIZE_EXPECTED = 1;
    private static final int ORDER_COUNT_NORMAL = 5;
    private static final int ORDER_COUNT_OVER_ACCEPTABLE = 21;
    private static final BigDecimal PRICE_OVERHAUL_1 = BigDecimal.valueOf(412);
    private static final BigDecimal PRICE_OVERHAUL_2 = BigDecimal.valueOf(313);
    private static final BigDecimal PRICE_DETAIL_1 = BigDecimal.valueOf(15);
    private static final BigDecimal PRICE_BOTH_EXPECTED = BigDecimal.valueOf(739.75);
    private static final BigDecimal PRICE_DETAIL_2 = BigDecimal.valueOf(10);
    private static final BigDecimal PRICE_MANY_ORDERS_EXPECTED = BigDecimal.valueOf(9.8);
    private static final BigDecimal PRICE_DIAGNOSIS_EXPECTED = BigDecimal.valueOf(500);
    private OrderService orderService;
    private OrderRepository orderRepository;
    private GenericService<Detail> detailService;
    private OverhaulService overhaulService;
    private RepairerService repairerService;
    private CarOwnerService carOwnerService;

    @BeforeEach
    void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
        detailService = Mockito.mock(GenericService.class);
        overhaulService = Mockito.mock(OverhaulService.class);
        repairerService = Mockito.mock(RepairerService.class);
        carOwnerService = Mockito.mock(CarOwnerService.class);
        orderService = new OrderServiceImpl(
                orderRepository,
                detailService,
                overhaulService,
                repairerService,
                carOwnerService
        );
    }

    @Test
    void create_acceptationAndStatusInitialized_ok() {
        Order order = new Order();
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(false);
        orderService.create(order);
        assertNotNull(order.getAcceptationDate(), "Expected to not be null");
        assertTrue(
                order.getAcceptationDate().isEqual(LocalDateTime.now()) ||
                        order.getAcceptationDate().isBefore(LocalDateTime.now()),
                "Expected to have valid date"
        );
        Order.OrderStatus expectedStatus = ACCEPTED;
        assertEquals(
                expectedStatus,
                order.getStatus(),
                "Expected to have status " + expectedStatus.name()
                        + " for just created orders"
        );
    }

    @Test
    void addOverhaul_addingOne_ok() {
        Order order = new Order();
        order.setId(ID_FIRST_ITEM);
        order.setOverhauls(new LinkedList<>());
        Mockito.when(orderRepository.getReferenceById(ID_FIRST_ITEM)).thenReturn(order);
        Overhaul overhaul = new Overhaul();
        overhaul.setId(ID_FIRST_ITEM);
        Mockito.when(overhaulService.getById(ID_FIRST_ITEM)).thenReturn(overhaul);
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(true);
        orderService.addOverhaul(order.getId(), overhaul.getId());
        assertEquals(
                List.of(overhaul),
                order.getOverhauls(),
                "Expected to have right amount after adding one overhaul in entity"
        );
    }

    @Test
    void addDetail_addingOne_ok() {
        Order order = new Order();
        order.setId(ID_FIRST_ITEM);
        order.setDetails(new LinkedList<>());
        Mockito.when(orderRepository.getReferenceById(ID_FIRST_ITEM)).thenReturn(order);
        Detail detail = new Detail();
        detail.setId(ID_FIRST_ITEM);
        Mockito.when(detailService.getById(ID_FIRST_ITEM)).thenReturn(detail);
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(true);
        orderService.addDetail(order.getId(), detail.getId());
        assertEquals(
                List.of(detail),
                order.getDetails(),
                "Expected to have right amount after adding one detail in entity"
        );
    }

    @Test
    void updateStatus_updatedToNotExisting_notOk() {
        Order order = new Order();
        order.setId(ID_FIRST_ITEM);
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(false);
        orderService.create(order);
        Mockito.when(orderRepository.getReferenceById(ID_FIRST_ITEM)).thenReturn(order);
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(true);
        assertThrows(
                RuntimeException.class,
                () -> orderService.updateStatus(ID_FIRST_ITEM, STATUS_ORDER_UNEXISTING),
                "Expected to not update status when passing unexisting/invalid"
                        + " status string"
        );
    }

    @Test
    void updateStatus_statusUpdatedToAcceptedForJustCreated_notOk() {
        Order order = new Order();
        order.setId(ID_FIRST_ITEM);
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(false);
        orderService.create(order);
        Mockito.when(orderRepository.getReferenceById(ID_FIRST_ITEM)).thenReturn(order);
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(true);
        String statusJustCreatedName = ACCEPTED.name();
        assertThrows(
                RuntimeException.class,
                () -> orderService.updateStatus(ID_FIRST_ITEM, statusJustCreatedName),
                "Expected to not able status to the same that is already set into order "
                        + "and to not able to set it to " + statusJustCreatedName + " for just "
                        + "created order."
        );
    }

    @Test
    void updateStatus_updatesAfterCreationCorrectly_ok() {
        Order order = new Order();
        order.setId(ID_FIRST_ITEM);
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(false);
        orderService.create(order);
        Mockito.when(orderRepository.getReferenceById(ID_FIRST_ITEM)).thenReturn(order);
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(true);
        orderService.updateStatus(ID_FIRST_ITEM, IN_PROGRESS.name());
        assertEquals(IN_PROGRESS, order.getStatus(),
                "Expected to be able update to valid next status by ordinal (stage) "
                        + "for just created order");
    }

    @Test
    void updateStatus_onCompletedAddsOrderToRepairers_ok() {
        Overhaul overhaul = new Overhaul();
        Repairer repairer = new Repairer();
        repairer.setCompletedOrders(new LinkedList<>());
        repairer.setId(ID_FIRST_ITEM);
        overhaul.setRepairer(repairer);

        Order orderSuccessful = new Order();
        orderSuccessful.setId(ID_FIRST_ITEM);
        orderSuccessful.setOverhauls(List.of(overhaul));
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(false);
        orderService.create(orderSuccessful);
        Mockito.when(orderRepository.getReferenceById(ID_FIRST_ITEM)).thenReturn(orderSuccessful);
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(true);
        orderService.updateStatus(ID_FIRST_ITEM, COMPLETED_SUCCESSFULLY.name());
        assertNotNull(orderSuccessful.getCompletionDate(),
                "Expected completion date to be not null after updating to"
                        + " corresponding status");
        assertTrue(orderSuccessful.getCompletionDate().isEqual(LocalDateTime.now()) ||
                orderSuccessful.getCompletionDate().isBefore(LocalDateTime.now()),
                "Expected date to be valid");
        assertFalse(repairer.getCompletedOrders().isEmpty(),
                "Expected repairers' (in this particular case - repairer's) orders list "
                        + "to be not empty after updating to corresponding status");
        assertEquals(List.of(orderSuccessful), repairer.getCompletedOrders(),
                "Expected repairer's order list be filled with right orders");

        repairer.setCompletedOrders(new LinkedList<>());
        Order orderUnsuccessful = new Order();
        orderUnsuccessful.setId(ID_SECOND_ITEM);
        orderUnsuccessful.setOverhauls(List.of(overhaul));
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(false);
        orderService.create(orderUnsuccessful);
        Mockito.when(orderRepository.getReferenceById(ID_SECOND_ITEM))
                .thenReturn(orderUnsuccessful);
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(true);
        orderService.updateStatus(ID_SECOND_ITEM, COMPLETED_UNSUCCESSFULLY.name());
        assertNotNull(orderUnsuccessful.getCompletionDate(),
                "Expected completion date to be not null after updating to"
                        + " corresponding status");
        assertTrue(orderUnsuccessful.getCompletionDate().isEqual(LocalDateTime.now()) ||
                orderUnsuccessful.getCompletionDate().isBefore(LocalDateTime.now()),
                "Expected date to be valid");
        assertFalse(repairer.getCompletedOrders().isEmpty(),
                "Expected repairers' (in this particular case - repairer's) orders list "
                        + "to be not empty after updating to corresponding status");
        assertEquals(List.of(orderUnsuccessful), repairer.getCompletedOrders(),
                "Expected repairer's order list be filled with right orders");
    }

    @Test
    void updateStatus_completedTwiceNotDuplicatingOrderRepairers_ok() {
        Overhaul overhaul = new Overhaul();
        Repairer repairer = new Repairer();
        repairer.setCompletedOrders(new LinkedList<>());
        repairer.setId(ID_FIRST_ITEM);
        overhaul.setRepairer(repairer);

        Order orderSuccessful = new Order();
        orderSuccessful.setId(ID_FIRST_ITEM);
        orderSuccessful.setOverhauls(List.of(overhaul));
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(false);
        orderService.create(orderSuccessful);
        Mockito.when(orderRepository.getReferenceById(ID_FIRST_ITEM)).thenReturn(orderSuccessful);
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(true);
        orderService.updateStatus(ID_FIRST_ITEM, COMPLETED_SUCCESSFULLY.name());
        orderService.updateStatus(ID_FIRST_ITEM, COMPLETED_UNSUCCESSFULLY.name());
        assertEquals(ORDERS_UNCHANGED_SIZE_EXPECTED, repairer.getCompletedOrders().size(),
                "Expected repairer's orders list size to be unchanged after "
                        + "updating from successful completion to unsuccessful");
        assertEquals(List.of(orderSuccessful), repairer.getCompletedOrders(),
                "Expected repairer's order list be filled with right orders");
    }

    @Test
    void updateStatus_updatePaidAddsOrderToCarOwner_ok() {
        Order order = new Order();
        order.setId(ID_FIRST_ITEM);
        Car car = new Car();
        CarOwner owner = new CarOwner();
        owner.setId(ID_FIRST_ITEM);
        owner.setOrders(new LinkedList<>());
        car.setOwner(owner);
        order.setCar(car);
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(false);
        orderService.create(order);
        Mockito.when(orderRepository.getReferenceById(ID_FIRST_ITEM)).thenReturn(order);
        Mockito.when(carOwnerService.getById(ID_FIRST_ITEM)).thenReturn(owner);
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(true);
        orderService.updateStatus(ID_FIRST_ITEM, PAID.name());
        assertEquals(List.of(order), owner.getOrders(),
                "Expected car owner done orders list be filled with right orders");
    }

    @Test
    void updateCalculatedPrice_overhaulsAndDetailsCalculatedWithDiscountCorrectly_ok() {
        CarOwner owner = new CarOwner();
        List<Order> orders = new LinkedList<>();
        for (int i = 0; i < ORDER_COUNT_NORMAL; i++) {
            orders.add(new Order());
        }
        owner.setOrders(orders);
        Car car = new Car();
        car.setOwner(owner);
        Overhaul overhaul = new Overhaul();
        Overhaul overhaul2 = new Overhaul();
        overhaul.setPrice(PRICE_OVERHAUL_1);
        overhaul2.setPrice(PRICE_OVERHAUL_2);
        Detail detail = new Detail();
        detail.setPrice(PRICE_DETAIL_1);
        Order order = new Order();
        order.setId(ID_FIRST_ITEM);
        order.setCar(car);
        order.setOverhauls(List.of(overhaul, overhaul2));
        order.setDetails(List.of(detail));
        Mockito.when(orderRepository.getReferenceById(ID_FIRST_ITEM)).thenReturn(order);
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(true);
        assertEquals(PRICE_BOTH_EXPECTED, orderService.updateCalculatedPrice(ID_FIRST_ITEM),
                "Expected price to be calculated properly");
    }

    @Test
    void updateCalculatedPrice_tooMuchOrdersDoesNotAmplifyDiscountAnyMore_ok() {
        CarOwner owner = new CarOwner();
        List<Order> orders = new LinkedList<>();
        for (int i = 0; i < ORDER_COUNT_OVER_ACCEPTABLE; i++) {
            orders.add(new Order());
        }
        owner.setOrders(orders);
        Car car = new Car();
        car.setOwner(owner);
        Detail detail = new Detail();
        detail.setPrice(PRICE_DETAIL_2);
        Order order = new Order();
        order.setId(ID_FIRST_ITEM);
        order.setCar(car);
        order.setOverhauls(new LinkedList<>());
        order.setDetails(List.of(detail));
        Mockito.when(orderRepository.getReferenceById(ID_FIRST_ITEM)).thenReturn(order);
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(true);
        assertEquals(
                PRICE_MANY_ORDERS_EXPECTED,
                orderService.updateCalculatedPrice(ID_FIRST_ITEM),
                "Expected price to be calculated properly"
        );
    }

    @Test
    void updateCalculatedPrice_calculatedAndUpdatedOrdersPrice_ok() {
        Order order = new Order();
        order.setId(ID_FIRST_ITEM);
        order.setOverhauls(new LinkedList<>());
        order.setDetails(new LinkedList<>());
        Mockito.when(orderRepository.getReferenceById(ID_FIRST_ITEM)).thenReturn(order);
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(true);
        assertEquals(
                orderService.updateCalculatedPrice(ID_FIRST_ITEM),
                order.getPrice(),
                "Expected to update order's price field after calculating"
        );
    }

    @Test
    void updateCalculatedPrice_onlyDiagnoseCalculated_ok() {
        Order order = new Order();
        order.setId(ID_FIRST_ITEM);
        order.setOverhauls(new LinkedList<>());
        order.setDetails(new LinkedList<>());
        Mockito.when(orderRepository.getReferenceById(ID_FIRST_ITEM)).thenReturn(order);
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
        Mockito.when(orderRepository.exists(Mockito.any())).thenReturn(true);
        assertEquals(
                PRICE_DIAGNOSIS_EXPECTED,
                orderService.updateCalculatedPrice(ID_FIRST_ITEM),
                "Expected to return and update price to fixed diagnosis price");
    }
}
