package com.ryanair.api.searchflights.service;

import java.util.List;
import java.util.Set;


import com.ryanair.api.searchflights.datatransferobject.FilterFlightsDTO;
import com.ryanair.api.searchflights.datatransferobject.FlightRouteDTO;

public interface FlightRouteService {
	
	public List<FlightRouteDTO> getInterconnectFlights(Set<String> directFlights, FilterFlightsDTO filterFlightDTO);
	
	public List<FlightRouteDTO> getDirectFlights(FilterFlightsDTO filterFlightDTO);

}
