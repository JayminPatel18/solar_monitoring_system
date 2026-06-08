package com.solar.repository;

import com.solar.entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    // latest reading panels
    SensorData findTopByPanelIdOrderByTimestampDesc(Long panelId);

    List<SensorData> findByPanelId(Long panelId);

    //total generated power in data
    @Query("Select SUM(s.power) FROM SensorData s WHERE s.panel.id = :panelId")
    Double getToalPowerByPanel(@Param("panelId") Long panelId);

    // avarage power
    @Query("""
                SELECT AVG(s.power)
                FROM SensorData s
                WHERE s.panel.id = :panelId
            """)
    Double getAveragePower(@Param("panelId") Long panelId);

    @Query("""
            SELECT MAX(s.power)
            FROM SensorData s
            WHERE s.panel.id = :panelId
            """)
    Double getMaxPower(@Param("panelId") Long panelId);

    @Query("""
            SELECT MIN(s.power)
            FROM SensorData s
            WHERE s.panel.id = :panelId
            """)
    Double getMinPower(@Param("panelId") Long panelId);
}
