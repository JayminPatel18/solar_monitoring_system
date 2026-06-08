package com.solar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sensor_data")
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Voltage is required")
    @Positive(message = "Voltage must be positive")
    private Double voltage;

    @NotNull(message = "Current is required")
    @Positive(message = "Current must be positive")
    private Double current;

    @NotNull(message = "Power is required")
    @Positive(message = "Power must be positive")
    private Double power;

    @NotNull(message = "Temperature is required")
    @Max(value = 100,message = "Temperature too high")
    private Double temperature;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "panel_id")
    @JsonBackReference
    private SolarPanel panel;

    @PrePersist
    public void prePersist() {
        this.timestamp = LocalDateTime.now();
    }
}
