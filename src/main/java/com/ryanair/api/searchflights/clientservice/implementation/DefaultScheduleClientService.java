package com.ryanair.api.searchflights.clientservice.implementation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ryanair.api.searchflights.clientservice.ScheduleClientService;
import com.ryanair.api.searchflights.datatransferobject.MonthScheduleDTO;
import com.ryanair.api.searchflights.exception.ScheduleApiException;

@Service
public class DefaultScheduleClientService implements ScheduleClientService {
	
    private static final String SCHEDULES_URL =
            "https://api.ryanair.com/timetable/3/schedules/{departure}/{arrival}/years/{year}/months/{month}";
	

	@Override
	@Cacheable(cacheNames = "SCHEDULE-CACHE") 
	public MonthScheduleDTO requestMonthSchedule(String fromAirport, String toAirport, int year, int month) {
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("departure", fromAirport);
        uriParams.put("arrival", toAirport);
        uriParams.put("year", String.valueOf(year));
        uriParams.put("month", String.valueOf(month));
    	return callScheduleAPI(uriParams);
	}
	
	private static MonthScheduleDTO callScheduleAPI(Map<String, String> uriParams) {
		RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(SCHEDULES_URL);

        String uri = builder.buildAndExpand(uriParams).toUri().toString();

        try {
            ResponseEntity<MonthScheduleDTO> responseEntity = restTemplate.getForEntity(uri, MonthScheduleDTO.class);
            MonthScheduleDTO monthScheduleDTO = responseEntity.getBody();

            return monthScheduleDTO;
        }
        catch (HttpClientErrorException e) {
        	if(e.getStatusCode() != HttpStatus.NOT_FOUND) {
        		e.printStackTrace();
            	throw new ScheduleApiException(e.getMessage());        		
        	}
        	return null;

        }
	}

}
