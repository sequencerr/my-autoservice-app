package task.autoservice.dto.request;

import java.math.BigDecimal;

public record CarServiceRequestDto(
        BigDecimal price,
        Boolean paidStatus,
        Long orderId,
        Long repairerId) {
}
