package task.autoservice.dto.request;

public record OrderRequestDto(
        String description,
        Long carId) {
}
