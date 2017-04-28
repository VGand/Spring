package com.epam.spring.cinema.dao.jdbc;

import com.epam.spring.cinema.domain.UserAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrey_Vaganov on 4/19/2017.
 */
public class UserAccountRowMapper implements RowMapper<UserAccount> {
    @Override
    public UserAccount mapRow(ResultSet resultSet, int i) throws SQLException {
        String userLogin = resultSet.getString("USER_LOGIN");
        Double userAvailableAmount = resultSet.getDouble("USER_AVAILABLE_AMOUNT");

        return new UserAccount(userLogin, userAvailableAmount);
    }
}
