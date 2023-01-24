package task.autoservice.dto.mapper;

import org.springframework.stereotype.Component;
import task.autoservice.dto.DtoMapper;
import task.autoservice.dto.ModelMapper;
import task.autoservice.dto.request.CarOwnerRequestDto;
import task.autoservice.dto.response.CarOwnerResponseDto;
import task.autoservice.model.Car;
import task.autoservice.model.CarOwner;
import task.autoservice.model.Order;
import task.autoservice.service.GenericService;
import task.autoservice.service.OrderService;

@Component
public class CarOwnerMapper implements
        DtoMapper<CarOwner, CarOwnerRequestDto>,
        ModelMapper<CarOwner, CarOwnerResponseDto> {
    private final GenericService<Car> carService;
    private final OrderService orderService;

    public CarOwnerMapper(GenericService<Car> carService, OrderService orderService) {
        this.carService = carService;
        this.orderService = orderService;
    }

    public CarOwner toModel(CarOwnerRequestDto requestDto) {
        return toModel(null, requestDto);
    }

    @Override
    public CarOwner toModel(Long id, CarOwnerRequestDto requestDto) {
        CarOwner owner = new CarOwner();
        owner.setId(id);
        owner.setCars(carService.findAllById(requestDto.carIds()));
        owner.setOrders(orderService.findAllById(requestDto.orderIds()));
        return owner;
    }

    @Override
    public CarOwnerResponseDto toDto(CarOwner carOwner) {
        return new CarOwnerResponseDto(
                carOwner.getId(),
                carOwner.getCars().stream().map(Car::getId).toList(),
                carOwner.getOrders().stream().map(Order::getId).toList());
    }
}
