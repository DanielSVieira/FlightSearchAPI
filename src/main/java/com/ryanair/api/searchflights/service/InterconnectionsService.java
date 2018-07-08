package com.ryanair.api.searchflights.service;

import java.util.List;


import com.ryanair.api.searchflights.datatransferobject.FilterFlightsDTO;
import com.ryanair.api.searchflights.datatransferobject.FlightRouteDTO;


public interface InterconnectionsService {
//    List<FlightRouteDTO> getFlights(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) throws IOException;
    List<FlightRouteDTO> getFlights(FilterFlightsDTO filterFlightsDTO);
    
}