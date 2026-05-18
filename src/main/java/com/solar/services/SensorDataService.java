package com.solar.services;

import com.solar.entity.SensorData;
import com.solar.exception.ResourceNotFoundException;
import com.solar.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import com.solar.dto.SensorDataDTO;
import org.springframework.stereotype.Service;

@Service
public class SensorDataService {

    @Autowired
    private SensorDataRepository repo;

    // Save sensor data
    public SensorData saveData(SensorData data){
        return repo.save(data);
    }

    // Get All Sensor Data
    public List<SensorDataDTO> getAllData(){
        return repo.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Latest reading
    public SensorDataDTO getLatestData(Long panelId){
        SensorData data = repo.findTopByPanelIdOrderByTimestampDesc(panelId);

        if(data == null){
            throw new ResourceNotFoundException("No Sensor Data found Panel id: "+panelId);
        }

        return convertToDTO(data);
    }

    // panel-wise data
    public List<SensorDataDTO> getDataByPanel(Long panelId){
        return repo.findByPanelId(panelId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Total Power
    public Double getTotalPower(Long panelId){
        Double totalPower = repo.getToalPowerByPanel(panelId);

        return totalPower != null ? totalPower : 0.0;
    }

    // panel Status
    public String getPanelStatus(Long panelId){

        SensorData data = repo.findTopByPanelIdOrderByTimestampDesc(panelId);

        if(data == null)
            return "NO DATA";

        if(data.getPower() == 0)
            return "FAULT";

        if(data.getTemperature() > 50)
            return "OVERHEATED";

        return "ACTIVE";
    }

    // DTO part implement
    private SensorDataDTO convertToDTO(SensorData data){

        if(data == null){
            throw new ResourceNotFoundException("Sensor data is null");
        }

        return new SensorDataDTO(
                data.getId(),
                data.getVoltage(),
                data.getCurrent(),
                data.getPower(),
                data.getTemperature(),
                data.getTimestamp(),
                data.getPanel().getId(),
                data.getPanel().getPanelName()
        );
    }
}
