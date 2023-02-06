package task.autoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.autoservice.dto.mapper.CarOwnerMapper;
import task.autoservice.dto.mapper.OrderMapper;
import task.autoservice.dto.request.CarOwnerRequestDto;
import task.autoservice.dto.response.CarOwnerResponseDto;
import task.autoservice.dto.response.OrderResponseDto;
import task.autoservice.model.CarOwner;
import task.autoservice.service.GenericService;

import java.util.List;

@RestController
@RequestMapping("/car-owners")
public class CarOwnerController {
    private final GenericService<CarOwner> carOwnerService;
    private final CarOwnerMapper carOwnerMapper;
    private final OrderMapper orderMapper;

    public CarOwnerController(
            GenericService<CarOwner> carOwnerService,
            CarOwnerMapper carOwnerMapper,
            OrderMapper orderMapper
    ) {
        this.carOwnerService = carOwnerService;
        this.carOwnerMapper = carOwnerMapper;
        this.orderMapper = orderMapper;
    }

    @Operation(summary = "CRUD: Update car owner by dto")
    @PostMapping
    public CarOwnerResponseDto create(@RequestBody CarOwnerRequestDto requestDto) {
        CarOwner savedCarOwner = carOwnerService.create(carOwnerMapper.toModel(requestDto));
        return carOwnerMapper.toDto(savedCarOwner);
    }

    @Operation(summary = "CRUD: Update car owner by id")
    @PutMapping("/{id}")
    public CarOwnerResponseDto update(
            @PathVariable Long id,
            @RequestBody CarOwnerRequestDto requestDto) {
        CarOwner carOwner = carOwnerMapper.toModel(requestDto);
        carOwner.setId(id);
        carOwnerService.update(carOwner);
        return carOwnerMapper.toDto(carOwner);
    }

    @Operation(summary = "Get car owner's performed orders")
    @GetMapping("/{id}/orders")
    public List<OrderResponseDto> getOrders(@PathVariable Long id) {
        return carOwnerService.getById(id).getOrders()
                .stream().map(orderMapper::toDto).toList();
    }
}

