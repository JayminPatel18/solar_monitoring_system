package com.solar.controller;

import com.solar.dto.ApiResponse;
import com.solar.services.SensorDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
@Tag(
        name = "Analytics APIs",
        description = "Analytics and chart data endpoints"
)
@PreAuthorize(
        "hasAnyRole('USER','TECHNICIAN','ADMIN')"
)
public class AnalyticsController {

    private final SensorDataService service;

    @Operation(summary = "Power Analytics")
    @GetMapping("/power/{panelId}")
    public ApiResponse<?> getPower(
            @PathVariable Long panelId) {

        return new ApiResponse<>(
                true,
                "Power Analytics",
                service.getPowerAnalytics(panelId)
        );
    }

    @Operation(summary = "Temperature Analytics")
    @GetMapping("/temperature/{panelId}")
    public ApiResponse<?> getTemperature(
            @PathVariable Long panelId) {

        return new ApiResponse<>(
                true,
                "Temperature Analytics",
                service.getTemperatureAnalytics(panelId)
        );
    }

    @Operation(summary = "Performance Analytics")
    @GetMapping("/performance/{panelId}")
    public ApiResponse<?> getPerformance(
            @PathVariable Long panelId) {

        return new ApiResponse<>(
                true,
                "Performance Analytics",
                service.getPerformance(panelId)
        );
    }
}
