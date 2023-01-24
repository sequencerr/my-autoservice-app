package task.autoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import task.autoservice.dto.mapper.OrderMapper;
import task.autoservice.dto.request.OrderRequestDto;
import task.autoservice.dto.response.OrderResponseDto;
import task.autoservice.model.Order;
import task.autoservice.model.Order.OrderStatus;
import task.autoservice.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @Operation(summary = "CRUD: Create order by dto")
    @PostMapping
    public OrderResponseDto create(@RequestBody OrderRequestDto requestDto) {
        Order order = orderMapper.toModel(requestDto);
        order.setAcceptationDate(LocalDateTime.now());
        order.setStatus(OrderStatus.ACCEPTED);
        return orderMapper.toDto(orderService.create(order));
    }

    @Operation(summary = "CRUD: Update order by id with dto")
    @PutMapping("/{id}")
    public OrderResponseDto update(
            @PathVariable Long id,
            @RequestBody OrderRequestDto requestDto) {
        Order order = orderMapper.toModel(id, requestDto);
        if (order.getStatus() == OrderStatus.COMPLETED_SUCCESSFULLY) {
            order.setCompletionDate(LocalDateTime.now());
        }
        return orderMapper.toDto(orderService.update(order));
    }

    @Operation(summary = "Add car part required for repairment to order by their ids")
    @PutMapping("/{id}/add-part")
    public ResponseEntity<String> addCarPart(
            @PathVariable Long id,
            @Parameter(description = "Car part's id to add")
            @RequestParam Long partId) {
        orderService.addPart(id, partId);
        return new ResponseEntity<>(
                "Part was successfully added to specified order", HttpStatus.OK);
    }

    @Operation(summary = "Update order's progress by its id")
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(
            @PathVariable Long id,
            @Parameter(description = "New order's status (Acceptable values: "
                    + "`ACCEPTED`, `IN_PROGRESS`, "
                    + "`COMPLETED_SUCCESSFULLY`, `COMPLETED_UNSUCCESSFULLY`, `PAID`.")
            @RequestParam String status) {
        orderService.updateStatus(id, status);
        return new ResponseEntity<>(
                "Order's status has been successfully updated.", HttpStatus.OK);
    }

    @Operation(summary = "Calculate price for client, save it to order entity and return the value")
    @GetMapping("/{id}/price")
    public BigDecimal getCalculatedTotalPriceForClient(@PathVariable Long id) {
        return orderService.calculateTotalPriceForClient(id);
    }
}
