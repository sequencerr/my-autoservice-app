package task.autoservice.dto.request;

import java.util.List;

public record OrderRequestDto(
        String description,
        Long carId,
        List<Long> carServiceIds,
        List<Long> carPartIds) {
}
