package task.autoservice.dto.request;

import java.util.List;

public record OrderRequestDto(
        String description,
        Long carId,
        List<Long> overhaulIds,
        List<Long> carPartIds) {
}
