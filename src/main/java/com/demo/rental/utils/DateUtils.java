package com.demo.rental.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

@Slf4j
public class DateUtils {

    private DateUtils(){}

    /**
     * Find the Labor day for a specific year
     * @param year
     * @return
     */
    public static LocalDate getLaborDay(int year){
        LocalDate septemberFirst = LocalDate.of(year, Month.SEPTEMBER, 1);
        return septemberFirst.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
    }

    /**
     * Get the July 4th holiday based on the closest weekend
     * @param year
     * @return
     */
    public static LocalDate getIndependenceDay(int year) {
        LocalDate julyFourth = LocalDate.of(year, Month.JULY, 4);
        return switch (julyFourth.getDayOfWeek()) {
            case SATURDAY -> julyFourth.minusDays(1);
            case SUNDAY -> julyFourth.plusDays(1);
            default -> julyFourth;
        };
    }

    /**
     * Count weekend days between 2 dates
     * @param start
     * @param end
     * @return
     */
    public static int countWeekendDays(LocalDate start, LocalDate end) {
        int weekendCount = 0;

        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();

            if (dayOfWeek == DayOfWeek.SATURDAY && dayOfWeek == DayOfWeek.SUNDAY) {
                weekendCount++;
            }
        }

        return weekendCount;
    }

    /**
     * Count week days between 2 dates
     * @param start
     * @param end
     * @return
     */
    public static int countWeekDays(LocalDate start, LocalDate end) {
        int weekdayCount = 0;

        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();

            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                weekdayCount++;
            }
        }

        return weekdayCount;
    }

    public static int countHolidays(LocalDate start, LocalDate end) {
        int holidayCount = 0;

        int checkoutYear = start.getYear();
        LocalDate independenceDay = getIndependenceDay(checkoutYear);
        log.info("independence day: " + independenceDay);
        LocalDate laborDay = getLaborDay(checkoutYear);
        log.info("labor day: " + laborDay);

        if((independenceDay.isEqual(start) || independenceDay.isAfter(start)) && (independenceDay.isBefore(end) || independenceDay.isEqual(end))){
            holidayCount++;
        }

        if((laborDay.isEqual(start) ||laborDay.isAfter(start)) && (laborDay.isBefore(end) || laborDay.isEqual(end))){
            holidayCount++;
        }

        return holidayCount;
    }

}
