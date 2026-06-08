package com.solar.services;

import com.solar.dto.DashboardSummaryDTO;
import com.solar.repository.SensorDataRepository;
import com.solar.repository.SolarPanelRepository;
import com.solar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService{

    private final UserRepository userRepository;
    private final SolarPanelRepository panelRepository;
    private final SensorDataRepository sensorDataRepository;

    @Override
    public DashboardSummaryDTO getSummary(){

        long totalUsers = userRepository.count();
        long totalPanels = panelRepository.count();
        long activePanels = panelRepository.countByStatus("ACTIVE");
        long faultPanels = panelRepository.countByStatus("FAULT");
        long overheatedPanels = panelRepository.countByStatus("OVERHEATED");
        long totalSensorReadinds = sensorDataRepository.count();

        return new DashboardSummaryDTO(
                totalUsers,
                totalPanels,
                activePanels,
                faultPanels,
                overheatedPanels,
                totalSensorReadinds
        );

    }
}
