package com.solar.services;

import com.solar.entity.SolarPanel;
import com.solar.exception.ResourceNotFoundException;
import com.solar.repository.SolarPanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolarPanelService {

    @Autowired
    private SolarPanelRepository repo;

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
}
