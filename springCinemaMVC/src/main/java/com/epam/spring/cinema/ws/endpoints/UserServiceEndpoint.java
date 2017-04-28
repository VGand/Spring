package com.epam.spring.cinema.ws.endpoints;

import com.epam.spring.cinema.CinemaConstants;
import com.epam.spring.cinema.service.UserService;
import com.epam.spring.cinema.util.UserJaxbObjectMaper;
import com.springcinema.cinema_web_service.GetUserRequest;
import com.springcinema.cinema_web_service.User;
import com.springcinema.cinema_web_service.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by Andrey_Vaganov on 4/28/2017.
 */
@Endpoint
public class UserServiceEndpoint {

    @Autowired
    private UserService userService;

    @PayloadRoot(namespace = CinemaConstants.NAMECPACE_URI, localPart = "saveUser")
    @ResponsePayload
    public UserResponse saveUser(@RequestPayload User user) {
        userService.save(UserJaxbObjectMaper.toUser(user));

        return new UserResponse();
    }

    @PayloadRoot(namespace = CinemaConstants.NAMECPACE_URI, localPart = "getUserByParams")
    @ResponsePayload
    public User getUserByParams(@RequestPayload GetUserRequest userRequest) {

        User jaxbUser = null;

        if (!StringUtils.isEmpty(userRequest.getEmail())) {
            com.epam.spring.cinema.domain.User user = userService.getUserByEmail(userRequest.getEmail());
            jaxbUser = UserJaxbObjectMaper.toJaxbUser(user);
        } else if (!StringUtils.isEmpty(userRequest.getLogin())) {
            com.epam.spring.cinema.domain.User user = userService.getBuLogin(userRequest.getLogin());
            jaxbUser = UserJaxbObjectMaper.toJaxbUser(user);
        }

        return jaxbUser;
    }


}
