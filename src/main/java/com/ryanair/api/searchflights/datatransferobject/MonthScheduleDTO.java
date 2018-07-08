package com.ryanair.api.searchflights.datatransferobject;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
@JsonIgnoreProperties({"code", "message"})
public class MonthScheduleDTO {
    private int month;
    private List<DayDTO> days;

    public MonthScheduleDTO(@JsonProperty("month") int month, @JsonProperty("days") List<DayDTO> days) {
        this.month = month;
        this.days = days;
    }
}
