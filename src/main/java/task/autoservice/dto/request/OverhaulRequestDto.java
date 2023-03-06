package task.autoservice.dto.request;

import java.math.BigDecimal;

public record OverhaulRequestDto(
        BigDecimal price,
        Long orderId,
        Long repairerId) {
}
