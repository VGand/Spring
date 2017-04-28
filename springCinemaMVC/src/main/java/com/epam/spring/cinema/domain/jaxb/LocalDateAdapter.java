package com.epam.spring.cinema.domain.jaxb;

import com.epam.spring.cinema.CinemaConstants;
import com.epam.spring.cinema.util.CinemaDateUtils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * Created by Andrey_Vaganov on 4/27/2017.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        LocalDate localDate = CinemaDateUtils.getDateByFormat(
                v, CinemaConstants.DATE_FORMAT);
        return localDate;
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        String strDate = CinemaDateUtils.dateToString(v, CinemaConstants.DATE_FORMAT);
        return strDate;
    }
}
