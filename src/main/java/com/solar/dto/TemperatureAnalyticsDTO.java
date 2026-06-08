package com.solar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TemperatureAnalyticsDTO {

    private LocalDateTime timestamp;
    private Double temperature;
}
