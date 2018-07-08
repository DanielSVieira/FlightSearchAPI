package com.ryanair.api.searchflights.datatransferobject;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor @Getter
@Builder
public class FlightLegDTO {
    private final String departureAirport;
    private final String arrivalAirport;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime departureDateTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime arrivalDateTime;
}
