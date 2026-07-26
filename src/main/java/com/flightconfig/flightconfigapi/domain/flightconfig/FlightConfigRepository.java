package com.flightconfig.flightconfigapi.domain.flightconfig;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightConfigRepository extends JpaRepository<FlightConfig, Long> {
    List<FlightConfig> findByStatus(ConfigStatus status);
    List<FlightConfig> findByAircraftType(AircraftType aircraftType);
}
