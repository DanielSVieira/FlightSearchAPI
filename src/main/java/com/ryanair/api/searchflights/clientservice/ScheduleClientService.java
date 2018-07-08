package com.ryanair.api.searchflights.clientservice;

import com.ryanair.api.searchflights.datatransferobject.MonthScheduleDTO;


public interface ScheduleClientService {
    MonthScheduleDTO requestMonthSchedule(String fromAirport, String toAirport, int year, int month);
}
