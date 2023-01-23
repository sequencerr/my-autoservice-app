package task.autoservice.dto.mapper;

import org.springframework.stereotype.Component;
import task.autoservice.dto.request.RepairerRequestDto;
import task.autoservice.dto.response.RepairerResponseDto;
import task.autoservice.model.Order;
import task.autoservice.model.Repairer;
import task.autoservice.service.OrderService;

@Component
public class RepairerMapper {
    private final OrderService orderService;

    public RepairerMapper(OrderService orderService) {
        this.orderService = orderService;
    }

    public Repairer toModel(RepairerRequestDto requestDto) {
        return toModel(null, requestDto);
    }

    public Repairer toModel(Long id, RepairerRequestDto requestDto) {
        Repairer repairer = new Repairer();
        repairer.setId(id);
        repairer.setFullName(requestDto.fullName());
        repairer.setCompletedOrders(orderService.findAllById(requestDto.completedOrderIds()));
        return repairer;
    }

    public RepairerResponseDto toDto(Repairer repairer) {
        return new RepairerResponseDto(
                repairer.getId(),
                repairer.getFullName(),
                repairer.getCompletedOrders().stream().map(Order::getId).toList());
    }
}