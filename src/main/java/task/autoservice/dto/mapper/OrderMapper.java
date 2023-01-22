package task.autoservice.dto.mapper;

import org.springframework.stereotype.Component;
import task.autoservice.dto.request.OrderRequestDto;
import task.autoservice.dto.response.OrderResponseDto;
import task.autoservice.model.CarPart;
import task.autoservice.model.Order;
import task.autoservice.service.CarService;
import task.autoservice.service.CarPartService;
import task.autoservice.service.CarServiceService;

@Component
public class OrderMapper {
    private final CarService carService;
    private final CarPartService carPartService;
    private final CarServiceService carServiceService;

    public OrderMapper(
            CarService carService,
            CarPartService carPartService,
            CarServiceService carServiceService
    ) {
        this.carService = carService;
        this.carPartService = carPartService;
        this.carServiceService = carServiceService;
    }

    public Order toModel(OrderRequestDto requestDto) {
        return toModel(null, requestDto);
    }

    public Order toModel(Long id, OrderRequestDto requestDto) {
        Order order = new Order();
        order.setId(id);
        order.setDescription(requestDto.description());
        order.setTotalPrice(requestDto.totalPrice());
        order.setAcceptationDate(requestDto.acceptationDate());
        order.setCompletionDate(requestDto.completionDate());
        order.setStatus(requestDto.status());
        order.setCar(carService.getById(requestDto.carId()));
        order.setCarParts(carPartService.findAllWithIds(requestDto.carPartIds()));
        order.setCarServices(carServiceService.findAllWithIds(requestDto.carServiceIds()));
        return order;
    }

    public OrderResponseDto toDto(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getDescription(),
                order.getTotalPrice(),
                order.getAcceptationDate(),
                order.getCompletionDate(),
                order.getStatus(),
                order.getCar().getId(),
                order.getCarParts().stream().map(CarPart::getId).toList(),
                order.getCarServices().stream().map(s -> s.getId()).toList());
    }
}
