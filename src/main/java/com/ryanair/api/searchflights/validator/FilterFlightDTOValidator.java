package com.ryanair.api.searchflights.validator;

import java.util.Set;

import com.ryanair.api.searchflights.datatransferobject.FilterFlightsDTO;
import com.ryanair.api.searchflights.domainvalues.DirectionRouteValue;
import com.ryanair.api.searchflights.exception.InvalidAirportIATACode;
import com.ryanair.api.searchflights.exception.InvalidDateInformedException;
import com.ryanair.api.searchflights.exception.RouteApiException;
import com.ryanair.api.searchflights.exception.SameDestinationException;

public class FilterFlightDTOValidator {

	public static void validateDTO(FilterFlightsDTO filterFlightsDTO) {
		if (filterFlightsDTO.getArrivalTime().isBefore(filterFlightsDTO.getDepartureTime())) {
			throw new InvalidDateInformedException("Arrival date must be after departure date"); 
		}
		
		if(filterFlightsDTO.getArrival().equals(filterFlightsDTO.getDeparture())) {
			throw new SameDestinationException("Provide a different arrival from departure destination");
		}
		
		if(filterFlightsDTO.getArrival().length() != 3 || filterFlightsDTO.getDeparture().length() != 3) {
			throw new InvalidAirportIATACode("IATA Code must have 3 charachters");
		}
	

		Set<String> directRoutes = DirectionRouteValue.directRoutes.get(filterFlightsDTO.getDeparture());
		if (directRoutes == null || directRoutes.isEmpty()) {
			throw new RouteApiException("Error initializing api route service");
		}
	}
	
}
