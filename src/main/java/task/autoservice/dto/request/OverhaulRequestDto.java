package task.autoservice.dto.request;

import java.math.BigDecimal;

public record OverhaulRequestDto(
        BigDecimal price,
        Boolean isPaid,
        Long orderId,
        Long repairerId) {
}
