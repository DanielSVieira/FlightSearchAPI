package com.ryanair.api.searchflights.service.implementation;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryanair.api.searchflights.clientservice.ScheduleClientService;
import com.ryanair.api.searchflights.datatransferobject.AirportScheduleDTO;
import com.ryanair.api.searchflights.datatransferobject.DayDTO;
import com.ryanair.api.searchflights.datatransferobject.FlightDTO;
import com.ryanair.api.searchflights.datatransferobject.MonthScheduleDTO;
import com.ryanair.api.searchflights.domainvalues.AirportSchedulesValue;
import com.ryanair.api.searchflights.service.AirportScheduleService;

@Service
public class DefaultAirportScheduleService implements AirportScheduleService {

	@Autowired
	private ScheduleClientService scheduleClientService;

	@Override
	public List<AirportScheduleDTO> getSchedules(String departureAirport, String arrivalAirport, int year, int month) {
		String key = departureAirport + "/" + arrivalAirport + "/" + year + "/" + month;
		List<AirportScheduleDTO> schedules = AirportSchedulesValue.routeSchedules.get(key);
		if (schedules != null) {
			return schedules;
		}

		schedules = new LinkedList<>();

		MonthScheduleDTO monthSchedule = scheduleClientService.requestMonthSchedule(departureAirport, arrivalAirport,
				year, month);
		if (monthSchedule != null && monthSchedule.getDays() != null && !monthSchedule.getDays().isEmpty()) {

			for (DayDTO day : monthSchedule.getDays()) {
				int dayNumber = day.getDay();
				schedules.addAll(day.getFlights().stream().map((FlightDTO flight) -> {
					LocalDateTime departureDateTime = new LocalDateTime(year, month, dayNumber,
							flight.getDepartureTime().getHour(), flight.getDepartureTime().getMinute());
					LocalDateTime arrivalDateTime = new LocalDateTime(year, month, dayNumber,
							flight.getArrivalTime().getHour(), flight.getArrivalTime().getMinute());
					return new AirportScheduleDTO(departureDateTime, arrivalDateTime);
				}).collect(Collectors.toList()));
			}
		}

		AirportSchedulesValue.routeSchedules.put(key, schedules);

		return schedules;
	}

}
