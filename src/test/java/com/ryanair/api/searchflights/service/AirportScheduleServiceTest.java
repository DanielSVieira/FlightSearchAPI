package com.ryanair.api.searchflights.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ryanair.api.searchflights.Application;
import com.ryanair.api.searchflights.clientservice.ScheduleClientService;
import com.ryanair.api.searchflights.datatransferobject.AirportScheduleDTO;
import com.ryanair.api.searchflights.datatransferobject.DayDTO;
import com.ryanair.api.searchflights.datatransferobject.FlightDTO;
import com.ryanair.api.searchflights.datatransferobject.MonthScheduleDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AirportScheduleServiceTest {

	@Autowired
	private AirportScheduleService airportScheduleService;
	
	@Autowired
	private ScheduleClientService scheduleClientService;
	
	private MonthScheduleDTO monthSchedule;
	
	@Before
	public void setup() {
		this.monthSchedule = scheduleClientService.requestMonthSchedule("DUB", "WRO", 2017, 8);
	}
	
	@Test
	public void emptyListTestPastDate() {
		List<AirportScheduleDTO> airportSchedule = airportScheduleService.getSchedules("DUB", "WRO", 2017, 8, monthSchedule);
		assertTrue(airportSchedule.isEmpty()); 
	}
	
	@Test
	public void testMockingMonthSchedule() {
		List<FlightDTO> flights = new ArrayList<>();
		List<DayDTO> days = new ArrayList<>();
		FlightDTO flightDTO = new FlightDTO("111", "10:00", "22:00");
		flights.add(flightDTO);
		
		DayDTO dayDTO = new DayDTO(1, flights);
		
		days.add(dayDTO);
		
		MonthScheduleDTO monthScheduleDTO = new MonthScheduleDTO(8, days);
		List<AirportScheduleDTO> airportSchedule = airportScheduleService.getSchedules("DUB", "WRO", 2018, 8, monthScheduleDTO);
		assertTrue(airportSchedule.size() == 1); 
	}
}
