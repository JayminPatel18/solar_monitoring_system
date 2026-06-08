package com.solar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardSummaryDTO {

    private long totalUsers;

    private long totalPanels;

    private long activePanels;

    private long faultPanels;

    private long overheatedPanels;

    private long totalSensorReadings;
}
