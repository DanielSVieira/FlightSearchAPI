package com.ryanair.api.searchflights.datatransferobject;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter @AllArgsConstructor
@EqualsAndHashCode
public class FlightRouteDTO {
	
    private final List<FlightLegDTO> legs;

    public int getStops() {
        return legs.size() - 1;
    }
}

