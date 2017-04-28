package com.epam.spring.cinema.util;

import com.epam.spring.cinema.CinemaConstants;
import com.springcinema.cinema_web_service.User;

/**
 * Created by Andrey_Vaganov on 4/28/2017.
 */
public class UserJaxbObjectMaper {

    public static User toJaxbUser(com.epam.spring.cinema.domain.User user) {
        User jaxbUser = new User();
        jaxbUser.setLogin(user.getLogin());
        jaxbUser.setBirthday(CinemaDateUtils.dateToString(user.getBirthday(), CinemaConstants.DATE_FORMAT));
        jaxbUser.setEmail(user.getEmail());
        jaxbUser.setFirstName(user.getFirstName());
        jaxbUser.setLastName(user.getLastName());

        return jaxbUser;
    }

    public static com.epam.spring.cinema.domain.User toUser(User jaxbUser) {
        com.epam.spring.cinema.domain.User user = new com.epam.spring.cinema.domain.User();

        user.setBirthday(CinemaDateUtils.getDateByFormat(jaxbUser.getBirthday(),CinemaConstants.DATE_FORMAT));
        user.setLogin(jaxbUser.getLogin());
        user.setEmail(jaxbUser.getEmail());
        user.setFirstName(jaxbUser.getFirstName());
        user.setLastName(jaxbUser.getLastName());

        return user;
    }
}
