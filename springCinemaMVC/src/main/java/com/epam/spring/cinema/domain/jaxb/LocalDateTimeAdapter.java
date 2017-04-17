package com.epam.spring.cinema.domain.jaxb;

import com.epam.spring.cinema.CinemaConstants;
import com.epam.spring.cinema.util.CinemaDateUtils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

/**
 * Created by Andrey_Vaganov on 4/11/2017.
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        LocalDateTime localDateTime = CinemaDateUtils.getDateTimeByFormat(
                v, CinemaConstants.VIEW_DATE_TIME_FORMAT);
        return localDateTime;
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        String strDate = CinemaDateUtils.dateToString(v, CinemaConstants.VIEW_DATE_TIME_FORMAT);
        return strDate;
    }
}
