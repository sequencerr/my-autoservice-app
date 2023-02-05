package task.autoservice.dto.mapper;

import org.springframework.stereotype.Component;
import task.autoservice.dto.DtoMapper;
import task.autoservice.dto.ModelMapper;
import task.autoservice.dto.request.OrderRequestDto;
import task.autoservice.dto.response.OrderResponseDto;
import task.autoservice.model.Car;
import task.autoservice.model.CarPart;
import task.autoservice.model.Order;
import task.autoservice.model.Overhaul;
import task.autoservice.service.GenericService;
import task.autoservice.service.OverhaulService;

@Component
public class OrderMapper implements
        DtoMapper<Order, OrderRequestDto>,
        ModelMapper<Order, OrderResponseDto> {
    private final GenericService<Car> carService;
    private final GenericService<CarPart> carPartService;
    private final OverhaulService overhaulService;

    public OrderMapper(
            GenericService<Car> carService,
            GenericService<CarPart> carPartService,
            OverhaulService overhaulService
    ) {
        this.carService = carService;
        this.carPartService = carPartService;
        this.overhaulService = overhaulService;
    }

    public Order toModel(OrderRequestDto requestDto) {
        return toModel(null, requestDto);
    }

    @Override
    public Order toModel(Long id, OrderRequestDto requestDto) {
        Order order = new Order();
        order.setId(id);
        order.setDescription(requestDto.description());
        order.setCar(carService.getById(requestDto.carId()));
        order.setCarParts(carPartService.findAllById(requestDto.carPartIds()));
        order.setOverhauls(overhaulService.findAllById(requestDto.overhaulIds()));
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
                order.getCarParts().stream().map(CarPart::getId).toList(),
                order.getOverhauls().stream().map(Overhaul::getId).toList());
    }
}
