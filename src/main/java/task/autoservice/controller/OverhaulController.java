package task.autoservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import task.autoservice.dto.mapper.OverhaulMapper;
import task.autoservice.dto.request.OverhaulRequestDto;
import task.autoservice.dto.response.OverhaulResponseDto;
import task.autoservice.model.Overhaul;
import task.autoservice.service.OverhaulService;

@RestController
@RequestMapping("/overhauls")
public class OverhaulController {
    private final OverhaulService overhaulService;
    private final OverhaulMapper overhaulMapper;

    public OverhaulController(OverhaulService overhaulService, OverhaulMapper overhaulMapper) {
        this.overhaulService = overhaulService;
        this.overhaulMapper = overhaulMapper;
    }

    @Operation(summary = "CRUD: Create car overhaul by dto")
    @PostMapping
    public OverhaulResponseDto create(@RequestBody OverhaulRequestDto requestDto) {
        Overhaul savedOverhaul = overhaulService.create(overhaulMapper.toModel(requestDto));
        return overhaulMapper.toDto(savedOverhaul);
    }

    @Operation(summary = "CRUD: Update car overhaul by id with dto")
    @PutMapping("/{id}")
    public OverhaulResponseDto update(
            @PathVariable Long id,
            @RequestBody OverhaulRequestDto requestDto) {
        Overhaul updatedOverhaul =
                overhaulService.update(overhaulMapper.toModel(id, requestDto));
        return overhaulMapper.toDto(updatedOverhaul);
    }

    @Operation(summary = "Update overhaul pay (by id) status (true/false)")
    @PutMapping("/{id}/isPaid")
    public ResponseEntity<String> updateStatus(
            @PathVariable Long id,
            @Parameter(description = "New overhaul status (true/false)")
            @RequestParam Boolean isPaid) {
        Overhaul overhaul = overhaulService.getById(id);
        overhaul.setIsPaid(isPaid);
        overhaulService.update(overhaul);
        return ResponseEntity.ok("Car overhaul's is paid status has been successfully updated.");
    }
}
