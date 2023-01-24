package task.autoservice.dto.mapper;

import org.springframework.stereotype.Component;
import task.autoservice.dto.DtoMapper;
import task.autoservice.dto.ModelMapper;
import task.autoservice.dto.request.CarPartRequestDto;
import task.autoservice.dto.response.CarPartResponseDto;
import task.autoservice.model.CarPart;

@Component
public class CarPartMapper implements
        DtoMapper<CarPart, CarPartRequestDto>,
        ModelMapper<CarPart, CarPartResponseDto> {
    public CarPart toModel(CarPartRequestDto requestDto) {
        return toModel(null, requestDto);
    }

    @Override
    public CarPart toModel(Long id, CarPartRequestDto requestDto) {
        CarPart carPart = new CarPart();
        carPart.setId(id);
        carPart.setName(requestDto.name());
        carPart.setPrice(requestDto.price());
        return carPart;
    }

    @Override
    public CarPartResponseDto toDto(CarPart carPart) {
        return new CarPartResponseDto(
                carPart.getId(),
                carPart.getName(),
                carPart.getPrice());
    }
}
