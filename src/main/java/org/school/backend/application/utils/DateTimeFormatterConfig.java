package org.school.backend.application.utils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterConfig {

    private static final String DATE_PATTERN = "dd/MM/yyyy";

    public static String convertIsoFormat(final String dateIso) {

            final LocalDate date = LocalDate.parse(dateIso);
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // contoh
            return date.format(formatter);

    }

    public static String toStringFormat(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static LocalDateTime parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(dateStr);
        } catch (Exception e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(dateStr, formatter);
        }
    }


}
