package com.solar.controller;

import com.solar.dto.ApiResponse;
import com.solar.dto.DashboardSummaryDTO;
import com.solar.services.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@Tag(
        name = "Dashboard APIs",
        description = "Dashboard summary and monitoring endpoints"
)
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(
            summary = "Get Dashboard Summary",
            description = "Returns overall statistics of users, panels and sensor readings"
    )
    @GetMapping("/summary")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TECHNICIAN')")
    public ResponseEntity<ApiResponse<DashboardSummaryDTO>> getSummary() {

        DashboardSummaryDTO summary = dashboardService.getSummary();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Dashboard Summary",
                        summary
                )
        );
    }

}
