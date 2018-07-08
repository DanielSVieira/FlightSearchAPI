package com.ryanair.api.searchflights.service;

import java.util.List;

import com.ryanair.api.searchflights.datatransferobject.AirportScheduleDTO;

public interface AirportScheduleService {
	
	public List<AirportScheduleDTO> getSchedules(String departureAirport, String arrivalAirport, int year, int month);

}
