package com.epam.spring.cinema.ws.client;

import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.util.UserJaxbObjectMaper;
import com.springcinema.cinema_web_service.GetUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;

import static com.epam.spring.cinema.CinemaConstants.CINEMA_WS_URI;

/**
 * Created by Andrey_Vaganov on 4/28/2017.
 */
public class UserWSClient {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public User getUserByLogin(String login) {
        GetUserRequest request = new GetUserRequest();
        request.setLogin(login);
        com.springcinema.cinema_web_service.User user = (com.springcinema.cinema_web_service.User)
                webServiceTemplate.marshalSendAndReceive(CINEMA_WS_URI, request);

        return UserJaxbObjectMaper.toUser(user);
    }
}
