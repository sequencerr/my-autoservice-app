package task.autoservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.autoservice.dto.mapper.RepairerMapper;
import task.autoservice.dto.request.RepairerRequestDto;
import task.autoservice.dto.response.RepairerResponseDto;
import task.autoservice.model.Repairer;
import task.autoservice.service.RepairerService;

@RestController
@RequestMapping("/repairers")
public class RepairerController {
    private final RepairerService repairerService;
    private final RepairerMapper repairerMapper;

    public RepairerController(RepairerService repairerService, RepairerMapper repairerMapper) {
        this.repairerService = repairerService;
        this.repairerMapper = repairerMapper;
    }

    @PostMapping
    public RepairerResponseDto create(@RequestBody RepairerRequestDto requestDto) {
        Repairer savedRepairer = repairerService.create(repairerMapper.toModel(requestDto));
        return repairerMapper.toDto(savedRepairer);
    }

    @PutMapping("/{id}")
    public RepairerResponseDto update(
            @PathVariable Long id,
            @RequestBody RepairerRequestDto requestDto) {
        Repairer updatedRepairer = repairerService.update(repairerMapper.toModel(id, requestDto));
        return repairerMapper.toDto(updatedRepairer);
    }
}