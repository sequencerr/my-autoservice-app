package task.autoservice.dto.mapper;

import org.springframework.stereotype.Component;
import task.autoservice.dto.DtoMapper;
import task.autoservice.dto.ModelMapper;
import task.autoservice.dto.request.OrderRequestDto;
import task.autoservice.dto.response.OrderResponseDto;
import task.autoservice.model.Car;
import task.autoservice.model.Detail;
import task.autoservice.model.Order;
import task.autoservice.model.Overhaul;
import task.autoservice.service.GenericService;

@Component
public class OrderMapper implements
        DtoMapper<Order, OrderRequestDto>,
        ModelMapper<Order, OrderResponseDto> {
    private final GenericService<Car> carService;

    public OrderMapper(GenericService<Car> carService) {
        this.carService = carService;
    }

    @Override
    public Order toModel(OrderRequestDto requestDto) {
        Order order = new Order();
        order.setDescription(requestDto.description());
        order.setCar(carService.getById(requestDto.carId()));
        return order;
    }

    @Override
    public OrderResponseDto toDto(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getDescription(),
                order.getPrice(),
                order.getAcceptationDate(),
                order.getCompletionDate(),
                order.getStatus(),
                order.getCar().getId(),
                order.getDetails().stream().map(Detail::getId).toList(),
                order.getOverhauls().stream().map(Overhaul::getId).toList());
    }
}
