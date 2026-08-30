package com.solar.repository;

import com.solar.entity.SolarPanel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolarPanelRepository extends JpaRepository<SolarPanel,Long> {
    long countByStatus(String status);
    long countByUserId(Long userId);
    List<SolarPanel> findByUserId(Long userId);
}
