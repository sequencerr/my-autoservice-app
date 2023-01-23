package task.autoservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.autoservice.dto.mapper.OrderMapper;
import task.autoservice.dto.mapper.RepairerMapper;
import task.autoservice.dto.request.RepairerRequestDto;
import task.autoservice.dto.response.OrderResponseDto;
import task.autoservice.dto.response.RepairerResponseDto;
import task.autoservice.model.Repairer;
import task.autoservice.service.RepairerService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/repairers")
public class RepairerController {
    private final RepairerService repairerService;
    private final RepairerMapper repairerMapper;
    private final OrderMapper orderMapper;

    public RepairerController(
            RepairerService repairerService,
            RepairerMapper repairerMapper,
            OrderMapper orderMapper
    ) {
        this.repairerService = repairerService;
        this.repairerMapper = repairerMapper;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public RepairerResponseDto create(@RequestBody RepairerRequestDto requestDto) {
        Repairer savedRepairer = repairerService.create(repairerMapper.toModel(requestDto));
        return repairerMapper.toDto(savedRepairer);
    }

    @PutMapping("/{id}")
    public RepairerResponseDto update(
            @PathVariable Long id,
            @RequestBody RepairerRequestDto requestDto) {
        Repairer updatedRepairer = repairerService.update(repairerMapper.toModel(id, requestDto));
        return repairerMapper.toDto(updatedRepairer);
    }

    @GetMapping("/{id}/completed-orders")
    public List<OrderResponseDto> getCompletedOrders(@PathVariable Long id) {
        return repairerService.getById(id).getCompletedOrders()
                .stream().map(orderMapper::toDto).toList();
    }

    @GetMapping("/{id}/pay-salary")
    public BigDecimal getSalaryAndPay(@PathVariable Long id) {
        return repairerService.calculateSalary(id);
    }
}
