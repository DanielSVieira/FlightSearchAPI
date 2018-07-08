package com.ryanair.api.searchflights.clientservice;

import java.util.List;

import com.ryanair.api.searchflights.datatransferobject.DirectionDTO;

public interface RouteClientSerivce {
	
	public List<DirectionDTO> requestDirections();

}
