package task.autoservice.dto.response;

import task.autoservice.model.Order.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(
        Long id,
        String description,
        BigDecimal totalPrice,
        LocalDateTime acceptationDate,
        LocalDateTime completionDate,
        OrderStatus status,
        Long carId,
        List<Long> carServiceIds,
        List<Long> carPartIds) {
}
