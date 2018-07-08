package com.ryanair.api.searchflights.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryanair.api.searchflights.datatransferobject.FilterFlightsDTO;
import com.ryanair.api.searchflights.datatransferobject.FlightRouteDTO;
import com.ryanair.api.searchflights.mapper.FilterFlightsMapper;
import com.ryanair.api.searchflights.service.InterconnectionsService;

@RestController
@RequestMapping("/interconnections")
public class InterconnectionsController {

	@Autowired
	private InterconnectionsService routeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FlightRouteDTO>> index(@RequestParam String departure, @RequestParam String arrival,
			@RequestParam String departureDateTime,
			@RequestParam String arrivalDateTime)
			throws IOException {

		FilterFlightsDTO filterFlightsDTO = FilterFlightsMapper.makeFilterFlightsDTO(departure, arrival,
				departureDateTime, arrivalDateTime);
		List<FlightRouteDTO> flights = routeService.getFlights(filterFlightsDTO);

		return new ResponseEntity<>(flights, HttpStatus.OK);
	}
}
