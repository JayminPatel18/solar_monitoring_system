package com.solar.controller;

import com.solar.entity.SensorData;
import com.solar.entity.SolarPanel;
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


}
