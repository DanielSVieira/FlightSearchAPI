package com.ryanair.api.searchflights.service.implementation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryanair.api.searchflights.clientservice.ScheduleClientService;
import com.ryanair.api.searchflights.datatransferobject.AirportScheduleDTO;
import com.ryanair.api.searchflights.datatransferobject.FilterFlightsDTO;
import com.ryanair.api.searchflights.datatransferobject.FlightLegDTO;
import com.ryanair.api.searchflights.datatransferobject.FlightRouteDTO;
import com.ryanair.api.searchflights.datatransferobject.MonthScheduleDTO;
import com.ryanair.api.searchflights.domainvalues.DirectionRouteValue;
import com.ryanair.api.searchflights.mapper.FlightLegMapper;
import com.ryanair.api.searchflights.service.AirportScheduleService;
import com.ryanair.api.searchflights.service.FlightRouteService;

@Service
public class DefaultFlightRouteService implements FlightRouteService {

	private final static int HOURS = 2;
	
	@Autowired
	private AirportScheduleService airportScheduleService;
	
	@Autowired
	private ScheduleClientService scheduleClientService;
	
	
	@Override
	public List<FlightRouteDTO> getInterconnectFlights(Set<String> directFlights, FilterFlightsDTO filterFlightDTO) {
		String departureAirport = filterFlightDTO.getDeparture();
		String arrivalAirport = filterFlightDTO.getArrival(); 
		LocalDateTime departureDateTime = filterFlightDTO.getDepartureTime();
		LocalDateTime arrivalDateTime = filterFlightDTO.getArrivalTime();
		Set<String> connectAirports = directFlights.stream().filter((String airport) -> {
			Set<String> routes = DirectionRouteValue.directRoutes.get(airport);

			return routes != null && routes.contains(arrivalAirport);
		}).collect(Collectors.toSet());

		if (connectAirports.isEmpty()) {
			return Collections.<FlightRouteDTO>emptyList();
		}

		List<FlightRouteDTO> result = new LinkedList<>();

		for (String connectAirport : connectAirports) {
			List<AirportScheduleDTO> schedules = getSchedules(departureAirport, connectAirport, departureDateTime,
					arrivalDateTime);

			for (AirportScheduleDTO schedule : schedules) {
				List<AirportScheduleDTO> destSchedules = getSchedules(connectAirport, departureAirport,
						schedule.getArrivalDateTime().plusHours(HOURS), arrivalDateTime);
				FlightLegDTO leg = new FlightLegDTO(departureAirport, connectAirport, schedule.getDepartureDateTime(),
						schedule.getArrivalDateTime());
				result.addAll(destSchedules.stream().map((AirportScheduleDTO connectAirportScheduleDTO) -> {
					List<FlightLegDTO> legs = new LinkedList<>();
					legs.add(leg);
					legs.add(
							new FlightLegDTO(connectAirport, arrivalAirport, connectAirportScheduleDTO.getDepartureDateTime(),
									connectAirportScheduleDTO.getArrivalDateTime()));
					return new FlightRouteDTO(legs);
				}).collect(Collectors.toList()));
			}
			
		}
		
		return result;
	}

	@Override
	public List<FlightRouteDTO> getDirectFlights(FilterFlightsDTO filterFlightDTO) {
		return getSchedules(filterFlightDTO).stream()
				.map((AirportScheduleDTO airportScheduleDTO) -> {
					List<FlightLegDTO> legs = new LinkedList<>();
					legs.add(FlightLegMapper.makeFlightLegDTO(filterFlightDTO, airportScheduleDTO));
					return new FlightRouteDTO(legs);
				}).collect(Collectors.toList());
	}

	private List<AirportScheduleDTO> getSchedules(String departureAirport, String arrivalAirport,
			LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {
		LocalDate date = departureDateTime.toLocalDate();
		LocalDate arrivalDate = arrivalDateTime.plusMonths(1).toLocalDate();

		List<AirportScheduleDTO> schedules = new LinkedList<>();
		while (date.isBefore(arrivalDate)) {
			MonthScheduleDTO monthScheduleDTO = scheduleClientService.requestMonthSchedule(departureAirport, arrivalAirport, date.getYear(), date.getMonthOfYear());
			schedules.addAll(filterSchedules(
					airportScheduleService.getSchedules(departureAirport, arrivalAirport, date.getYear(), date.getMonthOfYear(), monthScheduleDTO),
					departureDateTime, arrivalDateTime));

			date = date.plusMonths(1);
		}

		return schedules;
	}
	
	private List<AirportScheduleDTO> getSchedules(FilterFlightsDTO filterFlightDTO) {
		LocalDate departureDate = filterFlightDTO.getDepartureTime().toLocalDate();
		LocalDate arrivalDate = filterFlightDTO.getArrivalTime().plusMonths(1).toLocalDate();

		List<AirportScheduleDTO> schedules = new LinkedList<>();
		while (departureDate.isBefore(arrivalDate)) {
			MonthScheduleDTO monthSchedule = scheduleClientService.requestMonthSchedule(filterFlightDTO.getDeparture(), filterFlightDTO.getArrival(), departureDate.getYear(), departureDate.getMonthOfYear());
			schedules.addAll(filterSchedules(
					airportScheduleService.getSchedules(filterFlightDTO.getDeparture(), filterFlightDTO.getArrival(), departureDate.getYear(), departureDate.getMonthOfYear(), monthSchedule),
					filterFlightDTO.getDepartureTime(), filterFlightDTO.getArrivalTime()));

			departureDate = departureDate.plusMonths(1);
		}

		return schedules;
	}
	
	private List<AirportScheduleDTO> filterSchedules(
			List<AirportScheduleDTO> schedules, 
			LocalDateTime departureDateTime,
			LocalDateTime arrivalDateTime) {

		return schedules.stream()
						.filter((AirportScheduleDTO schedule) -> schedule.getDepartureDateTime().isAfter(departureDateTime)
								&& schedule.getArrivalDateTime().isBefore(arrivalDateTime))
						.collect(Collectors.toList());
	}

}
