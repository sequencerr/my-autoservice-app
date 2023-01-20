package task.autoservice.dto.response;

import java.math.BigDecimal;

public record CarPartResponseDto(
        Long id,
        String name,
        BigDecimal price) {
}
