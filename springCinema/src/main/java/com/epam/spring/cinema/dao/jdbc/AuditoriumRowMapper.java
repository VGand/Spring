package com.epam.spring.cinema.dao.jdbc;

import com.epam.spring.cinema.domain.Auditorium;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrey_Vaganov on 26.05.2016.
 */
public class AuditoriumRowMapper implements RowMapper<Auditorium> {
    @Override
    public Auditorium mapRow(ResultSet resultSet, int i) throws SQLException {
        Auditorium auditorium = new Auditorium();
        auditorium.setId(resultSet.getLong("ID"));
        auditorium.setName(resultSet.getString("NAME"));
        auditorium.setNumberOfSeats(resultSet.getLong("NUMBER_OF_SEATS"));
        if (resultSet.getString("VIP_SEATS") != null) {
            Set<Long> vipSeats = new HashSet<>();
            for(String str : resultSet.getString("VIP_SEATS").split(",")) {
                vipSeats.add(Long.valueOf(str));
            }
            auditorium.setVipSeats(vipSeats);
        }
        return auditorium;
    }
}
