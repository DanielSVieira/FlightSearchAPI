package com.ryanair.api.searchflights.datatransferobject;

import org.joda.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor @Getter
@Builder
public class FlightLegDTO {
    private final String departureAirport;
    private final String arrivalAirport;
    private final LocalDateTime departureDateTime;
    private final LocalDateTime arrivalDateTime;
}
