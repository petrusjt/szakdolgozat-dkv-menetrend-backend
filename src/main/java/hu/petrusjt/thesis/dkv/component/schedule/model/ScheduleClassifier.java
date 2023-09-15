package hu.petrusjt.thesis.dkv.component.schedule.model;

import hu.petrusjt.thesis.dkv.rest.controller.util.DateHelper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public enum ScheduleClassifier {
    TANITASI_IDOSZAK,
    TANSZUNET_MUNKANAP,
    SZABADNAP,
    MUNKASZUNETI_NAP;

    private static final List<Month> TANITASI_IDOSZAK_MONTHS = List.of(
            Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.SEPTEMBER,
            Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER
    );

    public static ScheduleClassifier getFromDate(final LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.SUNDAY || DateHelper.isHoliday(date)) {
            return MUNKASZUNETI_NAP;
        } else if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return SZABADNAP;
        } else {
            if (TANITASI_IDOSZAK_MONTHS.contains(date.getMonth())) {
                return TANITASI_IDOSZAK;
            }
            return TANSZUNET_MUNKANAP;
        }
    }
}
