package com.epam.spring.cinema.service.impl;

import com.epam.spring.cinema.dao.AuditoriumManager;
import com.epam.spring.cinema.domain.Auditorium;
import com.epam.spring.cinema.service.AuditoriumService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
@Component
public class AuditoriumServiceImpl implements AuditoriumService {

    private AuditoriumManager auditoriumManager;

    public Auditorium getByName(String name) {
        return auditoriumManager.getByName(name);
    }

    public List<Auditorium> getAll() {
        return auditoriumManager.getAll();
    }

    public void setAuditoriumManager(AuditoriumManager auditoriumManager) {
        this.auditoriumManager = auditoriumManager;
    }
}
