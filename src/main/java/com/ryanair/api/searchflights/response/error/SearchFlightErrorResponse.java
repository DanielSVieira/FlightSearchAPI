package com.ryanair.api.searchflights.response.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class SearchFlightErrorResponse {

	private Integer status;
    private String error;
    private String message;

}
