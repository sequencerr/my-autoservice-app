package task.autoservice.dto.mapper;

import org.springframework.stereotype.Component;
import task.autoservice.dto.DtoMapper;
import task.autoservice.dto.ModelMapper;
import task.autoservice.dto.request.CarServiceRequestDto;
import task.autoservice.dto.response.CarServiceResponseDto;
import task.autoservice.model.CarService;
import task.autoservice.service.OrderService;
import task.autoservice.service.RepairerService;

@Component
public class CarServiceMapper implements
        DtoMapper<CarService, CarServiceRequestDto>,
        ModelMapper<CarService, CarServiceResponseDto> {
    private final OrderService orderService;
    private final RepairerService repairerService;

    public CarServiceMapper(OrderService orderService, RepairerService repairerService) {
        this.orderService = orderService;
        this.repairerService = repairerService;
    }

    public CarService toModel(CarServiceRequestDto requestDto) {
        return toModel(null, requestDto);
    }

    @Override
    public CarService toModel(Long id, CarServiceRequestDto requestDto) {
        CarService service = new CarService();
        service.setId(id);
        service.setPrice(requestDto.price());
        service.setIsPaid(requestDto.isPaid());
        service.setOrder(orderService.getById(requestDto.orderId()));
        service.setRepairer(repairerService.getById(requestDto.repairerId()));
        return service;
    }

    @Override
    public CarServiceResponseDto toDto(CarService carService) {
        return new CarServiceResponseDto(
                carService.getId(),
                carService.getPrice(),
                carService.getIsPaid(),
                carService.getOrder().getId(),
                carService.getRepairer().getId());
    }
}
