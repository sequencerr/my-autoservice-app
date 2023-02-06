package task.autoservice.dto.mapper;

import org.springframework.stereotype.Component;
import task.autoservice.dto.DtoMapper;
import task.autoservice.dto.ModelMapper;
import task.autoservice.dto.request.CarRequestDto;
import task.autoservice.dto.response.CarResponseDto;
import task.autoservice.model.Car;
import task.autoservice.model.CarOwner;
import task.autoservice.service.GenericService;

@Component
public class CarMapper implements DtoMapper<Car, CarRequestDto>, ModelMapper<Car, CarResponseDto> {
    private final GenericService<CarOwner> carOwnerService;

    public CarMapper(GenericService<CarOwner> carOwnerService) {
        this.carOwnerService = carOwnerService;
    }

    @Override
    public Car toModel(CarRequestDto requestDto) {
        Car car = new Car();
        car.setBrand(requestDto.brand());
        car.setModel(requestDto.model());
        car.setProductionYear(requestDto.productionYear());
        car.setNumberPlate(requestDto.numberPlate());
        car.setOwner(carOwnerService.getById(requestDto.ownerId()));
        return car;
    }

    @Override
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
