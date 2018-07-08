package com.ryanair.api.searchflights.service;

import java.util.List;

import com.ryanair.api.searchflights.datatransferobject.AirportScheduleDTO;
import com.ryanair.api.searchflights.datatransferobject.MonthScheduleDTO;

public interface AirportScheduleService {
	
	public List<AirportScheduleDTO> getSchedules(String departureAirport, String arrivalAirport, int year, int month, MonthScheduleDTO monthSchedule);

}
