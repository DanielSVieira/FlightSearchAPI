package com.ryanair.api.searchflights.datatransferobject;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.joda.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FilterFlightsDTO {
	
	@NotNull
	@Size(min=3, max=3)
	private String departure;
	@NotNull
	@Size(min=3, max=3)
	private String arrival;
	@NotNull
	private LocalDateTime departureTime;
	@NotNull
	private LocalDateTime arrivalTime;

}
