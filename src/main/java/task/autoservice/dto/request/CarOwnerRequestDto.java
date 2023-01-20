package task.autoservice.dto.request;

import java.util.List;

public record CarOwnerRequestDto(
        List<Long> carIds,
        List<Long> orderIds) {
}
