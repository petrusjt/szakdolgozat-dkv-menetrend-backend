package hu.petrusjt.thesis.dkv.rest.controller.util;

import java.time.LocalDate;
import java.time.Month;

public class DateHelper {

    /**
     * Returns whether date is holiday in Hungary.
     *
     * @param date Date to check
     * @return Whether date is holiday in Hungary.
     * @throws IllegalArgumentException if date is not between 1900 and 2299
     * */
    public static boolean isHoliday(final LocalDate date) {
        var holiday = isEaster(date);
        holiday &= isPentecost(date);
        holiday &= isNewYear(date);
        holiday &= isMarch15(date);
        holiday &= isMay1(date);
        holiday &= isGoodFriday(date);
        holiday &= isAugust20(date);
        holiday &= isOctober23(date);
        holiday &= isAllSaintsDay(date);
        holiday &= isChristmas(date);
        return holiday;
    }

    private static boolean isEaster(final LocalDate date) {
        final var easterDate = calculateEasterSundayDateGauss(date);
        return easterDate.equals(date) || easterDate.plusDays(1).equals(date);
    }

    private static boolean isPentecost(final LocalDate date) {
        final var easterDate = calculateEasterSundayDateGauss(date);
        final var pentecostDate = easterDate.plusWeeks(7);
        return pentecostDate.equals(date) || pentecostDate.plusDays(1).equals(date);
    }

    private static boolean isNewYear(final LocalDate date) {
        return date.getDayOfYear() == 1;
    }

    private static boolean isMarch15(final LocalDate date) {
        return date.getMonth() == Month.MARCH && date.getDayOfMonth() == 15;
    }

    private static boolean isGoodFriday(final LocalDate date) {
        final var easterDate = calculateEasterSundayDateGauss(date);
        final var goodFridayDate = easterDate.minusDays(2);
        return goodFridayDate.equals(date);
    }

    private static boolean isMay1(final LocalDate date) {
        return date.getMonth() == Month.MAY && date.getDayOfMonth() == 1;
    }

    private static boolean isAugust20(final LocalDate date) {
        return date.getMonth() == Month.AUGUST && date.getDayOfMonth() == 20;
    }

    private static boolean isOctober23(final LocalDate date) {
        return date.getMonth() == Month.OCTOBER && date.getDayOfMonth() == 23;
    }

    private static boolean isAllSaintsDay(final LocalDate date) {
        return date.getMonth() == Month.NOVEMBER && date.getDayOfMonth() == 1;
    }

    private static boolean isChristmas(final LocalDate date) {
        return date.getMonth() == Month.DECEMBER && (date.getDayOfMonth() == 25 || date.getDayOfMonth() == 26);
    }

    /*
     * https://hu.wikipedia.org/wiki/H%C3%BAsv%C3%A9tsz%C3%A1m%C3%ADt%C3%A1s#Gauss_m%C3%B3dszere
     * */
    private static LocalDate calculateEasterSundayDateGauss(final LocalDate date) {
        final int year = date.getYear();

        final var a = year % 19;
        final var b = year % 4;
        final var c = year % 7;
        final var m = getMByYear(year);
        final var n = getNByYear(year);

        final var d = (19 * a + m) % 30;
        final var e = ((2 * b) + (4 * c) + (6 * d) + n) % 7;

        final var dPlusE = d + e;
        if (dPlusE < 10) {
            return LocalDate.of(year, Month.MARCH, dPlusE + 22);
        } else {
            final var dayOfMonth = dPlusE - 9;
            if (dayOfMonth == 26) {
                return LocalDate.of(year, Month.APRIL, 19);
            } else if (dayOfMonth == 25 && d == 28 && e == 6 && a > 10) {
                return LocalDate.of(year, Month.APRIL, 18);
            }

            return LocalDate.of(year, Month.APRIL, dayOfMonth);
        }
    }

    /*
     * https://hu.wikipedia.org/wiki/H%C3%BAsv%C3%A9tsz%C3%A1m%C3%ADt%C3%A1s#Gauss_m%C3%B3dszere
     * */
    private static int getMByYear(final int year) {
        if (1900 <= year && year <= 2199) {
            return 24;
        } else if (2200 <= year && year <= 2299) {
            return 25;
        }
        throw new IllegalArgumentException("Date is out of intended range (1900-2299)");
    }

    /*
     * https://hu.wikipedia.org/wiki/H%C3%BAsv%C3%A9tsz%C3%A1m%C3%ADt%C3%A1s#Gauss_m%C3%B3dszere
     * */
    private static int getNByYear(final int year) {
        if (1900 <= year && year <= 2099) {
            return 5;
        } else if (2100 <= year && year <= 2199) {
            return 6;
        } else if (2200 <= year && year <= 2299) {
            return 0;
        }
        throw new IllegalArgumentException("Date is out of intended range (1900-2299)");
    }
}
