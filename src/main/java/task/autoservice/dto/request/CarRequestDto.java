package task.autoservice.dto.request;

public record CarRequestDto(
        String brand,
        String model,
        Integer productionYear,
        String numberPlate,
        Long ownerId) {
}
