package task.autoservice.dto.mapper;

import org.springframework.stereotype.Component;
import task.autoservice.dto.request.OrderRequestDto;
import task.autoservice.dto.response.OrderResponseDto;
import task.autoservice.model.Car;
import task.autoservice.model.CarPart;
import task.autoservice.model.Order;
import task.autoservice.service.CarServiceService;
import task.autoservice.service.GenericService;

@Component
public class OrderMapper {
    private final GenericService<Car> carService;
    private final GenericService<CarPart> carPartService;
    private final CarServiceService carServiceService;

    public OrderMapper(
            GenericService<Car> carService,
            GenericService<CarPart> carPartService,
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
        order.setCar(carService.getById(requestDto.carId()));
        order.setCarParts(carPartService.findAllById(requestDto.carPartIds()));
        order.setCarServices(carServiceService.findAllById(requestDto.carServiceIds()));
        return order;
    }

    public OrderResponseDto toDto(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getDescription(),
                order.getTotalPriceForClient(),
                order.getAcceptationDate(),
                order.getCompletionDate(),
                order.getStatus(),
                order.getCar().getId(),
                order.getCarParts().stream().map(CarPart::getId).toList(),
                order.getCarServices().stream().map(s -> s.getId()).toList());
    }
}
