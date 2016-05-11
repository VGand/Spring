package com.epam.spring.cinema.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Andrey_Vaganov on 5/11/2016.
 */
public class CinemaDateUtils {

    public static LocalDateTime getDateTimeByFormat(String str, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(str, formatter);
    }
}
