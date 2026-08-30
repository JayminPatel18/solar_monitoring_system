package com.solar.controller;

import com.solar.dto.ApiResponse;
import com.solar.dto.PanelSummaryDTO;
import com.solar.entity.SensorData;
import com.solar.entity.SolarPanel;
import com.solar.exception.ResourceNotFoundException;
import com.solar.services.SolarPanelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@Tag(name = "Solar Panel APIs", description = "Operations related to solar panels")
@RestController
@RequestMapping("/api/panels")
public class SolarPanelController {

    @Autowired
    private SolarPanelService service;

    @Operation(summary = "Get all solar panels")
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'TECHNICIAN', 'ADMIN')")
    public List<SolarPanel> getAll(){
        return service.getAllPanels();
    }

    @Operation(summary = "Create solar panel")
    @PostMapping
    @PreAuthorize("hasAnyRole('TECHNICIAN', 'ADMIN')")
    public SolarPanel create(@Valid @RequestBody SolarPanel panel){
        return service.savePanel(panel);
    }


    @Operation(summary = "Get Panel Summary")
    @GetMapping("/{id}/summary")
    @PreAuthorize("hasAnyRole('USER','TECHNICIAN','ADMIN')")
    public ApiResponse<PanelSummaryDTO> getPanelSummary(
            @PathVariable Long id) {

        return new ApiResponse<>(
                true,
                "Panel Summary fetched successfully",
                service.getPanelSummary(id)
        );
    }


    @Operation(summary = "Get All Panel Summaries")
    @GetMapping("/summaries")
    @PreAuthorize("hasAnyRole('USER','TECHNICIAN','ADMIN')")
    public ApiResponse<List<PanelSummaryDTO>> getAllPanelSummaries() {

        return new ApiResponse<>(
                true,
                "Panel summaries fetched successfully",
                service.getAllPanelSummaries()
        );
    }

    // update panel
    @Operation(summary = "Update solar panel")
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('TECHNICIAN','ADMIN')")
    public SolarPanel updatePanel(
            @PathVariable Long id,
            @Valid @RequestBody SolarPanel panel) {

        return service.updatePanel(id, panel);
    }

    // delete panel by id
    @Operation(summary = "Delete solar panel")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('TECHNICIAN','ADMIN')")
    public ApiResponse<String> deletePanel(
            @PathVariable Long id) {

        service.deletePanel(id);

        return new ApiResponse<>(
                true,
                "Panel deleted successfully",
                null
        );
    }
}
