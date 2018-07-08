package com.ryanair.api.searchflights.datatransferobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
@JsonIgnoreProperties({"connectingAirport", "operator", "group", "newRoute", "seasonalRoute"})
public class DirectionDTO {
    private final String airportFrom;
    private final String airportTo;

    @JsonCreator
    public DirectionDTO(@JsonProperty("airportFrom") String airportFrom, @JsonProperty("airportTo") String airportTo) {
        this.airportFrom = airportFrom;
        this.airportTo = airportTo;
    }
}