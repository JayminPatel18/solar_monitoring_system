package com.solar.controller;

import com.solar.dto.ApiResponse;
import com.solar.dto.SensorDataDTO;
import com.solar.entity.SensorData;
import com.solar.repository.SensorDataRepository;
import com.solar.services.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/data")
public class SensorDataController {

    @Autowired
    private SensorDataService service;

    // save data
    @PostMapping
    public SensorData save(@Valid @RequestBody SensorData data){
        return service.saveData(data);
    }

    // get all data
    @GetMapping
    public ApiResponse<List<SensorDataDTO>> getAll(){
        return new ApiResponse<>(
                true,
                "Sensor data Fetched Successfully",
                service.getAllData()
        );
    }

    // latest reading
    @GetMapping("/latest/{panelId}")
    public ApiResponse<SensorDataDTO> getLatestData(@PathVariable Long panelId) {
        return new ApiResponse<>(
                true,
                "Latest Sensor data fetched Successfully",
                service.getLatestData(panelId)
        );
    }

    // get data by panel
    @GetMapping("/panel/{panelId}")
    public List<SensorDataDTO> getByPanel(@PathVariable Long panelId) {
        return service.getDataByPanel(panelId);
    }

    // Total Power
    @GetMapping("/total/{panelId}")
    public Double getTotalPower(@PathVariable Long panelId){
        return service.getTotalPower(panelId);
    }

    // Panel Status Logic
    @GetMapping("/status/{panelId}")
    public String getPanelStatus(@PathVariable Long panelId){
        return service.getPanelStatus(panelId);
    }
}
