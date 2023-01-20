package task.autoservice.dto.response;

import java.math.BigDecimal;

public record CarServiceResponseDto(
        Long id,
        BigDecimal price,
        Boolean paidStatus,
        Long orderId,
        Long repairerId) {
}
