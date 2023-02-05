package task.autoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.autoservice.dto.mapper.DetailMapper;
import task.autoservice.dto.request.DetailRequestDto;
import task.autoservice.dto.response.DetailResponseDto;
import task.autoservice.model.Detail;
import task.autoservice.service.GenericService;

@RestController
@RequestMapping("/details")
public class DetailController {
    private final GenericService<Detail> detailService;
    private final DetailMapper detailMapper;

    public DetailController(GenericService<Detail> detailService, DetailMapper detailMapper) {
        this.detailService = detailService;
        this.detailMapper = detailMapper;
    }

    @Operation(summary = "CRUD: Create car detail by dto")
    @PostMapping
    public DetailResponseDto create(@RequestBody DetailRequestDto requestDto) {
        Detail savedDetail = detailService.create(detailMapper.toModel(requestDto));
        return detailMapper.toDto(savedDetail);
    }

    @Operation(summary = "CRUD: Update car detail by id with dto")
    @PutMapping("/{id}")
    public DetailResponseDto update(
            @PathVariable Long id,
            @RequestBody DetailRequestDto requestDto) {
        Detail updatedDetail = detailService.update(detailMapper.toModel(id, requestDto));
        return detailMapper.toDto(updatedDetail);
    }
}
