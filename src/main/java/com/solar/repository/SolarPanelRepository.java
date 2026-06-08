package com.solar.repository;

import com.solar.entity.SolarPanel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolarPanelRepository extends JpaRepository<SolarPanel,Long> {
    long countByStatus(String status);
}
