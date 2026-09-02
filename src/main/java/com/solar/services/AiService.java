package com.solar.services;

import com.solar.dto.ai.AiAnomalyResponse;
import com.solar.dto.ai.AiSensorDataRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AiService {

    private final WebClient webClient;

    public AiService(WebClient webClient){
        this.webClient = webClient;
    }

    public AiAnomalyResponse detectAnomaly(AiSensorDataRequest request){

        return webClient
                .post()
                .uri("api/ai/anomaly/detect")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AiAnomalyResponse.class)
                .block();
    }
}
