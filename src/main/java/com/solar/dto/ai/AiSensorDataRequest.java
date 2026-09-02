package com.solar.dto.ai;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiSensorDataRequest {

    private Long panelId;
    private Double voltage;
    private Double current;
    private Double power;
    private Double temperature;
}
