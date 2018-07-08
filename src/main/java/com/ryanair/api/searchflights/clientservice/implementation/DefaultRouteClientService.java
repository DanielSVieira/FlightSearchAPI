package com.ryanair.api.searchflights.clientservice.implementation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ryanair.api.searchflights.clientservice.RouteClientSerivce;
import com.ryanair.api.searchflights.datatransferobject.DirectionDTO;
import com.ryanair.api.searchflights.domainvalues.DirectionRouteValue;
import com.ryanair.api.searchflights.exception.RouteApiException;

@Service
public class DefaultRouteClientService implements RouteClientSerivce {
	
    private final String ROUTES_URL = "https://api.ryanair.com/core/3/routes/";

    @Cacheable
    @Override
    public List<DirectionDTO> requestDirections() {
    	try {
    		RestTemplate restTemplate = new RestTemplate();
    		
    		ResponseEntity<DirectionDTO[]> responseEntity = restTemplate.getForEntity(ROUTES_URL, DirectionDTO[].class);
    		DirectionDTO[] directions = responseEntity.getBody();
    		
    		return Arrays.asList(directions);
    	} catch (HttpClientErrorException e) {
    		throw new RouteApiException(e.getMessage());
    	}
    }
    
	private void initializeDirection() {
		List<DirectionDTO> directions = this.requestDirections();
		for (DirectionDTO direction : directions) {
			String airportFrom = direction.getAirportFrom();
			Set<String> values = DirectionRouteValue.directRoutes.get(airportFrom);
			if (values == null) {
				values = new HashSet<>();
				DirectionRouteValue.directRoutes.put(airportFrom, values);
			}

			values.add(direction.getAirportTo());
		}
	}
	
	@PostConstruct
	public void init() {
		initializeDirection();
	}
	
}
