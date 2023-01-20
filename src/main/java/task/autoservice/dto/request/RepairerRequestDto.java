package task.autoservice.dto.request;

import java.util.List;

public record RepairerRequestDto(
        String fullName,
        List<Long> completedOrderIds) {
}