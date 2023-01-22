package task.autoservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.autoservice.dto.mapper.CarServiceMapper;
import task.autoservice.dto.request.CarServiceRequestDto;
import task.autoservice.dto.response.CarServiceResponseDto;
import task.autoservice.model.CarService;
import task.autoservice.service.CarServiceService;

@RestController
@RequestMapping("/car-services")
public class CarServiceController {
    private final CarServiceService carServiceService;
    private final CarServiceMapper carServiceMapper;

    public CarServiceController(CarServiceService carServiceService, CarServiceMapper carServiceMapper) {
        this.carServiceService = carServiceService;
        this.carServiceMapper = carServiceMapper;
    }

    @PostMapping
    public CarServiceResponseDto create(@RequestBody CarServiceRequestDto requestDto) {
        CarService savedCarService = carServiceService.create(carServiceMapper.toModel(requestDto));
        return carServiceMapper.toDto(savedCarService);
    }

    @PutMapping("/{id}")
    public CarServiceResponseDto update(
            @PathVariable Long id,
            @RequestBody CarServiceRequestDto requestDto) {
        CarService updatedCarService = carServiceService.update(carServiceMapper.toModel(id, requestDto));
        return carServiceMapper.toDto(updatedCarService);
    }
}
