package task.autoservice.dto.response;

import java.util.List;

public record RepairerResponseDto(
        Long id,
        String fullName,
        List<Long> completedOrderIds) {
}
