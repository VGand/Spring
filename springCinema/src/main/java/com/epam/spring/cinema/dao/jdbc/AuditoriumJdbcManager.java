package com.epam.spring.cinema.dao.jdbc;

import com.epam.spring.cinema.dao.AuditoriumManager;
import com.epam.spring.cinema.domain.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Andrey_Vaganov on 5/24/2016.
 */
@Resource
public class AuditoriumJdbcManager implements AuditoriumManager {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(Auditorium auditorium) {

    }

    @Override
    public Auditorium getByName(String name) {
        return null;
    }

    @Override
    public List<Auditorium> getAll() {
        return null;
    }
}
