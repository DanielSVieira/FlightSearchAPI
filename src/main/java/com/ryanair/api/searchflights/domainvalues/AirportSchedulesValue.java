package com.ryanair.api.searchflights.domainvalues;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.ryanair.api.searchflights.datatransferobject.AirportScheduleDTO;

import lombok.Getter;

@Getter
public class AirportSchedulesValue {
	
	public static final ConcurrentHashMap<String, List<AirportScheduleDTO>> routeSchedules = new ConcurrentHashMap<>();

}
