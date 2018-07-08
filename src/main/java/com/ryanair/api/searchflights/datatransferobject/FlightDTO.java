package com.ryanair.api.searchflights.datatransferobject;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class FlightDTO {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private String number;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    public FlightDTO(@JsonProperty("number") String number, @JsonProperty("departureTime") String departureTime, @JsonProperty("arrivalTime") String arrivalTime) {
        this.number = number;

        this.departureTime = LocalTime.parse(departureTime, formatter);
        this.arrivalTime = LocalTime.parse(arrivalTime, formatter);
    }
}