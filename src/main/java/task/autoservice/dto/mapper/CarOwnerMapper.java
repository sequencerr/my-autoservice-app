package task.autoservice.dto.mapper;

import org.springframework.stereotype.Component;
import task.autoservice.dto.ModelMapper;
import task.autoservice.dto.response.CarOwnerResponseDto;
import task.autoservice.model.Car;
import task.autoservice.model.CarOwner;
import task.autoservice.model.Order;

@Component
public class CarOwnerMapper implements ModelMapper<CarOwner, CarOwnerResponseDto> {
    @Override
    public CarOwnerResponseDto toDto(CarOwner carOwner) {
        return new CarOwnerResponseDto(
                carOwner.getId(),
                carOwner.getCars().stream().map(Car::getId).toList(),
                carOwner.getOrders().stream().map(Order::getId).toList());
    }
}
