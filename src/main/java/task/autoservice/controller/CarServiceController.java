package task.autoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Operation(summary = "CRUD: Create car service by dto")
    @PostMapping
    public CarServiceResponseDto create(@RequestBody CarServiceRequestDto requestDto) {
        CarService savedCarService = carServiceService.create(carServiceMapper.toModel(requestDto));
        return carServiceMapper.toDto(savedCarService);
    }

    @Operation(summary = "CRUD: Update car service by id with dto")
    @PutMapping("/{id}")
    public CarServiceResponseDto update(
            @PathVariable Long id,
            @RequestBody CarServiceRequestDto requestDto) {
        CarService updatedCarService =
                carServiceService.update(carServiceMapper.toModel(id, requestDto));
        return carServiceMapper.toDto(updatedCarService);
    }

    @Operation(summary = "Update service pay (by id) status (true/false)")
    @PutMapping("/{id}/isPaid")
    public ResponseEntity<String> updateStatus(
            @PathVariable Long id,
            @Parameter(description = "New service status (true/false)")
            @RequestParam Boolean isPaid) {
        CarService service = carServiceService.getById(id);
        service.setIsPaid(isPaid);
        carServiceService.update(service);
        return new ResponseEntity<>(
                "Car service's is paid status has been successfully updated.", HttpStatus.OK);
    }
}
