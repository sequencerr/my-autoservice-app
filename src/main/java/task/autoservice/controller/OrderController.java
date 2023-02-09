package task.autoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import task.autoservice.service.OrderService;

import java.math.BigDecimal;

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
        return orderMapper.toDto(orderService.create(order));
    }

    @Operation(summary = "CRUD: Update order by id with dto")
    @PutMapping("/{id}")
    public OrderResponseDto update(
            @PathVariable Long id,
            @RequestBody OrderRequestDto requestDto) {
        Order order = orderMapper.toModel(requestDto);
        order.setId(id);
        orderService.update(order);
        return orderMapper.toDto(order);
    }

    @Operation(summary = "Add car detail required for repairment to order by their ids")
    @PutMapping("/{id}/add-overhaul")
    public ResponseEntity<String> addOverhaul(
            @PathVariable Long id,
            @Parameter(description = "Overhaul's id to add")
            @RequestParam Long overhaulId) {
        orderService.addOverhaul(id, overhaulId);
        return ResponseEntity.ok("Overhaul was successfully added to specified order");
    }

    @Operation(summary = "Add car detail required for repairment to order by their ids")
    @PutMapping("/{id}/add-detail")
    public ResponseEntity<String> addDetail(
            @PathVariable Long id,
            @Parameter(description = "Car detail's id to add")
            @RequestParam Long detailId) {
        orderService.addDetail(id, detailId);
        return ResponseEntity.ok("Detail was successfully added to specified order");
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
        return ResponseEntity.ok("Order's status has been successfully updated.");
    }

    @Operation(summary = "Calculate price for client, save it to order entity and return the value")
    @GetMapping("/{id}/price")
    public BigDecimal getPrice(@PathVariable Long id) {
        return orderService.updateCalculatedPrice(id);
    }
}
