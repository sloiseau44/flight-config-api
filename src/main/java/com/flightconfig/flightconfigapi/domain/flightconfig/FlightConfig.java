package com.flightconfig.flightconfigapi.domain.flightconfig;

import com.flightconfig.flightconfigapi.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flight_configs")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AircraftType aircraftType;

    @Column(nullable = false)
    private String version;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConfigStatus status;

    @Column(nullable = false)
    private String parameters;

    @Column(nullable = false)
    private String checksum;

    @Column
    private String signature;

    @ManyToOne
    @JoinColumn(name = "created_by_id", nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "validated_by_id")
    private User validatedBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime validatedAt;

    @Column
    private LocalDateTime loadedAt;
}
