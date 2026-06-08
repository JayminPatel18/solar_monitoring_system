package com.solar.controller;

import com.solar.dto.ApiResponse;
import com.solar.dto.SensorDataDTO;
import com.solar.entity.SensorData;
import com.solar.repository.SensorDataRepository;
import com.solar.services.SensorDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@Tag(name = "Sensor Data APIs", description = "Operations related to sensor readings")
@RestController
@RequestMapping("/api/data")
public class SensorDataController {

    @Autowired
    private SensorDataService service;

    // save data
    @Operation(summary = "Store sensor data")
    @PostMapping
    @PreAuthorize("hasAnyRole('TECHNICIAN','ADMIN')")
    public ApiResponse<SensorData> save(
            @Valid @RequestBody SensorData data){

        return new ApiResponse<>(
                true,
                "Sensor data saved successfully",
                service.saveData(data)
        );
    }

    // get all data
    @Operation(summary = "Get All Sensor Data")
    @GetMapping
    @PreAuthorize("hasAnyRole('USER','TECHNICIAN','ADMIN')")
    public ApiResponse<List<SensorDataDTO>> getAll(){
        return new ApiResponse<>(
                true,
                "Sensor data Fetched Successfully",
                service.getAllData()
        );
    }

    // latest reading
    @Operation(summary = "Get latest sensor reading")
    @GetMapping("/latest/{panelId}")
    @PreAuthorize("hasAnyRole('USER','TECHNICIAN','ADMIN')")
    public ApiResponse<SensorDataDTO> getLatestData(@PathVariable Long panelId) {
        return new ApiResponse<>(
                true,
                "Latest Sensor data fetched Successfully",
                service.getLatestData(panelId)
        );
    }

    // get data by panel
    @Operation(summary = "Get Data by panel")
    @GetMapping("/panel/{panelId}")
    @PreAuthorize("hasAnyRole('USER','TECHNICIAN','ADMIN')")
    public List<SensorDataDTO> getByPanel(@PathVariable Long panelId) {
        return service.getDataByPanel(panelId);
    }

    // Total Power
    @Operation(summary = "Get total power generation")
    @GetMapping("/total/{panelId}")
    @PreAuthorize("hasAnyRole('USER','TECHNICIAN','ADMIN')")
    public Double getTotalPower(@PathVariable Long panelId){
        return service.getTotalPower(panelId);
    }

    // Panel Status Logic
    @Operation(summary = "Get smart panel status")
    @GetMapping("/status/{panelId}")
    @PreAuthorize("hasAnyRole('USER','TECHNICIAN','ADMIN')")
    public String getPanelStatus(@PathVariable Long panelId){
        return service.getPanelStatus(panelId);
    }
}
