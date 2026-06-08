package com.solar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PerformanceDTO {

    private Double averagePower;
    private Double maxPower;
    private Double minPower;
}
