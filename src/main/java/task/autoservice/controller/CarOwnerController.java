package task.autoservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.autoservice.dto.mapper.CarOwnerMapper;
import task.autoservice.dto.request.CarOwnerRequestDto;
import task.autoservice.dto.response.CarOwnerResponseDto;
import task.autoservice.model.CarOwner;
import task.autoservice.service.CarOwnerService;

@RestController
@RequestMapping("/car-owners")
public class CarOwnerController {
    private final CarOwnerService carOwnerService;
    private final CarOwnerMapper carOwnerMapper;

    public CarOwnerController(CarOwnerService carOwnerService, CarOwnerMapper carOwnerMapper) {
        this.carOwnerService = carOwnerService;
        this.carOwnerMapper = carOwnerMapper;
    }

    @PostMapping
    public CarOwnerResponseDto create(@RequestBody CarOwnerRequestDto requestDto) {
        CarOwner savedCarOwner = carOwnerService.create(carOwnerMapper.toModel(requestDto));
        return carOwnerMapper.toDto(savedCarOwner);
    }

    @PutMapping("/{id}")
    public CarOwnerResponseDto update(
            @PathVariable Long id,
            @RequestBody CarOwnerRequestDto requestDto) {
        CarOwner updatedCarOwner = carOwnerService.update(carOwnerMapper.toModel(id, requestDto));
        return carOwnerMapper.toDto(updatedCarOwner);
    }
}

