package task.autoservice.controller;

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

    @PostMapping
    public OrderResponseDto create(@RequestBody OrderRequestDto requestDto) {
        Order order = orderMapper.toModel(requestDto);
        order.setAcceptationDate(LocalDateTime.now());
        order.setStatus(OrderStatus.ACCEPTED);
        return orderMapper.toDto(orderService.create(order));
    }

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

    @PutMapping("/{id}/add-part")
    public ResponseEntity<String> addCarPart(
            @PathVariable Long id,
            @RequestParam Long partId) {
        orderService.addPart(id, partId);
        return new ResponseEntity<>(
                "Part was successfully added to specified order", HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        orderService.updateStatus(id, status);
        return new ResponseEntity<>(
                "Order's status has been successfully updated.", HttpStatus.OK);
    }

    @GetMapping("/{id}/price")
    public BigDecimal getCalculatedTotalPriceForClient(@PathVariable Long id) {
        return orderService.calculateTotalPriceForClient(id);
    }
}
