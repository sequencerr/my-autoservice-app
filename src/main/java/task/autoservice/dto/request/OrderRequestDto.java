package task.autoservice.dto.request;

import task.autoservice.model.Order.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderRequestDto(
        String description,
        BigDecimal totalPrice,
        LocalDateTime acceptationDate,
        LocalDateTime completionDate,
        OrderStatus status,
        Long carId,
        List<Long> carServiceIds,
        List<Long> carPartIds) {
}
