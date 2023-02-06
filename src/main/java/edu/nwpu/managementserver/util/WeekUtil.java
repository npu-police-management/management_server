package edu.nwpu.managementserver.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 * @author Jiayi Zhu
 * 2023/2/6
 */
public class WeekUtil {

    /**
     * 每周 从周一开始，到周日结束
     * @param currentTime
     * @param testTime
     * @return
     */
    public static boolean isSameWeek(LocalDateTime currentTime, LocalDateTime testTime) {

        LocalDateTime monday = currentTime.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).plusDays(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime sunday = currentTime.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).minusDays(1).withHour(23).withMinute(59).withSecond(59);

        return (testTime.isAfter(monday)||testTime.isEqual(monday)) && (testTime.isBefore(sunday)||testTime.isEqual(sunday));
    }

    public static LocalDate getCurrentDayOfWeek(LocalDate currentTime, DayOfWeek day) {

        LocalDate monday = currentTime.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).plusDays(1);

        switch (day) {
            case MONDAY -> {
                return monday;
            }
            case TUESDAY -> {
                return monday.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
            }
            case WEDNESDAY -> {
                return monday.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
            }
            case THURSDAY -> {
                return monday.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
            }
            case FRIDAY -> {
                return monday.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            }
            case SATURDAY -> {
                return monday.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
            }
            case SUNDAY -> {
                return monday.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
            }
            default -> {
                return null;
            }
        }
    }
}
