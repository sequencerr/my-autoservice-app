package task.autoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.autoservice.dto.mapper.CarMapper;
import task.autoservice.dto.request.CarRequestDto;
import task.autoservice.dto.response.CarResponseDto;
import task.autoservice.model.Car;
import task.autoservice.service.GenericService;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final GenericService<Car> carService;
    private final CarMapper carMapper;

    public CarController(GenericService<Car> carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @Operation(summary = "CRUD: Create car by dto")
    @PostMapping
    public CarResponseDto create(@RequestBody CarRequestDto requestDto) {
        Car savedCar = carService.create(carMapper.toModel(requestDto));
        return carMapper.toDto(savedCar);
    }

    @Operation(summary = "CRUD: Update car by id with dto")
    @PutMapping("/{id}")
    public CarResponseDto update(
            @PathVariable Long id,
            @RequestBody CarRequestDto requestDto) {
        Car updatedCar = carService.update(carMapper.toModel(id, requestDto));
        return carMapper.toDto(updatedCar);
    }
}
