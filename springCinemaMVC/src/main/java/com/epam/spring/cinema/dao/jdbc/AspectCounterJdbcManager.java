package com.epam.spring.cinema.dao.jdbc;

import com.epam.spring.cinema.dao.AspectCounterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andrey_Vaganov on 28.05.2016.
 */
@Component
public class AspectCounterJdbcManager implements AspectCounterManager {

    private final String GET_COUNTER_BY_NAME = "SELECT ASPECT_COUNTER FROM CINEMA_ASPECTS_COUNTERS WHERE ASPECT_NAME = ?";
    private final String INSERT_COUNTER = "INSERT INTO CINEMA_ASPECTS_COUNTERS (ASPECT_NAME, ASPECT_COUNTER) VALUES(?, ?)";
    private final String UPDATE_COUNTER = "UPDATE CINEMA_ASPECTS_COUNTERS SET ASPECT_COUNTER = ASPECT_COUNTER + 1 WHERE ASPECT_NAME = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void incItem(String itemName) {
        List<Object> counters = jdbcTemplate.query(GET_COUNTER_BY_NAME, new Object[]{itemName}, new RowMapper() {

            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString(1);
            }
        });

        if (counters != null && counters.size() > 0) {
            jdbcTemplate.update(UPDATE_COUNTER, itemName);
        } else {
            jdbcTemplate.update(INSERT_COUNTER, itemName, new Long(1));
        }
    }
}
