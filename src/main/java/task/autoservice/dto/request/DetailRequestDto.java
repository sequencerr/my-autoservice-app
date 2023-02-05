package task.autoservice.dto.request;

import java.math.BigDecimal;

public record DetailRequestDto(
        String name,
        BigDecimal price) {
}
