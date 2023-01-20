package task.autoservice.dto.response;

import java.util.List;

public record CarOwnerResponseDto(
        Long id,
        List<Long> carIds,
        List<Long> orderIds) {
}
