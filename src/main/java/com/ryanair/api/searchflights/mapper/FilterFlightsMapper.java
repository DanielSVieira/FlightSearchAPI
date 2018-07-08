package com.ryanair.api.searchflights.mapper;


import org.joda.time.LocalDateTime;

import com.ryanair.api.searchflights.datatransferobject.FilterFlightsDTO;

public class FilterFlightsMapper {
	
	public static FilterFlightsDTO makeFilterFlightsDTO(String departure, String arrival, String departureTime, String arrivalTime) {
		return FilterFlightsDTO.builder()
					.departure(departure)
					.arrival(arrival)
					.departureTime(new LocalDateTime(departureTime))
					.arrivalTime(new LocalDateTime(arrivalTime))
					.build();
			
	}
	
	public static FilterFlightsDTO makeFilterFlightsDTO(String departure, String arrival, LocalDateTime departureTime, LocalDateTime arrivalTime) {
		return FilterFlightsDTO.builder()
					.departure(departure)
					.arrival(arrival)
					.departureTime(departureTime)
					.arrivalTime(arrivalTime)
					.build();
			
	}

}
