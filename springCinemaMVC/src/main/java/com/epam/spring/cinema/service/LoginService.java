package com.epam.spring.cinema.service;

import com.epam.spring.cinema.domain.LoginBean;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public interface LoginService {

    boolean login(LoginBean loginBean);
}
