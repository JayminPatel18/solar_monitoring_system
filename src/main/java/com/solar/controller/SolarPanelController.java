package com.solar.controller;

import com.solar.entity.SensorData;
import com.solar.entity.SolarPanel;
import com.solar.services.SolarPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/panels")
public class SolarPanelController {

    @Autowired
    private SolarPanelService service;

    @GetMapping
    public List<SolarPanel> getAll(){
        return service.getAllPanels();
    }

    @PostMapping
    public SolarPanel create(@Valid @RequestBody SolarPanel panel){
        return service.savePanel(panel);
    }


}
