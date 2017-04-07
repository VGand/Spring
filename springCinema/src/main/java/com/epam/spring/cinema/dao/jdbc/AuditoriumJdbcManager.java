package com.epam.spring.cinema.dao.jdbc;

import com.epam.spring.cinema.dao.AuditoriumManager;
import com.epam.spring.cinema.domain.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by Andrey_Vaganov on 5/24/2016.
 */
@Resource
public class AuditoriumJdbcManager implements AuditoriumManager {

    private final String INSERT_QUERY = "INSERT INTO CINEMA_AUDITORIUM (ID, NAME, NUMBER_OF_SEATS, VIP_SEATS) VALUES (?, ?, ?, ?)";
    private final String GET_MAX_ID_QUERY = "SELECT MAX(ID) FROM CINEMA_AUDITORIUM";
    private final String DELETE_ALL_QUERY = "DELETE FROM CINEMA_AUDITORIUM";
    private final String GET_AUDITORIUM_BY_NAME_QUERY = "SELECT * FROM CINEMA_AUDITORIUM WHERE NAME = ?";
    private final String GET_ALL_AUDITORIUM = "SELECT * FROM CINEMA_AUDITORIUM";
    private final String GET_AUDITORIUM_BY_EVENT_ID = "SELECT LN.DATE AS DATE, AUD.ID AS ID, AUD.NAME AS NAME, AUD.NUMBER_OF_SEATS AS NUMBER_OF_SEATS, AUD.VIP_SEATS AS VIP_SEATS FROM CINEMA_EVENT_AUDITORIUM_LINK LN INNER JOIN CINEMA_AUDITORIUM AUD ON AUD.ID = LN.AUDITORIUM_ID WHERE LN.EVENT_ID = ?";
    private final String GET_AIR_DATES_BY_EVENT_ID = "SELECT * FROM CINEMA_AIR_DATES WHERE EVENT_ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuditoriumJdbcManager(List<Auditorium> auditoriums, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcTemplate.update(DELETE_ALL_QUERY);
        for (Auditorium auditorium : auditoriums) {
            add(auditorium);
        }
    }

    @Override
    public void add(Auditorium auditorium) {
        StringBuilder vipSeatsStr = new StringBuilder();
        Iterator<Long> iterator = auditorium.getVipSeats().iterator();
        while(iterator.hasNext()) {
            vipSeatsStr.append(iterator.next());
            if (iterator.hasNext()) {
                vipSeatsStr.append(",");
            }
        }

        Long id = jdbcTemplate.queryForObject(GET_MAX_ID_QUERY, new Object[]{}, Long.class);
        if (id == null ) {
            id = 0L;
        } else {
            id = id + 1;
        }

        jdbcTemplate.update(INSERT_QUERY, id, auditorium.getName(), auditorium.getNumberOfSeats(), vipSeatsStr);
    }

    @Override
    public Auditorium getByName(String name) {
        return jdbcTemplate.queryForObject(GET_AUDITORIUM_BY_NAME_QUERY,  new Object[]{name}, new AuditoriumRowMapper());
    }

    @Override
    public List<Auditorium> getAll() {
        return jdbcTemplate.query(GET_ALL_AUDITORIUM, new AuditoriumRowMapper());
    }

    @Override
    public NavigableSet<LocalDateTime> getAirDatesByEventId(Long eventId) {
        List<LocalDateTime> listDateTime = jdbcTemplate.query(GET_AIR_DATES_BY_EVENT_ID, new Object[]{eventId},
                new RowMapper<LocalDateTime>() {
            public LocalDateTime mapRow(ResultSet resultSet, int i) throws SQLException {
                java.sql.Date date = resultSet.getDate("DATE");
                Instant instant = Instant.ofEpochMilli(date.getTime());
                return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            }
        });
        return new TreeSet<>(listDateTime);
    }

    @Override
    public NavigableMap<LocalDateTime, Auditorium> getAuditoriumMapByEventId(Long eventId) {
        NavigableMap<LocalDateTime, Auditorium> auditoriumNavigableMap = new TreeMap<>();
        jdbcTemplate.query(GET_AUDITORIUM_BY_EVENT_ID, new Object[]{eventId}, new AuditoriumRowMapper() {
            public Auditorium mapRow(ResultSet resultSet, int i) throws SQLException {
                Auditorium auditorium = super.mapRow(resultSet, i);
                java.sql.Date date = resultSet.getDate("DATE");
                Instant instant = Instant.ofEpochMilli(date.getTime());
                LocalDateTime localDateTime = LocalDateTime.ofInstant(instant,  ZoneId.systemDefault());
                auditoriumNavigableMap.put(localDateTime, auditorium);
                return auditorium;
            }
        });
        return auditoriumNavigableMap;
    }
}
