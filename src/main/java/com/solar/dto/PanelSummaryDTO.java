package com.solar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PanelSummaryDTO {

    private Long panelId;
    private String panelName;
    private String location;
    private Double capacity;
    private String status;
    private Double latestpower;
    private Double latestTemperature;
    private Double totalPowerGenerated;
}
