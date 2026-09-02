package com.solar.dto.ai;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiAnomalyResponse {

    private Long panelId;
    private boolean anomaly;
    private Double anomalyScore;
    private String message;
}
