package task.autoservice.dto.response;

import java.math.BigDecimal;

public record OverhaulResponseDto(
        Long id,
        BigDecimal price,
        Boolean isPaid,
        Long orderId,
        Long repairerId) {
}
