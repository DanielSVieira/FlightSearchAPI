package com.ryanair.api.searchflights.mapper;

import org.joda.time.LocalDateTime;

import com.ryanair.api.searchflights.datatransferobject.AirportScheduleDTO;
import com.ryanair.api.searchflights.datatransferobject.FilterFlightsDTO;
import com.ryanair.api.searchflights.datatransferobject.FlightLegDTO;

public class FlightLegMapper {
	
	public static FlightLegDTO makeFlightLegDTO(String departureAirport, String arrivalAirport, LocalDateTime departureTime, LocalDateTime arrivalTime) {
		return FlightLegDTO.builder()
					.departureAirport(departureAirport)
					.arrivalAirport(arrivalAirport)
					.departureDateTime(departureTime)
					.arrivalDateTime(arrivalTime)
					.build();
	}
	
	public static FlightLegDTO makeFlightLegDTO(FilterFlightsDTO filterFlightsDTO, AirportScheduleDTO airportScheduleDTO) {
		return FlightLegDTO.builder()
					.departureAirport(filterFlightsDTO.getDeparture())
					.arrivalAirport(filterFlightsDTO.getArrival())
					.departureDateTime(airportScheduleDTO.getDepartureDateTime())
					.arrivalDateTime(airportScheduleDTO.getArrivalDateTime())
					.build();
	}

}
