package com.epam.spring.cinema.service;

import com.epam.spring.cinema.domain.Auditorium;

import java.util.List;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public interface AuditoriumService {

    Auditorium getByName(String name);

    List<Auditorium> getAll();
}
