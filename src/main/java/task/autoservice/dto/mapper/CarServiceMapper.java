package task.autoservice.dto.mapper;

import org.springframework.stereotype.Component;
import task.autoservice.dto.request.CarServiceRequestDto;
import task.autoservice.dto.response.CarServiceResponseDto;
import task.autoservice.model.CarService;
import task.autoservice.service.OrderService;
import task.autoservice.service.RepairerService;

@Component
public class CarServiceMapper {
    private final OrderService orderService;
    private final RepairerService repairerService;

    public CarServiceMapper(OrderService orderService, RepairerService repairerService) {
        this.orderService = orderService;
        this.repairerService = repairerService;
    }

    public CarService toModel(Long id, CarServiceRequestDto requestDto) {
        CarService service = new CarService();
        service.setId(id);
        service.setPrice(requestDto.price());
        service.setPaidStatus(requestDto.paidStatus());
        service.setOrder(orderService.getById(requestDto.orderId()));
        service.setRepairer(repairerService.getById(requestDto.repairerId()));
        return service;
    }

    public CarServiceResponseDto toDto(CarService carService) {
        return new CarServiceResponseDto(
                carService.getId(),
                carService.getPrice(),
                carService.getPaidStatus(),
                carService.getOrder().getId(),
                carService.getRepairer().getId());
    }
}
