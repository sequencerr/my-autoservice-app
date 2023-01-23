package task.autoservice.dto.request;

import java.math.BigDecimal;

public record CarServiceRequestDto(
        BigDecimal price,
        Boolean isPaid,
        Long orderId,
        Long repairerId) {
}
