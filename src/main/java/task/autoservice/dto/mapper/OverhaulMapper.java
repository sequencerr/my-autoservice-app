package task.autoservice.dto.mapper;

import org.springframework.stereotype.Component;
import task.autoservice.dto.DtoMapper;
import task.autoservice.dto.ModelMapper;
import task.autoservice.dto.request.OverhaulRequestDto;
import task.autoservice.dto.response.OverhaulResponseDto;
import task.autoservice.model.Overhaul;
import task.autoservice.service.OrderService;
import task.autoservice.service.RepairerService;

@Component
public class OverhaulMapper implements
        DtoMapper<Overhaul, OverhaulRequestDto>,
        ModelMapper<Overhaul, OverhaulResponseDto> {
    private final OrderService orderService;
    private final RepairerService repairerService;

    public OverhaulMapper(OrderService orderService, RepairerService repairerService) {
        this.orderService = orderService;
        this.repairerService = repairerService;
    }

    public Overhaul toModel(OverhaulRequestDto requestDto) {
        Overhaul service = new Overhaul();
        service.setPrice(requestDto.price());
        service.setIsPaid(requestDto.isPaid());
        service.setOrder(orderService.getById(requestDto.orderId()));
        service.setRepairer(repairerService.getById(requestDto.repairerId()));
        return service;
    }

    @Override
    public OverhaulResponseDto toDto(Overhaul overhaul) {
        return new OverhaulResponseDto(
                overhaul.getId(),
                overhaul.getPrice(),
                overhaul.getIsPaid(),
                overhaul.getOrder().getId(),
                overhaul.getRepairer().getId());
    }
}
