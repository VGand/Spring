package com.epam.spring.cinema.dao;

import com.epam.spring.cinema.domain.UserAccount;

/**
 * Created by Andrey_Vaganov on 4/19/2017.
 */
public interface UserAccountManager {

    void save(UserAccount userAccount);

    UserAccount get(String userLogin);
}
