package com.ryanair.api.searchflights.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ryanair.api.searchflights.exception.InvalidAirportIATACode;
import com.ryanair.api.searchflights.exception.InvalidDateInformedException;
import com.ryanair.api.searchflights.exception.RouteApiException;
import com.ryanair.api.searchflights.exception.SameDestinationException;
import com.ryanair.api.searchflights.exception.ScheduleApiException;
import com.ryanair.api.searchflights.response.error.SearchFlightErrorResponse;


@ControllerAdvice
public class SearchFlightsExceptionHandler {
	
    @ExceptionHandler(ScheduleApiException.class)
    public ResponseEntity<?> handleScheduleApiException(ScheduleApiException e) {
    	SearchFlightErrorResponse error = SearchFlightErrorResponse.builder()
        	.error(e.getMessage())
            .status(HttpStatus.BAD_REQUEST.value())
            .message("Error consuming Schedule API")
            .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(RouteApiException.class)
    public ResponseEntity<?> handleRouteApiException(RouteApiException e) {
    	SearchFlightErrorResponse error = SearchFlightErrorResponse.builder()
        	.error(e.getMessage())
            .status(HttpStatus.BAD_REQUEST.value())
            .message("Error consuming Route API")
            .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidDateInformedException.class)
    public ResponseEntity<?> handleInvalidDateInformedException(InvalidDateInformedException e) {
    	SearchFlightErrorResponse error = SearchFlightErrorResponse.builder()
        	.error(e.getMessage())
            .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
            .message("Invalid date provided")
            .build();

        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler(SameDestinationException.class)
    public ResponseEntity<?> handleSameDestinationException(SameDestinationException e) {
    	SearchFlightErrorResponse error = SearchFlightErrorResponse.builder()
        	.error(e.getMessage())
            .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
            .message("Invalid combination of arrival and departure")
            .build();

        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    
    @ExceptionHandler(InvalidAirportIATACode.class)
    public ResponseEntity<?> handleInvalidAirportIATACode(InvalidAirportIATACode e) {
    	SearchFlightErrorResponse error = SearchFlightErrorResponse.builder()
        	.error(e.getMessage())
            .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
            .message("Invalid IATA code provided")
            .build();

        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
