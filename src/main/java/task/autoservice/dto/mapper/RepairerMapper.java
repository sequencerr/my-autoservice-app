package task.autoservice.dto.mapper;

import org.springframework.stereotype.Component;
import task.autoservice.dto.DtoMapper;
import task.autoservice.dto.ModelMapper;
import task.autoservice.dto.request.RepairerRequestDto;
import task.autoservice.dto.response.RepairerResponseDto;
import task.autoservice.model.Order;
import task.autoservice.model.Repairer;
import task.autoservice.service.OrderService;

@Component
public class RepairerMapper implements
        DtoMapper<Repairer, RepairerRequestDto>,
        ModelMapper<Repairer, RepairerResponseDto> {
    private final OrderService orderService;

    public RepairerMapper(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Repairer toModel(RepairerRequestDto requestDto) {
        Repairer repairer = new Repairer();
        repairer.setFullName(requestDto.fullName());
        repairer.setCompletedOrders(orderService.findAllById(requestDto.completedOrderIds()));
        return repairer;
    }

    @Override
    public RepairerResponseDto toDto(Repairer repairer) {
        return new RepairerResponseDto(
                repairer.getId(),
                repairer.getFullName(),
                repairer.getCompletedOrders().stream().map(Order::getId).toList());
    }
}
