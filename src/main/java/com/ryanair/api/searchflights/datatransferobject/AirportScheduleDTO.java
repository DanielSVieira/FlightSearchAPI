package com.ryanair.api.searchflights.datatransferobject;

import org.joda.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AirportScheduleDTO {
	private final LocalDateTime departureDateTime;
	private final LocalDateTime arrivalDateTime;

}
