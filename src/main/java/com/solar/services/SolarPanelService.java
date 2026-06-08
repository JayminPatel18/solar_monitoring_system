package com.solar.services;

import com.solar.dto.PanelSummaryDTO;
import com.solar.entity.SensorData;
import com.solar.entity.SolarPanel;
import com.solar.exception.ResourceNotFoundException;
import com.solar.repository.SensorDataRepository;
import com.solar.repository.SolarPanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolarPanelService {

    @Autowired
    private SolarPanelRepository repo;

    @Autowired
    private SensorDataRepository sensorRepo;

    public List<SolarPanel> getAllPanels(){
        return repo.findAll();
    }

    public SolarPanel savePanel(SolarPanel panel){
        return repo.save(panel);
    }

    // New Method
    public SolarPanel getPanelById(Long id){

        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Solar Panel not found with i: "+id));
    }

    // New Method
    public PanelSummaryDTO getPanelSummary(Long panelId) {

        SolarPanel panel = getPanelById(panelId);

        SensorData latestData =
                sensorRepo.findTopByPanelIdOrderByTimestampDesc(panelId);

        Double totalPower =
                sensorRepo.getToalPowerByPanel(panelId);

        String status = "NO DATA";
        Double latestPower = 0.0;
        Double latestTemperature = 0.0;

        if (latestData != null) {

            latestPower = latestData.getPower();
            latestTemperature = latestData.getTemperature();

            if (latestData.getPower() == 0) {
                status = "FAULT";
            } else if (latestData.getTemperature() > 50) {
                status = "OVERHEATED";
            } else {
                status = "ACTIVE";
            }
        }

        return new PanelSummaryDTO(
                panel.getId(),
                panel.getPanelName(),
                panel.getLocation(),
                panel.getCapacity(),
                status,
                latestPower,
                latestTemperature,
                totalPower != null ? totalPower : 0.0
        );
    }

    public List<PanelSummaryDTO> getAllPanelSummaries() {

        return repo.findAll()
                .stream()
                .map(panel -> getPanelSummary(panel.getId()))
                .toList();
    }
}
