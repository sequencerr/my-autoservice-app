package task.autoservice.dto.response;

import java.math.BigDecimal;

public record CarServiceResponseDto(
        Long id,
        BigDecimal price,
        Boolean isPaid,
        Long orderId,
        Long repairerId) {
}
