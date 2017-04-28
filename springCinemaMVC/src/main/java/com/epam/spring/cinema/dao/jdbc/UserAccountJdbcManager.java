package com.epam.spring.cinema.dao.jdbc;

import com.epam.spring.cinema.dao.UserAccountManager;
import com.epam.spring.cinema.domain.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

/**
 * Created by Andrey_Vaganov on 4/19/2017.
 */
@Resource
public class UserAccountJdbcManager implements UserAccountManager {

    private final String SAVE_QUERY = "UPDATE CINEMA_USER_ACCOUNT SET USER_AVAILABLE_AMOUNT = ? WHERE USER_LOGIN = ?";
    private final String GET_QUERY = "SELECT * FROM CINEMA_USER_ACCOUNT WHERE USER_LOGIN = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(UserAccount userAccount) {
        jdbcTemplate.update(SAVE_QUERY, userAccount.getAvailableAmount(), userAccount.getUserLogin());
    }

    @Override
    public UserAccount get(String userLogin) {
        return jdbcTemplate.queryForObject(GET_QUERY, new Object[]{userLogin}, new UserAccountRowMapper());
    }
}
