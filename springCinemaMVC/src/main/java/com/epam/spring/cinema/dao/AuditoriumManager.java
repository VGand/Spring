package com.epam.spring.cinema.dao;

import com.epam.spring.cinema.domain.Auditorium;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NavigableSet;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public interface AuditoriumManager {

    void add(Auditorium auditorium);

    Auditorium getByName(String name);

    List<Auditorium> getAll();

    NavigableSet<LocalDateTime> getAirDatesByEventId(Long eventId);

    Auditorium getAuditoriumByEventId(Long eventId);
}
