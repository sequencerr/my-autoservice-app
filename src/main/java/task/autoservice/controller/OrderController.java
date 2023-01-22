package task.autoservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.autoservice.dto.mapper.OrderMapper;
import task.autoservice.dto.request.OrderRequestDto;
import task.autoservice.dto.response.OrderResponseDto;
import task.autoservice.model.Order;
import task.autoservice.service.OrderService;

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
        Order savedOrder = orderService.create(orderMapper.toModel(requestDto));
        return orderMapper.toDto(savedOrder);
    }

    @PutMapping("/{id}")
    public OrderResponseDto update(
            @PathVariable Long id,
            @RequestBody OrderRequestDto requestDto) {
        Order updatedOrder = orderService.update(orderMapper.toModel(id, requestDto));
        return orderMapper.toDto(updatedOrder);
    }
}
