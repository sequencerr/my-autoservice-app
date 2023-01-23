package task.autoservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.autoservice.dto.mapper.CarPartMapper;
import task.autoservice.dto.request.CarPartRequestDto;
import task.autoservice.dto.response.CarPartResponseDto;
import task.autoservice.model.CarPart;
import task.autoservice.service.GenericService;

@RestController
@RequestMapping("/car-parts")
public class CarPartController {
    private final GenericService<CarPart> carPartService;
    private final CarPartMapper carPartMapper;

    public CarPartController(GenericService<CarPart> carPartService, CarPartMapper carPartMapper) {
        this.carPartService = carPartService;
        this.carPartMapper = carPartMapper;
    }

    @PostMapping
    public CarPartResponseDto create(@RequestBody CarPartRequestDto requestDto) {
        CarPart savedCarPart = carPartService.create(carPartMapper.toModel(requestDto));
        return carPartMapper.toDto(savedCarPart);
    }

    @PutMapping("/{id}")
    public CarPartResponseDto update(
            @PathVariable Long id,
            @RequestBody CarPartRequestDto requestDto) {
        CarPart updatedCarPart = carPartService.update(carPartMapper.toModel(id, requestDto));
        return carPartMapper.toDto(updatedCarPart);
    }
}
