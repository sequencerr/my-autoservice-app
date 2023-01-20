package task.autoservice.dto.request;

import java.math.BigDecimal;

public record CarPartRequestDto(
        String name,
        BigDecimal price) {
}
