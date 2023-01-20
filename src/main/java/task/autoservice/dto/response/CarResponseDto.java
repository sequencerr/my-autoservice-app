package task.autoservice.dto.response;

public record CarResponseDto(
        Long id,
        String brand,
        String model,
        Integer productionYear,
        String numberPlate,
        Long ownerId) {
}
