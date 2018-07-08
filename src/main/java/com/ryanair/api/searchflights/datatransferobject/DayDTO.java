package com.ryanair.api.searchflights.datatransferobject;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class DayDTO {
    private final int day;
    private final List<FlightDTO> flights;

    public DayDTO(final @JsonProperty("day") int day, final @JsonProperty("flights") List<FlightDTO> flights) {
        this.day = day;
        this.flights = flights;
    }
}
