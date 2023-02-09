package task.autoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.autoservice.dto.mapper.CarOwnerMapper;
import task.autoservice.dto.mapper.OrderMapper;
import task.autoservice.dto.response.CarOwnerResponseDto;
import task.autoservice.dto.response.OrderResponseDto;
import task.autoservice.model.CarOwner;
import task.autoservice.service.CarOwnerService;

import java.util.List;

@RestController
@RequestMapping("/car-owners")
public class CarOwnerController {
    private final CarOwnerService carOwnerService;
    private final CarOwnerMapper carOwnerMapper;
    private final OrderMapper orderMapper;

    public CarOwnerController(
            CarOwnerService carOwnerService, CarOwnerMapper carOwnerMapper, OrderMapper orderMapper
    ) {
        this.carOwnerService = carOwnerService;
        this.carOwnerMapper = carOwnerMapper;
        this.orderMapper = orderMapper;
    }

    @Operation(summary = "CRUD: Create car owner")
    @PostMapping
    public CarOwnerResponseDto create() {
        CarOwner savedCarOwner = carOwnerService.create(new CarOwner());
        return carOwnerMapper.toDto(savedCarOwner);
    }

    @Operation(summary = "Get car owner's performed orders")
    @GetMapping("/{id}/orders")
    public List<OrderResponseDto> getOrders(@PathVariable Long id) {
        return carOwnerService.getById(id).getOrders()
                .stream().map(orderMapper::toDto).toList();
    }
}

