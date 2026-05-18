package com.solar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorDataDTO {

    private Long id;

    private Double voltage;
    private Double current;
    private Double power;
    private Double temperature;

    private LocalDateTime timestamp;

    private Long panelId;

    private String panelName;
}
