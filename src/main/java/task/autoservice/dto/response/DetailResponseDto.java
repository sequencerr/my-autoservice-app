package task.autoservice.dto.response;

import java.math.BigDecimal;

public record DetailResponseDto(
        Long id,
        String name,
        BigDecimal price) {
}
