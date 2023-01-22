package task.autoservice.dto.mapper;

import org.springframework.stereotype.Component;
import task.autoservice.dto.request.CarRequestDto;
import task.autoservice.dto.response.CarResponseDto;
import task.autoservice.model.Car;
import task.autoservice.service.CarOwnerService;

@Component
public class CarMapper {
    private final CarOwnerService carOwnerService;

    public CarMapper(CarOwnerService carOwnerService) {
        this.carOwnerService = carOwnerService;
    }

    public Car toModel(CarRequestDto requestDto) {
        return toModel(null, requestDto);
    }

    public Car toModel(Long id, CarRequestDto requestDto) {
        Car car = new Car();
        car.setId(id);
        car.setBrand(requestDto.brand());
        car.setModel(requestDto.model());
        car.setProductionYear(requestDto.productionYear());
        car.setNumberPlate(requestDto.numberPlate());
        car.setOwner(carOwnerService.getById(requestDto.ownerId()));
        return car;
    }

    public CarResponseDto toDto(Car car) {
        return new CarResponseDto(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getProductionYear(),
                car.getNumberPlate(),
                car.getOwner().getId());
    }
}
