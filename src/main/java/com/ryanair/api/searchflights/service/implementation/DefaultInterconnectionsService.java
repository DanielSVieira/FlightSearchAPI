package com.ryanair.api.searchflights.service.implementation;

import java.util.LinkedList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryanair.api.searchflights.datatransferobject.FilterFlightsDTO;
import com.ryanair.api.searchflights.datatransferobject.FlightRouteDTO;
import com.ryanair.api.searchflights.domainvalues.DirectionRouteValue;
import com.ryanair.api.searchflights.service.FlightRouteService;
import com.ryanair.api.searchflights.service.InterconnectionsService;
import com.ryanair.api.searchflights.validator.FilterFlightDTOValidator;


@Service
public class DefaultInterconnectionsService implements InterconnectionsService {

	@Autowired
	private FlightRouteService flightRouteService;

	@Override
	public List<FlightRouteDTO> getFlights(FilterFlightsDTO filterFlightsDTO) {
		FilterFlightDTOValidator.validateDTO(filterFlightsDTO);

		List<FlightRouteDTO> result = new LinkedList<>();

		result.addAll(flightRouteService.getDirectFlights(filterFlightsDTO));
		result.addAll(flightRouteService.getInterconnectFlights(DirectionRouteValue.directRoutes.get(filterFlightsDTO.getDeparture()), filterFlightsDTO));

		return result;
	}

}